package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.APP_ROOT;

import com.abdo.gestiondestock.dto.ClientDto;

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

@Tag(name = "clients", description = "API de gestion des clients")
public interface ClientApi {

    @PostMapping(value = APP_ROOT + "/clients/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un client",
            description = "Cette methode permet d'enregistrer ou modifier un client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet client cree / modifie",
                    content = @Content(schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet client n'est pas valide",
                    content = @Content)
    })
    ClientDto save(@RequestBody ClientDto dto);

    @GetMapping(value = APP_ROOT + "/clients/{idClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un client par ID",
            description = "Cette methode permet de chercher un client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun client n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value = APP_ROOT + "/clients/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des clients",
            description = "Cette methode permet de chercher et renvoyer la liste des clients qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des clients / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientDto.class))))
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @Operation(summary = "Supprimer un client",
            description = "Cette methode permet de supprimer un client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a ete supprime",
                    content = @Content)
    })
    void delete(@PathVariable("idClient") Integer id);

}