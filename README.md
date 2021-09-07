# Introduction

The goal of this project is to implement some of the most common resilience patterns for microservices.

## What Are Resilience Patterns?

Resiliency patterns are a type of service architecture that help to prevent cascading failures and to preserve functionality in the event of service failure.

## Organization

This project is compose of two simple applications: Customers and Orders, where Customers access Orders services. Because of this dependecy, the Customers project implement the resilience patterns.

### Language and Frameworks

* Java 11
* Springboot
* Resilience4j

### Resilience Patterns

* Retry
