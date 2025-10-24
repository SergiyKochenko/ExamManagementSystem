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
  - [Visual Interface](#visual-interface)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [Usage](#usage)
    - [Input Validation](#input-validation)
  - [Project Structure](#project-structure)
  - [Testing](#testing)
  - [Data Storage](#data-storage)
    - [summary\_result.txt](#summary_resulttxt)
    - [detailed\_results.txt](#detailed_resultstxt)
  - [Output Examples](#output-examples)
    - [Summary Results File](#summary-results-file)
    - [Detailed Results File](#detailed-results-file)
  - [Credits](#credits)
  - [License](#license)
  - [Acknowledgments](#acknowledgments)

## Overview
This Java console application demonstrates proficiency in object-oriented programming and data management through an exam results management system with a modern, color-coded terminal interface.

## Technical Competencies

### 1. Object-Oriented Programming
- Implementation of inheritance (`Exam`, `MultipleChoice`, `Essay`)
- Use of interfaces (`Scorable`, `Printable`)
- Encapsulation through private fields and public methods
- Polymorphism in exam type handling

### 2. Exception Handling
- Custom exceptions (`StudentException`, `ExamException`)
- Input validation and error management
- Robust error messaging system with visual feedback

### 3. File I/O Operations
- Writing to external files (summary_result.txt, detailed_results.txt)
- Structured data output formatting with proper alignment
- BufferedWriter implementation with professional table layouts

### 4. Data Structures
- ArrayList implementation for student and exam management
- List interface usage
- Complex object relationships

### 5. User Interface
- Console-based menu system with ASCII art banner
- Color-coded output for better UX (ANSI color codes)
- Box-drawing characters for professional tables
- Score-based color coding (green ≥70%, yellow ≥50%, red <50%)
- Clear screen functionality for clean navigation
- Visual success/error indicators

### 6. Design Patterns
- Singleton pattern for exam management
- Strategy pattern for exam scoring
- Factory method for exam creation

## Features

- ✅ Add and manage student records
- ✅ Record Multiple Choice exam results with automatic scoring
- ✅ Record Essay exam results with grammar and content evaluation
- ✅ Generate summary reports (both on-screen and file)
- ✅ Generate detailed exam results with color-coded scores
- ✅ Comprehensive input validation and error handling
- ✅ Modern color-coded console interface
- ✅ Professional table formatting in output files
- ✅ Real-time statistics display (student count, exam count)
- ✅ Validated input for all entries (IDs, names, numbers, etc.)

## Visual Interface

The application features a modern terminal interface with:

- **ASCII Art Banner**: Eye-catching gradient-colored title
- **Color-Coded Menu**: Clear visual hierarchy with color indicators
- **Box-Drawing Tables**: Professional-looking data displays
- **Status Messages**: Visual success/error boxes
- **Score Visualization**: Color-coded scores (green/yellow/red)
- **Progress Indicators**: Real-time student and exam counts

Colors used:
- `BRIGHT_GREEN`: Success messages, high scores (≥70%)
- `YELLOW`: Warnings, medium scores (50-69%)
- `RED`: Errors, low scores (<50%)
- `BRIGHT_CYAN`: Table borders and headers
- `BRIGHT_BLUE`: Menu titles and highlights
- `ORANGE`: Prompts and special indicators
- `MAGENTA`: Essay exam type indicators

## Getting Started

### Prerequisites

- Java JDK 11 or higher
- Terminal/Console with ANSI color support (most modern terminals)
- Any Java IDE (Eclipse, IntelliJ IDEA, or NetBeans) or command line

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

1. **[+] Add Student** - Register a new student with ID, first name, and surname
2. **[*] Add Multiple Choice Exam Result** - Record MC exam with questions and answers
3. **[*] Add Essay Exam Result** - Record essay exam with grammar, content, and word count
4. **[=] Print Summary Result** - Generate and display summary table
5. **[=] Print Detailed Results** - Generate and display detailed results with scores
6. **[X] Quit** - Exit the application

### Input Validation

- **Student IDs**: Must be numeric, positive, and not empty
- **Names**: Must be alphabetic (2-30 characters), no numbers or special characters
- **Exam IDs**: Must be numeric and positive
- **Subjects**: Must be alphabetic with optional spaces
- **Duration**: Must be numeric, between 30-180 minutes
- **Multiple Choice**: 
  - Total questions: 10-50
  - Correct answers: Must not exceed total questions
- **Essay**:
  - Word limit: 500-10000 words
  - Answer: Cannot be empty
  - Grammar/Content marks: Must be numeric and positive
- **All numeric fields**: Validated for correct format and valid range

## Project Structure

```
ExamResults/
├── Student.java              # Student entity with validation
├── Exam.java                 # Abstract base class for exams
├── MultipleChoice.java       # Multiple choice exam implementation
├── Essay.java                # Essay exam implementation
├── ExamResult.java           # Result entity linking student and exam
├── ExamManagement.java       # Main application with UI
├── StudentException.java     # Custom student exception
├── ExamException.java        # Custom exam exception
├── Scorable.java            # Interface for scorable entities
├── Printable.java           # Interface for printable entities
├── summary_result.txt       # Generated summary report
└── detailed_results.txt     # Generated detailed report
```

## Testing

The application includes comprehensive validation for:
- ✓ Student ID format and uniqueness
- ✓ Student name format (alphabetic only)
- ✓ Exam parameter ranges and validity
- ✓ Input type checking (numeric vs. text)
- ✓ Empty input detection
- ✓ Boundary value testing
- ✓ All user entries validated before processing
- ✓ Error messages with clear guidance

## Data Storage

Results are stored in two professionally formatted text files:

### summary_result.txt
- Clean table layout with borders
- Columns: Student ID, Name, Exam ID, Subject
- Total record count at bottom

### detailed_results.txt
- Comprehensive table with all exam details
- Columns: Student ID, Name, Exam ID, Subject, Exam Type, Score
- Percentage-based score display
- Total record count at bottom

Both files feature:
- Proper column alignment
- Header and footer sections
- Professional borders using `=` and `-` characters
- Text truncation with "..." for long entries
- Consistent spacing throughout

## Output Examples

### Summary Results File
```
================================================================================
                          SUMMARY RESULTS REPORT                                
================================================================================
Student ID      | Name                           | Exam ID      | Subject        
--------------------------------------------------------------------------------
4               | John Smith                     | 1            | Math           
================================================================================
Total Records: 1
================================================================================
```

### Detailed Results File
```
====================================================================================================
                                  DETAILED RESULTS REPORT                                          
====================================================================================================
Student ID   | Name                      | Exam ID    | Subject            | Exam Type     | Score  
----------------------------------------------------------------------------------------------------
4            | John Smith                | 1          | Math               | Multi Choice  |    97%
====================================================================================================
Total Records: 1
====================================================================================================
```

## Credits

- **Developer**: Sergiy Kochenko
- **Institution**: ATU Donegal
- **Course**: Software Development
- **Academic Year**: 2025/2026
- **Project Type**: CA1 Assignment

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgments

- ATU Donegal for project requirements and guidance
- Java documentation for ANSI color codes and formatting
- Console UI design patterns for terminal applications
- Professional table formatting standards
