package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.APP_ROOT;

import com.abdo.gestiondestock.dto.MvtStkDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "mvtstk", description = "API de gestion des mouvements de stock")
public interface MvtStkApi {

    @GetMapping(value = APP_ROOT + "/mvtstk/stockreel/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Consulter le stock reel d'un article",
            description = "Cette methode permet de consulter le stock reel d'un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La valeur du stock reel",
                    content = @Content(schema = @Schema(implementation = BigDecimal.class))),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe avec l'ID fourni",
                    content = @Content)
    })
    BigDecimal stockReelArticle(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/mvtstk/filter/article/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister les mouvements de stock d'un article",
            description = "Cette methode permet de renvoyer la liste des mouvements de stock d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des mouvements de stock / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MvtStkDto.class))))
    })
    List<MvtStkDto> mvtStkArticle(@PathVariable("idArticle") Integer idArticle);

    @PostMapping(value = APP_ROOT + "/mvtstk/entree",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une entree de stock",
            description = "Cette methode permet d'enregistrer une entree de stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'entree de stock enregistree",
                    content = @Content(schema = @Schema(implementation = MvtStkDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet mouvement de stock n'est pas valide",
                    content = @Content)
    })
    MvtStkDto entreeStock(@RequestBody MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/sortie",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une sortie de stock",
            description = "Cette methode permet d'enregistrer une sortie de stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La sortie de stock enregistree",
                    content = @Content(schema = @Schema(implementation = MvtStkDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet mouvement de stock n'est pas valide",
                    content = @Content)
    })
    MvtStkDto sortieStock(@RequestBody MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/correctionpos",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une correction de stock positive",
            description = "Cette methode permet d'enregistrer une correction de stock positive")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La correction de stock positive enregistree",
                    content = @Content(schema = @Schema(implementation = MvtStkDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet mouvement de stock n'est pas valide",
                    content = @Content)
    })
    MvtStkDto correctionStockPos(@RequestBody MvtStkDto dto);

    @PostMapping(value = APP_ROOT + "/mvtstk/correctionneg",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une correction de stock negative",
            description = "Cette methode permet d'enregistrer une correction de stock negative")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La correction de stock negative enregistree",
                    content = @Content(schema = @Schema(implementation = MvtStkDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet mouvement de stock n'est pas valide",
                    content = @Content)
    })
    MvtStkDto correctionStockNeg(@RequestBody MvtStkDto dto);

}