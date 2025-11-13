# Warehouse Simulation

## Task Requirements
There is a product catalog — a list of products with price and quantity in stock. Several customers create orders concurrently using multithreading (`Runnable` or `ExecutorService`). The warehouse is a shared resource (`ConcurrentHashMap<Product, Integer>`). Warehouse workers process orders taken from a `BlockingQueue`.

After all orders are processed, analytics are run in parallel (`parallelStream`) to show:

- Total number of orders processed.
- Total profit.
- Top 3 best-selling products.

---

## Description
This project is a simple warehouse simulation that demonstrates the use of multithreading in a producer-consumer scenario. 
Customers place orders for products, and warehouse workers process these orders. 
An analytics module calculates and displays statistics such as total orders, total profit, a
nd top-selling products.

## Compile and Run
  ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="leverx.homework.Main"