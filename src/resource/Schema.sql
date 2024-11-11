CREATE DATABASE InventoryApp;

USE InventoryApp;

-- Users Table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    mobileNumber VARCHAR(15),
    role ENUM('Admin', 'Agent') NOT NULL
);


-- Products Table
CREATE TABLE Product (
    productId INT PRIMARY KEY,
    productName VARCHAR(100) NOT NULL,
    minSellQuantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantityAvailable INT NOT NULL
);


-- Transactions Table
CREATE TABLE Transaction (
    transactionId INT PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    productId INT NOT NULL,
    productName VARCHAR(100) NOT NULL,
    transactionType ENUM('Buy', 'Sell') NOT NULL,
    quantity INT NOT NULL,
    cost DECIMAL(10, 2) NOT NULL,
    totalCost DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(id),
    FOREIGN KEY (productId) REFERENCES Product(productId)
);

select * from User;


-- Insert Sample Data
-- Insert sample Admin users
INSERT INTO User (name, password, mobileNumber, role)
VALUES 
('admin1', 'password123', '1234567890', 'Admin'),
('admin2', 'adminpass', '0987654321', 'Admin');

-- Insert sample Agent users
INSERT INTO User (name, password, mobileNumber, role)
VALUES 
('agent1', 'agentpass1', '1122334455', 'Agent'),
('agent2', 'agentpass2', '5566778899', 'Agent');