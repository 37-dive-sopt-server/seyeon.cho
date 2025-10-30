package org.sopt.common;

import java.time.LocalDate;
import java.time.Period;

public class MemberValidator {

    public static void validateAge(LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        if (age < 20) {
            throw new IllegalStateException("만 20세 미만은 가입할 수 없습니다.");
        }
    }
}
