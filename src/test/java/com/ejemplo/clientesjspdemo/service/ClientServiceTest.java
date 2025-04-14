package com.ejemplo.clientesjspdemo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.ejemplo.clientesjspdemo.model.Client;
import com.ejemplo.clientesjspdemo.repository.ClientRepository;

public class ClientServiceTest {

	private ClientService clientService;
	private ClientRepository clientRepository;
	
	@BeforeEach
	void setUp() {
		clientRepository = mock(ClientRepository.class);
		clientService = new ClientService(clientRepository);
	}
	
	@Test
	void testGetAllClients() {
		Client c1 = new Client("Juan", "juan@example.com");
		Client c2 = new Client("Ana", "ana@example.com");
		
		// Define the expected behaviour of mock
		when(clientRepository.findAll()).thenReturn(List.of(c1, c2));
		
		// Call to the method to test
		var list = clientService.getAllClients();
		
		// Check the results
		assertEquals(2, list.size());
		verify(clientRepository, times(1)).findAll();
		
	}
	
	@Test
	void testGetById() {
		Client client = new Client();
		client.setId(1L);
		client.setName("Juan");
		client.setEmail("juan@example.com");
		// Define the expected behaviour of mock
		when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
		when(clientRepository.findById(999L)).thenReturn(Optional.empty());
		
		// Call to the method to test
		Client result = clientService.getById(1L).get();
		
		// Check the results
		assertNotNull(result);
		assertEquals(1L, result.getId());
		
		// Verify the method has been called just twice
		verify(clientRepository, times(1)).findById(1L);
				
		// Check to retrive an object that not exists
		Optional<Client> clientOpt = clientService.getById(999L);
		assertFalse(clientOpt.isPresent(), "The client must not exist");
		
	}
	
	@Test
	void testSave() {
		Client client = new Client("Juan", "juan@example.com");
		
		//Define the expected behaviour of mock
		when(clientRepository.save(Mockito.any(Client.class))).thenReturn(client);
		
		// Call to the method to test
		Client result = clientService.save(client);
		
		// Check the results
		assertEquals("Juan", result.getName());
		
		// Verify the method has been called just once
		verify(clientRepository).save(client);
		
	}
	
	@Test
	void testDelete() {
		
		doNothing().when(clientRepository).deleteById(1L);
		
		// Call the method to test
		clientService.deleteById(1L);
		
		//Check the result
		verify(clientRepository, times(1)).deleteById(1L);
	}
}
