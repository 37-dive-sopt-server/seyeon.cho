package org.sopt.domain;

public enum Gender {
    FEMALE, MALE;

    public static Gender fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("성별은 필수 입력값입니다.");
        }

        switch (value.trim().toUpperCase()) {
            case "MALE", "M", "남", "남자" -> {
                return MALE;
            }
            case "FEMALE", "F", "여", "여자" -> {
                return FEMALE;
            }
            default -> throw new IllegalArgumentException("잘못된 성별 입력값입니다: " + value);
        }
    }
}
