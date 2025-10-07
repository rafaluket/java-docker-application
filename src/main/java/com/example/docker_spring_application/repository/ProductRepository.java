package com.example.docker_spring_application.repository;

import com.example.docker_spring_application.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
