package org.sopt.common.validator;

import java.time.LocalDate;
import java.time.Period;

public class MemberValidator {

    public static void validateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 20) {
            throw new IllegalStateException("만 20세 미만은 가입할 수 없습니다.");
        }
    }

    public static void validateInput(String name, String email, LocalDate birthDate) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("올바른 이메일이 아닙니다.");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("생년월일은 필수입니다.");
        }
    }
}
