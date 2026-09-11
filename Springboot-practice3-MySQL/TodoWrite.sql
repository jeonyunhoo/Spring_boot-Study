use spring_prc_db; -- 어떤 데이터베이스를 활성화함

create table TodoWrite ( -- 테이블 생성
	
	todoId bigint primary key auto_increment, -- todoId, 기본키 자동 생성(1,2,3...)
    todoDetail varchar(255) not null -- todoDetail, 내용 공백 불가
);

desc TodoWrite;