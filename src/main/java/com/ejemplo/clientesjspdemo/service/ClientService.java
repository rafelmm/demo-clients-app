package com.ejemplo.clientesjspdemo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.ejemplo.clientesjspdemo.model.Client;
import com.ejemplo.clientesjspdemo.repository.ClientRepository;

@Service
public class ClientService {

	private final ClientRepository clientRepository;
	
	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public Optional<Client> getById(Long id) {
		return clientRepository.findById(id);
	}
	
	public List<Client> getAllClients() {
		return clientRepository.findAll();
	}
	

	public Client save(Client client) {
		return clientRepository.save(client);
	}
	
	public void deleteById(Long id) {
		clientRepository.deleteById(id);
	}
}
