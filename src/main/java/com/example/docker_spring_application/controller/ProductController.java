package com.example.docker_spring_application.controller;

import com.example.docker_spring_application.domain.Product;
import com.example.docker_spring_application.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<String> getProductById(@RequestParam String productId){
        Optional<Product> product = productService.findById(productId);
        return ResponseEntity.ok(product.toString());
    }
}
