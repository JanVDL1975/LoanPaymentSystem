# LoanPaymentSystem
# 🏦 Loan Payment System

This repository implements a simple loan payment system within a single **Spring Boot** application.

The application provides RESTful APIs for creating loans and recording payments. It enforces business rules such as loan validation, payment processing, and automatic loan settlement.

---

## 📦 Features

- Create and retrieve loans
- Record payments towards existing loans
- Prevent overpayment
- Auto-settle loans when fully paid
- Unit-tested business logic

---

## 🧱 Core Domains

### 1. Loan Domain

Handles loan creation and retrieval.

#### Endpoints

- `POST /loans`:  
  Creates a new loan with a specified loan amount and term.

- `GET /loans/{loanId}`:  
  Retrieves loan details by ID, including:
  - Loan amount  
  - Remaining balance  
  - Status (e.g., ACTIVE, SETTLED)

---

### 2. Payments Domain

Handles payments made towards existing loans.

#### Endpoint

- `POST /payments`:  
  Records a payment by providing:
  - `loanId`
  - `paymentAmount`

---

## 📐 Business Logic

- A loan must include a **valid amount** and **term**; both are validated at creation.
- Payments are deducted from the remaining loan balance.
- **Overpayments** (amount > remaining balance) result in an error.
- Loans that are paid off are automatically marked as **SETTLED**.

---

## ✅ Unit Tests

The application includes unit tests that verify the following:

- ✅ Loan creation and persistence in the database  
- ✅ Payment processing and balance updates  
- ✅ Overpayment detection and error handling  
- ✅ Automatic transition to `SETTLED` state when loan is paid in full

---

---
## How to build and run the application

---
## Prerequisites:

- Java SDK installed (Java 17 or later)

- Apache Maven installed

- Environment variables JAVA_HOME and MAVEN_HOME properly set

- pom.xml file exists in the project root.

---
## Building the project:

- Open a terminal in your project directory (where pom.xml is located) and run: 

mvn clean install

---
## Running the project:

- Open a terminal in your project directory (where pom.xml is located) and run: 

mvn spring-boot:run

OR after building:

java -jar target/loanpaymentsystem.jar

---
## Running the tests:

- Open a terminal in your project directory (where pom.xml is located) and run: 

mvn test

---

---

## How to test the APIs

---
### Base URL

By default, the API is available at:

http://localhost:8080

---
### Location and collection name of Postman collection

You can test the API using the provided Postman collection:

[LoanPaymentSystem.postman_collection](./src/test/postman/LoanPaymentSystem.postman_collection)

The Postman collection is available under the following:<br>
src/test/postman

---
### Importing the collection

Select the main menu: <br>
![App Screenshot](images/MainMenu.jpg)

---
Select importing from the main menu:

![App Screenshot](images/PostmanImport.jpg)

---
A selector will be shown where you can drag and drop the Postman collection file or select "files":

![App Screenshot](images/ImportSelection.jpg)

---
Navigate to your Postman collection and then "Open":

![App Screenshot](images/SelectAndOpen.jpg)

---
### Collection Structure

![App Screenshot](images/CollectionStucture.jpg)

The collection for testing the API is divided into 2 folders:

- Loans

- Payments

The names of the API calls are created to indicate the intention of the call:
example: createValidLoan is used for creating a valid loan. 

---

---

## Additional notes

### Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- JUnit (for testing)

---

### H2 in-memory database<br>

NOTE: The in-memory database is only available when the application is running.


![App Screenshot](images/H2Setup.jpg)

The in memory database can be accessed via:

http://localhost:8080/h2-console

The JDBC URL needs to be set to:

jdbc:h2:mem:loanpaymentdb

It will then be possible to connect to the database.

---


# 📘 BancX API Examples

This document provides example API calls for interacting with the BancX loan and payment services.

---

## 🚀 Create a Valid Loan

**POST** `/loans`

```http
POST http://localhost:8080/loans
Content-Type: application/json

{
  "loanAmount": 1000,
  "term": 12
}
```

---

## ⚠️ Create an Invalid Loan (Missing Term)

**POST** `/loans`

```http
POST http://localhost:8080/loans
Content-Type: application/json

{
  "loanAmount": 1000
}
```

---

## ⚠️ Create an Invalid Loan (Missing Amount)

**POST** `/loans`

```http
POST http://localhost:8080/loans
Content-Type: application/json

{
  "term": 12
}
```

---

## 📄 Get Loan Details by ID

**GET** `/loans/{id}`

```http
GET http://localhost:8080/loans/10
```

---

## 💸 Make a Payment Exceeding Loan Amount

**POST** `/payments`

```http
POST http://localhost:8080/payments
Content-Type: application/json

{
  "loanId": 1,
  "paymentAmount": 1200
}
```

---

## ✅ Make a Full Payment on Loan

**POST** `/payments`

```http
POST http://localhost:8080/payments
Content-Type: application/json

{
  "loanId": 1,
  "paymentAmount": 1000
}
```

---

## 💵 Make a Partial Payment on Loan

**POST** `/payments`

```http
POST http://localhost:8080/payments
Content-Type: application/json

{
  "loanId": 1,
  "paymentAmount": 500
}
```

---

