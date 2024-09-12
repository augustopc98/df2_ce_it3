package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, List<OrderItem> items) {
        CustomerOrder order = new CustomerOrder(null, customerEmail, customerAddress, new java.util.Date(), items);
        return customerOrderRepository.save(order);
    }

    @Override
    public void addOrderItem(Long orderId, OrderItem item) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.getItems().add(item);
        customerOrderRepository.save(order);
    }

    @Override
    public void removeOrderItem(Long orderId, Long orderItemId) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Optional<OrderItem> itemToRemove = order.getItems().stream()
                .filter(item -> item.getId().equals(orderItemId))
                .findFirst();
        itemToRemove.ifPresent(order.getItems()::remove);
        customerOrderRepository.save(order);
    }

    @Override
    public void sendOrderForDelivery(Long orderId) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.sendForDelivery();
        customerOrderRepository.save(order);
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        CustomerOrder order = customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.updateDeliveryStatus(status);
        customerOrderRepository.save(order);
    }

    @Override
    public CustomerOrder getOrderDetails(Long orderId) {
        return customerOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}