package com.abdo.gestiondestock.services;

import com.abdo.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import com.abdo.gestiondestock.dto.UtilisateurDto;
import java.util.List;

public interface UtilisateurService {

    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Integer id);

    List<UtilisateurDto> findAll();

    void delete(Integer id);

    UtilisateurDto findByEmail(String email);

    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);


}