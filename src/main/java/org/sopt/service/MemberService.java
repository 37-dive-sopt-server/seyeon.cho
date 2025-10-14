package org.sopt.service;

import java.util.List;
import java.util.Optional;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;

public interface MemberService {

    Long join(String name, String email, String birthdate, Gender gender);

    Optional<Member> findOne(Long memberId);

    void deleteMember(Long memberId);

    List<Member> findAllMembers();
}
