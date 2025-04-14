package com.ejemplo.clientesjspdemo.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ejemplo.clientesjspdemo.config.Routes;

@Controller
public class HomeController {


	private final MessageSource messageSource;
	
	public HomeController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@GetMapping("/")
	public String index(Locale locale) {
		System.out.println(messageSource.getMessage("app.title", null, locale));
		return "redirect:" + Routes.PRODUCTS + Routes.LIST;
	}
}
