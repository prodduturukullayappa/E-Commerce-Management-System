package com.ecommerce.service;

import com.ecommerce.model.CartItem;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CartItemRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CartItem> getCartItems(Long customerId) {
        return cartItemRepository.findByCustomerId(customerId);
    }

    public CartItem addToCart(Long customerId, Long productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock available");
        }

        CartItem existingItem = cartItemRepository.findByCustomerIdAndProductId(customerId, productId);
        if (existingItem != null) {
            int newQty = existingItem.getQuantity() + quantity;
            if (product.getStock() < newQty) {
                throw new RuntimeException("Insufficient stock available");
            }
            existingItem.setQuantity(newQty);
            return cartItemRepository.save(existingItem);
        } else {
            CartItem newItem = new CartItem(customerId, product, quantity);
            return cartItemRepository.save(newItem);
        }
    }

    public CartItem updateCartItem(Long itemId, Integer quantity) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        if (item.getProduct().getStock() < quantity) {
            throw new RuntimeException("Insufficient stock available");
        }
        item.setQuantity(quantity);
        return cartItemRepository.save(item);
    }

    public void removeFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public void clearCart(Long customerId) {
        cartItemRepository.deleteByCustomerId(customerId);
    }
}
