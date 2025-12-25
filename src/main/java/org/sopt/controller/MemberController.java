package org.sopt.controller;

import org.sopt.common.SuccessResponse;
import org.sopt.controller.common.SuccessStatus;
import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.dto.response.MemberCreateResponse;
import org.sopt.dto.response.MemberListResponse;
import org.sopt.dto.response.MemberResponse;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<MemberCreateResponse>> createMember(
            @RequestBody MemberCreateRequest request
    ) {
        MemberCreateResponse response = memberService.join(request);
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_CREATE_MEMBER.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_CREATE_MEMBER, response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Void>> deleteMember(
            @PathVariable Long id
    ) {
        memberService.deleteMember(id);
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_DELETE_MEMBER.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_DELETE_MEMBER, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<MemberResponse>> getMember(
            @PathVariable Long id
    ) {
        MemberResponse response = memberService.findOne(id);
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_FIND_MEMBER.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_FIND_MEMBER, response));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<MemberListResponse>> getAllMembers() {
        MemberListResponse response = memberService.findAllMembers();
        return ResponseEntity
                .status(SuccessStatus.SUCCESS_FIND_MEMBER.getHttpStatus())
                .body(SuccessResponse.of(SuccessStatus.SUCCESS_FIND_MEMBER, response));
    }
}
