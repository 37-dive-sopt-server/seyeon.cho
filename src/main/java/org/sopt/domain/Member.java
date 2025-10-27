package org.sopt.domain;

import java.time.LocalDate;

public class Member {

    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private Gender gender;

    public Member(Long id, String name, String email, LocalDate birthdate, Gender gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }
}