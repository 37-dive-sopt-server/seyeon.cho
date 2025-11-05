package org.sopt.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 회원 (N:1 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    // 게시글 제목
    @Column(nullable = false)
    private String title;

    // 게시글 내용
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 태그 (CS, DB, SPRING, ETC 중 하나)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleTag tag;

    // 작성일
    @Column(nullable = false)
    private LocalDateTime createdAt;


    protected Article() {}

    public Article(Member member, String title, String content, ArticleTag tag) {
        if (member == null) {
            throw new IllegalArgumentException("작성자는 필수입니다.");
        }
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("제목은 필수 입력값입니다.");
        }
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("내용은 필수 입력값입니다.");
        }
        if (tag == null) {
            throw new IllegalArgumentException("태그는 필수 입력값입니다.");
        }

        this.member = member;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArticleTag getTag() {
        return tag;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
