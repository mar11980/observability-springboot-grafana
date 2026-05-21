package com.springboot.grafana;

import com.springboot.grafana.entity.Product;
import com.springboot.grafana.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GrafanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrafanaApplication.class, args);}
//
//        @Bean
//        CommandLineRunner commandLineRunner(ProductRepository productRepository){
//          return args->{
//                Product product = Product.builder().name("computer").price(12.8).build();
//                Product product1 = Product.builder().name("smartphone").price(10.7).build();
//                Product product2 = Product.builder().name("coco").price(23.9).build();
//               productRepository.saveAll(List.of(product,product1,product2));
//          };
//        }


}
