DROP TABLE IF EXISTS Employee;
create table Employee (id integer auto_increment primary key,
 name varchar(255),
 role varchar(255),
 manager integer
);