package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.AUTHENTICATION_ENDPOINT;

import com.abdo.gestiondestock.dto.auth.AuthenticationRequest;
import com.abdo.gestiondestock.dto.auth.AuthenticationResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "authentication", description = "API d'authentification")
public interface AuthenticationApi {

    @PostMapping(
            value = AUTHENTICATION_ENDPOINT + "/authenticate",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Authentifier un utilisateur",
            description = "Cette methode permet d'authentifier un utilisateur et de retourner un token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentification reussie",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = "401", description = "Identifiants invalides",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acces refuse",
                    content = @Content)
    })
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}