package ru.antongrutsin.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "PRODUCT")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 64, nullable = false)
    private String name;

    @Column(name = "description",length = 1000)
    private String description;

    @Column(name = "cost")
    private double cost;

    @Column(name = "photo", unique = true)
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category categoryId;
}