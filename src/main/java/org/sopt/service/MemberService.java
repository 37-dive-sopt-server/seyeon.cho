package org.sopt.service;

import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequest;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Long join(MemberCreateRequest request);

    Optional<Member> findOne(Long memberId);

    void deleteMember(Long memberId);

    List<Member> findAllMembers();
}
