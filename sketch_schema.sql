create database if not exists bus_ticket;

use bus_ticket;

create table person (
  id int auto_increment primary key,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  email varchar(100) null unique,
  password varchar(128) null,
  is_active boolean not null default true,
  license_number varchar(50) null unique,
  phone_number varchar(20) null,
  role enum ('user', 'admin', 'driver') not null default 'user',
);

-- Location table
create table location (
  id int auto_increment primary key,
  city_name varchar(100) not null,
  state varchar(100) not null,
  created_at timestamp default current_timestamp,
  unique key uk_location (city_name, state)
);

-- Bus table
create table bus (
  id int auto_increment primary key,
  plate_number varchar(20) not null unique,
  total_capacity int not null check (total_capacity > 0),
  is_active boolean default true,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp on update current_timestamp
);

-- Seat type table
create table seat_type (
  id int auto_increment primary key,
  name varchar(50) not null unique,
  upcharge decimal(10, 2) not null default 0.00 check (upcharge >= 0),
  created_at timestamp default current_timestamp
);

-- Trip table
create table trip (
  id int auto_increment primary key,
  bus_id int not null,
  driver_id int not null,
  origin_location_id int not null,
  destination_location_id int not null,
  departure_date datetime not null,
  arrival_date datetime not null,
  base_price decimal(10, 2) not null check (base_price > 0),
  status enum (
    'scheduled',
    'in_progress',
    'completed',
    'cancelled'
  ) default 'scheduled',
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp on update current_timestamp,
  foreign key (bus_id) references bus (id) on delete restrict on update cascade,
  foreign key (driver_id) references driver (id) on delete restrict on update cascade,
  foreign key (origin_location_id) references location (id) on delete restrict on update cascade,
  foreign key (destination_location_id) references location (id) on delete restrict on update cascade,
  check (departure_date < arrival_date),
  check (origin_location_id != destination_location_id)
);

-- Seat table
create table seat (
  id int auto_increment primary key,
  bus_id int not null,
  letter char(1) not null,
  number int not null check (number > 0),
  seat_type_id int,
  created_at timestamp default current_timestamp,
  foreign key (bus_id) references bus (id) on delete cascade on update cascade,
  foreign key (seat_type_id) references seat_type (id) on delete set null on update cascade,
  unique key uk_bus_seat (bus_id, letter, number)
);

-- Ticket table
create table ticket (
  id int auto_increment primary key,
  user_id int not null,
  trip_id int not null,
  seat_id int not null,
  final_price decimal(10, 2) not null check (final_price > 0),
  booking_time timestamp default current_timestamp,
  status enum ('active', 'cancelled', 'used') default 'active',
  cancelled_at timestamp null,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp on update current_timestamp,
  foreign key (user_id) references user (id) on delete restrict on update cascade,
  foreign key (trip_id) references trip (id) on delete restrict on update cascade,
  foreign key (seat_id) references seat (id) on delete restrict on update cascade,
  unique key uk_trip_seat (trip_id, seat_id)
);

-- Create indexes for performance optimization
create index idx_user_email on user (email);

create index idx_user_is_active on user (is_active);

create index idx_driver_license on driver (license_number);

create index idx_driver_is_active on driver (is_active);

create index idx_bus_plate on bus (plate_number);

create index idx_bus_is_active on bus (is_active);

create index idx_location_city_state on location (city_name, state);

create index idx_trip_bus on trip (bus_id);

create index idx_trip_driver on trip (driver_id);

create index idx_trip_origin_location on trip (origin_location_id);

create index idx_trip_destination_location on trip (destination_location_id);

create index idx_trip_departure on trip (departure_date);

create index idx_trip_status on trip (status);

create index idx_seat_bus on seat (bus_id);

create index idx_seat_type on seat (seat_type_id);

create index idx_ticket_user on ticket (user_id);

create index idx_ticket_trip on ticket (trip_id);

create index idx_ticket_seat on ticket (seat_id);

create index idx_ticket_status on ticket (status);

create index idx_ticket_booking_time on ticket (booking_time);
