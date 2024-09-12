package com.example.demo.controllers;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customerOrders")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    public CustomerOrder createOrder(
            @RequestParam("customerEmail") String customerEmail,
            @RequestParam("customerAddress") String customerAddress,
            @RequestBody List<OrderItem> items) {
        return customerOrderService.createOrder(customerEmail, customerAddress, items);
    }

    @PostMapping("/{orderId}/items")
    public void addOrderItem(@PathVariable Long orderId, @RequestBody OrderItem item) {
        customerOrderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public void removeOrderItem(@PathVariable Long orderId, @PathVariable Long orderItemId) {
        customerOrderService.removeOrderItem(orderId, orderItemId);
    }

    @PostMapping("/{orderId}/sendForDelivery")
    public void sendOrderForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendOrderForDelivery(orderId);
    }

    @PutMapping("/{orderId}/deliveryStatus")
    public void updateDeliveryStatus(@PathVariable Long orderId, @RequestParam String status) {
        customerOrderService.updateDeliveryStatus(orderId, status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrder getOrderDetails(@PathVariable Long orderId) {
        return customerOrderService.getOrderDetails(orderId);
    }
}