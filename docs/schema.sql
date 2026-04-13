create database if not exists bus_ticket;

use bus_ticket;

create table person (
  id int auto_increment primary key,
  is_user boolean not null default true,
  first_name varchar(100) not null,
  last_name varchar(100) not null,
  is_active boolean not null default true,
  email varchar(100) null unique,
  password varchar(128) null,
  is_admin boolean null default false,
  license_number varchar(50) null unique,
  phone_number varchar(20) null
);

create table location (
  id int auto_increment primary key,
  city_name varchar(100) not null,
  state varchar(100) not null
);

create table bus (
  id int auto_increment primary key,
  plate_number varchar(20) not null unique,
  total_capacity int not null default 0 check (total_capacity >= 0),
  is_active boolean default true
);

create table seat_type (
  id int auto_increment primary key,
  name varchar(100) not null unique,
  upcharge decimal(10, 2) not null default 0.00 check (upcharge >= 0)
);

create table trip (
  id int auto_increment primary key,
  id_bus int not null,
  id_driver int not null,
  id_location_origin int not null,
  id_location_destination int not null,
  departure_date datetime not null,
  arrival_date datetime not null,
  base_price decimal(10, 2) not null default 0.00 check (base_price >= 0),
  constraint fk_trip_bus FOREIGN key (id_bus) references bus (id) on delete restrict on update cascade,
  constraint fk_trip_person foreign key (id_driver) references person (id) on delete restrict on update cascade,
  constraint fk_trip_location_origin foreign key (id_location_origin) references location (id) on delete restrict on update cascade,
  constraint fk_trip_location_destination foreign key (id_location_destination) references location (id) on delete restrict on update cascade,
  constraint chk_trip_date check (arrival_date > departure_date),
  unique key uk_trip_bus_departure (id_bus, departure_date),
  unique key uk_trip_driver_departure (id_driver, departure_date)
);

create table seat (
  id int auto_increment primary key,
  id_bus int not null,
  id_seat_type int not null,
  letter char(1) not null,
  number int not null,
  foreign key (id_bus) references bus (id) on delete restrict on update cascade,
  foreign key (id_seat_type) references seat_type (id) on delete restrict on update cascade,
  unique key uk_bus_seat (id_bus, letter, number)
);

create table ticket (
  id int auto_increment primary key,
  id_user int not null,
  id_trip int not null,
  id_seat int not null,
  final_price decimal(10, 2) not null default 0.00 check (final_price > 0),
  booking_time datetime default current_timestamp,
  is_cancelled boolean default false not null,
  token varchar(64) not null unique,
  constraint fk_ticket_user foreign key (id_user) references person (id) on delete restrict on update cascade,
  constraint fk_ticket_trip foreign key (id_trip) references trip (id) on delete restrict on update cascade,
  constraint fk_ticket_seat foreign key (id_seat) references seat (id) on delete restrict on update cascade,
  unique key uk_trip_seat (id_trip, id_seat)
);
