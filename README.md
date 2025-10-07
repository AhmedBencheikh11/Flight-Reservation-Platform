# ✈️ Flight Reservation Platform

## Final Project: Online Travel Agency

This project is a **full-stack flight reservation platform** simulating an online travel agency. It was developed as a final project, implementing modern technologies for data management, authentication, and a responsive user interface.

---

## 🛠️ Technologies Used

| Category | Technology | Role |
| :--- | :--- | :--- |
| **Back-end** | **Spring Boot** (Java) | Development of the RESTful API and business logic. |
| **Security** | **Spring Security** | Management of authentication and authorization. |
| **Database** | **MySQL** | Storage for flight information, users, and bookings. |
| **Front-end** | **HTML5 / CSS3 / JavaScript** | Structure and interactivity of the user interface. |
| **Design/UI** | **Bootstrap** | Framework for a responsive and modern design. |

---

## ✨ Key Features

### 1. Authentication and Authorization System 🔐
* **User Registration** (`Créer un compte`): Allows new users to create an account with name, first name, email, and password.
* **User Login** (`Se connecter`): Secure access to the personal area using **Spring Security**.
![App Screenshot](./resources/static/images/2.png)
![App Screenshot](./resources/static/images/1.png)

### 2. Flight and Booking Management
* **Available Flights Display** (`Nos Vols Disponibles`): Clear presentation of destinations, departure airports, and prices.
* **Booking Module**: Enables users to select and confirm a flight reservation.
![App Screenshot](./resources/static/images/3.png)
* **User Space (`Mon Espace`)**: Allows users to view and modify their personal profile and view their booking history.
![App Screenshot](./resources/static/images/5.png)
    * **Profile Modification** (`Modifier mon profil`): Option to update the user's details and password.
    ![App Screenshot](./resources/static/images/6.png)

### 3. Administration
* **Admin Interface**: Implements **CRUD** (Create, Read, Update, Delete) operations for managing flights and user accounts (built upon the RESTful API).

### 4. Communication and Contact 💬
* **Contact Us Page** (`Contactez-nous`): Provides agency contact details and a form for submitting complaints/claims (`Déposer une réclamation`).
![App Screenshot](./resources/static/images/4.png)

---

## 🚀 Installation and Setup

To run this project locally:

1.  **Prerequisites:** Ensure you have **Java (JDK 17+)**, **Maven**, and a **MySQL** server installed.
2.  **Database Configuration:**
    * Create a MySQL database.
    * Update the Spring Boot configuration file (`application.properties` or `application.yml`) with your database credentials.
3.  **Build and Run:**
    * Navigate to the project's root directory.
