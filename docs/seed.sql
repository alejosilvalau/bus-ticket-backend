use bus_ticket;

set foreign_key_checks = 0;
truncate table ticket;
truncate table seat;
truncate table trip;
truncate table bus;
truncate table seat_type;
truncate table location;
truncate table person;
set foreign_key_checks = 1;

insert into location (city_name, state, postal_code) values
('Buenos Aires', 'Ciudad Autónoma de Buenos Aires', 'C1043'),
('La Plata', 'Buenos Aires', 'B1900'),
('Mar del Plata', 'Buenos Aires', 'B7600'),
('Tandil', 'Buenos Aires', 'B7000'),
('Rosario', 'Santa Fe', 'S2000'),
('Santa Fe', 'Santa Fe', 'S3000'),
('Córdoba', 'Córdoba', 'X5000'),
('Mendoza', 'Mendoza', 'M5500'),
('Salta', 'Salta', 'A4400'),
('Neuquén', 'Neuquén', 'Q8300'),
('Bariloche', 'Río Negro', 'R8400'),
('Posadas', 'Misiones', 'N3300');

insert into seat_type (name, upcharge) values
('Estándar', 0.00),
('Semicama', 30.00),
('Cama', 80.00);

insert into bus (plate_number, total_capacity, is_active) values
('AB 123 CD', 40, 1),
('EF 456 GH', 40, 1),
('IJ 789 KL', 40, 1),
('MN 012 OP', 40, 1),
('QR 345 ST', 40, 1),
('UV 678 WX', 40, 1),
('YZ 901 AB', 40, 1),
('CD 234 EF', 40, 1);

insert into person (is_user, first_name, last_name, is_active, email, password, is_admin, license_number, phone_number) values
(0, 'Carlos', 'Ramírez', 1, NULL, NULL, NULL, 'LIC-2021-0001', '+54 9 11 5555-0101'),
(0, 'Martín', 'Gutiérrez', 1, NULL, NULL, NULL, 'LIC-2019-0002', '+54 9 341 555-0102'),
(0, 'Jorge', 'Herrera', 1, NULL, NULL, NULL, 'LIC-2020-0003', '+54 9 223 555-0103'),
(0, 'Pablo', 'Sosa', 1, NULL, NULL, NULL, 'LIC-2018-0004', '+54 9 261 555-0104'),
(1, 'Valentina', 'Herrera', 1, 'admin@bus.com', '$2y$10$VDA/YalLwiedfzFiiksx/e0NNiGS.Auug0IOTpgN7ieVnMy8/e7w6', 1, NULL, NULL),
(1, 'Juan', 'Pérez', 1, 'juan.perez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'María', 'Gómez', 1, 'maria.gomez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Carlos', 'López', 1, 'carlos.lopez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Ana', 'Martínez', 1, 'ana.martinez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Lucía', 'Fernández', 1, 'lucia.fernandez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Pedro', 'Rodríguez', 1, 'pedro.rodriguez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Sofía', 'Díaz', 1, 'sofia.diaz@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(1, 'Diego', 'Sánchez', 1, 'diego.sanchez@mail.com', '$2y$10$PLF/XK6p7jmtxvhYq/axN.iNBiRKvxiiqxwrczuxtljREm2wgxwri', 0, NULL, NULL),
(0, 'Nicolás', 'Acosta', 1, NULL, NULL, NULL, 'LIC-2022-0005', '+54 9 11 5555-0105'),
(0, 'Gustavo', 'Molina', 1, NULL, NULL, NULL, 'LIC-2017-0006', '+54 9 223 555-0106'),
(0, 'Ricardo', 'Vega', 1, NULL, NULL, NULL, 'LIC-2023-0007', '+54 9 341 555-0107'),
(0, 'Ariel', 'Bustos', 1, NULL, NULL, NULL, 'LIC-2016-0008', '+54 9 351 555-0108');

insert into seat (bus_id, seat_type_id, letter, `number`, is_active)
select
  b.id,
  case
    when nums.n between 1 and 7 then (select id from seat_type where name = 'Estándar')
    when nums.n between 8 and 9 then (select id from seat_type where name = 'Semicama')
    else (select id from seat_type where name = 'Cama')
  end,
  l.letter,
  nums.n,
  1
from bus b
cross join (
  select 'A' as letter
  union all select 'B'
  union all select 'C'
  union all select 'D'
) l
cross join (
  with recursive numbers as (
    select 1 as n
    union all
    select n + 1 from numbers where n < 10
  )
  select n from numbers
) nums;

insert into trip (bus_id, driver_id, location_origin_id, location_destination_id, departure_date, arrival_date, base_price)
select
  mod(d.seq - 1, 8) + 1 as bus_id,
  case mod(d.seq - 1, 8)
    when 0 then 1
    when 1 then 2
    when 2 then 3
    when 3 then 4
    when 4 then 14
    when 5 then 15
    when 6 then 16
    else 17
  end as driver_id,
  case mod(d.seq - 1, 8)
    when 3 then 7
    when 6 then 5
    else 1
  end as location_origin_id,
  case mod(d.seq - 1, 8)
    when 0 then 7
    when 1 then 5
    when 2 then 3
    when 3 then 8
    when 4 then 6
    when 5 then 4
    when 6 then 7
    else 7
  end as location_destination_id,
  timestamp('2026-08-22 11:00:00')
    + interval (case mod(d.seq - 1, 8) when 0 then 0 when 1 then 1 when 2 then 2 when 3 then 3 when 4 then 5 when 5 then 7 when 6 then 10 else 12 end) hour
    + interval floor((d.seq - 1) / 8) day as departure_date,
  timestamp('2026-08-22 11:00:00')
    + interval (case mod(d.seq - 1, 8) when 0 then 0 when 1 then 1 when 2 then 2 when 3 then 3 when 4 then 5 when 5 then 7 when 6 then 10 else 12 end) hour
    + interval floor((d.seq - 1) / 8) day
    + interval (case mod(d.seq - 1, 8) when 0 then 10 when 1 then 5 when 2 then 6 when 3 then 10 when 4 then 6 when 5 then 4 when 6 then 4 else 10 end) hour as arrival_date,
  case mod(d.seq - 1, 8)
    when 0 then 18500.00
    when 1 then 9000.00
    when 2 then 12500.00
    when 3 then 16000.00
    when 4 then 11000.00
    when 5 then 9500.00
    when 6 then 9500.00
    else 18500.00
  end as base_price
from (
  with recursive days as (
    select 1 as seq
    union all
    select seq + 1 from days where seq < 72
  )
  select seq from days
) d;

insert into ticket (user_id, trip_id, seat_id, final_price, booking_time, is_cancelled) values
(6, 1, 12, 18500.00, '2026-08-20 10:15:00', 0),
(7, 1, 33, 18530.00, '2026-08-20 14:30:00', 0),
(8, 1, 38, 18580.00, '2026-08-21 09:00:00', 0),
(6, 1, 15, 18500.00, '2026-08-20 16:00:00', 0),
(9, 2, 45, 9000.00, '2026-08-20 11:00:00', 0),
(10, 2, 70, 9030.00, '2026-08-21 10:00:00', 0),
(11, 2, 60, 9000.00, '2026-08-21 15:45:00', 0),
(12, 3, 90, 12500.00, '2026-08-21 12:30:00', 0),
(13, 3, 110, 12530.00, '2026-08-21 13:00:00', 0),
(5, 3, 118, 12580.00, '2026-08-21 18:20:00', 0),
(6, 4, 130, 16000.00, '2026-08-21 08:45:00', 0),
(7, 4, 150, 16030.00, '2026-08-21 19:00:00', 0),
(8, 9, 5, 18500.00, '2026-08-22 09:30:00', 0),
(9, 9, 37, 18580.00, '2026-08-22 11:15:00', 0),
(10, 10, 50, 9000.00, '2026-08-22 10:00:00', 0),
(11, 11, 95, 12500.00, '2026-08-22 12:00:00', 0),
(12, 12, 140, 16000.00, '2026-08-22 13:00:00', 0),
(13, 17, 20, 18500.00, '2026-08-23 10:00:00', 0),
(5, 18, 44, 9000.00, '2026-08-23 11:00:00', 0),
(6, 19, 100, 12500.00, '2026-08-23 12:00:00', 0),
(7, 20, 160, 16080.00, '2026-08-23 13:00:00', 0),
(8, 25, 33, 18530.00, '2026-08-24 10:00:00', 1),
(9, 25, 10, 18500.00, '2026-08-24 10:30:00', 0),
(10, 26, 78, 9080.00, '2026-08-24 11:00:00', 1),
(6, 5, 165, 11000.00, '2026-08-20 12:00:00', 0),
(7, 5, 195, 11030.00, '2026-08-20 13:00:00', 0),
(8, 6, 210, 9500.00, '2026-08-21 08:30:00', 0),
(9, 7, 250, 9500.00, '2026-08-21 11:30:00', 0),
(10, 8, 313, 18530.00, '2026-08-21 16:00:00', 0),
(11, 8, 290, 18500.00, '2026-08-21 17:00:00', 0),
(12, 13, 180, 11000.00, '2026-08-22 14:00:00', 0),
(13, 14, 220, 9500.00, '2026-08-22 15:00:00', 0),
(5, 15, 260, 9500.00, '2026-08-22 16:00:00', 0),
(6, 16, 305, 18500.00, '2026-08-22 18:30:00', 0);
