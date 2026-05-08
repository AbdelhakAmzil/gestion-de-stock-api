package com.abdo.gestiondestock.utils;

import com.abdo.gestiondestock.model.auth.ExtendedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;

import static org.assertj.core.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private ExtendedUser userDetails;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        userDetails = new ExtendedUser(
                "test@test.com",
                "password",
                1,
                Collections.emptyList()
        );
    }

    @Test
    void shouldGenerateToken() {
        String token = jwtUtil.generateToken(userDetails);
        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void shouldExtractUsername() {
        String token = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.extractUsername(token);
        assertThat(username).isEqualTo("test@test.com");
    }

    @Test
    void shouldExtractIdEntreprise() {
        String token = jwtUtil.generateToken(userDetails);
        String idEntreprise = jwtUtil.extractIdEntreprise(token);
        assertThat(idEntreprise).isEqualTo("1");
    }

    @Test
    void shouldValidateToken() {
        String token = jwtUtil.generateToken(userDetails);
        assertThat(jwtUtil.validateToken(token, userDetails)).isTrue();
    }

    @Test
    void shouldNotValidateTokenWithWrongUser() {
        String token = jwtUtil.generateToken(userDetails);
        UserDetails otherUser = new ExtendedUser(
                "other@test.com", "password", 2, Collections.emptyList()
        );
        assertThat(jwtUtil.validateToken(token, otherUser)).isFalse();
    }
}