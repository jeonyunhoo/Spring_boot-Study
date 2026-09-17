use spring_prc_db;

create table todoUser3 (

	id bigint primary key auto_increment,
    userId varchar(50) not null unique,
    userPassword varchar(255) not null,
    userName varchar(50) not null
);