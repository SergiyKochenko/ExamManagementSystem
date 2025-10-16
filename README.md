# Exam Management System

## Table of Contents
- [Exam Management System](#exam-management-system)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Technical Competencies](#technical-competencies)
    - [1. Object-Oriented Programming](#1-object-oriented-programming)
    - [2. Exception Handling](#2-exception-handling)
    - [3. File I/O Operations](#3-file-io-operations)
    - [4. Data Structures](#4-data-structures)
    - [5. User Interface](#5-user-interface)
    - [6. Design Patterns](#6-design-patterns)
  - [Features](#features)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Usage](#usage)
    - [Input Validation](#input-validation)
  - [Project Structure](#project-structure)
  - [Testing](#testing)
  - [Data Storage](#data-storage)
  - [Credits](#credits)
  - [License](#license)
  - [Acknowledgments](#acknowledgments)

## Overview
This Java console application demonstrates proficiency in object-oriented programming and data management through an exam results management system.

## Technical Competencies

### 1. Object-Oriented Programming
- Implementation of inheritance (`Exam`, `MultipleChoice`, `Essay`)
- Use of interfaces (`Scorable`, `Printable`)
- Encapsulation through private fields and public methods
- Polymorphism in exam type handling

### 2. Exception Handling
- Custom exceptions (`StudentException`, `ExamException`)
- Input validation and error management
- Robust error messaging system

### 3. File I/O Operations
- Writing to external files (summary_result.txt, detailed_results.txt)
- Structured data output formatting
- BufferedWriter implementation

### 4. Data Structures
- ArrayList implementation for student and exam management
- List interface usage
- Complex object relationships

### 5. User Interface
- Console-based menu system
- Color-coded output for better UX
- Input validation and user feedback

### 6. Design Patterns
- Singleton pattern for exam management
- Strategy pattern for exam scoring
- Factory method for exam creation

## Features

- Add and manage student records
- Record Multiple Choice exam results
- Record Essay exam results
- Generate summary reports
- Generate detailed exam results
- Input validation and error handling
- Color-coded console interface
- **Validated input for all entries** (IDs, names, numbers, etc.)

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Any Java IDE (Eclipse, IntelliJ IDEA, or NetBeans)

### Installation

1. Clone the repository:
```bash
git clone [repository-url]
```

2. Navigate to the project directory:
```bash
cd ExamResults
```

3. Compile the Java files:
```bash
javac ExamResults/*.java
```

4. Run the application:
```bash
java ExamResults.ExamManagement
```

## Usage

The system provides a menu-driven interface with the following options:

1. Add Student
2. Add Multiple Choice Exam Result
3. Add Essay Exam Result
4. Print Summary Result
5. Print Detailed Results
6. Quit

### Input Validation

- Student IDs must be numeric and not empty
- First name and surname must be alphabetic and 2-30 characters long
- Multiple Choice exams must have 10-50 questions, correct answers must be valid
- Essay word limits must be between 500-10000 words, answer cannot be empty
- Exam duration must be between 30-180 minutes
- All numeric fields are validated for correct format and range

## Project Structure

```
ExamResults/
├── Student.java
├── Exam.java
├── MultipleChoice.java
├── Essay.java
├── ExamResult.java
├── ExamManagement.java
├── StudentException.java
├── ExamException.java
├── Scorable.java
└── Printable.java
```

## Testing

The application includes validation for:
- Student ID format
- Student name format
- Exam parameters
- Input type checking
- **All user entries are validated before processing**

## Data Storage

Results are stored in two formats:
- summary_result.txt - Basic exam information
- detailed_results.txt - Comprehensive exam details

## Credits

- Developed by Student Sergiy Kochenko
- For ATU Donegal Software Development Course
- Year: 2025/2026

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgments

- ATU Donegal for project requirements and guidance
- [Any other acknowledgments]
