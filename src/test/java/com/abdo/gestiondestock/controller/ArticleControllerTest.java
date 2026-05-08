package com.abdo.gestiondestock.controller;

import com.abdo.gestiondestock.dto.ArticleDto;
import com.abdo.gestiondestock.exception.EntityNotFoundException;
import com.abdo.gestiondestock.services.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

// ✅ Nouveau package Spring Boot 4
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
// ✅ Remplace @MockBean
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ✅ @MockBean → @MockitoBean
    @MockitoBean
    private ArticleService articleService;

    // ✅ ArticleDto utilise @Builder — utiliser .builder() au lieu de new ArticleDto()
    private ArticleDto buildArticleDto(Integer id, String code) {
        return ArticleDto.builder()
                .id(id)
                .codeArticle(code)
                .designation("Article Test")
                .idEntreprise(1)
                .build();
    }

    @Test
    @WithMockUser
    void shouldReturnAllArticles() throws Exception {
        ArticleDto dto = buildArticleDto(1, "ART-001");

        when(articleService.findAll()).thenReturn(List.of(dto));

        mockMvc.perform(get("/gestiondestock/v1/articles/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].codeArticle").value("ART-001"));
    }

    @Test
    @WithMockUser
    void shouldSaveArticle() throws Exception {
        ArticleDto dto = buildArticleDto(1, "ART-001");

        when(articleService.save(any(ArticleDto.class))).thenReturn(dto);

        mockMvc.perform(post("/gestiondestock/v1/articles/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeArticle").value("ART-001"));
    }

    @Test
    @WithMockUser
    void shouldFindArticleById() throws Exception {
        ArticleDto dto = buildArticleDto(1, "ART-001");

        when(articleService.findById(1)).thenReturn(dto);

        mockMvc.perform(get("/gestiondestock/v1/articles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeArticle").value("ART-001"));
    }

    @Test
    @WithMockUser
    void shouldReturn404WhenArticleNotFound() throws Exception {
        when(articleService.findById(99))
                .thenThrow(new EntityNotFoundException("Article non trouve"));

        mockMvc.perform(get("/gestiondestock/v1/articles/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldDeleteArticle() throws Exception {
        doNothing().when(articleService).delete(1);

        mockMvc.perform(delete("/gestiondestock/v1/articles/delete/1"))
                .andExpect(status().isOk());
    }
}