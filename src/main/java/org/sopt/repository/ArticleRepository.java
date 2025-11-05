package org.sopt.repository;

import org.sopt.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByMemberId(Long memberId);

}
