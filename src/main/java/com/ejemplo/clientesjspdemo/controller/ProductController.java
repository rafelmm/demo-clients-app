package com.ejemplo.clientesjspdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejemplo.clientesjspdemo.config.Routes;
import com.ejemplo.clientesjspdemo.service.ProductService;

@Controller
@RequestMapping(Routes.PRODUCTS)
public class ProductController {

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping(Routes.LIST)
	String productList(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products/product_list";
	}
}
