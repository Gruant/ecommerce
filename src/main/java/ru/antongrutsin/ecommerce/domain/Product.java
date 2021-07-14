package ru.antongrutsin.ecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Table(name = "product")
@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 64, nullable = false)
    private String name;

    @Column(name = "description",length = 1000)
    private String description;

    @Column(name = "cost")
    private Double cost;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<ProductImage> images;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category categoryId;

}