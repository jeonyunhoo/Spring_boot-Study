use spring_prc_db;

create table todo_user_a(
	
    id bigint primary key auto_increment,
    user_id varchar(50) not null unique,
    user_name varchar(50) not null,
    user_password varchar(255) not null
);


create table todo_write_a(

	todo_id bigint primary key auto_Increment,
    todo_detail varchar(255) not null,
    user_id bigint not null,
    
    foreign key (user_id) references todo_user_a(id)
);

select * from todo_user_a;
select * from todo_write_a;