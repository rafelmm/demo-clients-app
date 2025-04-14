package com.ejemplo.clientesjspdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ejemplo.clientesjspdemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
