package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.FOURNISSEUR_ENDPOINT;

import com.abdo.gestiondestock.dto.FournisseurDto;

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

@Tag(name = "fournisseurs", description = "API de gestion des fournisseurs")
public interface FournisseurApi {

    @PostMapping(value = FOURNISSEUR_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un fournisseur",
            description = "Cette methode permet d'enregistrer ou modifier un fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet fournisseur cree / modifie",
                    content = @Content(schema = @Schema(implementation = FournisseurDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet fournisseur n'est pas valide",
                    content = @Content)
    })
    FournisseurDto save(@RequestBody FournisseurDto dto);

    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/{idFournisseur}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un fournisseur par ID",
            description = "Cette methode permet de chercher un fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = FournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun fournisseur n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @GetMapping(value = FOURNISSEUR_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des fournisseurs",
            description = "Cette methode permet de chercher et renvoyer la liste des fournisseurs qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des fournisseurs / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = FournisseurDto.class))))
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}")
    @Operation(summary = "Supprimer un fournisseur",
            description = "Cette methode permet de supprimer un fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a ete supprime",
                    content = @Content)
    })
    void delete(@PathVariable("idFournisseur") Integer id);

}