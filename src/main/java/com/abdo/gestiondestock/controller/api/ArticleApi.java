package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.APP_ROOT;

import com.abdo.gestiondestock.dto.ArticleDto;
import com.abdo.gestiondestock.dto.LigneCommandeClientDto;
import com.abdo.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.abdo.gestiondestock.dto.LigneVenteDto;

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

@Tag(name = "articles", description = "API de gestion des articles")
public interface ArticleApi {

    @PostMapping(value = APP_ROOT + "/articles/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer un article",
            description = "Cette methode permet d'enregistrer ou modifier un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'objet article cree / modifie",
                    content = @Content(schema = @Schema(implementation = ArticleDto.class))),
            @ApiResponse(responseCode = "400", description = "L'objet article n'est pas valide",
                    content = @Content)
    })
    ArticleDto save(@RequestBody ArticleDto dto);

    @GetMapping(value = APP_ROOT + "/articles/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par ID",
            description = "Cette methode permet de chercher un article par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = ArticleDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value = APP_ROOT + "/articles/filter/{codeArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher un article par CODE",
            description = "Cette methode permet de chercher un article par son CODE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete trouve dans la BDD",
                    content = @Content(schema = @Schema(implementation = ArticleDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucun article n'existe dans la BDD avec le CODE fourni",
                    content = @Content)
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value = APP_ROOT + "/articles/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des articles",
            description = "Cette methode permet de chercher et renvoyer la liste des articles qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des articles / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))))
    })
    List<ArticleDto> findAll();

    @GetMapping(value = APP_ROOT + "/articles/historique/vente/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Historique des ventes d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historique des ventes",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LigneVenteDto.class))))
    })
    List<LigneVenteDto> findHistoriqueVentes(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/historique/commandeclient/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Historique des commandes client d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historique des commandes client",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LigneCommandeClientDto.class))))
    })
    List<LigneCommandeClientDto> findHistoriaueCommandeClient(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/historique/commandefournisseur/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Historique des commandes fournisseur d'un article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Historique des commandes fournisseur",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LigneCommandeFournisseurDto.class))))
    })
    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(@PathVariable("idArticle") Integer idArticle);

    @GetMapping(value = APP_ROOT + "/articles/filter/category/{idCategory}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Lister les articles par categorie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des articles de la categorie",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ArticleDto.class))))
    })
    List<ArticleDto> findAllArticleByIdCategory(@PathVariable("idCategory") Integer idCategory);

    @DeleteMapping(value = APP_ROOT + "/articles/delete/{idArticle}")
    @Operation(summary = "Supprimer un article",
            description = "Cette methode permet de supprimer un article par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete supprime",
                    content = @Content)
    })
    void delete(@PathVariable("idArticle") Integer id);

}