# Introduction

The goal of this project is to implement some of the most common resilience patterns for microservices.

## What Are Resilience Patterns?

Resiliency patterns are a type of service architecture that help to prevent cascading failures and to preserve functionality in the event of service failure.

## Organization of the project

This project is composed of two simple applications: Customers and Orders, where Customers access Orders services to retrieve orders of the customer. Because of this dependency between the projects, the Customers project implement the resilience patterns to prevent failures and reduce the load in the Orders application.

### Language and Frameworks

* Java 11
* Springboot
* Resilience4j

### Resilience Patterns

* Retry
* Circuit Breaker
* Fallback Method
