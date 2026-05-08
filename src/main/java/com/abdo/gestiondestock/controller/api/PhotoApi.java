package com.abdo.gestiondestock.controller.api;

import static com.abdo.gestiondestock.utils.Constants.APP_ROOT;

import com.flickr4java.flickr.FlickrException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "photos", description = "API de gestion des photos")
public interface PhotoApi {

    @PostMapping(value = APP_ROOT + "/save/{id}/{title}/{context}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Enregistrer une photo",
            description = "Cette methode permet d'uploader et d'associer une photo a une entite par son ID et contexte")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La photo a ete enregistree avec succes",
                    content = @Content(schema = @Schema(implementation = Object.class))),
            @ApiResponse(responseCode = "400", description = "La requete n'est pas valide",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Erreur lors de l'upload de la photo",
                    content = @Content)
    })
    Object savePhoto(
            @PathVariable("context") String context,
            @PathVariable("id") Integer id,
            @RequestPart("file") MultipartFile photo,
            @PathVariable("title") String title
    ) throws IOException, FlickrException;

}