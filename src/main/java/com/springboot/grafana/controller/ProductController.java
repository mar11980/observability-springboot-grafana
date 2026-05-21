package com.springboot.grafana.controller;

import com.springboot.grafana.entity.Product;
import com.springboot.grafana.model.Post;
import com.springboot.grafana.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class ProductController {

    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    public ProductController(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        log.info("Getting all products from products link");
        return productRepository.findAll();
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts() {
        log.info("Getting all posts from posts link");
        ResponseEntity<List<Post>> response =
                restTemplate.exchange(
                        "https://jsonplaceholder.typicode.com/posts",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Post>>() {}
                );

        if(Objects.requireNonNull(response.getBody()).isEmpty())
            log.error("No posts found in remote service..");
        log.info("Get all posts from remote service");
        return response.getBody();
    }
}
