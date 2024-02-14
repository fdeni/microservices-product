# Welcome to Microservices Product!

Microservice product is an simple app designed to add the product. Users can register and log in to the application, then enabling them to create, update, and delete for the product records.


# Prerequisite :
- Java version 17
- Docker
- Docker Compose
- Mysql

# How to Run the Project :

1. Clone this repository.

2. Go to terminal and go to this repository.

3. Make sure your local device installed with Docker and Docker Compose.

4. Run `docker-compose up`

5. Wait for a while to setup the microservices app, before start to use the app.

6. Run this url in postman :
- Authentication API `baseUrl`: `http:/localhost:9090/`
- Product API `baseUrl`: `http:/localhost:9191/`

7. To kill the Apps `CTRL + C`

# API Documentation
### Authentication Endpoint :
### Register: `{baseUrl}/api/auth/register`
### Method : POST

**Request Body:**
```json
{
"username": "brandal17",
"password": "brandal17",
"name": "Brandal Lokal"
}
```
**Response Success:**
```json
{
"data":  {
"username":  "brandal17",
"name":  "Brandal Lokal"
},
"error":  null,
"status":  200
}
```
### Login: `{baseUrl}/api/auth/login`
### Method : POST

**Request Body:**
```json
{
"username":  "brandal17",
"password":  "brandal17"
}
```
**Response Success:**
```json
{
"data":  {
"id":  1,
"username":  "brandal17",
"name":  "Brandal Lokal",
"token":  "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiQnJhbmRhbCBMb2thbCIsImlkIjoxLCJ1c2VybmFtZSI6ImJyYW5kYWwxNyIsImV4cCI6MTcwNzY1MTg2Mn0.BSd5yyk48RvyaGCoA50EcSUw0xQGFu1g83RoftaDEmc"
},
"error":  null,
"status":  200
}
```

### Product Endpoint :
### Authorization :
This endpoint need authorization to access it. Bellow sample for the authorization :
```
Authorization: Bearer {token}
```
```
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiQnJhbmRhbCBMb2thbCIsImlkIjoxLCJ1c2VybmFtZSI6ImJyYW5kYWwxNyIsImV4cCI6MTcwNzY1MTg2Mn0.BSd5yyk48RvyaGCoA50EcSUw0xQGFu1g83RoftaDEmc
```

### Get All Product + Filter: `{baseUrl}/api/product`
### Method : GET
Note : This endpoint has a feature to filter partially by name, or/and range of date when the product created.
****Request Param:****
-  `page`: Requested page [Default = 1]
-  `limit`: Limit date per-page [Default = 10]
-  `name`: name of product
-  `startDate`: Start date of product created.
-  `endDate`: End date of product created.

**Response Success:**
```json
{
"currentPage":  1,
"totalPage":  1,
"totalData":  2,
"pagingData":  [
{
"id":  2,
"name":  "rinso",
"description":  "sabun cuci",
"price":  3000,
"updatedBy":  null,
"updatedAt":  null,
"createdBy":  1,
"createdAt":  "2024-02-09 22:49:48"
},
{
"id":  3,
"name":  "rinso new",
"description":  "sabun cuci",
"price":  4000,
"updatedBy":  null,
"updatedAt":  null,
"createdBy":  1,
"createdAt":  "2024-02-09 22:57:09"
}
]
}
```

### Create Product: `{baseUrl}/api/product`
### Method : POST
**Request Body:**
```json
{
"name":"rinso",
"description":"sabun cuci",
"price":3000
}
```
**Response Success:**
```json
{
"data":  {
"id":  1,
"name":  "rinso",
"description":  "sabun cuci",
"price":  3000,
"updatedBy":  null,
"updatedAt":  null,
"createdBy":  1,
"createdAt":  "2024-02-11 16:44:39"
},
"error":  null,
"status":  200
}
```

### Update Product: `{baseUrl}/api/product/{productId}`
### Method : PUT
**Request Body:**
```json
{
"name":"rinso new",
"description":"sabun cuci new",
"price":3500
}
```
**Response Success:**
```json
{
"data":  {
"id":  1,
"name":  "rinso new",
"description":  "sabun cuci new",
"price":  3000,
"updatedBy":  1,
"updatedAt":  "2024-02-11 16:44:39",
"createdBy":  null,
"createdAt":  null
},
"error":  null,
"status":  200
}
```

### Delete Product: `{baseUrl}/api/product/{productId}`
### Method : DELETE
**Request Param:**
-  `productId`: Id of product data

**Response Success:**
```json
{
"data": "Delete product successful",
"error": null,
"status": 200
}
```



