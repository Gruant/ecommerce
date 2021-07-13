package ru.antongrutsin.ecommerce.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.antongrutsin.ecommerce.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    public Order() {

    }

    public enum Status {
        CREATED, PAID, SENT, RECEIVED, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> products;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(User user, String phone, String address) {
        this.user = user;
        this.phone = phone;
        this.address = address;
        this.products = new ArrayList<>();
        this.status = Status.CREATED;
        this.price = 0.0;
    }

    public void addItem(OrderProduct product) {
        products.add(product);
        product.setOrder(this);
        price += product.getTotalPrice();
    }
}
