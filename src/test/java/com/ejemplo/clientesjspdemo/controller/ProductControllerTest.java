package com.ejemplo.clientesjspdemo.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.ejemplo.clientesjspdemo.config.Routes;
import com.ejemplo.clientesjspdemo.model.Product;
import com.ejemplo.clientesjspdemo.service.ProductService;

@WebMvcTest(ProductController.class)	
public class ProductControllerTest {
	@Autowired 
	private MockMvc mockMvc; // Simulate HTTP Requests
	
	@MockBean
	private ProductService productService;  // ProductService simulation
	
	private List<Product> products;
	
	@BeforeEach
	void setUp() {
		Product product1 = new Product("Table", "Table description", 10.0F);
		product1.setId(1L);
		Product product2 = new Product("Chair", "Chair description", 9.5F);
		product2.setId(2L);
		product2.setName("Chair name");
		product2.setDescription("Chair long description");
		product2.setPrice(12.3F);
		products = Arrays.asList(product1, product2);
	}
	
	@Test
	void productListTest() throws Exception{
		when(productService.getAllProducts()).thenReturn(products);
		
		mockMvc.perform(get(Routes.PRODUCTS + Routes.LIST))
			.andExpect(status().isOk())
			.andExpect(view().name("products/product_list"))
			.andExpect(model().attribute("products", hasSize(2)))
			.andExpect(model().attribute("products", hasItem(
					allOf(
							hasProperty("id", is(1L)),
							hasProperty("name", is("Table")),
							hasProperty("description", is("Table description")),
							hasProperty("price", is(10.0F))
					)
			)));
	}
}
