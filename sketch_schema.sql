create database if not exists bus_ticket;

use bus_ticket;

create table person (
  id int primary key auto_increment,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  email varchar(100) unique null,
  password varchar(255) null,
  license_number varchar(50) unique null,
  phone_number varchar(20) null,
  role enum ('PASSENGER', 'ADMIN', 'DRIVER') default 'PASSENGER'
);

create table discount_type (
  id int primary key auto_increment,
  name varchar(100) not null,
  discount_percentage decimal(4, 2) not null,
);

create table discount (
  id int primary key auto_increment,
  start_date datetime not null,
  end_date datetime not null,
  id_discount_type int not null,
  foreign key (id_discount_type) references discount_type (id)
);

create table discount_user (
  id int primary key auto_increment,
  id_discount int not null,
  id_user int not null,
  foreign key (id_discount) references discount (id),
  foreign key (id_user) references person (id)
);

-- SKETCH BELOW
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

-- 6. Seats
CREATE TABLE seats (
  id INT PRIMARY KEY AUTO_INCREMENT,
  bus_id INT NOT NULL,
  seat_number VARCHAR(10) NOT NULL, -- e.g., 'A1', '12'
  FOREIGN KEY (bus_id) REFERENCES buses (id) ON DELETE CASCADE
);

-- 7. Trips
CREATE TABLE trips (
  id INT PRIMARY KEY AUTO_INCREMENT,
  bus_id INT NOT NULL,
  driver_id INT NOT NULL,
  origin_id INT NOT NULL,
  destination_id INT NOT NULL,
  departure_time DATETIME NOT NULL,
  arrival_time DATETIME NOT NULL,
  base_price DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (bus_id) REFERENCES buses (id),
  FOREIGN KEY (driver_id) REFERENCES drivers (id),
  FOREIGN KEY (origin_id) REFERENCES destinations (id),
  FOREIGN KEY (destination_id) REFERENCES destinations (id)
);

-- 8. Tickets
CREATE TABLE tickets (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  trip_id INT NOT NULL,
  seat_id INT NOT NULL,
  discount_id INT, -- Optional
  final_price DECIMAL(10, 2) NOT NULL,
  booking_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  status ENUM ('CONFIRMED', 'CANCELLED', 'PENDING') DEFAULT 'CONFIRMED',
  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (trip_id) REFERENCES trips (id),
  FOREIGN KEY (seat_id) REFERENCES seats (id),
  FOREIGN KEY (discount_id) REFERENCES discounts (id),
  -- CRITICAL: Prevent the same seat being booked twice for the same trip
  UNIQUE KEY unique_seat_per_trip (trip_id, seat_id)
);
