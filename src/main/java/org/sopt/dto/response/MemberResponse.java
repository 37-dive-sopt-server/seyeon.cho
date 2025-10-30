package org.sopt.dto.response;

import org.sopt.domain.Member;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String name,
        String email,
        LocalDate birthDate,
        String gender
) {
    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getBirthdate(),
                member.getGender().name()
        );
    }
}
