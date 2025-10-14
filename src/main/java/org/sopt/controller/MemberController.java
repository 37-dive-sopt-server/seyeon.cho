package org.sopt.controller;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.service.MemberServiceImpl;

import java.util.List;
import java.util.Optional;

public class MemberController {

    private final MemberServiceImpl memberServiceImpl = new MemberServiceImpl();

    public Long createMember(String name, String email, String birthdate, Gender gender) {

        return memberServiceImpl.join(name, email, birthdate, gender);
    }

    public Optional<Member> findMemberById(Long id) {
        return memberServiceImpl.findOne(id);
    }

    public void deleteMemberById(Long id) {
        memberServiceImpl.deleteMember(id);
    }

    public List<Member> getAllMembers() {
        return memberServiceImpl.findAllMembers();
    }
}