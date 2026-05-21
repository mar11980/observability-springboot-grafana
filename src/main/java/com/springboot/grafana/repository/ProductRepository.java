package com.springboot.grafana.repository;

import com.springboot.grafana.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
