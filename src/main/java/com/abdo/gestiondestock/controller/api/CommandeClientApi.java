package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.APP_ROOT;

import com.abdo.gestiondestock.dto.CommandeClientDto;
import com.abdo.gestiondestock.dto.LigneCommandeClientDto;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "commandesclients", description = "API de gestion des commandes clients")
public interface CommandeClientApi {

    @PostMapping(value = APP_ROOT + "/commandesclients/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une commande client",
            description = "Cette methode permet d'enregistrer ou modifier une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client cree / modifiee",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "400", description = "La commande client n'est pas valide",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto dto);

    @PatchMapping(value = APP_ROOT + "/commandesclients/update/etat/{idCommande}/{etatCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour l'etat d'une commande client",
            description = "Cette methode permet de mettre a jour l'etat d'une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'etat de la commande a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "400", description = "L'etat de la commande n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> updateEtatCommande(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("etatCommande") EtatCommande etatCommande);

    @PatchMapping(value = APP_ROOT + "/commandesclients/update/quantite/{idCommande}/{idLigneCommande}/{quantite}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour la quantite d'une ligne de commande client",
            description = "Cette methode permet de mettre a jour la quantite d'une ligne de commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La quantite a ete mise a jour",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "400", description = "La quantite n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aucune ligne de commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> updateQuantiteCommande(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande,
            @PathVariable("quantite") BigDecimal quantite);

    @PatchMapping(value = APP_ROOT + "/commandesclients/update/client/{idCommande}/{idClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour le client d'une commande",
            description = "Cette methode permet de mettre a jour le client associe a une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Le client a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande ou client n'existe avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> updateClient(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idClient") Integer idClient);

    @PatchMapping(value = APP_ROOT + "/commandesclients/update/article/{idCommande}/{idLigneCommande}/{idArticle}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Mettre a jour l'article d'une ligne de commande client",
            description = "Cette methode permet de mettre a jour l'article d'une ligne de commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete mis a jour",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande, ligne de commande ou article n'existe avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> updateArticle(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande,
            @PathVariable("idArticle") Integer idArticle);

    @DeleteMapping(value = APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Supprimer un article d'une commande client",
            description = "Cette methode permet de supprimer un article d'une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "L'article a ete supprime de la commande",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande ou ligne de commande n'existe avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> deleteArticle(
            @PathVariable("idCommande") Integer idCommande,
            @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(value = APP_ROOT + "/commandesclients/{idCommandeClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par ID",
            description = "Cette methode permet de chercher une commande client par son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la BDD avec l'ID fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

    @GetMapping(value = APP_ROOT + "/commandesclients/filter/{codeCommandeClient}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Rechercher une commande client par CODE",
            description = "Cette methode permet de chercher une commande client par son code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a ete trouvee dans la BDD",
                    content = @Content(schema = @Schema(implementation = CommandeClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Aucune commande client n'existe dans la BDD avec le CODE fourni",
                    content = @Content)
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(value = APP_ROOT + "/commandesclients/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des commandes clients",
            description = "Cette methode permet de chercher et renvoyer la liste des commandes clients qui existent dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des commandes clients / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CommandeClientDto.class))))
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @GetMapping(value = APP_ROOT + "/commandesclients/lignesCommande/{idCommande}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Renvoi la liste des lignes de commande d'une commande client",
            description = "Cette methode permet de renvoyer la liste des lignes de commande associees a une commande client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La liste des lignes de commande / Une liste vide",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LigneCommandeClientDto.class))))
    })
    ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(
            @PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(value = APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    @Operation(summary = "Supprimer une commande client",
            description = "Cette methode permet de supprimer une commande client par ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La commande client a ete supprimee",
                    content = @Content)
    })
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);

}