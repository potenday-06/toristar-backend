package com.workthis.toristar.infrastructure.outer.api.oauth.exception;

import com.workthis.toristar.common.annotation.ExplainError;
import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.workthis.toristar.common.consts.ProjectStatic.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum KakaoAuthErrorCode implements BaseErrorCode {
    KOE101(BAD_REQUEST, "KAKAO_KOE101", "invalid_client", "잘못된 앱 키 타입을 사용하거나 앱 키에 오타가 있을 경우"),
    KOE009(BAD_REQUEST, "KAKAO_KOE009", "misconfigured", "등록되지 않은 플랫폼에서 액세스 토큰을 요청 하는 경우"),
    KOE010(
            BAD_REQUEST,
            "KAKAO_KOE101",
            "invalid_client",
            "클라이언트 시크릿(Client secret) 기능을 사용하는 앱에서 토큰 요청 시 client_secret 값을 전달하지 않거나 정확하지 않은 값을 전달하는 경우"),
    KOE303(
            BAD_REQUEST,
            "KAKAO_KOE303",
            "invalid_grant",
            "인가 코드 요청 시 사용한 redirect_uri와 액세스 토큰 요청 시 사용한 redirect_uri가 다른 경우"),
    KOE319(BAD_REQUEST, "KAKAO_KOE319", "invalid_grant", "토큰 갱신 요청 시 리프레시 토큰을 전달하지 않는 경우"),
    KOE320(
            BAD_REQUEST,
            "KAKAO_KOE320",
            "invalid_grant",
            "동일한 인가 코드를 두 번 이상 사용하거나, 이미 만료된 인가 코드를 사용한 경우, 혹은 인가 코드를 찾을 수 없는 경우"),
    KOE322(
            BAD_REQUEST,
            "KAKAO_KOE322",
            "invalid_grant",
            "refresh_token을 찾을 수 없거나 이미 만료된 리프레시 토큰을 사용한 경우"),
    KOE_INVALID_REQUEST(BAD_REQUEST, "KAKAO_KOE_INVALID_REQUEST", "invalid_request", "잘못된 요청인 경우"),
    KOE400(BAD_REQUEST, "KAKAO_KOE400", "invalid_token", "ID 토큰 값이 전달되지 않았거나 올바른 형식이 아닌 ID 토큰인 경우"),
    KOE401(BAD_REQUEST, "KAKAO_KOE401", "invalid_token", "ID 토큰을 발급한 인증 기관(iss)이 카카오 인증 서버"),
    KOE402(BAD_REQUEST, "KAKAO_KOE402", "invalid_token", "서명이 올바르지 않아 유효한 ID 토큰이 아닌 경우"),
    KOE403(BAD_REQUEST, "KAKAO_KOE403", "invalid_token", "만료된 ID 토큰인 경우");

    private Integer status;
    private String errorCode;
    private String error;
    private String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder().status(status).code(errorCode).message(message).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }
}
