use spring_prc_db;

create table todoUser4 (

	id bigint primary key auto_increment,
    user_id varchar(50) not null unique,
    user_password varchar(255),
    user_email varchar(255),
    provider varchar(50),
    provider_id varchar(255)
);