package com.example.docker_spring_application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/single")
    public ResponseEntity<String> getProduct(@RequestParam long productId){

        return ResponseEntity.ok()
    }
}
