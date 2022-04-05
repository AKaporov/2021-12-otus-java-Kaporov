-- Для @GeneratedValue(strategy = GenerationType.IDENTITY)
/*
create table client
(
    id   bigserial not null primary key,
    name varchar(50)
);

 */

--drop table if exists client;
--drop table if exists address;
--drop table if exists phone;
--drop sequence if exists  hibernate_sequence;

-- Для @GeneratedValue(strategy = GenerationType.SEQUENCE)
create sequence hibernate_sequence start with 1 increment by 1;

create table addresses
(
    id bigint not null primary key,
    street varchar(50)
);

create table clients
(
    id bigint not null primary key,
    name varchar(50),
    address_id bigint,
    foreign key(address_id) references addresses(id) --on delete cascade
);

create table phones
(
    id bigint not null primary key,
    number varchar(50),
    client_id bigint not null,
    foreign key(client_id) references clients(id)
);