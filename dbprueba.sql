create table categoria ( 
  id serial primary key,
  nombre varchar(50) not null unique
);

insert into categoria (nombre) values ('categoria 1');
insert into categoria (nombre) values ('categoria 2');
insert into categoria (nombre) values ('categoria 3');

create table articulo (
  id serial primary key,
  nombre varchar(50) not null unique,
  precio decimal(10,2),
  categoria bigint unsigned references categoria(id)
)
