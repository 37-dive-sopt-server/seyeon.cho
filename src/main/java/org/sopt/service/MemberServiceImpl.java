package org.sopt.service;

import org.sopt.domain.Gender;
import org.sopt.domain.Member;
import org.sopt.repository.MemoryMemberRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {

    private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private static long sequence = 1L;

    public Long join(String name, String email, String birthDate, Gender gender) {

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

    private void validateAge(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        LocalDate today = LocalDate.now();

        int age = Period.between(birth, today).getYears();

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