package com.workthis.toristar.common.utils;

import com.workthis.toristar.common.properties.OauthProperties;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class NaverEncryptUtils {

    static final String ALGORITHM_HS256 = "HmacSHA256";
    final String baseStringFmt = "clientId=%s&encryptUniqueId=%s&timestamp=%s";

    static final String ALGORITHM_AES_CBC_PKCS5 = "AES/CBC/PKCS5Padding";
    static final String ALGORITHM_AES = "AES";
    static int BLOCK_SIZE = 16;
    private final OauthProperties oauthProperties;

    public String decryptNaverProviderId(String encryptedProviderId) throws Exception {
        byte[] key = generateKey(oauthProperties.getNaverClientSecret());
        return doDecryptWithPkcs5(encryptedProviderId, key);
    }

    public boolean validateNaverHmac(String clientId, String encryptUniqueId, String timestamp, String signature) throws Exception {
        String signatureBaseString = String.format(baseStringFmt, clientId, encryptUniqueId, timestamp);
        byte[] key = generateKey(oauthProperties.getNaverClientSecret());
        return signature.equals(generateMac(signatureBaseString, key));
    }

    private byte[] generateKey(final String clientSecret)
            throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(clientSecret.getBytes());
        return Arrays.copyOfRange(md.digest(), 0, BLOCK_SIZE);
    }

    //복호화 처리
    private String doDecryptWithPkcs5(final String encrypted,
                                            final byte[] aesKey) throws Exception {

        final byte[] encryptedWithIV = Base64.decodeBase64(encrypted);
        final byte[] iv = Arrays.copyOfRange(encryptedWithIV, 0, BLOCK_SIZE);
        final byte[] encryptedUniqueId =
                Arrays.copyOfRange(encryptedWithIV, BLOCK_SIZE, encryptedWithIV.length);

        final SecretKeySpec skeySpec = new SecretKeySpec(aesKey, ALGORITHM_AES);
        final Cipher cipher = Cipher.getInstance(ALGORITHM_AES_CBC_PKCS5);
        final IvParameterSpec ivspec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivspec);

        final byte[] decrypted = cipher.doFinal(encryptedUniqueId);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    private static String generateMac(final String signatureBaseString, final byte[] key) throws Exception{
        final SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM_HS256);
        final Mac mac = Mac.getInstance(ALGORITHM_HS256);
        mac.init(secretKey);
        final byte[] hash = mac.doFinal(signatureBaseString.getBytes());
        return Base64.encodeBase64URLSafeString(hash);
    }
}
