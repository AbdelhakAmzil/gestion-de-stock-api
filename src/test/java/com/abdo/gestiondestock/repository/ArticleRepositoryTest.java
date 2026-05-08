package com.abdo.gestiondestock.repository;

import com.abdo.gestiondestock.model.Article;
import com.abdo.gestiondestock.model.Category;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(ArticleRepositoryTest.JpaAuditingConfig.class)
class ArticleRepositoryTest {

    @EnableJpaAuditing
    static class JpaAuditingConfig {}

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ArticleRepository articleRepository;

    private Article buildArticle(String code) {
        Article article = new Article();
        article.setCodeArticle(code);
        article.setDesignation("Article Test");
        article.setPrixUnitaireHt(BigDecimal.valueOf(100));
        article.setPrixUnitaireTtc(BigDecimal.valueOf(120));
        article.setTauxTva(BigDecimal.valueOf(20));
        article.setIdEntreprise(1);
        return article;
    }

    private Category buildCategory(String code) {
        Category category = new Category();
        category.setCode(code);
        category.setDesignation("Category Test");
        return category;
    }

    @Test
    void shouldFindArticleByCodeArticle() {
        entityManager.persistAndFlush(buildArticle("ART-001"));

        Optional<Article> found = articleRepository.findArticleByCodeArticle("ART-001");

        assertThat(found).isPresent();
        assertThat(found.get().getCodeArticle()).isEqualTo("ART-001");
    }

    @Test
    void shouldReturnEmptyWhenCodeNotFound() {
        Optional<Article> found = articleRepository.findArticleByCodeArticle("INVALID");

        assertThat(found).isEmpty();
    }

    @Test
    void shouldSaveAndRetrieveArticle() {
        Article saved = entityManager.persistAndFlush(buildArticle("ART-002"));

        assertThat(saved.getId()).isNotNull();
        assertThat(articleRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void shouldReturnAllArticles() {
        entityManager.persistAndFlush(buildArticle("ART-003"));
        entityManager.persistAndFlush(buildArticle("ART-004"));

        List<Article> articles = articleRepository.findAll();

        assertThat(articles).hasSize(2);
    }

    @Test
    void shouldDeleteArticle() {
        Article saved = entityManager.persistAndFlush(buildArticle("ART-005"));
        Integer id = saved.getId();

        articleRepository.deleteById(id);
        entityManager.flush();

        assertThat(articleRepository.findById(id)).isEmpty();
    }

    @Test
    void shouldFindArticlesByCategoryId() {
        Category category = entityManager.persistAndFlush(buildCategory("CAT-001"));

        Article article = buildArticle("ART-006");
        article.setCategory(category);
        entityManager.persistAndFlush(article);

        entityManager.persistAndFlush(buildArticle("ART-007"));

        List<Article> found = articleRepository.findAllByCategoryId(category.getId());

        assertThat(found).hasSize(1);
        assertThat(found.get(0).getCodeArticle()).isEqualTo("ART-006");
    }
}