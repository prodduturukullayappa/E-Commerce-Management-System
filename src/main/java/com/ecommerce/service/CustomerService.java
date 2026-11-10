package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer registerCustomer(Customer customer) {
        Optional<Customer> existing = customerRepository.findByEmail(customer.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("Email is already registered! Please log in.");
        }
        if (customer.getRole() == null || customer.getRole().isEmpty()) {
            customer.setRole("CUSTOMER");
        }
        return customerRepository.save(customer);
    }

    public Customer loginCustomer(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
    }

    public Customer loginAdmin(String email, String password) {
        Customer user = customerRepository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new RuntimeException("Invalid admin email or password"));
        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            throw new RuntimeException("Access denied. Admin credentials required.");
        }
        return user;
    }
}
