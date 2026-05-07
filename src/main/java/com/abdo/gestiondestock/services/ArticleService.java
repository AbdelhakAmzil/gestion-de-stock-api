    package com.abdo.gestiondestock.services;

    import com.abdo.gestiondestock.dto.ArticleDto;
    import com.abdo.gestiondestock.dto.LigneCommandeClientDto;
    import com.abdo.gestiondestock.dto.LigneCommandeFournisseurDto;
    import com.abdo.gestiondestock.dto.LigneVenteDto;
import java.util.List;

public interface ArticleService {

    ArticleDto save(ArticleDto dto);

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    List<ArticleDto> findAll();

    List<LigneVenteDto> findHistoriqueVentes(Integer idArticle);

    List<LigneCommandeClientDto> findHistoriaueCommandeClient(Integer idArticle);

    List<LigneCommandeFournisseurDto> findHistoriqueCommandeFournisseur(Integer idArticle);

    List<ArticleDto> findAllArticleByIdCategory(Integer idCategory);

    void delete(Integer id);

}