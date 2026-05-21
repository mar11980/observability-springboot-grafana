package com.springboot.grafana.controller;

import com.springboot.grafana.entity.Product;
import com.springboot.grafana.model.Post;
import com.springboot.grafana.repository.ProductRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController

public class ProductController {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductController(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {

        ResponseEntity<List<Post>> response =
                restTemplate.exchange(
                        "https://jsonplaceholder.typicode.com/posts",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Post>>() {}
                );

        return response.getBody();
    }
}
