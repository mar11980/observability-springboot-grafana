package com.springboot.grafana.controller;

import com.springboot.grafana.entity.Product;
import com.springboot.grafana.model.Post;
import com.springboot.grafana.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@SuppressWarnings("NullableProblems")
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

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product dto) {

        log.info("Trying to create new product {} ",dto);

        Product productSaved = productRepository.save(dto);

        URI location = URI.create("/product/" + productSaved.getId());

        return ResponseEntity
                .created(location)
                .body(productSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        log.info("Trying to delete product id {}", id);

        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> {
                    log.error("Product id {} not found, nothing to delete", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
