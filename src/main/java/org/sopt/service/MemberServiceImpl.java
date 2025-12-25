package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.request.MemberCreateRequest;
import org.sopt.dto.response.MemberCreateResponse;
import org.sopt.dto.response.MemberListResponse;
import org.sopt.dto.response.MemberResponse;
import org.sopt.repository.MemberRepository;
import org.sopt.common.validator.MemberValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public MemberCreateResponse join(MemberCreateRequest request) {

        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        MemberValidator.validateInput(request.name(), request.email(), request.birthDate());
        MemberValidator.validateAge(request.birthDate());
        Gender gender = Gender.fromString(request.gender());

        Member member = new Member(request.name(), request.email(), request.birthDate(), gender);

        memberRepository.save(member);
        return MemberCreateResponse.from(member);
    }

    @Override
    public MemberResponse findOne(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + memberId));

        return MemberResponse.from(member);
    }


    @Override
    public void deleteMember(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }


    @Override
    public MemberListResponse findAllMembers() {
        List<MemberResponse> responses = memberRepository.findAll().stream()
                .map(MemberResponse::from)
                .collect(Collectors.toList());

        return MemberListResponse.from(responses);
    }
}
