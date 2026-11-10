# Complete Java E-Commerce Management System with Authentication

A fully functional E-Commerce System with **Customer Registration/Login** and **Admin Portal Access Control** built with **Java 21**, **Spring Boot 3**, **SQLite**, **REST APIs**, and **Bootstrap 5**.

---
## 🚀 Features
🛍️ Product & Catalog Management
✔ Browse products organized by categories with Indian Rupee (₹) pricing.
✔ Real-time stock tracking and inventory updates.

👤 User Authentication & Profiles
✔ Secure customer registration and login workflows with credential validation.
✔ Persistent client-side session management for seamless cart retention.

🛒 Shopping Cart & Checkout System
✔ Add, update quantities, and remove items from the shopping cart.
✔ "Buy Now" direct checkout option for instant single-item purchases.

💳 Order & Payment Management
✔ Support for multiple payment methods including UPI, Cards, Net Banking, and Cash on Delivery (COD).
✔ Automated order processing with atomic transactions and real-time inventory stock deduction.

📊 Admin Management Dashboard
✔ Password-protected admin portal for complete system control.
✔ Add new products, track overall sales revenue, and manage order statuses (PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED).

---

## 📁 Project Structure

```text
ecommerce-app/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── ecommerce/
│   │   │               ├── EcommerceApplication.java
│   │   │               ├── controller/
│   │   │               │   ├── ProductController.java
│   │   │               │   ├── CartController.java
│   │   │               │   ├── OrderController.java
│   │   │               │   ├── UserController.java
│   │   │               │   └── AdminController.java
│   │   │               │
│   │   │               ├── model/
│   │   │               │   ├── Product.java
│   │   │               │   ├── Category.java
│   │   │               │   ├── User.java
│   │   │               │   ├── Order.java
│   │   │               │   └── OrderItem.java
│   │   │               │
│   │   │               ├── repository/
│   │   │               │   ├── ProductRepository.java
│   │   │               │   ├── CategoryRepository.java
│   │   │               │   ├── UserRepository.java
│   │   │               │   └── OrderRepository.java
│   │   │               │
│   │   │               └── service/
│   │   │                   ├── ProductService.java
│   │   │                   ├── CartService.java
│   │   │                   ├── OrderService.java
│   │   │                   ├── UserService.java
│   │   │                   └── AdminService.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           ├── index.html
│   │           ├── login.html
│   │           ├── register.html
│   │           ├── admin-login.html
│   │           ├── admin.html
│   │           │
│   │           ├── css/
│   │           │   └── style.css
│   │           │
│   │           └── js/
│   │               └── app.js
│   │
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── ecommerce/
│                       └── EcommerceApplicationTests.java
│
├── .gitignore
├── pom.xml
├── README.md
└── ecommerce.db
```


## 🛠️ Installation & Execution Instructions

### Prerequisites
1. **Java Development Kit (JDK) 21**
2. **Apache Maven**

### Step-by-Step Execution
1. Extract `ecommerce-project.zip` to your project folder.
2. Build the project:
   ```cmd
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```cmd
   mvn spring-boot:run
   ```
4. Access via browser
   http://localhost:8080

 ## 📥Clone the Repository
 git 
 cd Ecommerce

 ## How the System works
1. User Interface (Frontend): You open the website in your browser to browse products, manage your shopping cart, and place orders.
2. Server Logic (Backend): The Java Spring Boot server receives your actions (such as logging in or checking out), processes the logic, and secures your data.
3. Data Storage (SQLite): All application data—including user accounts, product details, and orders—is saved automatically in a local database file (ecommerce.db).
4. Admin Control: Administrators log into a separate dashboard to add new products, track overall sales, and update order statuses in real time.

## 🏗️ Future Enhancements
●Real Online Payments: Integrate live payment gateways like Razorpay or Stripe so customers can make secure online payments with real cards and UPI.
●Email Confirmations: Automatically send email receipts, order confirmations, and delivery updates to customers when they place an order.
●Search & Filters: Add a search bar and price filters to help users find specific products quickly on the storefront.
●Wishlist & Reviews: Allow registered users to save favorite items to a personal wishlist and leave star ratings or reviews on products.

## 🏆  Why use this project
●This project is a lightweight, zero-setup full-stack e-commerce platform built with Java, Spring Boot, and SQLite that features role-based access, a shopping cart, multi-channel payments, and an admin dashboard

## 📞 Contact
For any issues or suggestions, feel free to reach out:
📧 Email: prodduturukullayappa@gmail.com
