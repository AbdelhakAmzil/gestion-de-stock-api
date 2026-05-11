package com.abdo.gestiondestock.repository;

import com.abdo.gestiondestock.model.Utilisateur;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {


    @Query("select u from Utilisateur u left join fetch u.roles left join fetch u.entreprise where u.email = :email")
    Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);

}