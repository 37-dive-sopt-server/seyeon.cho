package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberCreateRequest;
import org.sopt.repository.MemberRepository;
import org.sopt.util.IdGenerator;
import org.sopt.validator.MemberValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Long join(MemberCreateRequest request) {

        if (memberRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        MemberValidator.validateAge(request.birthDate());
        Gender gender = Gender.fromString(request.gender());

        Member member = new Member( IdGenerator.nextId(), request.name(), request.email(), request.birthDate(), gender);

        memberRepository.save(member);
        return member.getId();
    }

    @Override
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public void deleteMember(Long memberId) {
        if (memberRepository.findById(memberId).isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 회원 ID입니다: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }


    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
