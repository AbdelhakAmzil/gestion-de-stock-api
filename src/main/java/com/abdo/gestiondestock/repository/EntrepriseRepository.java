package com.abdo.gestiondestock.repository;

import com.abdo.gestiondestock.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {

}