package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerEmail;
    private String customerAddress;
    private Date orderDate;
    private String deliveryStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    public CustomerOrder() {}

    public CustomerOrder(Long id, String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.items = items;
        this.deliveryStatus = "PENDING";
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void sendForDelivery() {
        this.deliveryStatus = "IN_DELIVERY";
    }

    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }

    // Getters and Setters
}
