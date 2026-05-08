package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

import com.abdo.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.abdo.gestiondestock.dto.UtilisateurDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "utilisateurs", description = "API de gestion des utilisateurs")
public interface UtilisateurApi {

    @PostMapping(value = UTILISATEUR_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un utilisateur",
            description = "Cette methode permet d'enregistrer ou modifier un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet utilisateur cree / modifie",
                    content = @Content(schema = @Schema(implementation = UtilisateurDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet utilisateur n'est pas valide",
                    content = @Content)
    })
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @PostMapping(value = UTILISATEUR_ENDPOINT + "/update/password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Changer le mot de passe d'un utilisateur",
            description = "Cette methode permet de changer le mot de passe d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le mot de passe a ete modifie",
                    content = @Content(schema = @Schema(implementation = UtilisateurDto.class))),
            @ApiResponse(responseCode = "400", description = "La requete n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe avec l'ID fourni",
                    content = @Content)
    })
    UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto);

    @GetMapping(value = UTILISATEUR_ENDPOINT + "/{idUtilisateur}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par ID",
            description = "Cette methode permet de chercher un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = UtilisateurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(value = UTILISATEUR_ENDPOINT + "/find/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un utilisateur par email",
            description = "Cette methode permet de chercher un utilisateur par son adresse email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = UtilisateurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun utilisateur n'existe dans la BDD avec l'email fourni",
                    content = @Content)
    })
    UtilisateurDto findByEmail(@PathVariable("email") String email);

    @GetMapping(value = UTILISATEUR_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des utilisateurs",
            description = "Cette methode permet de chercher et renvoyer la liste des utilisateurs qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des utilisateurs / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = UtilisateurDto.class))))
    })
    List<UtilisateurDto> findAll();

    @DeleteMapping(value = UTILISATEUR_ENDPOINT + "/delete/{idUtilisateur}")
    @Operation(summary = "Supprimer un utilisateur",
            description = "Cette methode permet de supprimer un utilisateur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'utilisateur a ete supprime",
                    content = @Content)
    })
    void delete(@PathVariable("idUtilisateur") Integer id);

}