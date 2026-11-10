package com.ecommerce.config;

import com.ecommerce.model.Category;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.count() == 0) {
            // Seed Admin Account
            customerRepository.save(new Customer("Admin User", "admin@shop.com", "admin123", "+91 9999999999", "Admin Office, Tech Park", "ADMIN"));
            
            // Seed Default Customer
            customerRepository.save(new Customer("Rahul Sharma", "rahul@example.com", "customer123", "+91 9876543210", "123 MG Road, Bengaluru", "CUSTOMER"));
        }

        if (categoryRepository.count() == 0) {
            Category electronics = new Category("Electronics", "Smartphones, Laptops and Gadgets");
            Category fashion = new Category("Fashion", "Clothing, Footwear and Accessories");
            Category home = new Category("Home & Kitchen", "Home appliances and decor");

            categoryRepository.save(electronics);
            categoryRepository.save(fashion);
            categoryRepository.save(home);

            productRepository.save(new Product("Wireless Pro Headphones", "High fidelity active noise cancelling wireless headphones with 30h battery life.", 2999.00, 25, "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=500", electronics));
            productRepository.save(new Product("Smart Fitness Watch", "Track your heart rate, sleep, workouts and notifications on a vivid AMOLED display.", 1999.00, 40, "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=500", electronics));
            productRepository.save(new Product("Ultra HD 4K Camera", "Capture crystal clear videos and stunning photos with professional optics.", 45999.00, 10, "https://images.unsplash.com/photo-1516035069371-29a1b244cc32?w=500", electronics));
            
            productRepository.save(new Product("Classic Leather Jacket", "Premium genuine leather jacket with quilted lining and durable brass zippers.", 3499.00, 15, "https://images.unsplash.com/photo-1551028719-00167b16eac5?w=500", fashion));
            productRepository.save(new Product("Designer Casual Sneakers", "Comfortable everyday wear sneakers crafted from breathable canvas and leather.", 1499.00, 50, "https://images.unsplash.com/photo-1542291026-7eec264c27ff?w=500", fashion));
            
            productRepository.save(new Product("Stainless Steel Coffee Maker", "Programmable drip coffee maker with thermal carafe and aroma selector.", 2499.00, 30, "https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6?w=500", home));
            productRepository.save(new Product("Ergonomic Office Chair", "Breathable mesh backrest, adjustable lumbar support and cushioned seat for all-day comfort.", 7999.00, 12, "https://images.unsplash.com/photo-1580481077494-e3299ac2506e?w=500", home));
        }
    }
}
