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
  role enum ('user', 'admin', 'driver') not null default 'user'
);

create table location (
  id int auto_increment primary key,
  city_name varchar(100) not null,
  state varchar(100) not null,
);

create table bus (
  id int auto_increment primary key,
  plate_number varchar(20) not null unique,
  total_capacity int not null default 0,
  is_active boolean default true,
);

create table seat_type (
  id int auto_increment primary key,
  name varchar(100) not null unique,
  upcharge decimal(10, 2) not null default 0.00,
);

create table trip (
  id int auto_increment primary key,
  id_bus int not null,
  id_driver int not null,
  id_location_origin int not null,
  id_location_destination int not null,
  departure_date datetime not null,
  arrival_date datetytime not null,
  base_price decimal(10, 2) not null default 0.00,
  constraint fk_trip_bus FOREIGN key (id_bus) references bus (id) on delete restrict on update cascade,
  constraint fk_trip_person foreign key (id_driver) references person (id) on delete restrict on update cascade,
  constraint fk_trip_location_origin foreign key (id_location_origin) references location (id) on delete restrict on update cascade,
  constraint fk_trip_location_destination foreign key (id_location_destination) references location (id) on delete restrict on update cascade,
  unique key uk_trip_bus_departure (id_bus, departure_date),
  unique key uk_trip_driver_departure (id_driver, departure_date)
);

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
  foreign key (user_id) references person (id) on delete restrict on update cascade,
  foreign key (trip_id) references trip (id) on delete restrict on update cascade,
  foreign key (seat_id) references seat (id) on delete restrict on update cascade,
  unique key uk_trip_seat (trip_id, seat_id)
);

-- token varchar (64) not null unique
