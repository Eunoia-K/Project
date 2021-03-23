# Project

#### 1. 개발 환경 및 프레임워크
* Spring Boot - 2.4.2
* JDK - 1.8
* Spring Framework - 5.0.8 RELEASE
* Maven - 3.2+
* JPA
* IDE - Eclipse or Spring Tool Suite (STS)
* H2 Embedded Database


#### 2. 데이터베이스 설계
테이블명 - PAYMENTS
테이블설명 - 결제 API 내역 관리
테이블 스키마
 
번호|속성|데이터형|널|키|기본값|설명|제약규칙|
----|----|----|----|----|----|----|----|
1|ID|CHAR(20)|NN|PK| |관리번호||
2|CARDINFO|VARCHAR(300)|N| | |카드정보(암호화)| |
3|DIV|VARCHAR(10)|NN| | |거래구분(결제,취소)| |
4|AMOUNT|NUMBER|NN| | |거래금액| |
5|VAT|NUMBER|N| | |부가가치세| |
6|DISCOUNT_MONTH|VARCHAR(2)|N| | |할부기간(0-12)| |
7|STR_RESPONSE|CHAR(450)|NN| | |응답문자(헤더+바디)| |
8|PAYMENT_ID|CHAR(20)|N| | |결제관리번호(결제취소시)| |
8|SAVETIME|TIMESTAMP|NN| | |저장시간| |

#### 3. 문제해결 전략


#### 4. 빌드 및 실행방법
