package com.sportyshoes.SportyShoesApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String brand;

    private Double price;

    @ManyToOne
    private Category category;

}
