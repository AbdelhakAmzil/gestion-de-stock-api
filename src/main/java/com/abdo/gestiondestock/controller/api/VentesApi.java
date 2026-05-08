package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.VENTES_ENDPOINT;

import com.abdo.gestiondestock.dto.VentesDto;

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

@Tag(name = "ventes", description = "API de gestion des ventes")
public interface VentesApi {

    @PostMapping(value = VENTES_ENDPOINT + "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une vente",
            description = "Cette methode permet d'enregistrer ou modifier une vente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet vente cree / modifie",
                    content = @Content(schema = @Schema(implementation = VentesDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet vente n'est pas valide",
                    content = @Content)
    })
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(value = VENTES_ENDPOINT + "/{idVente}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par ID",
            description = "Cette methode permet de chercher une vente par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = VentesDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = VENTES_ENDPOINT + "/filter/{codeVente}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une vente par CODE",
            description = "Cette methode permet de chercher une vente par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = VentesDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune vente n'existe dans la BDD avec le CODE fourni",
                    content = @Content)
    })
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(value = VENTES_ENDPOINT + "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des ventes",
            description = "Cette methode permet de chercher et renvoyer la liste des ventes qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des ventes / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = VentesDto.class))))
    })
    List<VentesDto> findAll();

    @DeleteMapping(value = VENTES_ENDPOINT + "/delete/{idVente}")
    @Operation(summary = "Supprimer une vente",
            description = "Cette methode permet de supprimer une vente par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La vente a ete supprimee",
                    content = @Content)
    })
    void delete(@PathVariable("idVente") Integer id);

}