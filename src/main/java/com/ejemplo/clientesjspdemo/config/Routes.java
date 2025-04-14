package com.ejemplo.clientesjspdemo.config;

import org.springframework.stereotype.Component;

@Component("routes")
public class Routes {

	public static final String CLIENTS = "/clients";
	public static final String PRODUCTS = "/products";
	
	public static final String LIST = "/list";
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update";
	public static final String DELETE = "/delete";
	
	public static String getClients() {
		return CLIENTS;
	}
	public static String getList() {
		return LIST;
	}
	public static String getCreate() {
		return CREATE;
	}
	public static String getUpdate() {
		return UPDATE;
	}
	public static String getDelete() {
		return DELETE;
	}
	public static String getProducts() {
		return PRODUCTS;
	}
	
}
