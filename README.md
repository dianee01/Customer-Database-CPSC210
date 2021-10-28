# My Personal Project - Customer Log and Tracker

## Project Overview

This project is a customer log and tracker. It will track new customers who have purchased non-zero dollar amount of things in the store. A customer will only be removed from the list if they returned the only item they have purchased. There will be another category to identify VIP customers, which are customers who have purchased at least $1000 dollars for the past calendar year (From 01.01 of that year to 12.31 of that year). Similarly, a customer will be removed from the list if they purchased less than $1000 dollars of things from the store within the past calendar year. This VIP list will update once every year. You are also able to view the total amount of sales generated per calendar year. Furthermore, you can arrange customers by their purchasing power/loyalty with the store (greatest dollar amount spent in the store to the least). 

This project can be used by many stores to track their customers and sales. It can help the owner to be organized and stay on top of its business management. It can also help the owner to identify VIP customers from regular customers, which can be used to **evaluate its business strategy** (such as discounts available only to VIP customers) and making **new adjustments to the most current customer behaviors**.

**Main Features Overview**
- customer and sales tracking and organization
- VIP and regular customer differentiation
- sales overview

*As an Economics student, I am interested in connecting economics with computer science, and hope to be able to use my computer science skills to develop tools that can help with businesses and support their decision making. Although this project is developed as a UBC CPSC210 course project, I believe that this project can largely help businesses get organized with their daily sales (especially online stores or stores with large items and a smaller customer database) and become one of the first steps for businesses to become more digitized with their data.*

## User stories

**Already Developed For Phase 1**
- As a user, I want to be able to create a new customer and add it to a list of existing customers
- As a user, I want to be able to see the number of total customers, regular customers, and VIP customers
- As a user, I want to be able to differentiate between regular and VIP customers
- As a user, I want to be able to delete a customer from the regular customer list and add them into the VIP customer list if they purchased at least $1000 dollars within the past calendar year
- As a user, I want to be able to delete a customer from the VIP customer list and add them into the regular customer list if they purchased less than $1000 dollars within the past calendar year
- As a user, I want to be able to update my VIP customers list every year or after every newly added customer
- As a user, I want to be able to sort my VIP customers with decreasing purchasing power/loyalty with the store

**Planned for Phase 2**
- As a user, I want to be able to save my customer database to file
- As a user, I want to be able to be able to load my customer database from file
- As a user, I want to have loaded data available when I switch from viewing my customer to viewing sales

**Planned: class and test cases developed, missing ui**
- As a user, I want to be able to see the amount of sold items in total
- As a user, I want to be able to see the amount of sold items per year
- As a user, I want to be able to see my total sales (in dollars)
- As a user, I want to be able to see my annual sales (in dollars)
- As a user, I want to be able to select a customer and add a new item they purchased with associated dollar amount
- As a user, I want to be able to delete a returned item from the customer's items and total sales
- As a user, I want to be able to delete a customer from the list if they returned the only item they bought


