# mall-service
쇼핑몰 서비스 프로토타입 구현하기

---
**주요 개발 환경**
- kotlin
- Spring Boot 3.0.2
- Gradle 7.4

-------------------------

**요구 사항**

(공통)
- 사용자 회원가입
- 토큰 인증 기반 로그인
- 배송 처리 스케줄러

(관리자)
- 파트너를 등록/조회/활성or비활성

(파트너)
- 상품 등록/조회/수정/삭제(소프트)
- 판매 수익 조회

(고객)
- 상품 조회
- 상품 상세 조회
- 상품 주문
- 배송 처리 조회
- 리뷰 등록/조회

-------------------------
**요구 사항 상세 정의**

- 사용자 회원가입
  - 사용자는 email, password 를 입력하여 회원가입할 수 있다.
- 토큰 인증 기반 로그인
  - 사용자는 CUSTOMER 의 권한을 가진다.
  - 관리자는 ADMIN 의 권한을 가진다.
  - 파트너는 PARTNER 의 권한을 가진다.
- 배송 처리 스케줄러
  - 스케줄러가 배송 처리를 담당해준다.
  - 프로토타입이기 때문에 5분 기준으로 배송 처리가 한단계씩 진행된다.

- 파트너를 등록/조회/활성or비활성
  - 파트너는 email, password, 브랜드 이름, 사업자등록번호의 정보 입력을 통해 등록될 수 있다. 활성 여부의 기본값은 활성이다.
  - 파트너의 email, 이름, 사업자등록번호, 활성여부를 조회할 수 있다.
  - 파트너의 활성여부를 수정할 수 있다.

- 상품 등록/조회/수정/삭제(소프트)
  - 상품 -> 상품명, 상품기본가격, 상품옵션목록
  - 상품옵션목록 -> 상품옵션(색상, 사이즈를 모두 포함한다), 추가금액, 수량
- 판매 수익 조회

- 배송지 등록/조회/삭제
  - 배송지는 주소, 상세주소, 우편번호 정보를 가진다.
  - 사용자 당 여러 개의 배송지를 가질 수 있다.
- 상품 조회
  - 
- 상품 주문
- 배송 처리 조회
- 리뷰 등록/조회

