# 🍽️ FoodPulse – Smart Hostel Mess Management System

FoodPulse is a **full-stack smart mess management platform** designed to reduce food wastage in hostels and PG accommodations by combining **student participation, food analytics, and mess management tools**.

The system allows **students to vote for upcoming menus and give feedback**, while **mess managers monitor food preparation, consumption trends, and waste analytics through a dashboard**.

This data-driven approach helps hostels **optimize meal planning and significantly reduce food waste**.

---

# 🎯 Problem

In many hostel and PG mess systems:

* Food quantities are prepared based on assumptions
* Students are not involved in menu planning
* Food preferences are rarely tracked
* Mess managers lack consumption analytics

This often results in **large amounts of food waste, increased mess expenses, and low student satisfaction**.

---

# 💡 Solution

FoodPulse introduces a **two-sided system** where both students and mess administrators participate in improving food management.

Students influence menu planning through **voting and feedback**, while mess managers use **analytics and food tracking data** to optimize food preparation.

This helps reduce wastage while improving student satisfaction.

---

# 🚀 System Features

## 👨‍🍳 Mess Manager Dashboard

Mess administrators can manage and monitor food operations through a centralized dashboard.

Features include:

* Add food prepared in the mess
* Track quantity and serving location
* Edit or delete food records
* Monitor total food entries
* Track total plates prepared
* View hostel/location coverage
* Visual analytics dashboard

---

## 📊 Analytics Dashboard

The system provides real-time insights for mess managers.

Analytics include:

* Location-wise food distribution
* Food item consumption trends
* Total plates served
* Mess usage overview

Charts included:

* 📊 Bar Chart – Food distribution by location
* 🥧 Pie Chart – Food category distribution

---

## 🎓 Student Portal

Students can actively participate in improving mess operations.

### 🗳 Menu Voting

Students vote for upcoming meals.

Example:

```
Tomorrow's Lunch Menu

1. Biryani
2. Veg Meals
3. Fried Rice
4. Chicken Curry
```

The most voted item helps guide menu planning.

---

### ⭐ Food Feedback System

Students can rate meals after consumption.

Example:

```
Rate today's food

⭐ ⭐ ⭐ ⭐ ⭐
```

Mess managers can analyze average ratings to improve food quality.

---

### 📉 Food Waste Tracking

The system tracks leftover food quantities and consumption patterns, helping identify waste trends and optimize preparation.

---

### 💰 Mess Bill Insights

FoodPulse can estimate mess expenses and provide consumption insights based on recorded food quantities.

---

# 🏗️ System Architecture

```
Students (Voting / Feedback)
        │
        ▼
React Frontend
        │
        ▼
Spring Boot REST API
        │
        ▼
MySQL Database
        │
        ▼
Mess Manager Analytics Dashboard
```

---

# 🛠 Tech Stack

## Frontend

* React.js
* JavaScript
* CSS
* Recharts (Analytics & Charts)

## Backend

* Java
* Spring Boot
* Spring Data JPA
* REST APIs

## Database

* MySQL

## Tools

* Maven
* VS Code
* Thunder Client

---

# 📂 Project Structure

```
Wasteless
│
├── backend
│   └── foodpulse-backend
│       ├── controller
│       ├── entity
│       ├── repository
│       └── application
│
├── frontend
│   └── src
│       ├── App.js
│       ├── App.css
│
└── database
    └── database-setup.sql
```

---

# ⚙️ Setup Instructions

## 1. Clone Repository

```
git clone https://github.com/devikapolavarapu/FoodPulse.git
cd FoodPulse
```

---

# 2. Database Setup

Run the SQL initialization script:

```
database/database-setup.sql
```

This will create the required database tables.

---

# 3. Run Backend

Navigate to backend folder:

```
cd backend/foodpulse-backend
```

Run the Spring Boot application:

```
./mvnw spring-boot:run
```

Backend runs at:

```
http://localhost:8080
```

---

# 4. Run Frontend

Navigate to frontend folder:

```
cd frontend
```

Install dependencies:

```
npm install
```

Start the React application:

```
npm start
```

Frontend runs at:

```
http://localhost:3000
```

---

# 📡 API Endpoints

| Method | Endpoint   | Description            |
| ------ | ---------- | ---------------------- |
| GET    | /food/all  | Fetch all food records |
| POST   | /food/add  | Add food entry         |
| PUT    | /food/{id} | Update food entry      |
| DELETE | /food/{id} | Delete food entry      |

---

# 📊 Dashboard Preview

The FoodPulse dashboard includes:

* Food entry form
* Analytics charts
* Mess data overview
* Editable food records

(Add dashboard screenshot here)

---

# 👩‍💻 Author

**Devika Polavarapu**

B.Tech – Information Technology
Velagapudi Ramakrishna Siddhartha Engineering College

Interests:

* Software Engineering
* Artificial Intelligence

---

# ⭐ Support

If you find this project useful, consider giving it a **star ⭐ on GitHub**.
