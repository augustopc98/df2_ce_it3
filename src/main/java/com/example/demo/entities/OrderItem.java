package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    public OrderItem() {}

    public OrderItem(Long id, Long productId, int quantity, BigDecimal productPrice) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        if (productPrice == null) {
            return BigDecimal.ZERO; // Default to 0 if productPrice is null
        }
        return productPrice.multiply(BigDecimal.valueOf(quantity));
    }

    // Getters and Setters...
}
