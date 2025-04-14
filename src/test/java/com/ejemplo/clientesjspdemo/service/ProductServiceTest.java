package com.ejemplo.clientesjspdemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ejemplo.clientesjspdemo.model.Product;
import com.ejemplo.clientesjspdemo.repository.ProductRepository;

public class ProductServiceTest {

	private ProductRepository productRepository;
	private ProductService productService;
	
	@BeforeEach
	void setUp() {
		productRepository = mock(ProductRepository.class);
		productService = new ProductService(productRepository);
	}
	
	@Test
	void getAllProductsTest() {
		Product p1 = new Product("P1", "Product 1", 10.0F);
		Product p2 = new Product("P2", "Product 2", 15.32F);
		
		when(productRepository.findAll()).thenReturn(List.of(p1, p2));
		
		var list = productService.getAllProducts();
		
		assertEquals(2, list.size());
		verify(productRepository, times(1)).findAll();
		
	}
}
