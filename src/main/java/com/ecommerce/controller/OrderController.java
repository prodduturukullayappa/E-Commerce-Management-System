package com.ecommerce.controller;

import com.ecommerce.model.Order;
import com.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders(@RequestParam(required = false) String email) {
        if (email != null && !email.isEmpty()) {
            return orderService.getOrdersByEmail(email);
        }
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Map<String, Object> payload) {
        try {
            Long customerId = Long.valueOf(payload.get("customerId").toString());
            String name = (String) payload.get("customerName");
            String email = (String) payload.get("customerEmail");
            String phone = (String) payload.get("customerPhone");
            String address = (String) payload.get("customerAddress");
            String paymentMethod = (String) payload.get("paymentMethod");

            Order order = orderService.placeOrder(customerId, name, email, phone, address, paymentMethod);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/direct-buy")
    public ResponseEntity<?> directBuyOrder(@RequestBody Map<String, Object> payload) {
        try {
            Long productId = Long.valueOf(payload.get("productId").toString());
            Integer quantity = Integer.valueOf(payload.get("quantity").toString());
            String name = (String) payload.get("customerName");
            String email = (String) payload.get("customerEmail");
            String phone = (String) payload.get("customerPhone");
            String address = (String) payload.get("customerAddress");
            String paymentMethod = (String) payload.get("paymentMethod");

            Order order = orderService.directBuyOrder(productId, quantity, name, email, phone, address, paymentMethod);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> payload) {
        try {
            String status = payload.get("status");
            Order order = orderService.updateOrderStatus(id, status);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
