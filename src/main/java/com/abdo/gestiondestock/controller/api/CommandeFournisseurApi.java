package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.abdo.gestiondestock.utils.Constants.CREATE_COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.abdo.gestiondestock.utils.Constants.DELETE_COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.abdo.gestiondestock.utils.Constants.FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT;
import static com.abdo.gestiondestock.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT;
import static com.abdo.gestiondestock.utils.Constants.FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT;

import com.abdo.gestiondestock.dto.CommandeFournisseurDto;
import com.abdo.gestiondestock.dto.LigneCommandeFournisseurDto;
import com.abdo.gestiondestock.model.EtatCommande;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "commandefournisseur", description = "API de gestion des commandes fournisseurs")
public interface CommandeFournisseurApi {

    @PostMapping(value = CREATE_COMMANDE_FOURNISSEUR_ENDPOINT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une commande fournisseur",
            description = "Cette methode permet d'enregistrer ou modifier une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur cree / modifiee",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "400", description = "La commande fournisseur n'est pas valide",
                    content = @Content)
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto dto);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/etat/{idCommande}/{etatCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour l'etat d'une commande fournisseur",
            description = "Cette methode permet de mettre a jour l'etat d'une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'etat de la commande a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "400", description = "L'etat de la commande n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto updateEtatCommande(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/quantite/{idCommande}/{idLigneCommande}/{quantite}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour la quantite d'une ligne de commande fournisseur",
            description = "Cette methode permet de mettre a jour la quantite d'une ligne de commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La quantite a ete mise a jour",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "400", description = "La quantite n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune ligne de commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto updateQuantiteCommande(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande,
            @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/fournisseur/{idCommande}/{idFournisseur}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour le fournisseur d'une commande",
            description = "Cette methode permet de mettre a jour le fournisseur associe a une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le fournisseur a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande ou fournisseur n'existe avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto updateFournisseur(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idFournisseur") Integer idFournisseur);

    @PatchMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/update/article/{idCommande}/{idLigneCommande}/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour l'article d'une ligne de commande fournisseur",
            description = "Cette methode permet de mettre a jour l'article d'une ligne de commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande, ligne de commande ou article n'existe avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto updateArticle(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande,
            @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/delete/article/{idCommande}/{idLigneCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un article d'une commande fournisseur",
            description = "Cette methode permet de supprimer un article d'une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete supprime de la commande",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande ou ligne de commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto deleteArticle(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(value = FIND_COMMANDE_FOURNISSEUR_BY_ID_ENDPOINT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande fournisseur par ID",
            description = "Cette methode permet de chercher une commande fournisseur par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    CommandeFournisseurDto findById(@PathVariable("idCommandeFournisseur") Integer id);

    @GetMapping(value = FIND_COMMANDE_FOURNISSEUR_BY_CODE_ENDPOINT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande fournisseur par CODE",
            description = "Cette methode permet de chercher une commande fournisseur par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = CommandeFournisseurDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande fournisseur n'existe dans la BDD avec le CODE fourni",
                    content = @Content)
    })
    CommandeFournisseurDto findByCode(@PathVariable("codeCommandeFournisseur") String code);

    @GetMapping(value = FIND_ALL_COMMANDE_FOURNISSEUR_ENDPOINT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des commandes fournisseurs",
            description = "Cette methode permet de chercher et renvoyer la liste des commandes fournisseurs qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des commandes fournisseurs / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandeFournisseurDto.class))))
    })
    List<CommandeFournisseurDto> findAll();

    @GetMapping(value = COMMANDE_FOURNISSEUR_ENDPOINT + "/lignesCommande/{idCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des lignes de commande d'une commande fournisseur",
            description = "Cette methode permet de renvoyer la liste des lignes de commande associees a une commande fournisseur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des lignes de commande / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LigneCommandeFournisseurDto.class))))
    })
    List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(
            @PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(value = DELETE_COMMANDE_FOURNISSEUR_ENDPOINT)
    @Operation(summary = "Supprimer une commande fournisseur",
            description = "Cette methode permet de supprimer une commande fournisseur par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande fournisseur a ete supprimee",
                    content = @Content)
    })
    void delete(@PathVariable("idCommandeFournisseur") Integer id);

}