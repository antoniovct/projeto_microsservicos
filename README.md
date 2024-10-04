# Distribuidora de Cosméticos - API Rest

## Descrição

Este projeto implementa uma API Rest para uma distribuidora de cosméticos, utilizando uma arquitetura de microsserviços. A aplicação é construída com **Spring Boot**, **Spring Cloud Gateway**, **Eureka Client** e **OpenFeign** para comunicação entre os microsserviços. A autenticação e autorização estão centralizadas no Gateway, utilizando **Spring Security**.
O projeto ainda está em desenvolvimento!

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.4**
- **Spring Cloud Gateway**
- **Spring Security (JWT)**
- **Eureka Discovery Client**
- **OpenFeign**
- **MySQL** (ou outro banco de dados de sua escolha)
- **Maven**

## Arquitetura

O projeto é dividido em vários microsserviços que interagem entre si utilizando **OpenFeign** para facilitar a comunicação. Cada microsserviço possui responsabilidades específicas. A arquitetura é centralizada em um **API Gateway**, que controla o fluxo de requisições e gerencia a autenticação e autorização através de **JWT (JSON Web Token)**.



