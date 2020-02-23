# **my-ecommerce**
쇼핑몰 만들어보기
spring-boot + jpa
- version 1.0
1. 프로젝트 생성 및 데이터베이스 설계(진행중)
2. 프로젝트 초기설정 및 작동 테스트(진행중)
- spring boot 2.2.x 버전에서는 .properties 에 MySQL5InnoDBDialect추가시 오류(engine innodb가 h2에서도 안됨)
- 외래키 생성시에 transactional 어노테이션이 반드시 필요(servcie, test등등) -> 테이블 및 객체 생성이 순차적으로 이루어져야함
3. 엔티티 생성 및 기본적인 crud 구현
- comment 생성 crud 구현 (테스트 미구현)

***
- 기본적인 git workflow 대로 브랜치 생성해서 작업해보기 
- 단위 테스트 구현해보기
- admin 페이지 구현하기
