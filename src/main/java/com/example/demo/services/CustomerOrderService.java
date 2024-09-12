package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;

import java.util.List;

public interface CustomerOrderService {
    CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items);

    void addOrderItem(Long orderId, OrderItem item);

    void removeOrderItem(Long orderId, Long orderItemId);

    void sendOrderForDelivery(Long orderId);

    void updateDeliveryStatus(Long orderId, String status);

    CustomerOrder getOrderDetails(Long orderId);
}