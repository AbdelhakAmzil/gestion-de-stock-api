package com.abdo.gestiondestock.services;

import com.abdo.gestiondestock.dto.FournisseurDto;
import java.util.List;

public interface FournisseurService {

    FournisseurDto save(FournisseurDto dto);

    FournisseurDto findById(Integer id);

    List<FournisseurDto> findAll();

    void delete(Integer id);

}