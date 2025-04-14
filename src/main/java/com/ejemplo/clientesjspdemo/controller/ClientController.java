package com.ejemplo.clientesjspdemo.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplo.clientesjspdemo.config.Routes;
import com.ejemplo.clientesjspdemo.model.Client;
import com.ejemplo.clientesjspdemo.service.ClientService;

@Controller
@RequestMapping(Routes.CLIENTS)
public class ClientController {

	private final ClientService clientService;
	private final MessageSource messageSource;
	
	public ClientController(ClientService clientService, MessageSource messageSource) {
		this.clientService = clientService;
		this.messageSource = messageSource;
	}
	
	@GetMapping(Routes.LIST)
	public String clientList(Model model) {
		
		// Pass client list to the model
		model.addAttribute("clients", clientService.getAllClients());
		return "clients/client_list"; 
	}
	
	@GetMapping(Routes.CREATE)
	public String createClientForm(Model model) {
		model.addAttribute("client", new Client());
		return "clients/create_client";
	}
	
	@PostMapping(Routes.CREATE)
	public String saveClient(Client client) {
		clientService.save(client);
		return "redirect:"+Routes.CLIENTS+Routes.LIST;
	}
	
	@GetMapping(Routes.UPDATE+"/{id}")
	public String updateClient(Locale locale, @PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		Optional<Client> c = clientService.getById(id);
		if ( c.isPresent()) {
			model.addAttribute("client", c.get());
			return "clients/create_client";
		} else {
			redirectAttributes.addFlashAttribute("message", messageSource.getMessage("client.error.notFound", null, locale));
			return "redirect:"+Routes.CLIENTS+Routes.LIST;
		}
	}
	
	@GetMapping(Routes.DELETE+"/{id}")
	public String deleteClient(@PathVariable Long id) {
		clientService.deleteById(id);
		return "redirect:"+Routes.CLIENTS+Routes.LIST;
	}
}
