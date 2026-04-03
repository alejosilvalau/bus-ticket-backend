create user if not exists 'admin_bus'@'%' identified by '4920429';
GRANT SELECT, INSERT, UPDATE, DELETE ON `bus_ticket`.* TO 'admin_bus'@'%';
