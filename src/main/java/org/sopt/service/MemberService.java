package org.sopt.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemberRepository;
import org.sopt.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private static long sequence = 1L;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(String name, String email, LocalDate birthDate, Gender gender) {

        validateDuplicateEmail(email);
        validateAge(birthDate);

        Member member = new Member(sequence++, name, email, birthDate, gender);
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


    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

}
