# Project

### 1. 개발 환경 및 프레임워크
* Spring Boot - 2.4.4
* JDK - 1.8
* Maven - 3.2+
* JPA
* IDE - Eclipse
* H2 Embedded Database


### 2. 데이터베이스 설계
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

### 3. 문제해결 전략
1. API 정의서  
2. 기능별 로직 구현 
3. 데이터베이스 연결
4. 응답코드 예외처리
5. UNIT TEST 

##### A. API 정의서

* 결제 API
```
http mtehod : POST
Request url POST : http://localhost:8090/api/add

```
##### Request
변수명|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|
cardno|varchar|16|Y|결제카드번호||
exdirydate|varchar|4|Y|유효기간|mmyy형태입력|
cvc|varchar|3|Y|CVC||
discount|int|2|Y|할부개월수|0(일시불),1-12|
amount|int|10|Y|결제금액(100원이상,10억원 이하)||
vat|int|10|N|부가가치세|결제금액이상 불가,기본(결제금액/1.1)|

##### Response
변수명|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|
status|varchar|16|Y|성공여부|SUCCESS:성공/FAIL:실패|
id|varchar|20||결제관리번호|성공시|
strData|varchar|450|||카드사에 전달한 String 데이터|성공시

* 결제취소 API
```
http mtehod : POST
Request url : http://localhost:8090/api/cancel
```
##### Request
변수명|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|
id|varchar|20|Y|결제관리번호||
amount|int|10|Y|취소금액||
vat|int|10|N|부가가치세||

##### Response
변수명|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|
status|varchar|16|Y|성공여부|SUCCESS:성공/FAIL:실패|
id|varchar|20||결제관리번호|성공시|
strData|varchar|450|||카드사에 전달한 String 데이터|성공시


* 결제조회 API
```
http mtehod : GET
target url  : http://localhost:8090/api/get/{id}
```
##### Request
변수명|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|
id|varchar|20|Y|결제관리번호||

##### Response
변수명|배열구분|타입|최대길이|필수여부|컬럼내용|비고
---|---|---|---|---|---|---|
status| |varchar|16|Y|성공여부|SUCCESS:성공/FAIL:실패|
id| |varchar|20||결제관리번호|성공시|
cardinfo||array||||카드정보|성공시|
cardno|cardinfo|varchar|16|||카드정보|성공시|
exdiryDate|cardinfo|varchar|4|||유효기간|성공시|
cvc|cardinfo|varchar|3|||cvc|성공시|
div| |varchar|10||결제/취소 구분|결제:PAYMENT/취소:CANCEL|
payinfo||array||||금액정보|성공시|
amount|payinfo|int|10|||결제/취소금액|성공시|
vat|payinfo|int|10|||부가가치세|성공시|
savetime||date|||||성공시|

### 4. 빌드 및 실행방법
```
POSTMAN 사용 
```
**-결제API**  
<img src="https://user-images.githubusercontent.com/80779555/112127551-e8e6d100-8c08-11eb-91d5-b09cb0cf44b9.png" width="350">
<img src="https://user-images.githubusercontent.com/80779555/112127560-eab09480-8c08-11eb-8442-170a164c837c.png" width="350">
  
**-조회API**  
<img src="https://user-images.githubusercontent.com/80779555/112127564-eab09480-8c08-11eb-805a-da39452d411b.png" width="350">
<img src="https://user-images.githubusercontent.com/80779555/112127566-eb492b00-8c08-11eb-8288-559e9786a4c7.png" width="350">  
**- 결제취소API**  
<img src="https://user-images.githubusercontent.com/80779555/112127569-eb492b00-8c08-11eb-8174-675b4768d1f8.png" width="350">
<img src="https://user-images.githubusercontent.com/80779555/112127571-ebe1c180-8c08-11eb-991d-b4d0ba28b813.png" width="350">


