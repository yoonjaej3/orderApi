# toyProject

### 개발 스펙
- Gradle 7.4.1
- Spring Boot 2.6.2
- Spring Data JPA
- JAVA 11
- H2
- RESTful API
- QueryDSL
- Junit5

### 테이블 관계
![img.png](img.png)

### 테스트코드
- OrderServiceSaveTest
- OrderServiceCancelTest
- OrderServiceUpdateTest


### API
#### [POST] /api/orders
- 요청
```
{
  "orderBasicInfo": {
    "custName": "주윤재",
    "phoneNumber": "01099999999",
    "address": "파스토"
  },
  "orderItemInfos": [
    {
      "itemId": 1,
      "count": 1
    },
    {
      "itemId": 2,
      "count": 3
    }
  ]
}
```
- 응답
```
{
    "message": "주문 생성 완료",
    "data": "orderID : 1"
}
```
---
#### [GET] /api/orders?custName=주윤재&startDate=2023-07-17
- 응답
```
{
    "message": "주문 조회",
    "data": [
        {
            "orderid": 1,
            "orderNo": "2023-07-21#주윤재#00001",
            "custName": "주윤재",
            "phoneNumber": "01099999999",
            "address": "파스토",
            "orderStatus": "PREPARING",
            "orderDate": "2023-07-21 17:14:24",
            "itemName": "코카콜라",
            "totalPrice": 1,
            "count": 1000
        },
        {
            "orderid": 1,
            "orderNo": "2023-07-21#주윤재#00001",
            "custName": "주윤재",
            "phoneNumber": "01099999999",
            "address": "파스토",
            "orderStatus": "PREPARING",
            "orderDate": "2023-07-21 17:14:24",
            "itemName": "치킨",
            "totalPrice": 3,
            "count": 60000
        }
    ]
}
```
---
#### [PUT] /api/orders
- 요청
```
{
    "orderId" : 1,

    "orderBasicInfo": {
        "address": "저스트코타워"
    }
}
```
- 응답
```
{
    "message": "주문 수정 완료",
    "data": "orderID : 1"
}
```
---
#### [PATCH] /api/orders
- 응답
```
{
    "message": "주문 취소 완료",
    "data": "orderID : 1"
}
```
