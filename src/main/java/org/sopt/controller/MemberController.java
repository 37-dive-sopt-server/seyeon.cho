package org.sopt.controller;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /*
     * 회원 등록 API
     */
    @PostMapping
    public Long createMember(
            @RequestBody MemberCreateRequest request
    ) {
        return memberService.join(request);
    }


    /*
     * 회원 삭제 API
     */
    @DeleteMapping("/{id}")
    public void deleteMemberById(@PathVariable Long id) {
        memberService.deleteMember(id);
    }


    /*
     * 회원 단건 조회 API -> 옵셔널 제외하고 dto로 보내기
     */
    @GetMapping("/{id}")
    public Optional<Member> findMemberById(
            @PathVariable Long id
    ) {
        return memberService.findOne(id);
    }

    /*
     * 회원 전체 조회 API
     */
    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.findAllMembers();
    }
}
