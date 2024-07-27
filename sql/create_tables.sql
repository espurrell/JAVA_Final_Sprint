-- Create Database (Execute this in PostgreSQL client or GUI tool)
CREATE DATABASE vintique;

-- Connect to the database (Execute this in PostgreSQL client or GUI tool)
\c vintique;

-- Create Users Table
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(10) NOT NULL
);

-- Create Products Table
CREATE TABLE Products (
    item_id SERIAL PRIMARY KEY,
    item_name VARCHAR(100) NOT NULL,
    item_type VARCHAR(50) NOT NULL,
    item_description TEXT,
    seller_id INTEGER NOT NULL,
    FOREIGN KEY (seller_id) REFERENCES Users(user_id)
);
