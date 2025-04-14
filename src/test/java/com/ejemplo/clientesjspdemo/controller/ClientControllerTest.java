package com.ejemplo.clientesjspdemo.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ejemplo.clientesjspdemo.config.Routes;
import com.ejemplo.clientesjspdemo.model.Client;
import com.ejemplo.clientesjspdemo.service.ClientService;


@WebMvcTest(ClientController.class)	
public class ClientControllerTest {

	@Autowired 
	private MockMvc mockMvc; // Simulate HTTP Requests
	
	@MockBean
	private ClientService clientService;  // ClientService simulation
	
	private List<Client> clients;
	
	@BeforeEach
	void setUp() {
		Client client1 = new Client("Juan", "juan@example.com");
        Client client2 = new Client("Ana", "ana@example.com");
        clients = Arrays.asList(client1, client2);
	}
	
	@Test
	void clientListTest() throws Exception{
		when(clientService.getAllClients()).thenReturn(clients);
		
		mockMvc.perform(get(Routes.CLIENTS + Routes.LIST))
			.andExpect(status().isOk())
			.andExpect(view().name("clients/client_list"))
			.andExpect(model().attribute("clients", hasSize(2)))
			.andExpect(model().attribute("clients", hasItem(
					allOf(
							hasProperty("name", is("Juan")),
							hasProperty("email", is("juan@example.com"))
					)
			)));
	}
	
	@Test
	void createClientFormTest() throws Exception {
		mockMvc.perform(get(Routes.CLIENTS + Routes.CREATE))
			.andExpect(status().isOk())
			.andExpect(view().name("clients/create_client"))
			.andExpect(model().attribute("client", 
					allOf(
							hasProperty("name", is("")),
							hasProperty("email", is(""))
					)
			));
	}
	
	@Test
	void saveClientTest() throws Exception {
		mockMvc.perform(post(Routes.CLIENTS + Routes.CREATE)
				.param("name", "Miguel Fernández")
				.param("email", "miquel@example.com"))
				.andExpect(redirectedUrl(Routes.CLIENTS + Routes.LIST));
		
		verify(clientService).save(org.mockito.ArgumentMatchers.any(Client.class));
	}
	
	@Test 
	void updateClientTest() throws Exception {
		Client client = new Client("Luis", "luis@example.com");
		client.setId(1L);
		when(clientService.getById(1L)).thenReturn(Optional.of(client));
		when(clientService.getById(999L)).thenReturn(Optional.empty());
		
		mockMvc.perform(get(Routes.CLIENTS+Routes.UPDATE+"/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("clients/create_client"))
			.andExpect(model().attributeExists("client"))
			.andExpect(model().attribute("client", client));
		
		mockMvc.perform(get(Routes.CLIENTS+Routes.UPDATE+"/999"))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl(Routes.CLIENTS + Routes.LIST))
			.andExpect(flash().attributeExists("message"))
			.andExpect(flash().attribute("message", "No se ha encontrado el cliente"));				
	}
	
	@Test
	void deleteClientTest() throws Exception {
		Long clientId = 1L;
		mockMvc.perform(get(Routes.CLIENTS+Routes.DELETE+"/{id}", clientId))
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl(Routes.CLIENTS + Routes.LIST));
		
		verify(clientService).deleteById(clientId);
	}
}
