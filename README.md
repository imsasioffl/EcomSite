# EcomSite Automation Framework

This repository contains an automation framework for testing an E-commerce web application using Selenium WebDriver, Cucumber BDD, TestNG and Page Object Model (POM).

## 🛠️ Tech Stack
- **Java**
- **Selenium WebDriver**
- **Cucumber (BDD)**
- **TestNG**
- **Maven**
- **JSON Test Data Storage**

## 📁 Project Structure
src
└── test

├── java

│ ├── pages # POM classes

│ ├── stepdefinitions # Cucumber step definitions

│ ├── runners # Test runner classes

│ └── utils # Helpers & driver utils

└── resources

├── features # Cucumber .feature files

└── testdata # JSON files (saved user data)


## 📌 Features Automated
- User registration
- Login
- Product selection
- Cart operations
- Checkout flow
- JSON persistence of generated test data

## 🧪 Prerequisites
- Java 11 installed
- Maven installed
- Chrome browser installed
- ChromeDriver configured

## 🚀 How to Run

### Run all tests
```bash
mvn test
