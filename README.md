# [Spring 뉴스피드 프로젝트] Gamefeed

이 프로젝트는 Spring Framework 기반의 뉴스피드 프로젝트로,

여러 게임에 대한 `업데이트`와 `공지사항`, `이벤트` 등을 알려주고 각종 게임에 대한 `팁`을 알려주는 뉴스피드 게시판 프로젝트입니다.


## 팀 노션 페이지

https://www.notion.so/C-P-7add8cba7f9f40dfb5488905ef3311f5

## Team members

| 김진환                                                                                                                  | 정해인 | 황규정 |
|----------------------------------------------------------------------------------------------------------------------| --- | --- |
| [<img src="https://img.shields.io/badge/github-black?style=for-the-badge&logo=github">](https://github.com/jump6923) | [<img src="https://img.shields.io/badge/github-black?style=for-the-badge&logo=github">](https://github.com/seaStamp) | [<img src="https://img.shields.io/badge/github-black?style=for-the-badge&logo=github">](https://github.com/Mayst1232) |


## 기술 스택
Java JAR 17

Spring

Spring Boot 3.1.5

Spring Security 6

JPA/Hibernate

MySQL

JWT

## 디렉토리 구조

```
├── README.md
├── build.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── sparta
    │   │           └── gamefeed
    │   │               ├── GameFeedApplication.java
    │   │               ├── categoryfolder # 카테고리 및 게시판 관련
    │   │               │   ├── controller
    │   │               │   │   └── CategoryController.java
    │   │               │   ├── dto
    │   │               │   │   ├── CategoryResponseDto.java
    │   │               │   │   └── FolderResponseDto.java
    │   │               │   ├── entity
    │   │               │   │   ├── Category.java
    │   │               │   │   ├── CategoryFolder.java
    │   │               │   │   └── Folder.java
    │   │               │   ├── repository
    │   │               │   │   ├── CategoryFolderRepository.java
    │   │               │   │   ├── CategoryRepository.java
    │   │               │   │   └── FolderRepository.java
    │   │               │   └── service
    │   │               │       └── CategoryService.java
    │   │               ├── comment # 댓글관련 기능
    │   │               │   ├── controller
    │   │               │   │   └── CommentController.java
    │   │               │   ├── dto
    │   │               │   │   ├── CommentRequestDto.java
    │   │               │   │   └── CommentResponseDto.java
    │   │               │   ├── entity
    │   │               │   │   └── Comment.java
    │   │               │   ├── repository
    │   │               │   │   └── CommentRepository.java
    │   │               │   └── service
    │   │               │       └── CommentService.java
    │   │               ├── common # 공통
    │   │               │   ├── dto
    │   │               │   │   └── StatusResponseDto.java
    │   │               │   └── entity
    │   │               │       └── Timestamped.java
    │   │               ├── email # 이메일 관련 기능
    │   │               │   ├── config
    │   │               │   │   └── EmailConfig.java
    │   │               │   ├── controller
    │   │               │   │   └── EmailController.java
    │   │               │   ├── dto
    │   │               │   │   └── CodeRequestDto.java
    │   │               │   ├── entity
    │   │               │   │   └── Email.java
    │   │               │   ├── repository
    │   │               │   │   └── EmailRepository.java
    │   │               │   └── service
    │   │               │       └── EmailService.java
    │   │               ├── heart # 좋아요 기능
    │   │               │   ├── controller
    │   │               │   │   └── HeartController.java
    │   │               │   ├── dto
    │   │               │   │   └── HeartResponseDto.java
    │   │               │   ├── entity
    │   │               │   │   └── Heart.java
    │   │               │   ├── repository
    │   │               │   │   └── HeartRepository.java
    │   │               │   └── service
    │   │               │       └── HeartService.java
    │   │               ├── post # 게시물 관련 기능
    │   │               │   ├── controller
    │   │               │   │   └── PostController.java
    │   │               │   ├── dto
    │   │               │   │   ├── PostRequestDto.java
    │   │               │   │   └── PostResponseDto.java
    │   │               │   ├── entity
    │   │               │   │   └── Post.java
    │   │               │   ├── repository
    │   │               │   │   └── PostRepository.java
    │   │               │   └── service
    │   │               │       └── PostService.java
    │   │               ├── security # 인증/인가
    │   │               │   ├── JwtAuthenticationFilter.java
    │   │               │   ├── JwtAuthorizationFilter.java
    │   │               │   ├── UserDetailsImpl.java
    │   │               │   ├── UserDetailsServiceImpl.java
    │   │               │   ├── config
    │   │               │   │   └── WebSecurityConfig.java
    │   │               │   └── jwt
    │   │               │       └── JwtUtil.java
    │   │               └── user #회원가입/로그인/프로필
    │   │                   ├── controller
    │   │                   │   └── UserController.java
    │   │                   ├── dto
    │   │                   │   ├── IntroduceRequestDto.java
    │   │                   │   ├── LoginRequestDto.java
    │   │                   │   ├── PasswordRequestDto.java
    │   │                   │   ├── ProfileResponseDto.java
    │   │                   │   └── SignupRequestDto.java
    │   │                   ├── entity
    │   │                   │   └── User.java
    │   │                   ├── repository
    │   │                   │   └── UserRepository.java
    │   │                   └── service
    │   │                       └── UserService.java
    │   └── resources
    │       ├── application.properties
    │       ├── data.sql
    │       ├── static
    │       └── templates    
    └── test
    
```

## ERD

![Gamefeed ERD.png](document%2FGamefeed%20ERD.png)


## API 명세서

### [Gamefeed API Postman Doc](https://documenter.getpostman.com/view/30904217/2s9YeD9ZNN)
![Gamefeed API.png](document%2FGamefeed%20API.png)


## 구현 기능 목록

### 사용자(User) 관련 기능 목록
 - **사용자 인증기능**  
   - [x] 회원 가입 기능
      - 새로운 `사용자`가 `ID` / `비밀번호` / `이메일`을 입력하여 서비스에 가입
      - 비밀번호 암호화 하여 저장
   - [x] 이메일 인증 기능
     - 가입시 입력한 이메일로 인증코드를 보낸다
     - 인증코드를 입력해야 가입이 완료된다
   - [x] 로그인 기능
      - JWT 인증방식을 사용하여 구현
   

 - **프로필 관리**
   - [x] 프로필 조회기능
     - 이름, 한줄 소개와 같은 기본적인 정보를 반환한다
   - [x] 프로필 수정기능
     - 프로필의 내용을 수정할 수 있다
     - 비밀번호 수정 시에 비밀번호를 한번 더 입력받는다.

### 게시글(Post) 관련 기능 목록
- **게시물 작성 기능**
    - 로그인한 상태에서만 작성가능
- **전체페이지(뉴스피드) 조회기능**
    - `게시판 전체(Category)` 게시물 목록을 조회
    - `특정게시판(Folder)`의 게시물 목록을 조회
- **게시물 조회 기능**
  - 수정한 순으로 게시글 조회
  - 로그인 안한상태에서 조회가능
- **게시물 수정 기능**
  - 작성자 본인만 수정가능
- **게시물 삭제 기능**
  - 작성자 본인만 삭제가능


### 댓글(Comment) 관련 기능 목록
- **댓글 작성 기능**
  - 로그인한 상태에서만 작성가능
- **댓글 조회 기능**
  - 작성한 순으로 댓글 조회
  - 로그인 안한상태에서도 조회 가능
- **댓글 수정 기능**
  - 작성자 본인만 수정 가능
- **댓글 삭제 기능**
  - 작성자 본인만 삭제 가능

### 좋아요(Heart) 관련 기능 목록
- **게시글 / 댓글 좋아요 토글 기능**
  - 좋아요를 누르면 `좋아요 수`가 증가
  - 이미 누른 좋아요를 다시 누르면 좋아요 수가 감소(좋아요 취소)
  - 자신의 `게시글/댓글`에는 좋아요 불가능

- **좋아요를 눌렀는지 조회**
  - 자신이 좋아요를 눌렀으면 DTO가 `true`를 반환
  - 좋아요를 누르지 않았으면 `false`를 반환


### 예외처리
 - 해당하는 회원이 존재하지 않거나, 중복되는 사용자일 때 -> `400, IllegalArgumentException`
 - 비밀번호 변경시에 기존 비밀번호 입력하지 않았을 경우 -> `400, NullPointException`
 - 댓글 작성자가 아닌 사람이 입력할 경우 -> `400, RejectedExecutionException`
 - 존재하지 않는 포스트를 조회할 경우 -> `400, IllegalArgumentException`
 - 존재하지 않는 댓글을 조회할 경우 -> `400, IllegalArgumentException`
 - 본인의 댓글/게시글에 좋아요를 누를 경우 -> `400, RejectedExecutionException`
