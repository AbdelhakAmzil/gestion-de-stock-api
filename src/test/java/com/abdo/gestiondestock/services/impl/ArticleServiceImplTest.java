package com.abdo.gestiondestock.services.impl;

import com.abdo.gestiondestock.dto.ArticleDto;
import com.abdo.gestiondestock.exception.EntityNotFoundException;
import com.abdo.gestiondestock.model.Article;
import com.abdo.gestiondestock.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private Article article;
    private ArticleDto articleDto;

    @BeforeEach
    void setUp() {
        article = new Article();
        article.setId(1);
        article.setCodeArticle("ART-001");
        article.setDesignation("Article Test");

        articleDto = ArticleDto.fromEntity(article);
    }

    @Test
    void shouldSaveArticleSuccessfully() {
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        ArticleDto saved = articleService.save(articleDto);

        assertThat(saved).isNotNull();
        assertThat(saved.getCodeArticle()).isEqualTo("ART-001");
        verify(articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    void shouldFindArticleByIdSuccessfully() {
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));

        ArticleDto found = articleService.findById(1);

        assertThat(found).isNotNull();
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    void shouldThrowExceptionWhenArticleNotFound() {
        when(articleRepository.findById(99)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> articleService.findById(99))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void shouldReturnAllArticles() {
        when(articleRepository.findAll()).thenReturn(List.of(article));

        List<ArticleDto> articles = articleService.findAll();

        assertThat(articles).hasSize(1);
    }

    @Test
    void shouldDeleteArticle() {
        when(articleRepository.findById(1)).thenReturn(Optional.of(article));
        doNothing().when(articleRepository).deleteById(1);

        assertThatCode(() -> articleService.delete(1))
                .doesNotThrowAnyException();

        verify(articleRepository, times(1)).deleteById(1);
    }
}