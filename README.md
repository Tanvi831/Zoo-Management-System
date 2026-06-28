# Zoo Management System

A desktop application built with **Java (Swing)** and **MySQL** for managing zoo operations.

## Features
-  Login system with 3 roles — Admin, Staff, Veterinarian
-  Full CRUD for Animals, Staff, Enclosures, Species, Diet, and more Medical checkup and quarantine record tracking
-  Feeding schedule management with inventory tracking
-  Automatic alerts for overdue vet checkups, low stock, and missed feedings
-  Role-based access control — different permissions per role

## Tech Stack
- Java (Swing for UI)
- MySQL (Database)
- JDBC (Database connectivity)

## How to Run
1. Import `zoo_management_tables.sql` into MySQL Workbench
2. Update DB credentials in `dao/DBConnection.java`
3. Run the project from `ui/LoginPage.java`

## Default Login Credentials
| Username | Password | Role  |
|----------|----------|-------|
| admin    | admin123 | Admin |
| vet1     | vet123   | Vet   |
| staff1   | staff123 | Staff |

## Database Schema
- 14 tables with auto-increment IDs
- Foreign key constraints with CASCADE rules
