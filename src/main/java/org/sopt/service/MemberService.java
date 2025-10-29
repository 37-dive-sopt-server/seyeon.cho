package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberCreateRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(MemberCreateRequest request);

    Optional<Member> findOne(Long memberId);

    void deleteMember(Long memberId);

    List<Member> findAllMembers();
}
