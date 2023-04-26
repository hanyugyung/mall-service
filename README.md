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
- 배송 상태 조회
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
  - 상품 -> 상품명, 상품기본가격, 상품옵션목록, 노출여부
  - 상품옵션목록 -> 상품옵션(색상, 사이즈를 모두 포함한다), 추가금액, 수량
- 판매 수익 조회

- 배송지 등록/조회/삭제
  - 배송지는 주소, 상세주소, 우편번호 정보를 가진다.
  - 사용자 당 여러 개의 배송지를 가질 수 있다.
- 상품 조회
  - 브랜드 목록 조회할 수 있다.
  - 기본 조회는 등록상품순으로 20건씩 조회할 수 있다.
  - 브랜드별 상품 조회할 수 있다.
- 상품 주문
  - 상품을 주문할 수 있다.
  - 결제 기능은 논외로 한다.
  - 배송이 '주문완료' 상태일 때 주문을 취소할 수 있다.
  - 모든 주문 이력을 조회할 수 있다.
- 배송 상태 조회
  - 배송 상태는 주문완료 -> 배송준비중 -> 배송중 -> 배송완료 상태가 있다.
  - 상품 배송 상태를 조회할 수 있다.
- 리뷰 등록/조회
  - 배송완료 상태인 주문 건에 대해 리뷰를 등록할 수 있다.
-------------------------
**회고**
- 2023.04.21
  - 시작이 반이라고 생각한다. 오늘은 레파지토리 만드는 것부터 했으니 벌써 반절 온 것 같다!
- 2023.04.26
  - 코틀린의 기본 사용법을 좀 더 익혀야 될 것 같다. 엔티티 필드의 초기값.. 생성자.. 등 선언 시 자바를 사용할 때와 비교해볼 때 허점이 많은 것 같다.
  - 주문 관련 엔티티가 상품 관련 엔티티와 어떻게 관계를 가져가야할지, 객체 참조가 필요할지, 값을 복사해서 쓸지 고민이 되었다. 값을 복사해야 될 것 같다. 상품 수정 시 주문되었던 상품가격이나 정보가 변경되면 안되기 때문이다. 그치만 요청으로부터 price 가 들어올 때 올바른 가격인지 어떻게 유효성을 체크할 수 있을까...
