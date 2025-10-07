package com.example.docker_spring_application.service;

import com.example.docker_spring_application.domain.Product;
import com.example.docker_spring_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findById(String productId){
        return productRepository.findById(productId);
    }
}
