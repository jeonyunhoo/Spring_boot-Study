use spring_prc_db;

create table todo_user_a(
	
    id bigint primary key auto_increment,
    userId varchar(50) not null unique,
    userName varchar(50) not null,
    userPassword varchar(255) not null
);


create table todo_write_a(

	todoId bigint primary key auto_Increment,
    todoDetail varchar(255) not null,
    id bigint not null,
    
    foreign key (id) references todo_user_a(id)
);