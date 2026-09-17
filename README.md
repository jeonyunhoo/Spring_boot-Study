# Spring boot study

Docker 세팅, 로그아웃 확인하기

## Spring boot란?

Spring Framework는 자바 기반의 애플리케이션을 만들기 위한 프레임워크이다. 강력하지만 초기 설정(XML 설정, 라이브러리 버전 관리, 서버 배포 설정 등)이 매우 복잡하다는 단점이 있었음. 그리고 이 단점을 해결하기 위해 나온 도구가 Spring boot.

요약: Spring Framework의 귀찮은 초기 설정을 대신 해줄 초간단 도구

## Spring boot의 특징

### 자동 설정
개발자가 어떤 라이브러리를 추가했는지 감지하여 Spring이 필요로 하는 설정을 자동으로 구성해줌
### 내장 서버
별도 웹 서버를 설치하고 배포할 필요 없이 애플리케이션 자체에 **서버가 내장**되어 실행만 하면 바로 웹 서비스가 됨
### 의존성 관리 최소화
spring-boot-starter-web처럼 필요한 기능 단위로 묶인 패키지를 추가하면, 관련된 여러 라이버리를 일일이 버전에 맞춰 넣을 필요가 없음

## IoC 컨테이너와 Bean의 개념

### IoC(Inversion of Control, 제어의 역전)
일반적인 자바 프로그래밍의 경우 개발자가 직접 new 키워드를 통해 객체를 생성하고 객체가 필요한 다른 객체에 직접 생성하여 넣음. 즉 객체 생성과 관리의 제어권이 개발자에게 있음

그럼 IoC는 어떤 역할을 하나, 바로 **IoC는 제어권을 Spring 컨테이너에게 넘김**. 개발자는 객체가 필요하다는 표시를 하면 **Spring이 알아서 객체를 생성하고 필요한 곳에 연결**까지 하여 **생성 부터 소멸까지 알아서 관리**해주는 매우 편리한 기능을 제공함

그리고 이 기능을 관리해 주는 것이 바로 IoC 컨테이너, 애플리케이션(웹)이 시작될 때, Spring은 이 컨테이너를 만들고 객체를 채우기 시작함

### Bean
(IoC와 연결된 내용) 이 때, 컨테이너 내부에서 Spring이 생성하고 관리하는 객체를 Bean이라고 부름. 그리고 어떠한 클래스를 Bean으로 등록하라고 표시하는 방법이 어노테이션(아래에 서술)

### Bean으로 등록시키는 어노테이션
아래 서술한 @ComponenetScan(8번)은 Bean으로 등록해야 할 클래스를 찾음. 이번에는 Spring이 어떤 클래스를 Bean으로 등록해야 하는지 특정하는 어노테이션을 서술함

* @Component: 가장 기본적인 표시이며 "이 클래스는 Spring이 관리하는 Bean이다"라는 뜻

* @Service: 비즈니스 로직(핵심 처리 로직)을 담당하는 클래스에 붙임. 기능적으로는 @Component와 동일하게 동작하지만, "이 클래스의 역할은 서비스의 계층임"라는 의미를 코드만 보고 알 수 있게 해줌

* @Repository: 데이터베이스 접근을 담당하는 클래스에 붙임. 마찬가지로 @Component의 역할을 하며, 추가로 데이터베이스 관련 예외를 Spring이 이해하는 형태로 변환해주는 기능을 포함함

* @Controller: 웹 요청을 받는 클래스에 사용됨

즉, 네 가지 어노테이션 모두 하위 클래스를 Bean으로 등록하라는 같은 기능을 하지만, 각자가 어떤 역할을 맡고 있는지를 이름만으로 구분해주는 것임, 이렇게 역할별로 나누는 이유는, 코드를 읽는 사람이 클래스의 역할을 **한눈에 파악할 수 있게 하기 위함**이며, 이는 대분류 4의 계층형 아키텍처와 직결됨 

### 의존성 주입(DI, Dependency Injection)
DI는 IoC의 구체적인 실현 방법 중 하나

어떤 클래스(A)가 동작하기 위해 다른 클래스(B)의 기능이 필요할 때, "A가 B에 의존한다"고 표현함. 이 때 B 객체를 A가 직접 생성하는 것이 아니라, **외부(Spring 컨테이너)에서 만들어서 A에게 넣어주는 것**을 의존성 주입이라고 함

의존성을 주입하는 방법 3가지
1. 생성자 주입: 클래스의 생성자를 통해 필요한 객체를 전달받는 방식, Spring 공식 문서와 실무에서 가장 권장받는 방식.
    - 객체가 생성되는 시점에 필요한 의존성이 모두 갖춰짐이 보장됨(즉, 필요한 것이 없으면 애초에 객체 생성 자체가 안 됨)
    - 필드를 final로 선언할 수 있어, 한 번 주입된 이후 변경되지 않음이 보장됨
    - 테스트 코드 작성 시 객체를 직접 생성하기 용이함.
2. 필드 주입: 클래스의 필드(맴버 변수에) @Autowired를 직접 붙여서 주입받는 방식. 코드가 짧아보이지만, 객체가 불완전한 상태로 생성될 수 있고 테스트가 어려워 최근에는 권장되지 않음
3. Setter 주입: Setter 메서드를 통해 주입받는 방식. 선택적인 의존성이 있을 때 사용되지만, 자주 쓰이지는 않음

### @Autowired란?
이 자리에 필요한 Bean을 Spring 컨테이너에서 찾아서 자동으로 연결하라는 표시

동작 원리
1. Spring 컨테이너 안에 여러 Bean들이 등록되어 있음
2. 어떤 클래스가 다른 Bean을 필요로 할 때, @Autowired가 붙은 지접(생성자, 필드, Setter)에 타입을 기준으로 컨테이너 안에서 일치하는 Bean을 찾아 연결해줌

## MVC 패턴 개념
MVC는 애플리케이션을 세 가지 역할로 나누는 설계 패턴

* Model: 데이터베이스와 비즈니스 로직을 담당
* View: 사용자에게 보여지는 화면
* Controller: 사용자의 요청을 받아서, 어떤 Model을 사용할지 정하고, 처리 결과를 어떤 View로 보여줄지 연결하는 역할

즉, Controller는 요청을 받는 입구이며, 어떤 Model을 사용하고 어떻게 View로 보여주어야 할 지 전달하는 총괄을 맡음

*주의*  
Spring에서는 Controller가 요청을 받으면, 실제 처리는 Service에게 위임하고, 처리된 결과를 어떻게 응답할지를 결정하는 역할을 함

### @Controller vs @RestController
Controller 클래스에 붙이는 어노테이션은 두 가지가 있으며, 응답 방식이 근본적으로 다름

* @Controller: 메서드가 반환하는 문자열을 "View의 이름(파일명)"으로 해석함. 즉, 해당 이름을 가진 HTML 파일을 찾아 사용자에게 화면을 보여줌. 전통적인 화면(페이지) 기반 웹 개발에 사용
* @RestController: 메서드가 반환하는 값을 View의 이름이 아닌 **순수 데이터**로 취급하여 그대로 응답 본문에 담아 전달. 주로 문자열이나 Json의 형태의 데이터로 반환하며, 자주 쓰이는 어노테이션임(어노테이션 정리 2번에 자세히 서술됨)

요약: Controller는 직관적인 화면 출력, RestController는 데이터 전송

### @RequestMapping, @GetMapping, @PostMapping 등
Controller클래스 안의 메서드가 어떤 주소(URL), 어떤 방식(HTTP Method)의 요청이 왔을 때 실행될 지를 정하는 어노테이션들

HTTP 요청의 대표적인 방식(Method)
* GET: 데이터 조회
* POST: 데이터 생성
* PUT: 데이터 수정
* DELETE: 데이터 삭제

이를 지정하는 방법들
* @RequestMapping: 가장 기본적인 형태로 URL, Method를 함께 지정 가능함
* @Get, Post, Put, Delete(중 하나)Mapping: @RequestMapping에서 Method를 미리 고정해둔 축약형. Get요청을 받고 싶다면 Get을, Put요청을 받고싶다면 Put을 쓰는 아~주 직관적이고 마음에 드는 실무에서 가장 많이 사용되는 방식

*한 번 보기*  
클레스 레벨에 @RequestMapping("/api") 처럼 붙여질 수 있는데, 이 경우 그 클래스 안의 모든 메서드 URL 앞에 /api가 공통적으로 붙게 됩니다.

### @RequestParam, @PathVariable, @RequestBody
사용자가 보낸 요청에는 많은 형태로 데이터가 담겨옴. Controller메서드는 그 데이터를 파라미터로 받아야 함, 허나 데이터가 어떤 형태로 왔는지에 따라 받는 방법이 다름

* @RequestParam: URL 뒤에 ?key = value 형태로 붙는 쿼리 파라미터를받을 때 사용.  
    ex) /search?keyword=spring 에서 keyword를 받고 싶을 때
* @PathVariable: URL 경로 자체에 값이 포함되어있을 때 사용.  
    ex) /users/3에서 3 이라는 값(사용자 ID)을 URL경로의 일부로 받고 싶을 때. URL 매핑 시 {}로 자리표시를 해둠(예: /user/{id})
* @RequestBody: 요청의 본문(body)에서 JSON 형태로 담겨 온 데이터를 받을 때 사용됨. 주로 POST, PUT 처럼 새로운 데이터를 생성 및 수정 할 때, 클라이언트가 JSON 형태의 객체 데이터를 보내면 이를 JAVA 객체로 변환해서 받음.

*세 가지 방법의 차이점 요약*   
데이터가 어디(쿼리스트링/URL경로/본문)에 담겨왔는지에 따라 받는 방법이 달라짐

### ResponseEntity
ResponseEntity는 응답을 좀 더 세밀하게 제어하고 싶을 때 사용되는 객체

단순히 데이터만 반환하면, Spring이 자동으로 HTTP 상태 코드(예: 200OK)를 붙여서 응답함. 하지만 실제 서비스에서는 상황에 따라 드른 상태 코드를 명시적으로 응답해야 할 때가 많음(예: 데이터를 찾지 못하면 404, 새로 생성했으면 201 등)

ResponseEntity를 사용한다면
* 응다 본문에 어떤 데이터를 담을지
* HTTP 상태 코드를 무엇으로 할지
* 필요하다면 응답 헤더까지

이 세 가지를 개발자가 직접 조합하여 응답을 구상할 수 있음. 즉, "이 요청은 정상 처리되었으니 200과 함께 데이터를 줍니다." 혹은 "이 데이터는 없으니 404만 응답합니다."와 같은 **상황별 다른 응답을 만들 수 있게 해주는 도구**

## 서비스/데이터 계층

### 계층형 아키텍쳐의 필요성
지금 배운 Controller는 요청을 받는 입구의 역할을 함. 그런데 만약 이 안에서 DB조회, 비즈니스 규칙 검증, 응답 데이터 가공 등 전부 처리하려고 하면 상당히 복잡하고 머리가 아플 것임

* 코드가 한 곳에 이리저리 뒤섞여 전달력이 부족해지고 역할을 확인하기 어려움
* 위치를 찾거나 역할을 찾기 어려워지니 같은 로직을 다른 곳에서 활용하기 어려워짐
* 테스트하기가 어려워짐

그리하여 있는 것이 Spring의 역할 분담 3계층

* Controller: 요청을 받고 응답을 돌려주는 역할(입구/출구)
* Service: 실제 비즈니스 로직(규칙, 계산, 처리, 흐름 등)을 담당
* Repository: 데이터베이스에 직접 접근하여 데이터를 저장 및 조회하는 역할

흐름
```
사용자 요청 -> Controller -> Service -> Repository -> DB
                                ▽
사용자 응답 <- Controller <- Service <- Repository <- DB
```

정리  
Controller가 Service를 부름, 이후 Service가 Repository를 부름, Repository가 DB를 조회함
위와 같이 딱딱 나누어 자신의 역할에 집중함 이것이 바로 DI가 실전에서 쓰이는 방식

### Service의 역할과 어노테이션
Service는 비즈니스 로직을 담당한다. 구체적으로는 아래 서술
* Controller로부터 전달받은 데이터를 가공하거나 검증
* 여러 Repository를 조합하여 하나의 비즈니스를 완성(동시 실행 작업을 정돈 한다는 뜻)
* 실제 "업무 규칙"을 코드로 구현(예: DB의 domain 범위 조절 등)

*중요 개념 잡기*  
트랜잭션(Transaction): 여러 작업을 하나의 단위로 묶어 전부 성공, 혹은 전부 실패하게 만드는 것

*실무 예시로 감 잡기*  
사용자의 주문에 의해 재고의 개수가 떨어지며 주문이 올라야 함. 이 때 재고의 개수는 떨어졌지만 주문이 올라가지 않는다면 데이터가 불일치하게 됨. 그리하여 Service메서드에는 @Transaction 어노테이션을 사용하여 모든 작업이 성공, 혹은 실패로 남게 하여 만일 하나라도 실패하게 될 경우 전부 실패하도록 합니다.  
(하나라도 실패하면 원래 상태로 복구(rollback)합니다.)

### JPA와 Entity의 개념
JPA(Java Persistence API): 자바 객체와 데이터와 DB의 테이블을 서로 연결(매핑) 해주는 표준 기술

원래라면 DB를 다루기 위해 SQL 쿼리를 작성해야 함으로 상당히 귀찮지만, JPA는 다르다. **자바의 객체(클래스)를 데이터베이스와 매핑시키고 객체를 다루는 것 만으로도 데이터베이스 작업이 가능**하도록 해주는 엄청나게 편한 기술이다.  

이 때 DB의 테이블과 매핑되는 자바의 클래스를 Entity라고 한다.

Entity 클래스 생성 대표 어노테이션
* @Entity: 이 클래스가 DB의 테이블과 매핑되는 Entity임을 선언
* @Id: 이 필드는 테이블의 기본 키(Primary key)임을 알림
* @GeneratedValue: 기본 키 값을 데이터베이스가 자동으로 생성하도록 설정(SQL의 'auto_increment'였나 그거)
* @Column: 특정 필드를 테이블과의 컬럼과 매핑할 때, 컬럼명이나 제약조건을 세부적으로 지정(not null, unique 등, 생략한다면 일반 컬럼이 됨)

*정리*  
**Entity 클래스 하나 당 테이블 하나이고 클래스의 필드 하나하나가 테이블의 컬럼과 대응**한다고 생각하면 편하다.

### Repository 인터페이스(JpaRepository)
Repository의 역할은 DB에 직접 접근 하는 계층 여기서 또 개편한 방식이 하나 있는데 그것이 바로 Spring data JPA를 이용하는 것.  
어떻게 편해지느냐 하면, DB에 접근하는 코드를 직접 작성하지 않아도 된다는 점

방법은 바로 **JpaRepository라는 인터페이스를 상속**받는 것.  
이 인터페이스는 두 가지 정보 타입을 요구함
1. 어떤 Entity를 다룰 것인가
2. 그 Entity의 기본 키 즉 @Id의 타입이 무엇인가

이리하여 상속을 받게 된다면, Spring이 알아서 기본적인 DB작업 메서드를 생성해줌
* 저장하기(save)
* ID로 조회하기(findById)
* 전체 조회하기(findAll)
* 삭제하기(deleteById)

개발자는 인터페이스 선언만 하면 되고, 실제 구현은 Spring data JPA가 내부적으로 해결해주니 이보다 맛도리일 수가 없다. 이는 @Repository가 왜 별도로 필요 없어지는지와도 연결됨  
JpaRepository를 상속 받은 인터페이스는 Spring이 자동으로 Bean으로 인식해 등록해주기 때문이다.

## 데이터베이스와 연동

### 데이터소스 설정
Entity나 Repository 등은 동작하기 위해 어떤 DB에 어떻게 접속하게 할 지 설정해주어야 함. 이 접속 정보를 담는 것을 데이터소스(DataSorce)라고 함.  
주로 쓰이는 데이터베이스는
* H2: 자바에서 만들어진 아주 가벼운 데이터베이스, 별도의 설치 없이 실행될 때 자동적으로 메모리(혹은 파일) 위에 즉석에서 생성되고 종료하게 되면 함께 사라지며 기능이 잘 되는지 확인하는 빠른 테스트할 때 쓰기에 용이함
* MySQL: 실무에서 널리 쓰이는 데이베이스로, 별도의 다운로드가 필요하며 별도의 서버를 띄우고 이에 접속하는 방식을 사용해야 함, 데이터가 영구적으로 저장됨

이에 대한 접속 정보는 application.properties(아마도 IoC부분과 관련 있는 듯)에 작성해야 함.  
설정 항목
* spring.datasource.url: 접속할 데이터베이스와 구조
* spring.datasource.username: 접속 계정
* spring.datasource.password: 접속 비밀번호
* spring.datasource.driver-class-name: 어떤 종류의 데이터베이스에 접속하는지 알려주는 드라이버 정보

### Hibernate의 동작 원리
위에 쓴 것 처럼 JPA는 java의 클래스와 DB를 매핑해주는 표준 같은 것 정도를 배웠는데 그럼 또 생각해 볼 것이 "그럼 이걸 누가 실행해?"를 생각 해 볼 수 있는데, 그러기 위해서 필요한게 구현체.  
JPA는 "너(Java 클래스)랑 너(DB 테이블), 둘이 짝꿍해"라고 시키는 명령이라면 구현체는 이제 그걸 진짜로 시키는 행동이라 생각할 수 있음

그런 구현체들 중 널리 사용되는 것이 "Hibernate". Spring boot에서 JPA 관련 스터디(spring-boot-starter-data-jpa)를 추가하면, 내부적으로 Hibernate가 기본 구현체로 딸려들어옴

Java의 클래스에 @Entity를 붙이고 save()를 호출하면 이 구현체(Hivernate)가 알아서 SQL의 필드값을 분석해서 create table ~이건 insert into ~와 같은 SQL을 생성하여 데이터베이스에 실행해주는 것. 이래서 SQL을 몰라도 개발자는 데이터베이스 관련 개발을 조금이나마 할 수 있느 것.

### ddl-auto
써있지는 않지만 DDL이 아마 SQL의 DDL(정의어), DML(조작어), DCL(제어어)의 DDL이라 감히 생각함  
Entity 클래스를 만든다고 자동으로 DB에 테이블이 생기고 뭣이고 하는게 아님. 그게 되면 그건 마법이지 코딩이 아님, 즉 이 Entity를 어떻게 조리해서 테이블로 만들지 어떻게 처리할지를 설정하는 것이 "spring.jpa.hibernate.ddl-auto".

옵션값으로는
* create: 실행 때 마다 이전에 생성되었던 테이블을 싹 밀어버리고 Entity를 기준으로 새로 만듦(기존 데이터 삭제)
* update: 기존 데이터를 유지하며, Entity에 변경된 내용(새로운 필드든, 필드의 삭제든)을 반영하여 수정함
* validate: 테이블을 생성 및 수정 하지 않고, Entity와 실제 테이블의 구조가 일치하는 확인함(일치하지 않으면 오류)
* none: 아무것도 안함(왜 있는거임?)

학습 단계에서는 create와 update를 사용해 Entity를 만들면 자동으로 테이블에 생기는 것을 확인하는 것이 일반적이며 실무에는 데이터의 손실의 위험성 때문에 validate나 none을 사용함

## 예외 처리와 검증

### @Valid를 이용한 입력 검증
사용자가 API로 데이터를 보낼 때(회원가입의 이메일 등), 항상 그에 옳은 데이터를 보낸다는 보장이 없음.  
그래서 이 잘못된 데이터가 입력되었을 때 Service나 Repository로 넘어가기 전에 Controller 단계에서 미리 걸러내는 작업이 중요함

Spring에서는 이를 위해 두 가지 요소를 함께 사용함

1. Entity(혹은 DTO) 클래스의 필드에 검증된 규칙을 어노테이션으로 선언
    * @NotNull: 값이 null이면 안됨
    * @NotBlank: 문자열이 null이거나 빈 문자열(""), 공백만 있으면 안됨
    * @Size(min= , max= ): 문자열 길이나 리스트의 크기의 범위를 제한
    * @Email: 이메일 형식이여야 함
    * @Min, @Max: 숫자의 최솟값 및 최댓값 제한
2. Controller메서드의 파라미터 앞에 @Valid 붙이기  
@RequestBody로 밭을 때 앞에 @Valid를 붙이면, Spring이 그 객체를 Service로 넘기기 전에 먼저 각 필드에 선언된 검증 규칙을 전부 확인함. 만약 하나라도 규칙을 어기면 Service 코드가 실행되기도 전에 자동으로 예외가 발생하고 요청이 거부됨  
흐름  
```
요청 도착 -> @Valid가 필드별 규칙 검사 -> 통과하면 Service로 전달 -> 위반 시 즉시 예외 발생(Service 실행 안됨)
```

이렇게 한다면 매 순간마다 데이터가 올바르게 작성되었는지 확인하는 로직을 항상 구현 할 필요 없음

### @ExceptionHandler/@ControllerAdvice

그런데 말입니다? 만약에 @Valid에서 검증을 실패하거나, Service/Repository 단에서 예외가 발생하면 어떻게 될까??  
아무런 처리도 하지 않으면, Spring이 기본적으로 매우 불친절한 형태의 오류 응답을 사용자에게 고스란히 보여줍니다!  
이 상황은 보안적 문제로도 사용자의 입장에서도 매우 꼴사나운 모습이죠.  
그래서 그래서 이 문제가 발생하였을 때 우리의 언어로 보기 좋게 변환하여 보여주는 장치가 필요 함

* @ExeptionHandler: 특정 예외 타입이 발생하였을 때 실행될 메서드를 지정하는 어노테이션. 예시 상황으로는 "회원을 찾을 수 없음" 예외가 발생하면 이 어노테이션이 붙은 메서드가 붙잡아서, 404와 가은 상태 코드와 함께 "회원을 찾을 수 없습니다"라는 메시지를 담은 ResponseEntity로 변환하여 응답할 수 있음
* @ControllerAdvice: 개별 Controller클래스마다 예외 처리 코드를 반복해서 넣은 대신, 애플리케이션 전체에서 발생하는 예외를 한 곳으로 공통으로 처리할 수 있게 해주는 어노테이션. 이 어노테이션이 붙은 클래스 안에 @ExeptionHandler가 붙은 메서드들을 모아두면, 어떤 Controller에서 예외가 발생하든 이 클래스가 가져와서 해결함

**정리**  
@ExeptionHandler: 예외 발생 시 어떻게 처리할 지 알려주는 개별 규칙  
@ControllerAdvice: 그 규칙들이 애플리케이션 전역에서 공통으로 적용되게 모아두는 컨테이너

## 빌드와 실행

### 내장 톰캣의 개념
Spring boot의 특징인 '내장 서버'의 구체화

원래의 Java 애플리케이션은 모두 웹 서버(WAS, Web Application Server) 위에서 작동함. 대표적인 것으로는 톰캣(Tomcat)이 있음. 전통적인 방식으로는 개발자가 톰캣을 따로 설치하고, 자신이 만든 애플리케이션을 톰캣 위에 배포 하는 형식의 길고 지루한 과정이 필요했음.

하지만, 우리의 Spring Boot는 이 과정을 확 뒤엎어버림. spring-boot-starter-web을 의존성에 추가하면, 톰캣 자체가 라이브러리 형태로 프로젝트 안에 포함되게 됨. 즉 별도의 다운 없이 애플리케이션을 시작하는 순간 내부 톰캣이 켜지며 곧바로 웹 서버의 역할을 하는 초 간편 시스템

This 내장 톰캣 이라고 함. 그냥 main 메서드를 시작하는 것 만드로도 서버가 딸깍으로 켜지는 이유가 바로 이것 덕분임. 이 때 열리는 포트가 server.port 설정값(기본값 8080)

### jar 빌드와 실행
지금까지 해온 방법들은 컴퓨터 내부에서 실행하는 것 뿐임. 하지만 실제 다른 컴퓨터(서버)에 배포하기 위해서는 이 프로젝트를 하나의 실행 가능한 파일로 묶어야 함

그럼 나오는게 무엇이냐? 하면 바로 위에서 배웠던 Gradle. 저 도구의 빌드 기능을 사용하면, 작성한 모든 자바 코드, 라이브러리, 설정 파일이 실행 가능한 '.jar'파일로 압축됨

요로코롬 만들어진 jar 파일의 특징
* 내장 톰캣도 함께 압축이 되어 이 jar 파일 하나만 있어도 어디서든 서버를 열 수 있음.
* java -jar 파일이름.jar의 명령어의 형태로 실행함
* 이 jar 파일을 실제 운영 서버에 옮겨서 실행하면 그게 바로 배포임.

**흐름**  
```
소스 코드 작성 -> Grable로 빌드(jar 생성) -> 서버로 jar 전달 ->  java -jar 로 실행 -> 서비스 시작
```

### 프로파일(Profile)의 개념
이게 개발을 하다 보면 테스트도 해봐야 하고, 배포도 해야 하는데. 배포해서 실사용 되는 환경이랑, 테스트 하는 환경이 다를 수 있기 때문에 이 프로파일이 필요함

대표적인 환경으로는
* 개발자의 컴퓨터에서 테스트 할 때
* 실제 사용자에게 서비스할 때

'그럼 테스트가 끝나고 배포할 때 코드를 수정해야 하나요?'라는 생각이 드는데, 이거이거 그럴꺼면 때려칩니다. 바로바로 이 프로파일을 이용하는 것인데
* application-dev.properties, application-prod.properties처럼 환경별로 별도의 설정 파일을 만들어둠
* 애플리케이션 실행 시 spring.profiles.active=dev 처럼 어떤 프로파일을 이용할지 지정하면 환경에 맞는 설정 파일이 적용됨
* 기본 application.properties에는 모든 환경에서 공통으로 쓰이는 설정을, 각 프로파일 파일에는 환경별로 다른 설정만 작성하는 방식이 일반적임  
이렇게 나누니 배포할 때나 테스트 할 때나 등등 코드를 바꾸지 않고 설정값을 지정해 줌으로써 편리하고 유연하게 바꿀 수 있음


## 세션과 토큰

### 세션(Session) 구현
세션(Session)이란 Spring(그리고 자바 웹 전반)에서는 세션을 다룰 때 'HttpSession'이라는 객체를 사용함. 이건 마치 "서버가 각 사용자마다 하나씩 나눠주는 서랍"과도 같은 것.

* 방식
    - 사용자가 최초로 접속하면 서버는 사용자의 전용 서랍(Session)을 하나 만듦
    - Controller 메서드에서 'HttpSession session'을 매개 변수로 받으면, 지금 이 요청을 보낸 사용자의 서랍을 그대로 건네받을 수 있음.
    - session.setAttribute("key", value)로 그 서람의 데이터를 넣어둘 수 있고 session.getAttribute("key")로 다시 데이터를 꺼내 쓸 수 있음
    - 클라이언트(브라우저/Postman)는 이 서람을 열 수 있는 열쇠(세션 쿠키, JSESSIONID)를 자동으로 받아서, 다음 요청부터 계속 그 열괴를 함께 보냄. 그로 인해 서버는 이 사용자가 어떤 서랍의 주인임을 알아 볼 수 있음.

하지만 이 세션 방식은 단점이 명확하게 존재함  
서버가 여러 대로 늘어나면, 세션 정보를 서버들 끼리 공유해야 하는 번거로움이 생길 수 있다는 것  

그래서 사용되는 방식이 **토큰(Token)**방식 하지만 이건 지금 쓸건 아니기에 다음에 설명하도록 하겠음.

---

### 토큰(Token) 구현
위에 설명한 세션(Session)과 비슷하면서 확연히 다른 방식인 토큰 아래서 실습도 할 것 이지만 설명 정도는 필요해 보임.

세션의 구조가
```
로그인 성공 → 서버가 세션 서랍(HttpSession)에 정보 저장 → 클라이언트는 열쇠(JSESSIONID)만 들고 있음
```
이렇게 생겼다고 치면

토큰의 구조는
```
로그인 성공 → 서버가 "이 사람은 인증됐다"는 정보 자체를 토큰 안에 담아서 클라이언트에게 통째로 줌 → 서버는 아무것도 안 남김
```
이런 식임. 좀 길어서 읽기 싫을 테니 간단하게 요약하면

**세션**은 서버가 사용자를 기억하는 것.  
**토큰**은 사용자가 나를 증명하는 것.  
정도로 생각하면 될 것 같음

그럼 어떻게 서버가 사용자를 기억하고 유지하나? 생각 할 수 있음.  
토큰의 구조부터 보자면
```
헤더.내용.서명
```
이렇게 **.**으로 연결된 세 가지 부분으로 구성되어 있는데  
- 헤더(Header): "이건 JWT이고, 이런 암호화 방식을 썼다"는 메타정보  
- 내용(Payload): 우리가 담고 싶은 실제 정보 (예: userId, 토큰 만료 시각 등) — 이건 암호화된 게 아니라 그냥 인코딩만 된 것이라, 사실 누구나 내용을 볼 수 있습니다(비밀정보는 담으면 안 됨).  
- 서명(Signature): 서버만 알고 있는 **비밀 키(secret key)**를 이용해서, "헤더+내용"이 위조되지 않았음을 증명하는 부분  

그럼 클라이언트는 사용자에게 어떻게 토큰을 보내는가?
```
Authorization: Bearer 토큰
```
관례적으로 이런 형식이다. 'Authorization'라는 이름의 헤더에, 'Bearer '(뒤에 공백 한 칸)

---

## 어노테이션 정리
* 필수적인 것 부터 천천히 추가할 것
1. @SpringbootApplication
    - 위치: 프로젝트를 처음 켤 때 실행하는 메인 클래스(ex ~Application.java)의 위
    - 역할: 프로젝트의 시작점을 알리는 총괄 어노테이션
    - 특징: 내부에 컴포넌트 스캔 기능을 포함하고 있어 앱이 켜질 때 주위를 훑어 하위 클래스 및 패키지를 실행할 수 있게 하고 @RestController 등의 어노테이션을 찾아 등록시키는 역할을 함, @Configuration, @EnableAutoConfiguration, @ComponentScan이 합쳐진 형태
2. @RestController
    - 위치: 웹 요청을 받아 처리하는 클래스의 위
    - 역할: 응답을 받은 데이터를 순수 데이터(JSON, 텍스트 등)으로 전달하는 공간임을 선언함
    - 특징: @Controller와 @ResponseBody가 합쳐진 형태이며, 데이터를 다루는 컨트롤러를 만들 때 기본으로 붙임
3. @GetMapping
    - 위치: 컨트롤러 내부의 개별 메서드 위
    - 역할: 누군가 특정 주소(/주소)로 조회 요청을 보내면 그에 걸맞는 주소를 가진 어노테이션이 아래 있는 메서드를 실행하도록 하는 표지판의 역할을 함
    - 특징: 브라우저 주소창에 치고 들어오는 모든 조회 요청을 받아내는 기본적인 문고리 역할을 함
4. @Autowired
    - 위치: 필드, 생성자, 혹은 수정자 위(변수나 메서드 앞)
    - 역할: 클래스 내부에서 필요한 다른 부품(Service, Repository 등)을 조리하여 가져오게 함
    - 특징: 개발자가 직접 new 키워드로 객체를 만들지 않아도, 스프링이 미리 만들어둔 부품을 알아서 연결해줌
5. @PathVariable
    - 위치: 메서드의 파라미터 앞
    - 역할: 사용자가 주소의 빈칸 자리에 실제로 무언가 적어 보냈을 때 스프링에게 주소 빈 칸에 들어온 값을 받아 자바 변수에 집어넣으라 지시하는 어노테이션(URL 경로에 들어간 값을 파라미터 변수로 바인딩할 때 사용)
    - 특징: ..
6. @Configuration
    - 위치: @SpringbootApplication으로 인해 잘 사용되지 않음
    - 역할: 이 클래스가 설정 정보를 담는 클래스임을 알림
    - 특징: ..
7. @EnableAutoConfiuration
    - 위치: @SpringbootApplication으로 인해 잘 사용되지 않음
    - 역할: 자동 기능을 활성화 함
    - 특징: ..
8. @ComponentScan
    - 위치: @SpringbootApplication으로 인해 잘 사용되지 않음
    - 역할: 이 클래스가 위치한 패키지의 하위의 모든 클래스를 스캔해서, Spring이 관리해야 할 객체(Bean)들을 찾아냄
    - 특징: ..
9. @Entity
    - 위치: DB와 매핑될 클래스의 위
    - 역할: 이 클래스가 DB와 매핑됨을 알림
    - 특징: 내 마음에 쏙 듦
10. @Id
    - 위치: DB와 연결된 클래스의 기본 키로 설정 될 필드를 지정
    - 역할: 기본 키(Primary key) 지정
    - 특징: ..
11. @Column
    - 위치: DB와 연결된 클래스의 컬럼을 설정하는 부분(사실 잘 모름, 수정 필요)
    - 역할: 컬럼 설정. 이게 없으면 기본 세팅됨(아무 제약 조건 없음)
    - 특징: 이름이 아주 직관적임
12. @GeneratedValue
    - 위치: 자동으로 생성되었으면 하는 기본 키의 위
    - 역할: 기본 키를 중복되지 않도록 자동으로 만들어줌
    - 특징: SQL의 auto_incerment과 아주 유사함
13. @Transactional
    - 위치: Service랑 관계 있는데 사실 잘 모름(수정 필요)
    - 역할: 트랜잭션, 연계되는 작업을 하나의 작업 단위로 만들어 모두 실패 혹은 모두 성공하도록 함
    - 특징: 이름이 아주 직관적임
14. @Valid
    - 위치: ..
    - 역할: 예외 캐치
    - 특징: ..
15. @ExeptionHandler
    - 위치: 메서드 위(정확하게 모름)
    - 역할: 예외 발생 시 어떻게 처리할 지 알려주는 개별 규칙
    - 특징: ..
16. @ControllerAdvice
    - 위치: 클래스의 위(정확하게 모름)
    - 역할: 규칙들이 애플리케이션 전역에서 공통으로 적용되게 모아두는 컨테이너
    - 특징: ..

## 기억나는 것들만 쏙쏙 쓰기(생각 날 때마다 쓰기)

### 1차 2026.08.15
Spring boot는 Spring Framework를 사용하기 편하게 만든 도구이다.  
어노테이션으로 클래스 혹은 필드를 지정(영역 선언) 하여 용도와 역할을 깔끔하게 분리하며 어노테이션들마다의 기능이 따로따로이다.  
JPA, 지금까지 배운 것들 중 가장 마음에 드는 것이다. DB와 연결하기 위해 하나하나 로직을 구현하는 것이 아닌 어노테이션으로 DB와 연결하여 편리한 기능을 활용할 수 있다. @Id는 테이블의 기본 키를 지정하는 것이고, @Column은 이름처럼 컬럼을 지정하는 것이다. @Column이 없다면 아무 제약이 없는 기본필드가 생성된다. @Transaction, 이건 프로그래밍기능사 연습할 때 봤던 기억이 있어서 생각보다 쉽게 외웠음. 여러개의 연계되는 작업을 하나의 작업단위로 맞추어 내부의 작업이 모두 실패, 혹은 성공 하도록 한다. 만일 하나의 작업이라도 실패하게 된다면 모든 작업을 실패한 것으로 복구한다. 이는 Rollback의 개념과 같다.  
너무 늦게 나온 감이 있지만 IoC, Bean, 그리고 뭐가 있던 것 같은데 다시 읽어봐야겠다. 사실 뭔지도 잘 기억 안 난다.  
그리고 아까 설명한 JPA에 대하여 추가로 기억난 것이있다. @Entity인데 이것의 위치는 Java의 클래스를 DB의 클래스와 매핑? 하는 것이다. 하나의 @Entity당 하나의 테이블이다.  
그리고 순수 데이터 전송 공간을 선언하는 어노테이션이 있었는데 아마도 @RestController였던 것으로 기억난다. 이건 @Responsabody? 이것과 @Controller가 합쳐진 것이고 @Controller와 비교하였을 때 @Controller는 사용자로 하여금 데이터를 받았을 떄 그것을 이름 자체로 기억을 하여 그에 맞는 HTML과 연결한다는 식으로 기억을 하고 있고 @RestController는 전달 받은 데이터를 순수한 데이터(텍스트나 JSON)로 받아들여 다른 하위 어노테이션이 받도록 영역을 선언하는 느낌이었던 것 같다.
#### 틀린 부분 다시 집기
(순서는 아주 뒤죽박죽)  
1. @Transactiona이 아닌 @Transactional이다.
2. 258번 줄 'DB의 클래스와 매핑?'이 아닌 'DB의 테이블과 매핑'이다. 좀 멍청한 실수....
3. @RestController 부분 '@Responsabody?'가 아닌 '@ResponseBody' 영어 실력이 문제인 듯 하다
4. 가장 큰 문제 IoC, Bean 부분 추가 설명
    * IoC(Inversion of Control, 제어의 역전): 일반적인 Java는 new 치고 뭐 하고 힘들게 객체를 생성하여 개발자가 제어권을 갖지만 IoC는 제어권을 Spring에게 넘겨 개발자가 객체가 필요하다는 표시를 하면 Spring이 알아서 객체를 생성하고 필요한 곳 까지 연결함, 즉 생성부터 소멸까지 알아서 관리해줌
    * Bean: 컨테이너 내부 Spring이 생성&관리 하는 객체. 이놈이 어떤 클래스를 어떤 Bean으로 등록할 지 표시하는 어노테이션이 존재함(Bean으로 등록시키는 어노테이션 부분 30번 줄)

### 2차 2026. 08. 31
Spring boot는 Spring framework를 편하게 사용하기 위한 초간단 도구, Spring boot 자체에 내장 서버가 들어있어 외부 프로그램의 도움 없이 바로 서버 구축이 가능함. IoC(제어의 역전), Bean이라는 것이 있는데 IoC는 원래 개발자가 하나하나 객체를 생성하여 관리하지 않고 Spring이 하도록 넘김. 즉 IoC는 Spring으로 객체를 넘겨 생성부터 소멸까지 모든 것을 자동화 시킴. 여기서 빠질 수 없는 것이 Bean인데 IoC가 넘겨 Spring이 관리하는 객체를 Bean이라고 함, 그런데 여기까지 밖에 모르겠음.  
그리고 뭐 의존성 설정 이런 것도 있었고, JPA라는 것도 있었는데 아마도 JPA가 DB에 보다 편하게 접근? 하거나 개발 자체에서 DB를 생성할 수 있도록 할 수 있게 함, 그리고 Spring Boot는 '@~'모양으로 생긴 어노테이션이라는 걸로 역할을 확인하는 표지판 역할을 하는 친구가 있는데, DB와 매핑하는 @Entity어노테이션, 그리고 각종 제약 조건을 달아주는 '@Column', '@Email' 등등 필드에 작성하는 부분들도 존재한다.  
참고로 하나의 '@Entity'가 달린 클래스 하나 당 하나의 클래스이고 내부의 맴버변수? 하나당 하나의 필드이다.(@Id가 기본 키 였던 것 같다.)  
사실 안 한지 오래되어서 기억이 나는게 별로 없다. 트렌잭션도 있고, @Springboot 뭐시기 해서 @RestController 읽어들여 공간 만드는 어노테이션도 있었고 아 프로파일(profile)이 있다.  
프로파일(profile)이란 개발자가 테스트하는 공간, 그리고 실제 배포되어 실행되는 공간은 다른데 항상 개발자 환경에서 테스터 하다가 배포 전에 코드를 다시 하나하나 사용자의 환경에 맞추어 고쳐 쓰기에는 시간이 아깝기도 하고 인간인지라 모든 것을 고치지 못해 오류를 일으킬 수도 있다. 그래서 이 프로파일이라는 것으로 환경마다 다른 세팅을 저장해 두어 개발자가 테스트 할 때는 이 프로필로 배포 할 때는 이 프로필로 설정하여 시간을 절약하고 오류를 줄일 수 있다. 배포 이야기가 나와서 생각이 난 건데 아 진짜 기억이 애매하게 난다. 어떤 압축 방법을 통해 '.jpa'파일로 묶으면 모든 내용을 저장하여 이 파일 하나만 있으면 어디서든 서버를 열 수 있고 그 파일을 실제 서버에 올리면 그게 배포가 될 수도 있다.  
음.... 잔잔바리 기억나는 것 다 쓰자 하면  
IoC가 던져주고 Spring이 관리하는 부분은 IoC 컨테이너  
의존성은 getter setter 썼던 것 같고  
예외 검증 으로 @Varchar? 확실히 저건 아닌데 아무튼 저런게 있었다.  
그리고 저기서 검증을 못 하면 다른 어노테이션에서 또 걸러지는 뭐가 있어서 거기에서 사용자가 오류를 보기 좋게 표시하고 보안을 유지? 보안을 지키는(?) 그런 것도 있었으며
JpaRepository 인터페이스에서 상속 받는 뭐 그런 것도 있다.  
더이상 기억이 나지 않는다
#### 틀린 부분 다시 집기
(순서는 뒤죽박죽)  
1. 압축 방법을 통해 파일록 묶고 뭐시기(397번 줄), '.jpa'파일이 아닌 '.jar'파일로 묶이게 됨, 또한 묶는 방법? 묶는 도구를 Gradle이라고 하며 Spring Boot에 추가되어 있음(279번 줄)
2. 의존성에 getter/setter는 관계 없음 직접적인 동일 개념이 아니고 과거에 setter 방법을 사용했었음
3. @SpringbootApplication 아까 '@Springboot 어쩌고' 이랬던 부분, 이건 컴포넌트 스캔으로 @Restcontroller, @Service, @Repositoty 등의 어노테이션이 붙은 클래스들을 자동으로 찾아 Bean으로 등록시킴
4. @Varchar 애초에 존재하지 않은 어노테이션임, @Valid가 기억이 안 났을 뿐임
    - 부가 설명: 값의 유효성을 검증하는 어노테이션으로는 @Size, @NotNull, @Email 등등 존재

---


## 실습 단계1 - H2 Database
*폴더명*: Springboot-practice2
![@SpringBootApplication 실제 모습 및 위치](images/image.png)  
@SpringBootApplication의 위치  
'프로젝트이름Application.java' 파일에 위치함(프로젝트 이름에 '-'이 들어갔지만 지워진 형태로 써져있음)  

이론 때 썼던 spring-boot-starter-web은 업데이트를 통해 spring-boot-starter-webmvc로 이름이 바뀜

---

![추가한 의존성의 모습](images/image-1.png)  
Spring boot를 생성할 때 추가한 의존성들

---
![연습용 Entity 클래스](images/image-2.png)  
클래스 위에 @Entity 어노테이션을 달아 이 클래스가 DB의 테이블임을 알림

@Id, @GeneratedValue, @Column등의 어노테이션을 사용해봄(각각의 역할은 위에 정리)  
빈 생성자는 Spring/JPA(JSON 변환 라이브러리) 같은 프레임워크가 리플렉션을 통해 객체를 동적으로 생성 할 때 필요로 함

![Getter/Setter](images/image-3.png)  
값을 받기 위해 Getter/Setter를 사용함

---

![repository, interface](images/image-4.png)  
interface로 만든 이유: Spring Data JPA는 애플리케이션이 실행될 때, JpaRepository를 상속받은 인터페이스를 발견하면 인터페이스를 구현할 실제 객체(프록시)를 자동으로 만들어 Spring의 Bean으로 등록함.  
내용이 비어있는 이유는 이런 형식으로 설계도(interface)를 작성해두면 Spring이 대신 만들어 주는 구조

왜 'long'의 래퍼 클래스 'Long'을 썼는가, Java의 제네릭(<>)은 내부적으로 객체만 받아냄, 하지만 'long', 'int', 'boolean'과 같은 기본형은 객체가 아니기에 기본형을 객체 처럼 보이도록 하는 래퍼 클리스를 사용합니다.  

래퍼 클래스의 종류
```
기본 타입    래퍼 클래스
byte        Byte
char        Character
int         integer
float       Float
double      Double
boolean     Boolean
long        Long
short       Short
```

---

![TodoService](images/image-5.png)  
@Service로 비즈니스 로직을 담당하는 영역을 선언함  
Service로 넘기기 위한 todoRepository와 생성자

---

![Todo.java의 값을 저장소로 옮기기](images/image-6.png)  
public void save(Todo todo)의 Todo todo는 Todo의 형의 todo 변수에 Todo.java에서 받은 값을 불러옴(privateCode, todoDetail, check 한 번에 불러옴. 즉 받은 모든 값을 가져옴)  
todoRepository.save(todo): '~.save()'는 괄호 안에 들어있는 변수의 값을 지정한 레파지토리에 저장하는 명령어. todo에 Todo.java에서 받아온 필드값들을 todoRepository에 담는 작업

![todoRepository에 담긴 값을 조회](images/image-7.png)  
List<> 반환  
list 형식으로 todoRepository에 담긴 값을 호출하여 반환함

*궁금증*  
이 'TodoService.java'파일에서는 'Todo.java'의 필드값을 가져와서 todoRepository에 담을 뿐이고, todoRepository에 저장된 값을 가져와서 보여줄 뿐인데 왜 굳이 따로 파일을 만들어야 하나?  
*답변*  
일단 파일을 합치는 것은 가능은 함, 하지만 굳이 그렇게 하지 않는 것에는 다 이유가 있음.  
1. TodoRepository는 interface이고 코드를 작성하지 않음. 왜냐? Spring이 알아서 구현체를 만들어주는 자리이기에 여기에 비즈니스 규칙을 작성하면 구조 자체가 깨져버리기 때문이며, Repository는 DB와 대화하는 것으로 한정됨.
2. 왜 Todo.java(Entity)에 넣으면 안되는가? 솔찍히 조금만 생각해보면 쉬움. 애초에 Entity로 만든 파일 즉 데이터의 모양과 테이블의 형식을 작성하는 자리에 저장 및 조회 로직을 추가하면 가독성이 떨어짐.

---

Controller 작성  
![TodoController, @Restcontroller](images/image-8.png)  
@RestController로 순수 데이터들이 이동하는 장소임을 정함  

![생성자, TodoService](images/image-9.png)  
TodoService의 기능을 가져다 쓰기 위해 생성자를 만듦  
생성자를 만든 이유는 TodoService에서 생성자를 만든 이유와 동일함

![Post/GetMapping](images/image-10.png)  
TodoServise의 saveTodo, getAllTodos의 기능을 모두 가져옮  
사용자로 하여금 Post, /todos의 요청이 오면 createTodo를, Get, /todos의 요청이 오면 getTodo를 실행시켜 각각의 결과를 보임

---

![application.properties](images/image-11.png)  
spring.datasource.url=jdbc:h2:mem:todo_db: 테스트용 데이터베이스 이름  
spring.jpa.hibernate.ddl-auto=create: 테스트를 위해 애플리케이션을 실행 할 때 마다 테이블을 새로 갈아엎는 것(ddl_auto의 create, update, validata, none 중 create)  

spring.h2.console.enabled=true: 편의성 기능, H2를 직접 볼 수 있는 화면(콘솔)을 웹 브라우저로 제공하는 기능(직접 DB를 볼 수 있음)  
spring.jpa.show-sql=true: 편의성 기능, Hibernate가 내부적으로 어떤 SQL을 생성하는지 콘솔 로그에 그대로 출력하여 보여줌  

실무에서는 편의성 두 가지는 제거하고 배포해야 함  
spring.h2.console.enabled=true 이유: 실무에서는 H2가 아닌 MySQL같은 것을 쓰기에 H2콘솔 자체가 아무 의미가 없어짐. 또한 외부에서 /h2-console에 접근하여 실제 사용자가 데이터를 볼 수 있는 통로가 열리는 것과 같음.  
spring.jpa.hibernate.ddl-auto=create 이유: 보안보다는 성능에서 문제가 생기는데, 로그의 양과, 아주 미세하지만 SQL의 로그로 남기는데에도 성능을 갉아먹음

---

![실행](images/image-12.png)  
Tomcat started on port 8080: 내장 톰캣이 정상 실행됨  
H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:todo_db': 방금 설정한 H2 콘솔과 데이터베이스 이름(todo_db)가 반영되어 켜짐  
Started SpringbootPractice2Application in 3.728 seconds → 애플리케이션이 완전히 실행 완료됐다는 최종 확인 메시지

---

![postman](images/image-13.png)  
데이터를 보내고 저장을 테스트 하기 위한 세팅  
POST로 데이터를 보내고  
'http://localhost:8080/todos' 이전에 만들어두었던 서버 주소와, "/todos"의 주소에 POST 신호를 보내 createTodo를 실행시킴  

---

문제 상황 발생  
![문제 상황](images/image-14.png)  
POST로 데이터를 전송했지만, DB가 생성되지 않아 저장할 공간이 생기지 않음.  
(이미지의 "org.h2.jdbc...(중략).. Table "TODO" not found ~"부분에 TODO테이블을 찾을 수 없다고 명확하게 쓰여있음) 

일전에 서버가 구축될 때 'created table...'문구가 있었음, 하지만 사용하려고 했을 때 찾을 수 없다고 하는 것, 이건 H2의 특성인 '휘발성'을 고려해 볼 수 있음.  
H2의 특성상 생성 및 사용 후 연결이 끊겼을 때 데이터를 유지하지 않고 지움.  
그로 인해 테이블이 지워졌다고 가정.

![application.properties, 추가](images/image-15.png)  
spring.datasource.url=jdbc:h2:mem:todo_db에 ':DB_CLOSE_DELAY=-1'을 추가함.  

DB_CLOSE_DELAY=-1란?  
'서버의 실행이 완전히 종료되기 전 까지는 데이터를 지우지 말라'라는 뜻을 주며 '-1'이 "무기한 유지"를 뜻하는 값.

하지만, 저 구문을 추가했음에도 계속하여 오류가 발생함  
![check 오류](images/image-16.png)  
위 이미지의 마지막 구문
```
Caused by: org.h2.jdbc.JdbcSQLSyntaxErrorException: Syntax error in SQL statement 
"create table todo (check boolean [*]not null, private_code bigint not null, todo_detail varchar(255), primary key (private_code))"
```
저 부분에 'Syntax error' 즉 구문 오류가 있다는 뜻, 아래 친절히 문제 내용을 보여주니 읽어보자.  
잘 써지다가 'check boolean [ * ]...'어라? [ * ]라는건 내가 쓴 적이 없는데 표시된걸 보아하니 저 언저리에서 멈춤 것을 보니 저 부분쪽이 문제겠구나 싶어 웹 서핑을 한 결과,  
"check: 테이블의 컬럼에 입력될 수 있는 값의 범위나 조건을 제한하는 도메인 무결성 도구"  
이미 SQL에 존재하는 예약어를 내가 이름으로 사용해서 생긴 문제이구나 임을 확인함

![cheak 어노테이션 추가](images/image-17.png)  
@Column(name = "is_checked")라는 어노테이션을 추가함, 딱 봐도 알 수 있듯이 컬럼 이름을 "is_checked"로 설정함

---
  
![get결과 postman 터미널](images/image-18.png)
![get결과 intellij 터미널](images/image-19.png)    
POST로 데이터를 보낸 후, GET 요청을 보냈을 때 POST로 보낸 데이터가 순수 데이터(JSON)으로 돌아온 것을 확인

잘 보면 intellij 터미널 이미지에서 'insert into todo...'처럼 SQL 문장이 써져있는 것을 볼 수 있으며 이것이 POST의 기능이고 Spring boot(Hibernate)가 만들어준 자동 SQL 구문임을 알 수 있다.  
또한 'select t1_0.private_code...'처럼 조회하는 SQL문까지 GET으로 가져온 것을 확인할 수 있다.  

*POST 흐름*
```
Postman으로 Post 요청 전송
    -> createTodo(@RequestBody Todo todo)
    -> todoService.saveTodo(todo)
    -> public void saveTodo(Todo todo) (JpaRepository의 기본 기능 '.save')
```
위 과정을 통해 POST 요청이 들어가고 결국 'Hibernate'에게 신호가 가며 '.save'를 보고는 저장함을 판단하고 insert문을 생성 및 실행함  

*GET 흐름*  
```
Postman으로 Get 요청 전송
    -> getTodo()
    -> todoService.getAllTodos()
    -> return todoRepository.findAll() (JpaRepository의 기본 기능 '.findAll')
    -> getTodo()로 돌아와 결과값 반환(return)
```
위 과정을 통해 GET 요청이 들어가고 결국 'Hibernate'에게 신호가 가며 'findAll'을 보고 조회를 판단하고 select문을 생성 및 실행하고 값을 반환함  

---

Update 생성  

![todoService, Update](images/image-20.png)  
todoService에 updateTodo를 만들어 수정이 가능하도록 만듦.  
'Long id'로 id(Todo테이블의 id)를 받아오고 'Todo changeThing'으로 변경할 내용, check나 todoDetail을 받아옴  

마지막으로 todoRepository.save()를 쓰며 받아온 수정 내용을 DB에 저장함

![todoController, Update](images/image-21.png)  
@PutMapping으로 수정하는 메서드임을 알림.  
또한 id의 앞에 @PathVariable을 이용하여 주소의 일부로 사용함(99번줄에 자세히 설명)  
todoService.updateTodo()를 불러내며 값을 수정하도록 함

---

Delete 생성

![todoService, Delete](images/image-22.png)  
todoService에 deleteTodo를 만들어 삭제가 가능하도록 함.  
Update와는 다르게 Long id로 바로 id를 받아와 '.deleteById'를 이용하여 바로 제거함  

![todoController, Delete](images/image-23.png)  
@DeleteMapping으로 제거하는 메서드임을 알림.  
또한 id의 앞에 @PathVariable을 이용하여 주소의 일부로 사용함.  
todoService.deleteTodo()를 불러내 데이터를 제거함  

---

CRUB 테스트  

### POST

![로컬 호스트 서버 실행](images/image-24.png)  
로컬 호스트 서버 실행

![Post, insert](images/image-25.png)  
Post을 호출하여 'insert'구문을 실행함  

![Post, 보낸 데이터](images/image-26.png)  
Post으로 전송한 데이터

### GET

![Get, select](images/image-27.png)  
Get을 호출하여 'select'구문을 실행함

![Get, 저장된 값](images/image-28.png)  
Get으로 확인한 저장된 데이터

### UPDATE(PUT)

![Update, update](images/image-29.png)  
Update를 호출하여 값을 수정함

![수정된 값](images/image-30.png)  
구문이 'CRUB TEST'에서 'CRUB TEST 수정'으로 변경되었으며 check가 false로 변경됨을 알 수 있음.

### DELETE

![Delete](images/image-31.png)  
특정 주소에 있는 값을 지우도록 함

![공백](images/image-32.png)  
값을 삭제하여 남아있는 것이 없음을 확인함

---

### 예외처리(PUT, 존재하지 않는 아이디 요청)

존재하지 않는 값 수정을 요청  
![PUT, 존재하지 않는 값](images/image-33.png)  

당연하게도 오류가 남  
![intellij, 터미널](images/image-34.png)  
'No value present' 이미지에도 나오듯 존재하지 않는 값이라 에러가 났다고 알림

![Postman, 에러](images/image-35.png)  
```
"status": 500,
"error": "Internal Server Error"
```
아주 잘 에러가 나 준 모습이다.  

그럼 이제 예외 처리를 만들어 줄 것인데,  
![TodoNotFoundException](images/image-36.png)  
간단하게 써 준다.  
"에엥? 갑자기 니가 쓴 적도 없는 'RuntimeException'을 상속 받고 'super(message)'를 쓰냐?"  
라고 생각 할 수 있는데 이 'RuntimeException'은 자바에서 이미 만들어진 예외를 표현하는 클래스들 중 하나이다. 아무튼간에 얘는 뭐 하는 애 인가? 궁금해한다면  
RuntimeException은 프로그램 실행 줄 발생하는 일반적인 오류를 표현하는 대표적인 클래스임.  
(NullPointerException, NoSuchElementException과 같은 실습 과정에서 본 그것들이 거의 전부 RuntimeException의 자손 클래스임)

그럼 왜 이걸 상속 받았느냐?  
이렇게 상속을 받으면 이 클래스는 RuntimeException의 한 종류이다. 라고 선언한 것이고, 이는 RuntimeException이 갖고 있던 기능들을 내가 작성한 클래스에서도 사용할 수 있게된 셈이다.(.getMessage, throw로 던지고 catch나 @ExceptionHandler로 잡을 수 있는 성질 등)

그럼그럼 이제 'super(message)'가 뭐냐?  
일단 super는 아니까 넘어가고, 내가 작성한 이 코드들을 아무리 눈 씻고 찾아봐도 이 예외가 왜 발생했는지 설명하는 문자열을 받아서 어떻게 저장을 하고 하는 등의 길고 현학적인 코드를 짠 적이 없는데 아주 좋게 RuntimeException에 이 기능이 있다. 그래서 뭐 super(message)로 문자열을 보내주면 알아서 딱딱 해준다. 이겁니다.

![GlobalExceptionHandler](images/image-37.png)  
이건 뭐냐, 싶을 수도 있음. 왜냐 이미 위에 있는 클래스에서 예외 처리 구문을 만들지 않았느냐? 그런데 보면 알 수 있듯이 불러오기만 할 뿐 출력을 한다거나, 사용자에게 에러 내역을 보여주는 것을 존재하지 않음. 그래서 이 클래스를 만듦.  

이번에는 어노테이션들이 눈에 띌 텐데, 설명하기는 귀찮으니 위에 있는 것을 읽도록(254번줄)

하지만 여기서 설명해야 할 것이 하나 있는데 '@ExceptionHandler(TodoNotFoundException.class)'바로 이것, "앵 아까는 설명하기 귀찮다면서 왜 설명함?" 왜냐하면 어노테이션 자체의 설명은 해놓았지만 뒤에 이어지는 괄호 부분은 위에 설명이 없기 때문.  
아주 간단하게 설명하자면 이 메서드가 어떤 에러 클래스의 값을 가져오는가? 구문을 가져오는가? 라고 생각하면 편함. 사실 어떻게 더 설명해야 할 지 모르겠음  

그럼 '.status(HttpStatus.NOT_FOUND).body(ex.getMessage())'이 뜬금없이 나온 이 친구가 궁금할 수 있는데 하나하나 뜯어서 써봅시다.

1. ResponseEntity, 위에 정리 되어있지만 응답을 세밀하게 만들 수 있는 도구.
2. .status(...): 그 도구에게 상태 코드를 이러하게 정해달라 지시하는 것
3. HttpStatus.NOT_FOUND: "404"라는 숫자를 자바 코드에서 이름으로 표현한 것. 음.. 그냥 404로 써도 되는데 왜 이렇게 쓰냐 하면 저 이름에도 명시 되어 있듯이 "아 이건 찾을 수 없구나"하고 보기 좋도록 하는 것.
4. ex: 매개변수로 받아온 것
5. ex.getMessage(): 저 위에 RuntimeException에서 썼던 그 예외가 만들어질 때 super(message)로 저장했던 문자열을 꺼내오는 것
7. .body(...): 문자열을 응답의 본문으로 넣어줌

이렇게 풀어놓으니까 별것 없죠?

자 이제 작성한 코드가 잘 작동 하는지, 실행을 해 보면

![Post, 성공](images/image-38.png)  
아주 깔끔하게 오류 문자가 정상 출력 되는 것을 확인할 수 있음. 문제가 없는게 좋기는 한데, 트러블 슈팅 솔찍히 해보고 싶었음

---

### 제약 조건(POST, @NotBlank + @Valid)

![POST, 공백 등록 성공](images/image-39.png)  
아무런 값을 보내지 않아도 너무나도 잘 보내짐.  

---
문제 상황 발생

![NotBlank를 불러올 수 없음](images/image-40.png)  
여기서 문제가 하나 발생함, 이상하게도 NotBlank가 되지 않음. 그래서 import 문제인가 하고 import문을 봤지만 Validation 부터 오류가 떠 있음  
이런 상황에서 내가 생각해 볼 수 있는 것은 딱 하나 '의존성 추가가 안 되었나?'  
바로 build.gradle 파일을 확인해 봄

![build.gradle, 의존성 확인](images/image-41.png)  
역시나 의존성이 추가 되어있지 않음  

![의존성 추가](images/image-42.png)  
바로 구글링으로 의존성 추가 방법을 찾아 추가함
```
implementation 'org.springframework.boot:spring-boot-starter-validation'
```
다시 파일로 돌아가서 확인해 보면  
![NotBlank 불러오기 성공](images/image-43.png)  
아주 정상적으로 불러와 진 것을 확인할 수 있음  

---

그럼 다시 돌아와서 실행 해 보면  
![POST, 빈 칸이라 등록 실패](images/image-44.png)  
역시나 오류가 뜨며 실패한 모습이다.  

그런데, 오류 구문을 자세히 보면
```
500 Internal Server Error
웹 서버가 요청을 처리하는 도중 예상치 못한 문제에 부딪혀 요청을 완료하지 못했음
```
의미까지 보면 저렇게 되어있는데 말 그대로 그냥 @NotBlank가 오류 검증을 잡아 내면서 바로 '꺼져 이 맞지 않는 더러운 데이터야'라고 팽 시킨 것이다.  

그럼 어떻게 해야 깔끔하게 돌려 보낼 수 있을까 하면 우리 @NotBlank의 동업자 어노테이션인 @Valid를 작성해 주면 된다.  

![@Valid 추가](images/image-45.png)  
이 처럼, @RequestBody 뒤에 작성해 주면 된다. 이러고 다시 POST 요청을 보내면?  

![POST, 400에러](images/image-46.png)  
아까와는 다르게 400에러가 뜬다
```
400 Bad Request
웹 서버가 클라이언트(사용자의 브라우저나 앱)의 요청(Request) 형식이 잘못되었거나 이해할 수 없어 처리를 거부한다는 HTTP 상태 코드
```
이는 즉 맞지 않는 데이터라고 팽 시킨게 아니고 이유를 알려주며 돌아가라 말 한 격이다. 아까는 꺼지라며 돌려 보낸 것에 비해 아주 순해진 것을 확인할 수 있다.  
하지만 아직도 불친절하다. 사용자가 '400 Bad Request'라는 오류를 보면 검색하는 것이 아닌 '뭐라는거야'하며 넘어갈 정도의 불친절함이다. 이를 어떻게 해결하는가, 바로 이전에 썼던 예외 처리를 여기서도 써먹으면 된다.

![예외 처리](images/image-47.png)  
잘 보면 이전과 거의 똑같은데, 잘 보면 'BAD_REQUEST'나 클래스의 이름 정도가 다르다.  
'예전에는 클래스 하나 만들었는데 이번에도 만들었겠지?' 생각할 수 있는데 이 'MethodArgumentNotValidException'는 내가 만들지 않았다.  
왜냐 하면, 저 예외는 Spring에 이미 만들어져 있는 클래스이기에 
```
import org.springframework.web.bind.MethodArgumentNotValidException;
```
이처럼 import만 잘 써주면 클래스를 작성해 줄 필요 없이 사용 가능하다.

다시 실행해 주면......  
![장황한 에러 내용](images/image-48.png)  
음.. 내가 생각했던 그 길이가 아닌 엄청나게 긴 에러 상황이 나온다.  
대체 왜? 라고 물으면 
```
Spring이 내부적으로 자동 생성한 예외입니다. Spring은 이 예외를 만들 때, "어떤 메서드의 몇 번째 매개변수에서, 어떤 필드가, 어떤 이유로 실패했는지"를 아주 상세하게(그리고 프로그래밍적으로) 기록해서 메시지에 담습니다. 그래서 .getMessage()를 호출하면, 사람이 읽기엔 불편하지만 기계적으로는 매우 정확한 정보가 통째로 나오는 것입니다.
```
라고 한다. 요컨데 Spring에서 직접 만든 에러이기에 Spring이 만든 직접적인 message를 가져오도록 하면 100점짜리 기계적인 오류 정보가 출력된다는 것이다.  
그럼 어떻게 해결하냐?  
```
ex.getMessage()는 "이 시험에서 뭐가 틀렸는지 전부 설명이 빽빽하게 적힌 종이 한 장"을 통째로 주는 것이고, ex.getBindingResult()는 "틀린 문제들만 정리된 채점표"를 주는 것에 가깝습니다. 우리는 그 채점표에서 "몇 번 문제가 틀렸고, 어떤 실수였는지"를 하나씩 꺼내 쓸 수 있습니다.
```
일단 이 상황을 이해 해야 해결이 가능한데... 아 음...  
요약하자면 'getMessage()'는 모범 답안 그런데 너무 세밀한. 정도이고 '.getBindingResult()'는 문제에 대한 정답만 알려주는 답안. 정도라고 생각하면 될 것 같음  

그러면 이제 문제 상황을 다시 직면 해서 어떻게 해결하느냐?  
```
ex.getBindingResult().getFieldError().getDefaultMessage()
```
이걸 body 자리에 넣어주고

```
@NotBlank(message = "원하는 문장")
```
이렇게 써주기만 하면 된다.  

'쓰기만 하지 말고, 설명을 해라!'  
그럼 하나하나 뜯어서 보자.

1. .getBindingResult()
    - 검증 실패 정보들이 모여있는 결과 묶음을 가져옴
2. .getFieldError()
    - 그 묶음 중에서 "실패한 필드 정보 하나"를 꺼냄
    (여러 개를 실패할 수도 있지만, 지금은 하나뿐)
3. .getDefaultMessage()
    - 이름 그대로 그 필드 오류에 할당 된 기본 메시지를 꺼내옴

그리고 @NotBlank 뒤에 작성된 message가 설정된 기본 문장.  
참 쉽죠?  

실제 작성된 코드를 확인 하면
```
** Todo.java
@NotBlank(message = "내용은 빈칸일 수 없습니다.")
@Column(length=255)
private String todoDetail;

** GlobalExceptionHandler.java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity handlerMethodArgumentNotValid(MethodArgumentNotValidException ex) {

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getFieldError().getDefaultMessage());
}
```
이런 모습이다. 이제 바로 실행해 보면?

![간략해진 오류 구문](images/image-49.png)  
설정해 놓은 기본 메시지로 잘 출력되는 모습이다.

## 실습 단계2 - MySQL 연동
*폴더명*: Springboot-practice3-MySQL
H2 database로 적당히 해봤으니 이제는 MySQL과 연결해서 사용해 볼것임.  
가볍게 종속성은 두 개 정도 필수만 챙겨주기
```
Spring Data JPA
MySQL Driver
```
편의성이고 뭐고 일단 진짜 필요한 것만 추가함 필요한 건 그 때 가서 추가하는걸로 함.

그럼 지금 SQL에서 할 것이
1. database생성(spring_prc_db)
2. user 생성(SpringAdmin1)
3. 권한 부여                            

![MySQL | DB 생성, 유저 생성, 권한 부여](images/image-50.png)  
이렇게 다 만들어 줬다면 다음으로는 Spring boot와 연결해 줄 차례.

![ddl-auto가 없는 모습](images/image-51.png)  
하지만 ddl-auto가 없기 때문에 굳이 받아오지 않고 SQL에서 테이블을 만들어줘야 함. Spring boot와 연결 전에 생성하기로 함.

![SQL테이블 구성](images/image-52.png)  
깔끔하게 테이블을 만들어 준 후  

![desc, 구조확인](images/image-53.png)  
desc로 구조를 확인해 주면 잘 생성된 것을 확인할 수 있다.

그럼 이제 진짜로 Spring boot와 연동을 시킬 것인데, 어떻게 하는지 모름  

![application.properties, sql 연결](images/image-54.png)  
구글링과 AI를 통한 작성, 하나하나 보자면

1. spring.datasource.url=jdbc:mysql://localhost:3306/spring_boot_prc
    - sql의 url을 작성, 'jdbc:mysql://localhost:3306/'까지는 기본적으로 같음(3306은 포트 번호, 기본값) 그 뒤에는 사용할 데이터베이스를 작성(아까 만든 spring_boot_prc 작성)
2. spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    - 이거는 이제 Spring과 MySQL을 이어주는 jdbc 드라이버의 이름
3. spring.datasource.username=SpringAdmin1
    - SQL에서 생성한 user 이름
4. spring.datasource.password=12345
    - user의 비밀번호
5. spring.jpa.hibernate.ddl-auto=none
    - 추가한 적은 없지만 일단 ddl-auto를 끔

이정도로 볼 수 있고, 이제 그럼 코드를 작성할 차례.

---

### 코드 작성

![Todo 코드](images/image-55.png)  
![TodoService 코드](images/image-56.png)
![TodoRepository 코드](images/image-57.png)

---

문제 상황 발생

![@RestController error](images/image-58.png)  
갑자기 @RestController가 사용되지 않음, @PostMapping도 마찬가지.  
이번에도 종속성을 가장 먼저 의심해 봄

![종속성](images/image-59.png)  
음... 그냥 딱 보면 잘 모르겠지만, 구글링 결과 @RestController는 'Spring Web' 종속성이 필수라고 함.
```
implementation 'org.springframework.boot:spring-boot-starter-web'
```
그럼 한치에 망설임도 없이 바로 작성하고 확인해 보면?

![해결](images/image-60.png)  
아주 깔끔하게 해결된 것을 확인할 수 있음.

---

![TodoController 코드](images/image-61.png)  

이번에는 하는 김에 예외 처리까지 했던 부분까지 완성할 예정  
저번과 똑같이 TodoNotFoundException.java 파일을 만들었는데  
![TodoNotFoundException 코드](images/image-62.png)  
알아서 완성 되어있는 모습. 원래 이랬나? 넘어가고

![에러 헨들러](images/image-63.png)  
대강 다 완성해 주고 이제 Postman으로 테스트

---

문제 상황 발생

문제가 한 두개가 아님
```
org.hibernate.exception.SQLGrammarException
Caused by: java.sql.SQLSyntaxErrorException
org.springframework.beans.factory.BeanCreationException
Caused by: org.hibernate.service.spi.ServiceException
Caused by: org.hibernate.HibernateException
```
아. 하기 싫다  
차근차근 생각해보자, 
- SQLGrammarException: 하이버네이트(Hibernate)나 JPA에서 데이터베이스로 보낸 SQL 쿼리에 문법 오류가 있거나 잘못된 객체를 참조했을 때 발생하는 예외
- SQLSyntaxErrorException: SQL 문법 규칙을 위반했거나 잘못된 데이터베이스 명령어를 실행했을 때 발생하는 자바(Java) 예외
- BeanCreationException: 얘는 뭐냐
- ServiceException: 프로그램이 외부 서비스나 서버에 요청을 보냈지만, 처리 과정에서 문제가 생겼음을 알려주는 오류
- 자바 ORM 프레임워크인 하이버네이트(Hibernate)에서 데이터베이스 연동 및 영속성 계층 처리 중 발생하는 모든 예외의 최상위(기본) 클래스

고로 SQL부분에서 모든 문제가 터졌다고 봐도 무방하다. 뭔지 모르겠는 저 오류도 'BeanCreate'만 봐도 대충 감은 온다.

그럼 이제 이걸 어떻게 해결하는지가 관건인데, 일단 내가 생각할 수 있는 것은 SQL파일을 외부 라이브러리로 추가하지 않았다는 것, 그래서 그걸 지금 해봐야 한다는 것. 그런데 원래 안하지 않나? 몰라 일단 해본다.

찾아보니 종속성의 'runtimeOnly 'com.mysql:mysql-connector-j' 얘가 다 해준단다. 그럼 얘는 아니고, 아 귀찮아도 하나하나 봐야겠다.

1. SQLGrammarException
    - 매핑을 제대로 안 한 것 같으니 매핑 하는 방법을 찾자.  
    - Access denied for user 'SpringAdmin1'@'localhost' to database 'spring_boot_prc'뒤에 이런 놈이 더 숨어있었다. 애초에 연결 자체가 안되고 있던 것.
    멍청하게도 db이름을 이상하게 썼다......... 아 쪽팔려
저거 하나 똑바로 썼다고 다 됐다. 아 개쪽팔려 이건 트러블 슈팅에 안 쓸꺼다. 쪽팔리니까
그래도 고친 것은 있다.  
![Todo수정](images/image-64.png)  
이 정도

참고: JAVA에서 'Long'타입을 썼다면 SQL에서 'Bigint'타입으로 선언해야 한다.  
+SQL의 auto_increment를 사용하고자 하면 JAVA에서 @GenerateValue(이 자리에) 'strategy = GenerationType.IDENTITY'를 작성해 주어야 한다.

---

다시 돌아와서 Postman으로 하나하나 테스트 해 보면

![Post](images/image-65.png)  
![Get](images/image-66.png)  
![Put - error](images/image-67.png)
![Put - 성공](images/image-68.png)  
![Get - 수정 후](images/image-69.png)  
![Delete](images/image-70.png)
![Get - 삭제 후](images/image-71.png)

---

## 실습 단계3 - 회원 가입/로그인 및 비밀번호 암호화
*폴더명*: Springboot-practice4-Login
웹 서비스의 기본적인 것 중 하나인 회원 가입 및 로그인  

기본적으로 필요한 종속성을 먼저 추가함
```
Spring web - 이전에 추가 안 해서 어노테이션을 사용하지 못 했음
Spring data JPA - 당연히 DB도 연결해야 함
MySQL Driver - H2말고 MySQL을 사용함
Validation - 요류 잡기용
Spring Security - 얘가 오늘 할 암호화의 필수적 요소
```
간단하게 이 정도 종속성만 추가해 주고, 이제 뭘 작성 해야 하나?
1. 기본적인 CRUD
2. SQL
3. **중요** 암호화

### 코드 작성

가장 중요하고 기본적인 CRUD 작성을 먼저 실시(평소화 같기에 사진은 없음)

그리고 저번에 만든 'spring_prc_db'에 'todoUser' 테이블을 생성함  
![todoUser](images/image-72.png)  

그럼 이제 Service 단계에서 비밀번호 암호화를 실시해야 하는데, 이거 뭐 어떻게 하냐.  
JDBC와 같은 경우에는 헤시 만들고... 솔트 만들고... 그 잡다한 모든 것을 손수 수작업 한 것과는 다르게  
'PasswordEncoder'로 처리가 가능.  

그럼 바로 코드로 보면  
![PasswordEncoder](images/image-73.png)  
이런 모습.  
뭐 잡다한 것 없이 깔끔하게 import와 생성자, final 선언. 이것 만으로 모든 준비는 끝남.

이제 진짜 비밀번호를 암호화 할 것인데, 코드를 먼저 보면  
![savaUser, 암호화](images/image-74.png)  
원래 한 줄만 있던 savaUser가 세 줄이나 되게 됨. 하나하나 뜯어보면

- String encodedPassword = passwordEncoder.encode(todouser.getUserPassword());
    * 차근차근 읽어보면 알 수 있듯이 encodedpassword라는 변수에 회원가입 시 작성한 비밀번호를 저장함(가져옴)
- todoUser.setUserPassword(encodidpassword);
    * 이것도 비슷한 맥락으로 todoUser의 setUserPassword에 윗줄에서 만든 변수에 담은 값을 다시 담음
- userRepository.save(todoUser);
    * 이건 뭐 항상 보던 저장하는 로직

그러면 이제 실행을 해볼까 하는데  
![실행 에러](images/image-75.png)  
에러가 뜨는 모습이다.  
뭐 포트가 이미 사용중이다, 오류가 일어났다. 하는데, 이거 읽어볼 필요가 없다. 왜냐 원래 써야 하는 파일 하나를 쓰지 않았으니까  
무슨 말이냐 하면, 암호화에 필요한 PasswordEncoder를 사용하고 저장하기 위해서는 새로운 Class가 필요하다. 그 클래스는 바로

![SecurityConfig](images/image-76.png)  
바로 이 클래스이다. 대강 훑어보고 어노테이션 보면 알다시피 이건 PasswordEncoder를 Bean으로 등록시키는 방법이다. 솔찍히 잘 모르겠고 일단 실행 해 보면?

![실행 성공](images/image-77.png)  
짜잔~ 실행이 성공적이게 진행되었다. 중간에 써있는 뭐 비밀번호는 뭐시기 개발 용도로만 사용 뭐시기는 그냥 무시하자.

그럼 이제 POST로 회원 가입 단계를 진행해 보자.

![Post](images/image-78.png)  
간단하게 아이디, 비밀번호, 이름 정도만 작성해 준다면...

![에러](images/image-79.png)  
음.. 에러다.

---

문제 상황 발생

아주 간단하게 나온
```
401 Unauthorized
```
엄청나게 짧고 intellij의 터미널에는 아무런 변화도 없다. 그럼 서버에 도달하지도 못 했다는 건데, 음.. 내 머리로는 어림도 없으니 구글링 시작

일단 401에러 라는 것은 접근 권한 부족? 그런 것 때문에 생기는 에러라고 한다.  
그럼 이걸 어떻게 해결하나?

바로 요청하는 API의 인증에 한해서 권한을 풀어주는 것이다.  
말은 어렵지만 생각보다 간단한게  
```
 @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                );
                //.httpBasic().disable();
        return http.build();
    }
```
아까 그 Config 파일에 이 구문만 추가해 주면 된다. 얘는 솔찍히 읽을 줄 모르겠다. 모르는 것이 투성이라....

그럼에도 한 번은 뜯어봐야 한다.
1. .csrf: 인증된 사용자가 자신의 의지와 무관하게 공격자가 의도한 행위(수정, 삭제, 송금 등)를 특정 웹사이트에 요청하게 만드는 웹 보안 취약점
2. .disable(): 영어 그대로 작동하지 않게 하는 것
3. .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/auth/register").permitAll()
                        .anyRequest().authenticated()
                );
                : 특정 URL 인증만을 열어주고 나머지는 전부 보안으로 잠금
4. 결과를 반환하며 이 설정은 끝났으니 실행하라는 의미

---

자, 이렇게 봤으니 코드를 추가해 주고 실행 해 보면?

![403 에러](images/image-80.png)  
오? 싸우자는 건가?

---

문제 상황 발생

또다시 떠버린 오류.
```
403 Forbidden
```
이건 서버는 요청을 확인 했지만 클라이언트에서 권한이 없기에 일어나는 에러. 그런데 나는 config까지 설정 했는데?

다시 한 번 읽어 보자.  
.requestMatchers("/api/auth/register").permitAll()
요놈 뭔가 꼬롬하다.  
내가 설정한 주소는 "/user"그런데 이놈은 다르다. 그러면 나는 요놈을 고쳐볼까 한다.  
![주소 변경](images/image-81.png)  

---

이렇게 다시 해 보면?

![성공](images/image-82.png)  
크으으으으으으으응으으 이거지 잘 되는 모습.  

![Get](images/image-83.png)  
비밀번호도 암호화 되어서 잘 나오는 모습까지 확인  

![Put 실패](images/image-84.png)  
당연하게도 나는 '/user'에만 권한을 주었으니 Put과 del은 되지 않을 것이다.  

### 로그인 설계

일단 로그인도 당연하게도 Controller와 Service를 사용하는데, Service를 먼저 보면

일단 로그인에 필요한 userId, userPassword를 받아오면 됨.  
```
public boolean login(String userId, String userPassword)
```
이렇게 작성해 줌.  
'뭐야, 왜 말도 없이 반환값을 boolean으로 지정함?' 그것은 Service에서 로그인을 완벽하게 해내는 것이 아닌 아이디와 비밀번호가 맞는지, 틀렸는 지를 판가름 하기 때문임.  

![Service, login](images/image-85.png)  
이미지에 나오다시피 엄청나게 길지만, 내용은 별거 없다.

```
TodoUser existingUser = userRepository.findByUserId(userId)
    .orElseThrow(() -> new UserNotFoundException("아이디 오류"));
```
자 일단 이놈, 슥 보면 이게 뭔가 싶지만 다시 읽고 한 번 더 보면, 이놈 update에서 지겹게 본 로직이다.  
'엥? 그런데, findByUserId가 뭐임? 그런건 없잖음'라고 할 텐데, 맞음. 기본적으로 'findUserId'라는건 존재하지 않음. 그렇기에 이걸 만들어줘야 하는데 이것도 난리 부르스를 하는게 아니고 repository에 딱 한 줄만 써주면 됨.

```
Optional<TodoUser> findByUserId(String userId);
```
짜잔, 이 한 줄만 있으면 findByUserId를 사용할 수 있고 이건 findById 처럼 작동함.

그러면 이제 다음
```
if (passwordEncoder.matches(userPassword, existingUser.getUserPassword())) {

            return true;
        }
return false;
```
요놈 같은 경우에는 이제 이것도 엄청나게 길어 보이지만 막상 보면 별것 없음
passwordEncoder.matches: 암호화한 비밀번호와 로그인을 위해 작성한 비밀번호를 검증함
```
passwordEncoder.matches(작성한 비밀번호, 저장된 비밀번호)
```
이런 식으로 작성되며 이것의 결과는 true 혹은 false로 나옴. 그래서 if를 바로 써줄 수 있는 것임.  

그럼 이제 controller로 넘어가자.

```
@PostMapping("/login")
public ResponseEntity login(@RequestBody LoginRequest logRe) {

    if (userService.login(logRe.getUserId(), logRe.getUserPassword())) {

        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
}
```
솔찍히 보면 다 읽힐텐데, 딱 하나 이해 안 되는 부분이 있을꺼임, 바로 'LoginRequest'의 정체, 이건 배운 적도 없고 존재하지도 않는데 어디서 가져온 거냐. 하면

![LoginRequest](images/image-86.png)  
새로운 클래스를 작성한 것. 구성을 보면 알겠다시피 TodoUser.java와 아주 유사함. 이걸 왜 작성했나?  
이 클래스를 작성하지 않고 login을 완성하려면 매개변수 자리에 'TodoUser todouser'를 작성해야 함. 그게 무슨 문제냐 할 수 있는데 저렇게 값을 불러오게 되면 로그인에는 필요 없는 'userName'까지 불러오게 됨. 그래서 새로운 클래스를 작성해서 나에게 딱 필요한 것만 가져옴(userId, userPassword)

그리고 또 볼 만한게 반환형이 ResponseEntity라는 것. 이건 왜 그런 거냐.  
일반적인 boolean을 반환형으로 삼으면 로그인에 성공하든, 실패하든 200 OK라는 답만이 돌아오게 되는데 그렇게 되면 사용자는 '이게 로그인이 되어서 200OK인지, 로그인에 실패해서 200OK인지 알 턱이 없다. 그래서 성공 여하와 로그인 성공 여부를 사용자가 읽을 수 있도록 반환시켜 주는 형이다.(참고로 예외 처리도 저 형으로 반환한다.)

이렇게 모두 완성하고..... 라고 생각하기는 금물. 우리는 'Spring Security'를 사용한다. 이는 즉 API 접근 권한이 없으면 접근할 수 없다는 것. 그리고 로그인의 주소는 '/login'이라는 것.  
참고로 아직까지 허용해 둔 개방 주소는 '/user'뿐이니 이 Post신호를 보내도 받을 수 있는 것은 '403 에러'뿐. 그럼 이제 다시 Config를 고치면 되는데 이건 아주 쉽다.
```
.requestMatchers("/user", "/login").permitAll()
```
이렇게 쉼표를 넣고 삽입 하는 것.

그럼 이제 실행 해 보면.
![로그인 성공](images/image-87.png)  
로그인에 성공함을 확인할 수 있다(firstUser는 윗 단계에서 만들어 놓은 유저이다.)

그럼 이제 아이디를 틀리게 하면  
![아이디 오류](images/image-88.png)  
아이디 오류라는 말이 반환되고

![비밀번호 오류](images/image-89.png)  
비밀번호를 이상하게 대입하니 로그인 실패라고 뜨는 모습이다.  
여기서 왜 401오류가 뜨는지 궁금할 수 있는데, Controller 단계에서 작성한 'HttpStatus.UNAUTHORIZED'가 권한을 주지 않았다는 오류이다.

---

## 실습 단계4 - 회원 가입/로그인 및 로그인 유지/로그아웃(By Session)
*폴더명*: Springboot-practice5-Session
이번에는 이전 실습 단계에서 했던 로그인에서 더 이어가 로그인 유지를 해볼 생각, 그리고 빠질 수 없는 로그 아웃 까지.

추가한 종속성
```
Spring web
Spring data JPA
Spring Security
MySQL Driver
Validation
```
이전 실습 단계에서 했던 것과 같다.

또 지루하고 현학적인 CRUD, 예외처리, 로그인, 회원 가입은 빠르게 작성하도록 하자.

### 코드 작성

얘는 이제 생각보다 진짜 짧다.  
먼저 내가 지금 사용할 방법은 세션(Session)인데 자세한건 위에 정리해 두었으니 그것 읽어보고, 아주 간단하게 설명을 해보면  
로그인 할 때 키를 내어줌
```
httpSession.setAttribute("UserSessKey", logRe.getUserId());
```
그럼 끝임.

이게 뭔 헛소리냐 할 수도 있지만. 코드를 보면  
![세션 추가 후 로그인 메서드](images/image-90.png)  
이렇게 되어있다. 로그인을 성공하게 되면 세션 키를 부여하고 원래처럼 작동한다.  

이렇게 추가만 하면 로그인이 유지 되는지 안되는지 어떻게 아냐? 싶은데 그래서 로그인을 확인할 수 있는 것을 하나 만든다.

![로그인 확인](images/image-91.png)  
```
String userId = (String) httpSession.getAttribute("UserSessKey");
```
이 문장으로 사용자가 가진 키를 확인 하고 if문에서 키의 존재(비어있는가?)를 확인해서 변수가 비어있다면 로그인이 필요하다는 말과 함께 401에러(권한 부족 에러)가 일어나도록 한다.  
만약 키가 존재한다면 로그인된 유저의 아이디와 함께 로그인이 되어있음을 확인시켜 준다.  
(Config 파일에 '/mypage'를 추가해야 한다.)

Postman에서 확인해 보면

![로그인 하지 않고 Get요청](images/image-92.png)  
로그인 하지 않으면 이렇게 로그인이 필요하다며 401에러가 나오고

![로그인 이후 Get요청](images/image-93.png)  
로그인 이후 요청하게 되면 200 OK와 함께 '아이디 + 로그인 확인문'이 출력됨을 확인할 수 있다.

이제 더 나아가 로그아웃 까지 만들어볼까 한다. 로그인이 있으면 로그아웃도 있어야 하는 법이니까.

로그 아웃은 뭐 줬던 세션(Session) 키를 다시 거두면 된다. 그 코드는
```
httpSession.invalidate();
```
이건데, 로그아웃 코드는 로그인 코드랑 거의 99% 똑같다  
![로그아웃 코드](images/image-94.png)  
이렇게 문장만 바꾸고 세션 거두는 코드 추가해 준 것이 끝이다. 그렇게 해서 실행 해 보면

![에러, 403](images/image-95.png)  
이제는 반가운 에러다

---

문제 상황 발생

SecurityConfig에도 '/login'을 써 놨고, Mapping도 '/login'으로 설정까지 잘 해 두었다. 그런데 권한 부족이 뜬다면 다른 곳에 문제가 있을 수도 있다.
```
2026-09-16T15:02:26.301+09:00  WARN 29640 --- [Springboot-practice5-Session] [nio-8080-exec-1] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.HttpRequestMethodNotSupportedException: Request method 'GET' is not supported]
```
intellij에서 이런 오류가 뜨는 걸 보아하니 'get'요청을 지원하지 않지만 해결되었다느니 뭐라느니 감도 안 잡히게 헛소리를 해버린다. 위에서는 내가 POST로 보냈다는걸 알 수 있는데.  

내 지식 선에서 벗어났으니 구글링을 시작해 본다.

Spring Security 종속성을 추가하면 '/login' URL이 자동적으로 생성된다고 한다. 그러면 내가 작성한 코드 말고 Spring에서 자동 생성된 '/login'으로 끌고가는 듯 하다. 이렇게 되면 해결하는 방법은 두 개 정도가 있을텐데.  
1. 내가 작성한 코드의 URL을 바꾸는 것.
2. Config에 설정을 추가하여 '/login'을 강제로 끌오는 것.  
사실 두 번째가 압도적으로 끌린다. 로그아웃을 하는데 URL이 '/logout'이 아니면 조금 이상하니까.  
그리고 많이 어렵게 쓰는 것도 아니고
```
.logout(logout -> logout.disable())
```
이 한 문장만 추가해 주면 된다.  

---

![Config](images/image-96.png)   
이렇게 작성된 모습이다. 그럼 이렇게 하고 다시 실행해 보면?

![해결](images/image-97.png)  
작성한 코드로 끌고 와서 정확하게 출력되는 모습이다.

---

## 실습 단계5 - 회원 가입/로그인 및 로그인 유지/로그아웃(By Token)
*폴더명*: Springboot-practice6-Token

세션(Session)에 이어 이번에는 토큰(Token)을 사용해 볼 것이다. 거두절미하고 종속성 부터 알아보면

종속성
```
Spring web
Spring Security
Spring data JPA
MySQL Driver
validation
Java Json Web Token - 신규, Token사용을 위한 라이브러리
```

Spring boot 폴더 생성을 intellij로 하는 편인데, JJWT종속성 추가가 생성 화면에 나오지 않아 따로 추가해 줌
```
implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'
```

그럼 이제 지루하고 현학적인 기본 작업 시작

### 코드 작성

일단 토큰을 생성하기 위해서는 새로운 클래스가 필요하다.  
JwtUnit 클래스를 생성해 주고 아래와 같이 작성해 준다.  
![JwtUnit](images/image-98.png)  
하나하나 뜯어보면

1. Key.secretKeyFor(...): 기본적으로 제공하는 기능으로 안전한 비밀 키 생성
2. SignatureAlgorithm.HS256: 이건 생성하는 방식 정도라고 생각하면 됨.
3. Jwts.builder(): 토큰 생성을 시작하겠다는 선언
4. .setSubject(userId): 토큰의 **내용**부분의 담을 핵심 정보로 userId를 넣음.
5. .setIssuedAt(new Date()): 토큰이 언제 발급되었는지의 시각 기록
6. .setExpiration(...): 토큰이 언제 만료되는지.(지금은 (현재 시각 + 1시간(1000 * 60 * 60)밀리초)로 설정)
7. .signWith(key): 위에서 만든 비밀 키를 서명으로 붙인다.
8. .compact(): 이 모든 것을 하나의 문자열(토큰)으로 압축시킨다.

정도로 보면 되고. 이제 Controller로 돌아가 토큰을 부여 해 주면 된다.

![Controller, Jwt받아오기](images/image-99.png)  
먼저 JwtUnit을 받아와 준 후

![Login메서드, 토큰 지급](images/image-100.png)  
토큰을 지급해 준다.  

여기서 궁금할 점.
저렇게 하면 토큰이 공개되는 것 아닌가요?  
맞음. 저렇게 작성 하면 토큰이 공개됨. 하지만 상관없음. 저렇게 공개한다고 해서 조작할 수 있는 것도 아니며 지금은 테스트이기에 토큰이 정확하게 지급 되었는지 확인하기 편해야 하기도 함.

이렇게 설정하고 Postman으로 테스트 해 보면?

![Postman, 로그인 성공](images/image-101.png)  
아주 완벽하게 성공한 모습

```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmaXJzdFVzZXIiLCJpYXQiOjE3ODk2MDk5MTgsImV4cCI6MTc4OTYxMzUxOH0.3JtXG_UdBzN-banjlYbxcxUO4NgeQyo7A9Cb_5HLHVs
```
이렇게 보면 알 수 있듯이 '.'을 기준으로 세 덩이로 나뉘어 있음.  
위에서 설명한 세 부분  
'헤더.내용.서명'  

그럼 이제 로그인이 유지되는지 확인할 수 있도록 mypage작업을 시작

이 토큰의 검증은 좀 귀찮은데 먼저 JwtUnit.java에 이 토큰이 옳은가 검증이 먼저임  
![토큰 검증](images/image-102.png)  
또 하나하나 설명하자면

1. Jwts.parserBuilder(): 토큰을 해석하는 도구 만들기를 시작하겠다는 선언
2. .setSigningKey(key): 위에서 만든 비밀 키로 서명을 검증한다는 선언
3. .build(): 도구를 완성
4. .parseClaimsJws(token): 도구로 매개변수로 받은 token을 실제로 해석함. 이 순간 서명이 검증되며 만약 위조되었거나 만료된 토큰이면 여기서 자동으로 예외처리가 발생함
5. .getBody(): 토큰의 내용 부분을 꺼냄
6. .getSubject(): 내용 중 sub(createToken에서 setSubject(userId)로 넣은 값)을 꺼냄

이렇게 쓰면 된다.

![Controller, loginCheck](images/image-103.png)  
이건 이제 Controller 코드인데
```
String token = authHeader.substring(7);
```
이건 앞의 7글자를 빼고 가져오라는 뜻인데 'bearer '를 제거하고 딱 토큰만 가져오기 위한 것이다.

```
String userId = jwtUnit.getUserIdFromToken(token);
return ResponseEntity.status(HttpStatus.OK).body(userId + "님, 로그인되었습니다.");
```
이건 위에서 만든 도구로 추출한 userId를 뽑아와서 로그인 성공 로그를 출력한다.

Postman에서 보면  
![Postman로그인 확인](images/image-104.png)  
이런 식인데 'Key'에는 헤더의 이름(Authorization)을 'value'에는 토큰을 써주면 된다. 애초에 로그인을 하지 않으면 요청을 보내도 403에러가 뜨기 때문에 굳이 실패시 나올 구문은 만들지 않았다.

그럼 만약 토큰을 수정해서 보낸다면 어떻게 될까?  
일단 Postman에서는 403에러가 뜰 것이고, Intellij에서는 
```
JWT signature does not match locally computed signature. JWT validity cannot be asserted and should not be trusted.
```
라고 뜬다. 장황하게 쓰여 있지만 짧게 요약하면  
JWT에서 연산된 서명과 같이 않으므로 믿을 수 없다. 이런 뜻이다.

그럼 이제 로그아웃을 구현 할 것인데, 이게 진짜 복잡하다. 일단 추가되는 종속성이 있는데
```
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
```
이걸 작성하여 'Redis'를 추가한다.

**Redis란?**  
키-값(Key-Value) 구조의 비정형 데이터를 메모리에 저장하고 처리하여 빠른 속도를 제공하는 오픈소스 인메모리 데이터 구조 저장소

그리고 Redis를 따로 작동 시킨다(application.properties에 추가)
```
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

그 다음 config 패키지에 RedisConfig.java를 작성해 준다.
![RedisConfig](images/image-105.png)  

그러고는 JwtUnit을 수정해야 하는데 그건 일단 길어서 사진은 넘기기로 하자

![로그아웃 구현](images/image-106.png)  
이렇게 다 완성 해 주고 Postman으로 확인 해 주면?

*참고*
```
docker run -d -p 6379:6379 --name my-redis redis
```
최초 실행 시 이렇게 해줘야 Redis가 실행된다  
다회차 부터는 
```
docker start my-redis
```
이렇게 하면 된다.

염병할 이걸 하려고 해도 난리네 안 해