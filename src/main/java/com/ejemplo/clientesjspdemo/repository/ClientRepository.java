package com.ejemplo.clientesjspdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ejemplo.clientesjspdemo.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	

}
