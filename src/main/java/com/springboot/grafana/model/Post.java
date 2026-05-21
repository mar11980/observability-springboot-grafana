package com.springboot.grafana.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Post {

    private Integer id;

    private Integer userId;

    private String title;

    private String body;

}
