package org.sopt.service;

import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.dto.response.MemberCreateResponse;
import org.sopt.dto.response.MemberResponse;
import org.sopt.dto.response.MemberListResponse;

public interface MemberService {
    MemberCreateResponse join(MemberCreateRequest request);

    MemberResponse findOne(Long memberId);

    void deleteMember(Long memberId);

    MemberListResponse findAllMembers();
}
