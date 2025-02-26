package com.workthis.toristar.member.ui;

import com.workthis.toristar.member.application.dto.request.CreateMemberRequest;
import com.workthis.toristar.member.application.dto.response.ReadProfileResponse;
import com.workthis.toristar.member.application.service.CreateProfileUseCase;
import com.workthis.toristar.member.application.service.ReadProfileUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "회원 APIs")
@RestController
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
@RequestMapping("/v1/members")
public class MemberRestController {

    private final CreateProfileUseCase createProfileUseCase;
    private final ReadProfileUseCase  readProfileUseCase;

    // 회원 정보 등록 api
    @Operation(summary = "온보딩 페이지에서 입력받은 회원 정보 등록(이름, 나이, 성별")
    @PostMapping
    public void setProfile(@RequestBody CreateMemberRequest request) {
        createProfileUseCase.execute(request);
    }

    // 회원 정보 조회 api
    @Operation(summary = "온보딩을 진행한 회원들의 정보 조회")
    @GetMapping
    public ReadProfileResponse getProfile() {
        return readProfileUseCase.execute();
    }
}
