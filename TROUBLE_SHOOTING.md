# Trouble Shooting

## first Trouble

### 문제 상황
- DB의 생성 오류  
*오류 구문*  
```
org.h2.jdbc.JdbcSQLSyntaxErrorException: Table "TODO" not found (this database is empty); SQL statment
```

### 문제 내용 
TODO 테이블을 찾을 수 없음 또한 TODO의 테이블이 비어있음.

#### 생각해보기
서버가 실행될 때, 'create table'구문이 출력된 것을 기억함
    -> 테이블 생성쪽 오류는 아님
    -> 현재 사용중인 DB의 종류가 H2임을 되새김
H2의 특징 중 하나, '휘발성' 즉 테이블이 생성되고 아무런 접촉 및 시도가 없으니 H2가 테이블을 유지하지 않고 제거함.

---

### 해결법
application.properties파일의 H2세팅 부분
```
spring.datasource.url=jdbc:h2:mem:todo_db
```
':DB_CLOSE_DELAY=-1'를 뒤에 추가함

DB_CLOSE_DELAY=-1란?  
'서버의 실행이 완전히 종료되기 전 까지는 데이터를 지우지 말라'라는 뜻을 주며 '-1'이 "무기한 유지"를 뜻하는 값.


### 기억하기
H2의 휘발성은 강하다. 사용하지 않으면 사라진다. 하지만 이것은 코드를 추가함으로써 막을 수 있다.

---

## Second Truble

### 문제 상황
- 문법 오류
*오류 구문*  
```
Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement 
"create table todo (check boolean [*]not null, private_code bigint not null, todo_detail varchar(255), primary key (private_code))"
```

#### 생각해보기
오류 구문에 "Syntax error". 즉, 구문 오류  
테이블 생성 구문의 컬럼 부분에 '[ * ]' 확이
    -> 작성한 적 없는 부분
    -> 저 근처에 오류가 있을것이라 추측

---

### 해결법
웹서핑 결과 SQL에 'check'라는 예약어가 존재함을 확인  
'check' 위에 '@Column(name = "is_checked")'어노테이션을 추가함으로써 SQL에 전달되는 이름을 'is_checked'로 변경함으로 해결

### 기억하기
구문 오류는 "Syntax error"와 함께 오류 구문을 친절히 출력해줌  
오류 부분 근처에 '[ * ]'를 표시함으로써 오류 부분을 더 쉽게 찾을 수 있음

---

## Third Truble

### 문제 상황
- 어노테이션을 불러올 수 없음  

### 문제 내용 
Validation을 import 할 수 없음

#### 생각해보기
@NotBlank가 작성되지 않음
    -> 'import'의 문제인가?
    -> 'import ... Validation'에서 부터 문제 발생
    -> 의존성 추가를 안 했나?

---

### 해결법
build.gradle파일의 'dependencies'파트에 아래 문장 추가
```
implementation 'org.springframework.boot:spring-boot-starter-validation'
```
저게 Validation 의존성을 추가하는 문장

### 기억하기
추가하지 않은 의존성은 구글링과 'build.gradle'파일로 해결 가능하다. 만약 import나 다른 부분에서 문제가 생길 시 한 번 확인해 보자.

---

## Fourth Truble

### 문제 상황
- 종속성 없음  

### 문제 내용 
@RestController, PostMapping, PutMapping... 등등을 사용할 수 없음

#### 생각해보기
@RestController를 사용할 수 없음
    -> import도 되지 않음
    -> 종속성 문제인가?

---

### 해결법
종속성 'Spring web' 추가
```
implementation 'org.springframework.boot:spring-boot-starter-web'
```


### 기억하기
폴더 생성 때 종속성 추가시 'Spring web'은 추가하는 것이 좋을 것 같다