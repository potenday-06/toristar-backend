package com.workthis.toristar.member.application.adaptor;

import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.member.repository.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class MemberAdaptor {

    private final JpaMemberRepository memberRepository;

}
