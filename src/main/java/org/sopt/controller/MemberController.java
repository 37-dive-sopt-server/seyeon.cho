package org.sopt.controller;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
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
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthdate,
            @RequestParam Gender gender) {
        return memberService.join(name, email, birthdate, gender);
    }



    /*
     * 회원 삭제 API
     */
    @DeleteMapping("/{id}")
    public void deleteMemberById(Long id) {
        memberService.deleteMember(id);
    }


    /*
     * 회원 단건 조회 API
     */
    @GetMapping("/{id}")
    public Optional<Member> findMemberById(Long id) {
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