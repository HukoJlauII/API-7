package com.example.api7.controller;

import com.example.api7.entity.Product;
import com.example.api7.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @QueryMapping
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @QueryMapping
    public List<Product> findProductsByProducer(@Argument String producer) {
        return productRepository.findProductsByProducer(producer);
    }

    @MutationMapping
    public Product createProduct(@Argument ProductInput productInput) {
        Product product = new Product(productInput.text, productInput.qty, productInput.completed, productInput.producer);
        return productRepository.save(product);
    }

    record ProductInput(String text, Integer qty, Boolean completed, String producer) {
    }
}
