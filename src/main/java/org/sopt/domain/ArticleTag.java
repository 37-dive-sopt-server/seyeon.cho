package org.sopt.domain;

public enum ArticleTag {
    CS, DB, SPRING, ETC;

    public static ArticleTag fromString(String value) {
        if (value == null) {
            throw new IllegalArgumentException("태그는 필수 입력값입니다.");
        }

        return switch (value.trim().toUpperCase()) {
            case "CS" -> CS;
            case "DB" -> DB;
            case "SPRING" -> SPRING;
            case "ETC" -> ETC;
            default -> throw new IllegalArgumentException("잘못된 태그 입력값입니다: " + value);
        };
    }
}
