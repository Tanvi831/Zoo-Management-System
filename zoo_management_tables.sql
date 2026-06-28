-- Create Database
CREATE DATABASE IF NOT EXISTS zoo_management;
USE zoo_management;

-- ===========================
-- 1. Staff Table
-- ===========================

CREATE TABLE Staff (
    StaffID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Role VARCHAR(50),
    Contact VARCHAR(15),
    Salary DECIMAL(10,2),
    DOJ DATE,
    Address VARCHAR(255),
    Shift VARCHAR(50),
    DOB DATE
);
ALTER TABLE Staff AUTO_INCREMENT = 201;

-- ===========================
-- 2. Species Table
-- ===========================

CREATE TABLE Species (
    SpeciesID INT PRIMARY KEY AUTO_INCREMENT,
    ScientificName VARCHAR(100) NOT NULL,
    CommonName VARCHAR(100),
    Category VARCHAR(50),
    Avg_Lifespan INT
);
ALTER TABLE Species AUTO_INCREMENT = 301;


-- ===========================
-- 3. Enclosure Table
-- ===========================

CREATE TABLE Enclosure (
    EnclosureID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    Size VARCHAR(50),
    Type VARCHAR(50),
    EnclosureCapacity INT,
    StaffID INT,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
ALTER TABLE Enclosure AUTO_INCREMENT = 401;

-- ===========================
-- 4. Food_Info Table
-- ===========================

CREATE TABLE Food_Info (
    Food_Name VARCHAR(100) PRIMARY KEY,
    Description VARCHAR(255),
    Cost DECIMAL(10,2)
);

-- ===========================
-- 5. Diet_Details Table
-- ===========================

CREATE TABLE Diet_Details (
    DietID INT PRIMARY KEY AUTO_INCREMENT,
    Food_Name VARCHAR(100),
    Quantity VARCHAR(50),
    FOREIGN KEY (Food_Name) REFERENCES Food_Info(Food_Name)
        ON DELETE SET NULL
        ON UPDATE CASCADE

    
);
ALTER TABLE Diet_Details AUTO_INCREMENT = 501;
-- ===========================
-- 6. Animal Table
-- ===========================

CREATE TABLE Animal (
    AnimalID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100),
    DOB DATE,
    Gender VARCHAR(10),
    Arrival_Date DATE,
    SpeciesID INT,
    DietID INT,
    EnclosureID INT,
    FOREIGN KEY (SpeciesID) REFERENCES Species(SpeciesID)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (DietID) REFERENCES Diet_Details(DietID)
        ON DELETE SET NULL
        ON UPDATE CASCADE,
    FOREIGN KEY (EnclosureID) REFERENCES Enclosure(EnclosureID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
ALTER TABLE Animal AUTO_INCREMENT = 101;

-- ===========================
-- 7. Donor Table
-- ===========================

CREATE TABLE Donor (
    DonorID INT PRIMARY KEY AUTO_INCREMENT,
    Donor_Name VARCHAR(100),
    Contact VARCHAR(15),
    Donor_Addr VARCHAR(255)
);
ALTER TABLE Donor AUTO_INCREMENT = 601;

-- ===========================
-- 8. Sponsorship Table
-- ===========================

CREATE TABLE Sponsorship (
    SponsorshipID INT PRIMARY KEY AUTO_INCREMENT,
    DonorID INT,
    AnimalID INT,
    StartDate DATE,
    EndDate DATE,
    Amount DECIMAL(10,2),
    FOREIGN KEY (DonorID) REFERENCES Donor(DonorID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (AnimalID) REFERENCES Animal(AnimalID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
ALTER TABLE Sponsorship AUTO_INCREMENT = 701;

-- ===========================
-- 9. MedicalCheckup Table
-- ===========================

CREATE TABLE MedicalCheckup (
    CheckupID INT PRIMARY KEY AUTO_INCREMENT,
    AnimalID INT,
    StaffID INT,
    Date DATE,
    Diagnosis VARCHAR(255),
    Treatment VARCHAR(255),
    Next_Checkup_Date DATE,
    FOREIGN KEY (AnimalID) REFERENCES Animal(AnimalID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
ALTER TABLE MedicalCheckup AUTO_INCREMENT = 801;



-- ===========================
-- 10. QuarantineRecord Table
-- ===========================

CREATE TABLE QuarantineRecord (
    QuarantineID INT PRIMARY KEY AUTO_INCREMENT,
    AnimalID INT,
    StaffID INT,
    StartDate DATE,
    EndDate DATE,
    Reason VARCHAR(255),
    Location VARCHAR(100),
    FOREIGN KEY (AnimalID) REFERENCES Animal(AnimalID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);
ALTER TABLE QuarantineRecord AUTO_INCREMENT = 901;

-- ===========================
-- 11. Feeding_Schedule Table
-- ===========================

CREATE TABLE Feeding_Schedule (
    StaffID INT,
    AnimalID INT,
    DietID INT,
    Time TIME,
    ActualFoodQuantity VARCHAR(50),
    PRIMARY KEY (StaffID, AnimalID, DietID),
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (AnimalID) REFERENCES Animal(AnimalID)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (DietID) REFERENCES Diet_Details(DietID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


-- Inventory / food stock (links to your existing FoodInfo table)
CREATE TABLE Inventory (
    Food_Name        VARCHAR(100) PRIMARY KEY,
    CurrentStock    DOUBLE NOT NULL DEFAULT 0,
    MinThreshold    DOUBLE NOT NULL DEFAULT 5,
    Unit            VARCHAR(20) DEFAULT 'kg',
    FOREIGN KEY (Food_Name) REFERENCES Food_Info(Food_Name)
);

-- Notifications generated by the alert engine
CREATE TABLE Notifications (
    NotifID     INT PRIMARY KEY AUTO_INCREMENT,
    Type        ENUM('VET_DUE','FEEDING_DUE','LOW_STOCK') NOT NULL,
    Message     VARCHAR(500) NOT NULL,
    IsRead      BOOLEAN DEFAULT FALSE,
    CreatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE Notifications AUTO_INCREMENT = 1001;

CREATE TABLE Users (
    UserID      INT PRIMARY KEY AUTO_INCREMENT,
    Username    VARCHAR(50) UNIQUE NOT NULL,
    Password    VARCHAR(100) NOT NULL,
    Role        ENUM('ADMIN','STAFF','VET') NOT NULL,
    StaffID     INT,
    FOREIGN KEY (StaffID) REFERENCES Staff(StaffID)
);
ALTER TABLE Users AUTO_INCREMENT = 1101;


INSERT INTO Users (Username, Password, Role) VALUES
('admin',   'admin123',  'ADMIN'),
('vet1',    'vet123',    'VET'),
('staff1',  'staff123',  'STAFF');