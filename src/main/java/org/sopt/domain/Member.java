package org.sopt.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private LocalDate birthdate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "member")
    private List<Article> articles = new ArrayList<>();


    public  Member() {

    }

    public Member(Long id, String name, String email, LocalDate birthdate, Gender gender) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("올바른 이메일이 아닙니다.");
        }
        if (birthdate == null) {
            throw new IllegalArgumentException("생년월일은 필수입니다.");
        }

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

    public boolean hasEmail(String email) {
        return this.email.equals(email);
    }
}
