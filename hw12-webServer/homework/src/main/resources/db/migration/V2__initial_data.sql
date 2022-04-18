insert into addresses (id, street) values (1, 'Заводская');
insert into addresses (id, street) values (2, 'Варварка');
insert into addresses (id, street) values (3, 'Авиаторов');


insert into clients (id, name, address_id) values (4, 'Злата', 1);
insert into clients (id, name, address_id) values (5, 'Варвара', 2);
insert into clients (id, name, address_id) values (6, 'Августина', 3);


insert into phones (id, number, client_id) values (7, '123-45-56', 4);
insert into phones (id, number, client_id) values (8, '987-65-43', 5);
insert into phones (id, number, client_id) values (9, '345-67-89', 5);
insert into phones (id, number, client_id) values (10, '111-11-11', 6);

ALTER SEQUENCE hibernate_sequence RESTART WITH 10;