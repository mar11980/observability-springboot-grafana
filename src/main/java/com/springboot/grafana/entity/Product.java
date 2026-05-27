package com.springboot.grafana.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Product")
public class Product implements Serializable {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id ;
    private String name;
    private double price;
}
