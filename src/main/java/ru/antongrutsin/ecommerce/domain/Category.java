package ru.antongrutsin.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany (mappedBy="categoryId", fetch=FetchType.LAZY)
    private List<Product> products;
}
