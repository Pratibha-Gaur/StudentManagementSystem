## Student Management System

A desktop-based Student Management System built entirely from scratch using Java, Swing, JDBC, and MySQL.
No templates, no auto-generated code — every feature was hand‑crafted to demonstrate real-world Java development skills.

## Features

## Authentication

- Secure role-based login (Admin & Student)
- Input validation with error messages
- SQL injection prevention via PreparedStatement

## Admin Panel

- Add, update, delete student records
- Dynamic JTable with live MySQL data
- Multi-parameter search (name, email, course)
- Popup notifications for success/error

## Student Panel

- Personalized dashboard with live profile data
- View complete student record
- Real-time welcome screen with marks & grade

## Grade Calculator

- 5-subject grade calculator
- Auto percentage & grade computation
- Color-coded results with performance remarks

## Database Layer

- Normalized MySQL schema
- Dedicated DatabaseConnection class
- Full CRUD operations (Create, Read, Update, Delete)
- Proper connection handling & pooling

## Tech Stack

- Java (JDK 24) — Core logic & OOP
- Swing — GUI design
- MySQL (8.0.46) — Relational database
- JDBC (Connector 9.7) — Database connectivity
- NetBeans (29) — Development IDE

 Project Structure
Code
src/
 ├── com/student/database/
 │    └── DatabaseConnection.java
 └── com/student/ui/
      ├── LoginForm.java
      ├── AdminDashboard.java
      ├── AddStudentForm.java
      ├── ViewStudentsForm.java
      ├── StudentDashboard.java
      └── GradeCalculator.java


## Database Schema

- users
id, username, password, role
- students
id, first_name, last_name, email, phone, course, marks, address


## Setup & Run
Create Database

sql
CREATE DATABASE student_management;
Add users and students tables (see schema above).

Configure Connection
Update DatabaseConnection.java with your MySQL password.

Add JDBC Connector

Download MySQL Connector/J

Add JAR to project libraries in NetBeans

Run Project

Set main class to com.student.ui.LoginForm

Press F6 or Run

## Default Login
Role	Username	Password
Admin	admin	admin123
Student	student	student123


## Highlights
- Built completely from scratch
- Real MySQL integration (no dummy data)
- Role-based dashboards
- Clean, professional code structure
- Validation, error handling, user feedback

## Future Enhancements
Password hashing (BCrypt)
Export data to PDF/Excel
Profile photo upload
Forgot password feature
Attendance tracking
Executable JAR deployment