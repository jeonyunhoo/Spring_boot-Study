use spring_prc_db;

create table todoUser (

	id bigint primary key auto_increment,
	userId varchar(50) not null unique,
    userName varchar(50) not null,
    userPassword varchar(255) not null
);

desc todoUser;