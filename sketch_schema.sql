-- 1. Users (Passengers and Admins)
CREATE TABLE users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  full_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role ENUM ('PASSENGER', 'ADMIN') DEFAULT 'PASSENGER'
);

-- 2. Drivers
CREATE TABLE drivers (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  license_number VARCHAR(50) UNIQUE NOT NULL,
  phone_number VARCHAR(20)
);

-- 3. Buses
CREATE TABLE buses (
  id INT PRIMARY KEY AUTO_INCREMENT,
  plate_number VARCHAR(20) UNIQUE NOT NULL,
  bus_type VARCHAR(50), -- e.g., 'Sleeper', 'AC', 'Standard'
  total_capacity INT NOT NULL
);

-- 4. Destinations (Cities/Stations)
CREATE TABLE destinations (
  id INT PRIMARY KEY AUTO_INCREMENT,
  city_name VARCHAR(100) NOT NULL,
  state VARCHAR(100)
);

-- 5. Discounts
CREATE TABLE discounts (
  id INT PRIMARY KEY AUTO_INCREMENT,
  promo_code VARCHAR(20) UNIQUE NOT NULL,
  discount_percentage DECIMAL(5, 2), -- e.g., 10.00 for 10%
  valid_until DATETIME
);
