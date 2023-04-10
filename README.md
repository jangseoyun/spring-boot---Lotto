# 로또번호 추첨 사이트

> **🔗 반자동, 전자동 랜덤 번호 추출기**
> - **배포 링크:** Link
> - **Swagger API 문서화:** Link
> - **프로젝트 기술 블로그:** [Velog Link](https://velog.io/@may_yun/series/SpringBoot-JPALottoProject)


<img width="400" alt="image" src="https://user-images.githubusercontent.com/94329274/230808903-5fa3a48d-d13a-47c6-a4fe-36f5fe2b33fd.png">

- **프로젝트 기한:** 2021.06 ~ 2021.07 (약 2개월)
- **개인 프로젝트:** DB 설계 / FrontEnd / BackEnd / 인프라
- **데이터 설계 및
  기획:** [Link](https://velog.io/@may_yun/Lotto-springboot-1.-%EB%B9%84%EC%A6%88%EB%8B%88%EC%8A%A4-%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EC%A0%95%EB%A6%AC)

---

# 1. 구동 방법

> **Environment Variable**에 아래 값들을 설정하고 실행 합니다.

| **Environment Variable**   | **예시**                            |
|----------------------------|-----------------------------------|
| SPRING_DATASOURCE_URL      | jdbc:mysql://localhost:3306/Lotto |
| SPRING_DATASOURCE_USERNAME | root                              |
| SPRING_DATASOURCE_PASSWORD | password                          |

---

# 2. EndPoint

| 구분       | Method | URI                           | Description | 요구사항        |
|----------|--------|-------------------------------|-------------|-------------|
| User     | GET    | /user/join-form               | 회원가입 폼      |             |
|          | POST   | /user/join                    | 회원가입        |             |
|          | GET    | /user/login-form              | 로그인 폼       |             |
|          | POST   | /user/login                   | 로그인         |             |
|          | GET    | /user/logout                  | 로그아웃        |             |
| Main     | GET    | /lottery/index                | 메인페이지       | 로그인 후 이용 가능 |
| Machine  | GET    | /machine/get-lotto-num        | 사용자 랜덤 번호 추출 요청       |             |
| Winning  | GET    | /winning/check-winner         |                |    schedule로 실행         |
|          | GET    | /winning/result/game          |    회차별 당첨 내용 요청       |                         |
|          | GET    | /winning/result/user/{userNo} |                       |                         |
| LottoAPI | GET    | /lotto/loop                   |   1~요청회차 api loop 저장      |                         |
|          | GET    | /lotto/cycle-num              |     단일 회차 저장                      |                         |

---

# 3. 기능

## 3-1) 구현 기능

- [x] 절차 지향 코드를 객체 지향으로 설계
- [x] 동행복권 lotto Api 저장
  - 매주 일요일 동행복권 Api 추첨 정보 자동 저장 (schedule)
- [x] 회원 요청 로또 번호 추출
  - 전자동, 반자동, 전체수동
  - 로또 번호 검증(Ball)
- [x] 당첨자 선별 도메인

|페이지|역할|기능|
|:---:|:---:|:---:|
|로그인, 회원가입|유저 관리|- 로그인 / 로그아웃 <br> - 회원가입|
|메인|로또 티켓 구매| - 번호 추출 타입 선택 <br> - 로또 구입|
|로또 번호 추출 페이지|추출한 랜덤 번호 확인|- 요청한 랜덤 번호 리스트 확인(페이징) <br> - 메인으로 돌아가기 <br> - 회차별 당첨자 조회 페이지 이동|
|이번주 동행복권 당첨번호|동행복권 당첨 결과 확인 <br> 사이트 기준 당첨 결과 확인 | - 이번주 동행복권 추첨 결과 확인 <br> - 사이트 기준 순위, 당첨금액, 참여 수, 총 판매 금액|
|이번주 내번호 당첨확인|이번주 참여 결과 확인|- 이번주 내번호 당첨 결과 확인 <br> - 이번주 추출 전체 리스트 <br> (구입 티켓 수, 참여 회차, 로또 번호, 보너스 번호, 추첨일)|
|회차별 당첨 결과 확인|회차 선택 기능|- 이번주 동행복권 추첨 결과 확인 <br> - 사이트 기준 순위, 당첨금액, 참여 수, 총 판매 금액|

## 3-2) ERD

| Table Name    |Description| 상세                                         |
|:--------------|---|:-------------------------------------------|
| t_subscription | 랜덤번호 구독서비스 (이메일, 핸드폰 전송)          |                                            |
| t_lotto_api   | 1~현재 회차 당첨 정보 데이터          |                                            |
| t_winning_info | 당첨자 정보          | 당첨자 전체 누적 데이터 회원이 정보 요청을 하거나 데이터 분석이 필요할 수 있다 |
| t_winner              | 이번회차 당첨자          |                                            |
| t_users              | 데이터 관리를 위해 회원가입 진행          |                                            |
| t_cycle_storage              | 당 회차 랜덤 번호 추첨 결과          | //1)                                       |
| t_ball_storage                             | 사용자 추출 번호 누적 저장소 (회차가 지난 데이터 확인 가능)                          | cycle_storage의 동일한 데이터가 누적되어 들어간다          |

- //1) 매회차별 다른 경우의 수가 나와야하기때문에 데이터 저장 / 검열 하는 역할을 하는 저장소이다.<br>
  - 한 회차가 지나면 리셋된다.<br>
  - 존재하는 경우(이미 존재) : 랜덤 번호 다시 추출<br>
    존재하지 않는 경우 : 사용자에게 응답<br>

<img width="1151" alt="image" src="https://user-images.githubusercontent.com/94329274/230861367-d892f865-83ae-4ccb-ac9f-b454264a2bd9.png">

## 3-3) Return Example(Json 형태) 타입

## 🔗 Postman API Example Link

---

# 4. 코드 설명

## [🔗 프로젝트 코드 설명 Link](https://velog.io/@may_yun/%EB%A1%9C%EB%98%90-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-04.-%EC%95%84%ED%82%A4%ED%85%8D%EC%B3%90#1%EF%B8%8F%E2%83%A3-%EB%8F%99%ED%96%89%EB%B3%B5%EA%B6%8C-api-%EC%9A%94%EC%B2%AD-%ED%9B%84-%EB%82%B4%EB%B6%80-db-%EC%A0%80%EC%9E%A5)

---

# 5. UI

- Main
  <img width="1680" alt="image" src="https://user-images.githubusercontent.com/94329274/230857299-90a980e0-26a6-4fa8-8ef7-e06c9b68d05d.png">

- 로또 번호 추출 결과
  <img width="1680" alt="image" src="https://user-images.githubusercontent.com/94329274/230857516-130440ce-532c-4193-a54f-bf2dde846168.png">

- 이번주 로또 구입 결과 조회
  <img width="1680" alt="image" src="https://user-images.githubusercontent.com/94329274/230857674-f6c16b8b-0e32-4f14-9cc6-7b014991e70f.png">
  <img width="1680" alt="image" src="https://user-images.githubusercontent.com/94329274/230858085-3a50e1b7-54f0-4b6b-84fc-38ef2c9b5278.png">

- 회차별 당첨 결과 조회
  <img width="1680" alt="image" src="https://user-images.githubusercontent.com/94329274/230857868-d88308ef-6719-4369-892b-cf82b792544e.png">
