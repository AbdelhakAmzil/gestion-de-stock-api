package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.ENTREPRISE_ENDPOINT;

import com.abdo.gestiondestock.dto.EntrepriseDto;

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

@Tag(name = "entreprises", description = "API de gestion des entreprises")
public interface EntrepriseApi {

    @PostMapping(value = ENTREPRISE_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une entreprise",
            description = "Cette methode permet d'enregistrer ou modifier une entreprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet entreprise cree / modifie",
                    content = @Content(schema = @Schema(implementation = EntrepriseDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet entreprise n'est pas valide",
                    content = @Content)
    })
    EntrepriseDto save(@RequestBody EntrepriseDto dto);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/{idEntreprise}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une entreprise par ID",
            description = "Cette methode permet de chercher une entreprise par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entreprise a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = EntrepriseDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune entreprise n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @GetMapping(value = ENTREPRISE_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des entreprises",
            description = "Cette methode permet de chercher et renvoyer la liste des entreprises qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des entreprises / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = EntrepriseDto.class))))
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = ENTREPRISE_ENDPOINT + "/delete/{idEntreprise}")
    @Operation(summary = "Supprimer une entreprise",
            description = "Cette methode permet de supprimer une entreprise par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entreprise a ete supprimee",
                    content = @Content)
    })
    void delete(@PathVariable("idEntreprise") Integer id);

}