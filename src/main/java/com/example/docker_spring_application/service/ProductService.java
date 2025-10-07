package com.example.docker_spring_application.service;

import com.example.docker_spring_application.domain.Product;
import com.example.docker_spring_application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }
}
