package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.dto.MemberCreateRequest;
import org.sopt.repository.MemberRepository;
import org.sopt.util.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
        validateDuplicateEmail(request.email());
        validateAge(request.birthDate());

        Member member = new Member( IdGenerator.nextId(), request.name(), request.email(), request.birthDate(), Gender.fromString(request.gender()));
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 이메일입니다.");
                });
    }

    private void validateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("생년월일은 필수입니다.");
        }

        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if (age < 20) {
            throw new IllegalStateException("만 20세 미만은 가입할 수 없습니다.");
        }
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
