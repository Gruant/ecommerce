package ru.antongrutsin.ecommerce.domain;

import lombok.Data;

@Data
public class CartInfo {

    public Integer productCount;
    public Integer totalCost;
}