# SWAcademy-Server

탄소중립 sw아카데미 다회용기팀 서버

## 👥 Server 팀원
|이희주|황규혁|김동한|
|:-:|:-:|:-:|
|<img width="100px" alt="희주" src="https://github.com/Team-SWAcademy/SWAcademy-Server/assets/126947828/7e2fca2c-466f-4913-bfaa-db37ec5c5690">|<img src="https://github.com/umc-hackathon-Y/Y-Server/assets/113760409/22148297-a7db-4abd-86cf-952e35e1be61" width="100px" />|<img width="100" alt="동한" src="https://github.com/Team-SWAcademy/SWAcademy-Server/assets/126947828/9b46a66f-0d35-40c4-961a-7f4670cb6945">
|[@hj1487](https://github.com/hj1487)|[@Gyuhyeok99](https://github.com/Gyuhyeok99)|[@kdhan235](https://github.com/kdhan235)|


## 🌟 담당 역할
|담당 역할|Role|
|:-:|:-:|
|Nginx 배포, CI/CD 구축|황규혁|
|DB 구축 (RDS)|황규혁|
|ERD 작성|이희주, 황규혁, 김동한|
|API 개발|황규혁|

## 🛠️ 개발 환경
|||
|:-:|:-:|
|통합 개발 환경|IntelliJ|
|Spring 버전|3.2.4|
|데이터베이스|AWS RDS(MySQL), ElastiCache(Redis)|
|배포|AWS Elastic beanstalk, EC2|
|Project 빌드 관리 도구|Gradle|
|Java version|java 17|
|패키지 구조|도메인 패키지 구조|
|API 테스트|PostMan, Swagger(https://dev.swacademy.store/swagger-ui/index.html#/)|

## 🔧 시스템 아키텍처
<img width="685" alt="아키텍처" src="https://github.com/Team-SWAcademy/SWAcademy-Server/assets/126947828/4c916e69-fef1-410f-b452-fe0c7315b786">



## 📜 API Docs
<a href="https://holy-ulna-79c.notion.site/API-d6649045e52b4510843b0c6d52c41c3d?pvs=4">🔗 API Docs</a>

## ☁️ERD
<img width="675" alt="erd" src="https://github.com/Team-SWAcademy/SWAcademy-Server/assets/126947828/2d7e7110-1bdc-42d5-b754-28822234851a">


## ✨Structure
```text
api-server-spring-boot
  > .ebextensions-dev // dev 서버 관련 ci/cd 구축
    | 00-makeFiles.config
    | 01-set-timezone.config
  > .ebextensions-prod // prod 서버 관련 ci/cd 구축
    | 00-makeFiles.config
    | 01-set-timezone.config
  > .github
    > ISSUE_TEMPLATE
      | ✨feat.md
      | 🆘help.md
      | 🐛bug-report.md
      | 🚑fix.md
    > worksflows
      | develop_dev.yml // dev 서버 github action을 위한 파일
      | develop_prod.yml // prod 서버 github action을 위한 파일
  > .platform
    | nginx.conf // nginx 설정
  > * build
  > gradle
  > src.main.java.carbonneutral.academy
    > api
      > controller
        > auth
          > dto
            > request
            > response
          | AuthController.java
        > point
          > dto
            > request
            > response
          | PointController.java
        > use
          > dto
            > request
            > response
          | UseController.java
        > user
          > dto
            > request
            > response
          | UserController.java
      > converter
        > auth
          | AuthConverter.java
        > time
          | TimeConverter.java
        > use
          | UseConverter.java
        > user
          | UserConverter.java
      > service
        > auth
          > social // 소셜 로그인 관련
            > kakao
              | KakaoLoginService.java
              | KakaoLoginServiceImpl.java
          | AuthService.java
          | AuthServiceImpl.java
        > point
          | PointService.java
          | PointServiceImpl.java
        > use
          | UseService.java
          | UseServiceImpl.java
        > user
          | UserService.java
          | UserServiceImpl.java
    > common
      > code
        > status
          | ErrorStatus.java // 에러 응답 메시지 모아놓은 곳
          | SuccessStatus.java // 성공 응답 메시지 모아놓은 곳
        | BaseCode.java
        | BaseErrorCode.java
        | ErrorReasonDTO.java
        | ReasonDTO.java
      > config
        | AppConfig.java
        | RedisConfig.java // 레디스 관련 설정
        | Security.config.java // Spring Security 관련 설정
        | SwaggerConfig.java // Swagger 관련 설정
      > exceptions
        > handler
          | ExceptionHandler.java
        | BaseException.java // Controller, Service에서 Response 용으로 공통적으로 사용 될 익셉션 클래스
        | ExceptionAdvice.java // ExceptionHandler를 활용하여 정의해놓은 예외처리를 통합 관리하는 클래스
      | BaseEntity.java // create, update, state 등 Entity에 공통적으로 정의되는 변수를 정의한 BaseEntity
      | BaseResponse.java // Controller 에서 Response 용으로 공통적으로 사용되는 구조를 위한 모델 클래스
    > domain
      > location
        > enums
          | LocationType.java
        > repository
          | LocationJpaRepository
        | Location.java
      > mapping
        > repository
          | LocationContainerJpaRepository.java
        | LocationContainer.java
      > multi_use_container
        > repository
          | MultiUseContainerRepository.java
        | MultiUseContainer.java
      > point
        > repository
          | PointJpaRepository.java
        | Point.java
      > use
        > enums
          | UseStatus.java
        > repository
          | UseJpaRepository.java
          | UseQueryRepository // 통계 쿼리를 위한 querydsl용 repository
        | Use.java
      > user
        > enums
          | Permission.java //권한 부여
          | Role.java // 유저 역할
          | SocialType.java
        > repository
          | UserJpaRepository.java
        | User.java
    > utils
      > jwt // jwt 관련
        | JwtAuthenticationFilter.java
        | JwtProvider.java
        | LogoutService.java // 로그아웃
      | ApplicationAuditAware.java
      | RedisProvider.java // 레디스 서비스
    | AcademyApplication // SpringBootApplication 서버 시작 지점
  > resources
    | application.yml // Database 연동을 위한 설정 값 세팅 및 Port 정의 파일
    | application-dev.yml // dev 연동
    | application-local.yml // local 연동
build.gradle // gradle 빌드시에 필요한 dependency 설정
.gitignore // git 에 포함되지 않아야 하는 폴더, 파일들을 작성

```
## 환경 설정 내역
- Local 실행 시
  - 실행 방법: 프로젝트를 로컬 환경에서 실행할 때는 환경 변수 또는 설정 파일을 통해 local 모드로 설정
  - 서버 접속 주소: localhost:8080 에서 서버 실행
  - 데이터베이스 접속: 로컬에 설치된 MySQL 데이터베이스에 접속, 로컬 데이터베이스의 접속 정보(호스트, 포트, 사용자 이름, 비밀번호 등)는 개발 환경에 맞게 설정.
  - 캐시 서버 접속: 로컬에서 실행 중인 Redis 인스턴스에 접속, 로컬 Redis 서버의 접속 정보를 환경에 맞게 설정.

- Dev 실행 시
  - 실행 방법: 개발 환경에서는 환경 변수 또는 설정 파일을 dev 모드로 설정
  - 서버 접속 주소: 프로젝트는 https://dev.swacademy.store/ 주소를 통해 접근
  - 데이터베이스 접속: AWS RDS(MySQL) 인스턴스에 접속, 개발 환경에 맞는 RDS 인스턴스의 접속 정보(엔드포인트, 포트, 사용자 이름, 비밀번호 등)를 설정 파일에 명시해야 함.
  - 캐시 서버 접속: AWS ElastiCache(Redis) 인스턴스에 접속, 개발 환경에 맞는 ElastiCache 인스턴스의 접속 정보(엔드포인트, 포트 등)를 설정 파일에 명시해야 함.

- Prod 서버 아직 존재 x

## 🌱 Branch
-  main : 최종
-  develop : 개발
-  feat : 기능 개발
-  refactor : 기능 수정
-  ci : ci/cd 구축
