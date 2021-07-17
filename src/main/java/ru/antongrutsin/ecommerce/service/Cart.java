package ru.antongrutsin.ecommerce.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.antongrutsin.ecommerce.domain.OrderProduct;
import ru.antongrutsin.ecommerce.domain.Product;

import javax.annotation.PostConstruct;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private Map<Long, OrderProduct> items;
    private Double totalPrice;

    public Map<Long, OrderProduct> getItems() {
        return items;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    @PostConstruct
    public void init() {
        items = new LinkedHashMap<>();
    }

    public void addProduct(Product product) {
        OrderProduct item = items.get(product.getId());
        if (item == null) {
            item = new OrderProduct();
            item.setItemPrice(product.getCost());
            item.setProduct(product);
            item.setQuantity(0);
        }
        item.setQuantity(item.getQuantity() + 1);
        item.setTotalPrice(item.getItemPrice() * item.getQuantity());
        items.put(product.getId(), item);
        recalculate();
    }

    public void removeItem(Product product) {
        items.remove(product.getId());
        recalculate();
    }

    public void clear() {
        items.clear();
        totalPrice = 0.0;
    }

    private void recalculate() {
        totalPrice = 0.0;
        items.values().forEach(oi -> totalPrice += oi.getTotalPrice());
    }
}