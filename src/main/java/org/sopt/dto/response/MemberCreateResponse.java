package org.sopt.dto.response;

import org.sopt.domain.Member;

import java.time.LocalDate;

public record MemberCreateResponse(
        Long id,
        String name,
        String email,
        LocalDate birthDate,
        String gender
) {
    public static MemberCreateResponse from(Member member) {
        return new MemberCreateResponse(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getBirthdate(),
                member.getGender().name()
        );
    }
}
