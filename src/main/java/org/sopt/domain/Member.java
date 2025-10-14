package org.sopt.domain;

public class Member {

    private Long id;
    private String name;
    private String email;
    private String birthdate;
    private Gender gender;

    public Member(Long id, String name, String email, String birthdate, Gender gender) {
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

    public String getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

}