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
{"orderBasicInfo":{"custName":"주윤재","phoneNumber":"23232","address":"파스토"},"orderItemInfos":[{"itemId":1,"count":1},{"itemId":2,"count":1}]}
```
- 응답
```
{
    "message": "Save Order",
    "data": "orderID : 1"
}
```
---
#### [GET] /api/orders?custName=주윤재&startDate=2023-07-17
- 응답
```
{
    "message": "Find Orders",
    "data": [
        {
            "orderid": 1,
            "orderNo": "2023-08-16#주윤재#00001",
            "custName": "주윤재",
            "phoneNumber": "23232",
            "address": "파스토",
            "orderStatus": "PREPARING",
            "orderDate": "2023-08-16 16:12:54",
            "itemName": "코카콜라",
            "totalPrice": 1,
            "count": 1000
        },
        {
            "orderid": 1,
            "orderNo": "2023-08-16#주윤재#00001",
            "custName": "주윤재",
            "phoneNumber": "23232",
            "address": "파스토",
            "orderStatus": "PREPARING",
            "orderDate": "2023-08-16 16:12:54",
            "itemName": "치킨",
            "totalPrice": 1,
            "count": 20000
        }
    ]
}
```
---
#### [PUT] /api/orders
- 요청
```
{"orderId":1,"orderBasicInfo":{"address":"저스트코타워"}}
```
- 응답
```
{
    "message": "Update Order",
    "data": "orderID : 1"
}
```
---
#### [PATCH] /api/orders
- 응답
```
{
    "message": "Cancel Order",
    "data": "orderID : 1"
}
```
### 필터로그확인
```
2023-08-16 16:18:53.473 INFO  --- [http-nio-8080-exec-8] c.j.api.common.filter.CustomFilter : {"threadId": "http-nio-8080-exec-8", "method": "GET", "url": "/api/orders", "userAgent": "PostmanRuntime/7.32.3", "host": "localhost:8080", "clientIp": "0:0:0:0:0:0:0:1", "requestParams": , "responseParams": {"message":"Find Orders","data":[{"orderid":1,"orderNo":"2023-08-16#주윤재#00001","custName":"주윤재","phoneNumber":"01090626317","address":"저스트코타워","orderStatus":"CANCEL","orderDate":"2023-08-16 16:16:06","itemName":"코카콜라","totalPrice":1,"count":1000},{"orderid":1,"orderNo":"2023-08-16#주윤재#00001","custName":"주윤재","phoneNumber":"01090626317","address":"저스트코타워","orderStatus":"CANCEL","orderDate":"2023-08-16 16:16:06","itemName":"치킨","totalPrice":1,"count":20000}]}, "requestAt": "2023-08-16 16:18:53.200","responseAt": "2023-08-16 16:18:53.472","elapsedTimeInMS": 272}
```
