
INSERT INTO regiones(id,nombre) values (1,'sudamerica');
INSERT INTO regiones(id,nombre) values (2,'centroamerica');
INSERT INTO regiones(id,nombre) values (3,'norteamerica');
INSERT INTO regiones(id,nombre) values (4,'Europa');
INSERT INTO regiones(id,nombre) values (5,'Africa');
INSERT INTO regiones(id,nombre) values (6,'Asia');
INSERT INTO regiones(id,nombre) values (7,'Oceania');

INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(1,'Arcadio','López','arcadiolg2@gmail.com','2020-08-28','',1);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(2,'Jesus','López','jesussad2@gmail.com','2020-08-28','',2);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(3,'Jeesus','Ló2pez','jaesus2@gmail.com','2020-08-28','',3);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(4,'Jesus','López','jescvxus2@gmail.com','2020-08-28','',4);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(5,'Jedssus','Ló2pez','sdajesus2@gmail.com','2020-08-28','',5);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(6,'Jesus','López','jesus2@gmail.com','2020-08-28','',6);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(7,'Jesus','López','jesusnxd@gmail.com','2020-08-28','',7);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(8,'J3esus','Ló2pez','jesdsus2@gmail.com','2020-08-28','',1);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(9,'Jesus','López','krospesi2@gmail.com','2020-08-28','',1);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(10,'Jesus','López','krospesi21@gmail.com','2020-08-28','',2);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(11,'Jesus','López','jescxvus2@gmail.com','2020-08-28','',3);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(12,'Jesus','López','jesedswsus2@gmail.com','2020-08-28','',4);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(14,'Jesus','López2','jdsfesus2@gmail.com','2020-08-28','',5);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(15,'Jesus2','López','jesdfus2@gmail.com','2020-08-28','',6);


INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(16,'Arcadio','López','arsacadiolg2@gmail.com','2020-08-28','',1);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(17,'Jesus','López','jesusdassad2@gmail.com','2020-08-28','',2);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(18,'Jeesus','Ló2pez','jsdaaesus2@gmail.com','2020-08-28','',3);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(19,'Jesus','López','jesadscvxus2@gmail.com','2020-08-28','',4);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(20,'Jedssus','Ló2pez','xcsdajesus2@gmail.com','2020-08-28','',5);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(21,'Jesus','López','jesucvs2@gmail.com','2020-08-28','',6);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(22,'Jesus','López','jescxcvxus2@gmail.com','2020-08-28','',7);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(23,'J3esus','Ló2pez','jescvxdsus2@gmail.com','2020-08-28','',2);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(24,'Jesus','López','sayayin@hmail.com','2020-08-28','',1);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(25,'Jesus','López','jesucxvs2@gmail.com','2020-08-28','',2);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(26,'Jesus','López','elperroperro@gmail.com','2020-08-28','',3);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(27,'Jesuxs','López','salaverga@gmail.com','2020-08-28','',4);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(28,'Jesus','López2','jdsxcvfesus2@gmail.com','2020-08-28','',5);
INSERT INTO clientes(id,nombre,apellido,email,create_at,foto,region_id) values(29,'Jesus2','López','jesdxcvfus2@gmail.com','2020-08-28','',6);






INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) values ('arcadio','$2a$10$v4RgozYXiSyb8ckZb41QDuUorAI8YzFeOqZkyUdL3hKE/e7Fh3PeC',1,'Arcadio','Lopez','arcadio@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) values ('admin','$2a$10$W143tnyFIwDOhaGhPF2p8eK225fmycCIos5cgGWQevh3EMDBXHUYG',1,'Jesus','Lopez','jesus@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) values ('jesus','$2a$10$P7NdGgsEiWdPoCYs7dbOYO62kv9ey6dfddQxue6/E8yPItmGkZQWO',1,'Luis','Lopez','luis@gmail.com');
INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) values ('luis','$2a$10$MPD0VH9TkPNjoZk.nRXgU.4n7UguXIrXrEuYQ8lohs8xXJsDf6LCi',1,'Galilea','Lopez','gali@gmail.com');

INSERT INTO roles (nombre) values ('ROLE_USER');
INSERT INTO roles (nombre) values ('ROLE_ADMIN');

insert into usuario_roles (usuario_id,role_id) values (1,1); 
insert into usuario_roles (usuario_id,role_id) values (2,2);
insert into usuario_roles (usuario_id,role_id) values (2,1);  




