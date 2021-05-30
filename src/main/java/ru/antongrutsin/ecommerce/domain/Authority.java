package ru.antongrutsin.ecommerce.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @Column(length = 50)
    private String name;
}
