package com.ecommerce.service;

import com.ecommerce.model.*;
import com.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Order placeOrder(Long customerId, String customerName, String customerEmail, String customerPhone, String customerAddress, String paymentMethod) {
        List<CartItem> cartItems = cartItemRepository.findByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double totalAmount = 0.0;
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Product " + product.getName() + " has insufficient stock");
            }
            totalAmount += product.getPrice() * item.getQuantity();
        }

        Order order = new Order();
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        order.setCustomerPhone(customerPhone);
        order.setCustomerAddress(customerAddress);
        order.setTotalAmount(totalAmount);
        order.setStatus("PROCESSING");
        
        String pMethod = (paymentMethod != null && !paymentMethod.isEmpty()) ? paymentMethod : "COD";
        order.setPaymentMethod(pMethod);
        order.setPaymentStatus("COD".equalsIgnoreCase(pMethod) ? "PENDING_PAYMENT" : "PAID");

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem(order, product, product.getName(), product.getPrice(), item.getQuantity());
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        cartItemRepository.deleteByCustomerId(customerId);

        return savedOrder;
    }

    @Transactional
    public Order directBuyOrder(Long productId, Integer quantity, String customerName, String customerEmail, String customerPhone, String customerAddress, String paymentMethod) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Product " + product.getName() + " has insufficient stock");
        }

        double totalAmount = product.getPrice() * quantity;

        Order order = new Order();
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        order.setCustomerName(customerName);
        order.setCustomerEmail(customerEmail);
        order.setCustomerPhone(customerPhone);
        order.setCustomerAddress(customerAddress);
        order.setTotalAmount(totalAmount);
        order.setStatus("PROCESSING");
        
        String pMethod = (paymentMethod != null && !paymentMethod.isEmpty()) ? paymentMethod : "COD";
        order.setPaymentMethod(pMethod);
        order.setPaymentStatus("COD".equalsIgnoreCase(pMethod) ? "PENDING_PAYMENT" : "PAID");

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        List<OrderItem> orderItems = new ArrayList<>();
        OrderItem orderItem = new OrderItem(order, product, product.getName(), product.getPrice(), quantity);
        orderItems.add(orderItem);

        order.setOrderItems(orderItems);
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByCustomerEmail(email);
    }

    public Order updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
