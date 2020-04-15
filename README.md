# **my-ecommerce**
쇼핑몰 만들어보기
spring-boot + jpa
- version 1.0
1. 프로젝트 생성 및 데이터베이스 설계(진행중)
2. 프로젝트 초기설정 및 작동 테스트(진행중)
- spring boot 2.2.x 버전에서는 .properties 에 MySQL5InnoDBDialect추가시 오류(engine innodb가 h2에서도 안됨)
- 외래키 생성시에 transactional 어노테이션이 반드시 필요(servcie, test등등) -> 데이터베이스에 동일하게 적용
3. 엔티티 생성 및 기본적인 crud 구현
- comment 생성 crud 구현
4. 로그인 구현(spring security jwt)
 - 패스워드 암호화 (0)
 - jwt 토큰 발행(0)
 - 토큰 검증 및 유저 정보 확인(0)
 - 로그인 회원가입 테스트 구현(0)
 - role에 맞는 요청 나누기(x)
 - 회원 crud 구현(0) -> test구현(x)
5. 파일업로드 
 - 업로드, list 호출(o)
 - product에 연관매핑하기(x)
 - test 구현(x)

***
- 기본적인 git workflow 대로 브랜치 생성해서 작업해보기 
- 단위 테스트 구현해보기
- admin 페이지 구현하기
- swagger를 사용하여 api 명세서 자동 생성(0)
- 회원 구현 완료 후에 develop 에 merge!!!!!!(x)
