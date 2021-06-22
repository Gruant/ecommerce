package ru.antongrutsin.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.Map;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@Entity
@Table(name = "ORDER")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    // Products

    @Column(name = "date_created", nullable = false)
    @Temporal(TIMESTAMP)
    private Date dateCreated;

    @Column(name = "executed", nullable = false)
    private boolean executed;


}
