# Complete Java E-Commerce Management System with Authentication

A fully functional E-Commerce System with **Customer Registration/Login** and **Admin Portal Access Control** built with **Java 21**, **Spring Boot 3**, **SQLite**, **REST APIs**, and **Bootstrap 5**.

---

## 🔐 Authentication Credentials

### Admin Login (`/admin-login.html`)
- **Email:** `admin@shop.com`
- **Password:** `admin123`

### Default Customer Login (`/login.html`)
- **Email:** `rahul@example.com`
- **Password:** `customer123`
- *Or register a new account on `/register.html`*

---

## Installation & Execution Instructions

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
4. Access via Browser:
   - Storefront: `http://localhost:8080`
   - Customer Login: `http://localhost:8080/login.html`
   - Customer Register: `http://localhost:8080/register.html`
   - Admin Login: `http://localhost:8080/admin-login.html`
   - Admin Dashboard: `http://localhost:8080/admin.html`
