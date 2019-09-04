/*
 * Script for creating the database BOOKS
 * where the events shall be stored
 */
DROP table BOOKS;
create table BOOKS ( Id VARCHAR(255) PRIMARY KEY ,  name VARCHAR(255) NOT NULL,  price DOUBLE);
insert into BOOKS values(1, 'La chica que vivio dos veces', 21.37);
insert into BOOKS values(2,'El Pintor de Almas',21.75);
insert into BOOKS values(3, 'La cara norte del corazón', 21.75);
insert into BOOKS values(4, 'La red Púrpura', 18.9);
insert into BOOKS values(5, 'Come comida real', 16.15);
insert into BOOKS values(6, 'Cómo hacer que te pasen cosas buenas', 18.9);
insert into BOOKS values(7, 'Sapiens (De Animales a Dioses)', 18.9);
insert into BOOKS values(8,'La voz de tu alma', 21.74);
insert into BOOKS values(9, 'El Poder de confiar en Ti', 16.05);
insert into BOOKS values(10, 'El Poder del ahora', 9.5); 
insert into BOOKS values(11, 'El Hombre en busca de sentido', 12.25);
insert into BOOKS values(12, 'Voces de Chernobil', 11.35);
insert into BOOKS values(13, 'Vadesatu - Guía de Medicación Parenteral', 11.95);
insert into BOOKS values(14, 'El Arte de Pensar', 8);
insert into BOOKS values(15, 'Maneras de Amar', 7.6);
select * from BOOKS;