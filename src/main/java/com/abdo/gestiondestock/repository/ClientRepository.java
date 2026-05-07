package com.abdo.gestiondestock.repository;

import com.abdo.gestiondestock.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
