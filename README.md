# application.properties 설정
```properties
spring.application.name=member

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=manager

#mybatis-mapper
mybatis.mapper-locations=classpath:/mybatis/mapper/**/*.xml
mybatis.type-aliases-package=com.ksj.vo
mybatis.configuration.map-underscore-to-camel-case=true

#log-level
logging.level.root=debug

#Swagger
spring.mvc.pathmatch.matching-strategy=ant_path_matcher
```

# DB
### create문

```
CREATE TABLE member (
id          VARCHAR(100) NOT NULL PRIMARY KEY, -- 회원 ID
name        VARCHAR(100) NOT NULL,            -- 회원 이름
passwd      VARCHAR(300),                     -- 비밀번호
cell_phone  VARCHAR(20),                      -- 휴대폰 번호
email       VARCHAR(100),                     -- 이메일 주소
zip_code    VARCHAR(5),                       -- 우편번호
address     VARCHAR(100),                     -- 주소
status      CHAR(1)                           -- 상태 (활성화 여부)
);


-- 컬럼에 주석 추가
COMMENT ON COLUMN member.id IS '고유 ID';
COMMENT ON COLUMN member.name IS '이름';
COMMENT ON COLUMN member.passwd IS '비밀번호';
COMMENT ON COLUMN member.cell_phone IS '휴대폰 번호';
COMMENT ON COLUMN member.email IS '이메일 주소';
COMMENT ON COLUMN member.zip_code IS '우편번호';
COMMENT ON COLUMN member.address IS '상세 주소';
COMMENT ON COLUMN member.status IS '회원 상태 (Y: 활성, N: 비활성)';
```

### insert문
```
INSERT INTO member (id, name, passwd, cell_phone, email, zip_code, address, status) VALUES
('M001', '홍길동', 'password123', '010-1234-5678', 'hong@gmail.com', '12345', '서울특별시 강남구', 'Y'),
('M002', '김철수', 'securepass456', '010-2345-6789', 'chulsoo@gmail.com', '54321', '부산광역시 해운대구', 'Y'),
('M003', '이영희', 'mypassword789', '010-3456-7890', 'younghee@gmail.com', '10101', '대구광역시 수성구', 'Y'),
('M004', '박민수', 'pass1234', '010-4567-8901', 'minsoo@gmail.com', '20202', '광주광역시 북구', 'N'),
('M005', '최지영', 'pass5678', '010-5678-9012', 'jiyeong@gmail.com', '30303', '인천광역시 남동구', 'Y'),
('M006', '장윤아', 'secure098', '010-6789-0123', 'yuna@gmail.com', '40404', '울산광역시 중구', 'N'),
('M007', '윤재희', 'mypassword321', '010-7890-1234', 'jaehee@gmail.com', '50505', '대전광역시 서구', 'Y'),
('M008', '백승호', 'abc123', '010-8901-2345', 'seungho@gmail.com', '60606', '경기도 성남시', 'Y'),
('M009', '오현지', 'pass9999', '010-9012-3456', 'hyunji@gmail.com', '70707', '경상남도 창원시', 'N'),
('M010', '정유진', 'secure555', '010-0123-4567', 'yujin@gmail.com', '80808', '충청북도 청주시', 'Y');

commit;
```


## 11.27 정리
```java
@ModelAttribute MemberVO memberVO;
@RequestParam Map<String, String> param;
```
@ModelAttribute를 쓰면 내가 만든 VO에 바로 담을 수 있다.

@RequestParam을 쓰면 param에서 뽑아서 VO에 다시 담아서 Service단으로 넘겨야 함.
 
* 데이터만 통신할 시에는 RestController 이용.

* json data 통신의 경우
  * @ResponseBody, @RequestBody 사용.

* form data 통신의 경우
  * 낱개로 받을 때
    * @RequestParam("변수명") String [변수명]

  * 여러개 받을 때
    * @RequestParam Map<String, String> param

* DTO, VO를 따로 쓸 때가 있음.
  * DTO : 클라이언트-서버 단 통신 시 이용
  * VO : DB와의 통신 시 이용
    * 이 둘의 변수가 같을 시 자동매핑해주는 함수도 있음. 직접 구현도 가능.

## 12.06 정리
* .loginProcessingUrl("/login/member_login") ----> loadUserByUsername()
```javascript
<!-- 기존코드 -->
<div class="login-container">
    <h1>로그인</h1>
    <form id="loginForm" th:action="@{/member_main.page}" method="post">
        <input type="text" id="id" name="id" placeholder="아이디" required />
        <input type="password" id="passwd" name="passwd" placeholder="비밀번호" required />
        <button type="button" th:onclick="chkLogin()">로그인</button>
    </form>
    <a th:href="@{/login/member_register.page}">회원가입</a>
</div>

<!-- SpringSecurity 쓰면서바뀐 코드 -->
<div class="login-container">
    <h1>로그인</h1>
    <form id="loginForm" th:action="@{/login/member_login}" method="post">
        <input type="text" id="id" name="id" placeholder="아이디" required />
        <input type="password" id="passwd" name="passwd" placeholder="비밀번호" required />
        <button type="button" th:onclick="chkLogin()">로그인</button>
    </form>
    <a th:href="@{/login/member_register.page}">회원가입</a>
</div>
```
* th:action="@{/member_main.page}" --> th:action="@{/login/member_login}"
* 기존 /member_main.page는 로그인 버튼(chkLogin())누르면 ajax 이용해서 
  ```java
  memberService.selectMemberOne(memberVO);
  ```
  호출해서 회원 조회 sql 호출해서 status 값이 Y인지 N인지 확인 후 N이면 이메일 인증값에 대해 isStatusN return, Y면 이메일 인증값에 대해
  ```java
  memberService.loginMember(memberVO);
  ```
  로그인 호출 하여 아이디, 비밀번호 들고가서 계정이 맞는지 확인 후, loginForm.submit()하여 action에 위치한 member_main.page로 화면이동 하는 로직이었음.
* **
* 바뀐 /login/member_login는 
  ```java
  .formLogin(formLogin -> formLogin
          .loginPage("/login/member_login.page")
          .loginProcessingUrl("/login/member_login")
          .usernameParameter("id")
          .passwordParameter("passwd")
          .defaultSuccessUrl("/member_main.page")
          .permitAll()
  )
  ```
  (Config설정에 .formLogin()에서 셋팅을 먼저 한다.)
  Config설정에 셋팅이 되어있으니 loginForm.submit() 했을 때 /login/member_login(로그인 처리 URL) 찾아 security 설정 되어있는 곳으로 연결됌.
  id를 loadUserByUsername() 여기로 보내서
  ```java
  memberMapper.selectMemberPasswd(memberVO);
  ```
  그 아이디의 passwd, role를 조회해오고 User.builder() 하면
  UserDetails 객체를 생성하고 이 객체는 SpringSecurity가 내부적으로 사용자 인증 및 권한 관리를 처리할 때 사용 됌.

## 12.17 정리

## 12.22 정리
### AWS 환경구성
* tomcat 설치
```shell
wget http://archive.apache.org/dist/tomcat/tomcat-9/v9.0.98/bin/apache-tomcat-9.0.98.tar.gz
tar zxvf apache-tomcat-9.0.98.tar.gz
```
* java 설치
```shell
sudo apt upgrade
sudo apt-get update
sudo apt install openjdk-17-jdk

```
* DB 설치 
```shell
sudo apt show postgresql-16
sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list. d/pgdg.list'
sudo apt-get update
sudo apt-get -y install postgresql
psql --version

```
* DB 계정 생성
```shell
[ubuntu]sudo -i -u postgres
psql
create user sjkang password '1234' superuser;
\du --> 계정 조회
create database sjdb owner sjkang;
\l --> database 조회
exit
[ubuntu]sudo service postgresql restart

error남 설정 추가해야함
vi /etc/postgresql/16/main/pg_hba.conf
# "local" is for Unix domain socket connections only
local   all             all                                     md5
# IPv4 local connections:
host    all             all             0.0.0.0/0               scram-sha-256
vi postgresql.conf
listen_addresses = '*' 

[ubuntu]sudo service postgresql restart
[ubuntu]sudo -i -u postgres
psql -U sjkang -d sjdb
select * from PG_DATABASE;
```

* prod 설정 
   * application-prod.properties 하나 더 만들기 -> aws private ip 넣어서 설정하기
   * package
   * server
      ```shell
      java -jar -Dspring.profiles.active=prod member-0.0.1-SNAPSHOT.jar
      ```

## 12.24 정리
* jenkins 설치
```shell
startup.sh 작성
nohup java -jar jenkins.war --httpPort=8081 &
```
* chmod 700 startup.sh --> 실행 권한 나에게만 부여
* jenkins 들어가서 설정하기
  * cat /home/ubuntu/jenkins/webapps/secrets/initialAdminPassword 비밀번호 입력
  * maven plugin 설치, 수동설정
    * Jenkins관리 - Tools에 있음.
  * credentials 적용(?)
  * 새로운 item - pipeline 만들기
    * shell script 쓰기
      * ```shell
        pipeline {
          agent any
      
          tools {
            maven 'maven399' --> 내가 설정한 이름
          }
      
          stages {
            stage('Git Pull') {
              steps {
                echo '========= Git Pull ========'
                git branch: 'main', credentialsId: 'gitadmin',
                url: 'https://github.com/aksk179/msa-member.git'
              }
            }
            stage('Maven_Build') {
              steps {
                echo '======== Maven_Build ========'
                sh '''
                mvn clean package -DskipTests
                ls -l
                '''
              }
            }
          }
        }
        ```
* 만든 pipeline - 구성에 들어가면 됌.
* 지금 빌드

# msa-bbs
## 12.25 정리
* 게시판 목록조회까지
* spring-security 설정하면 처음 id, passwd는 user/console에 뜨는거 입력하기.
* DB->Mapper->Service->Controller->html
  * 백 -> 프론트 순으로 만듬
  * 기존 msa-member 참고함
  * 근데 왜 template parsing (template: "class path resource [templates/manager/bbs_master_list.html]") 에러가 뜨는지 모르겠음.
    * => @Data 가 Getter, Setter를 못 불러와서 그런듯. member에서도 이랬었는데 왜 이러지?
    * => 강제로 Getter, Setter 주입하니까 화면에 데이터 뿌리기까지 성공
      * 해결방법 ====>> ![img.png](img/img.png) 두 개 체크해줘야 함.

## 12.26 정리
* 게시판 등록, 수정까지
* controller 하나로 다 이동가능. checkModel()로 분기
```java
@RequestMapping(value = "/{dirPath}/{pageName}.page")
    public String dynamicPage(@PathVariable("dirPath") String dirPath, @PathVariable("pageName") String pageName, Model model) {
        checkModel(pageName, "", model);
        return "/" + dirPath + "/" + pageName;
    }
```
* html 파일 하나로 등록과 수정을 같이 이용.
```html
<button type="button" th:if="${bbsId == '' || bbsId == null}"     class="btn btn-primary" onclick="save('CREATE')"><span th:text="${btnName}">등록</span></button>
<button type="button" th:unless="${bbsId == '' || bbsId == null}" class="btn btn-primary" onclick="save('UPDATE')"><span th:text="${btnName}">수정</span></button>
```
```shell
var url = '';
if (mode === 'CREATE') {
    url = '/manager/create_bbs_master.do';
} else {
    url = '/manager/update_bbs_master.do';
}
```

## 12.29 정리
* 삭제 기능
  ```shell
  <a class="btn btn-sm btn-danger" th:attr="data-bbs-id=${bbs.bbsId}" onclick="deleteBbsMaster(this)">삭제</a>
  ```
  * thymleaf에서는 문자열 데이터를 이벤트 핸들러 속성에 직접 사용하려고 하면 보안상의 이유로 제한 발생. 숫자나 boolean 값만 신뢰함.
    * 해결방법 => data-* 속성을 사용.

### 재가동하지 않고 변경사항 반영하기
1. application.properties설정
  ```properties
  spring.thymeleaf.cache=false
  spring.thymeleaf.prefix=classpath:/templates/
  spring.thymeleaf.suffix=.html
  ```
2. spring boot devtools 사용
  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-devtools</artifactId>
      <scope>runtime</scope>
  </dependency>
  ```
3. File > Settings > Advanced Settings > Allow auto-make to start even if developed application is currently running 활성화
4. File > Settings > Build, Execution, Deployment > Compiler > Build project automatically 체크

* 게시글 기능 DB 설계 및 생성완료 PPT참고

## 12.30 정리
* 게시글 조회
* bbs_ main / master 분리
* 게시글 조회 할 때 sql에서 join해서 가져오면 중복됌.(ex) 첨부파일이 두 개, 댓글이 두 개면 총 네 개가 조회되어버림.)
* 각자 조회해오는 service를 만들고, controller단에서 조합해서 화면으로 던져주기
* 잘 안 될때는 일단 코딩해보고 중복되는 부분을 변수화하고, 하나로 합치기. 하드코딩X!

## 01.04 정리
* 게시글 댓글 기능
* 리다이렉션 안 하고 리스트 밑에 append()
* 시간 관련
  1. sql에서 now()로 시간 넣기
  * now()로 넣고, 댓글 조회해와서 result.put() 하기  ==> 이게 제일 좋은 방법 나중에 보안도 생각하면..
  2. java에서 시간 넣기
  3. javascript에서 시간 넣기

## 01.06 wjdfl
* 게시글 답글 기능
* recursive() 상-하위 관계별로 조회해오는 sql문 이용.
  ```shell
  WITH RECURSIVE find_cmt(bbs_id, bbs_no, bbs_cmt_seq, bbs_cmt_writer, bbs_cmt_wtime, bbs_comment, bbs_cmt_upseq)
        AS (
        -- START_QUERY: 최상위 댓글 찾기
        SELECT bbs_id, bbs_no, bbs_cmt_seq, bbs_cmt_writer, bbs_cmt_wtime, bbs_comment, bbs_cmt_upseq
        FROM bbs_comment
        WHERE 1=1
        AND bbs_id = #{bbsId}
        AND bbs_no = #{bbsNo}
        AND bbs_cmt_upseq = 0

        UNION ALL

        -- REPEAT_QUERY: 답글 찾기
        SELECT bc.bbs_id, bc.bbs_no, bc.bbs_cmt_seq, bc.bbs_cmt_writer, bc.bbs_cmt_wtime, bc.bbs_comment, bc.bbs_cmt_upseq
        FROM find_cmt fc
        INNER JOIN bbs_comment bc
        ON fc.bbs_cmt_seq = bc.bbs_cmt_upseq -- 부모 댓글-답글 관계
        AND bc.bbs_id = #{bbsId}                -- 동일 게시글
        AND bc.bbs_no = #{bbsNo}                   -- 동일 게시글 번호
        )
        -- VIEW_QUERY
        SELECT *
        FROM find_cmt
        ORDER BY
        CASE
        WHEN bbs_cmt_upseq = 0 THEN bbs_cmt_seq -- 댓글은 그대로 시간순으로 정렬
        ELSE bbs_cmt_upseq                     -- 답글은 부모 댓글 바로 아래로 정렬
        END,
        bbs_cmt_seq
  ```

## 01.09 정리
* 게시판 성격별 미완성 기능 추가
* bbsUserWriteYn 컬럼 추가
* newHour 관련
  * sql에서 처리 24h, 48h 등 시간단위 표시라서 이렇게 처리함.
  ```shell
    (EXTRACT(EPOCH FROM NOW() - bbs_wtime) / 3600) AS new_time
  ```

## 01.10 정리
* 게시글 등록 기능 추가

## 01.11 정리
* jpa
  1. dependency 추가
  2. application.properties 추가
* vo랑 model이랑 비슷한데 다름.
* jpa에서 model을 정의할 때는 실제 테이블인지 명시해주는 게 있음.
* dialect는 방언이라는 뜻. db가 다양하니까 내가 선택한 db로 하면 됌.

* @entity 는 객체라는 뜻.
* @Table은 DB 테이블 이름이랑 다를 때 지정.

* @GeneratedValue(strategy = GenerationType.IDENTITY) 자동증가
* @Column(nullable = false, unique = true)    //not null, unique
* 생성자를 꼭 만들어줘야 함.

* Repository
* jpaRepository에서 기능들을 함수로 구현해놔있음. extends 해서 쓰면 됌.
* postgre문법에 맞는 문장을 자동으로 만들어줌

```java
Role findByRoleName (String roleName); ==> where name = ${name}
Role findByRoleNameContains (String roleName); ==> where name LIKE ${NAME} || '%'
Role findByRoleNameContainsIgnoreCase (String roleName);  ==> where upper(name) LIKE upper (${NAME}) || '%'
```

```shell
controller -> service -> mapper -> sql                    --> 기존
                      -> reposotory -> model -> Dialect   --> jpa
```
* 테이블 생성하지 않아도 실행하면 테이블 만들어져 있음..

## 1.14 정리
* 회원 수정화면-권한 부여 추가, 메뉴 목록, 등록, 상세등록 화면 추가
* 프론트단에서 쓰려고 추출해놓고 서버사이드에서 값 가져옴..
  * 막 쓰지 말고 구별해서 제대로 쓸 것
* pagecontroller 어떻게 하면 더 깔끔하게 쓸 것인지 고민해보기