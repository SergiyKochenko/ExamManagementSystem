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
    - [Class Diagram](#class-diagram)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [User Manual](#user-manual)
    - [Starting the Application](#starting-the-application)
    - [Main Menu Navigation](#main-menu-navigation)
    - [Step-by-Step Guide](#step-by-step-guide)
      - [1. Adding a Student](#1-adding-a-student)
      - [2. Adding a Multiple Choice Exam](#2-adding-a-multiple-choice-exam)
      - [3. Adding an Essay Exam](#3-adding-an-essay-exam)
      - [4. Adding a Practical Exam](#4-adding-a-practical-exam)
      - [5. Viewing Summary Results](#5-viewing-summary-results)
      - [6. Viewing Detailed Results](#6-viewing-detailed-results)
      - [7. Exiting the Application](#7-exiting-the-application)
      - [8. Running the System Simulation (Demo)](#8-running-the-system-simulation-demo)
  - [Tips and Best Practices](#tips-and-best-practices)
    - [Common Error Messages](#common-error-messages)
  - [Usage](#usage)
    - [Input Validation](#input-validation)
  - [System Simulation (Demo)](#system-simulation-demo)
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

- Includes a demo System Simulation (option 8) that showcases realistic interactions among admin, lecturers, course, modules, students, and assessments, with per-module, per-student, and course averages.

## Technical Competencies

### 1. Object-Oriented Programming
- Implementation of inheritance (`Exam`, `MultipleChoice`, `Essay`, `Practical`)
- Use of interfaces (`Scorable`, `Printable`)
- Encapsulation through private fields and public methods
- Polymorphism in exam type handling
- Abstract classes with concrete implementations

### 2. Exception Handling
- Custom exceptions (`StudentException`, `ExamException`)
- Input validation and error management
- Robust error messaging system with visual feedback
- Automatic data correction for practical exams

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
- ✅ Record Practical exam results with implementation and viva marks (no time limit)
- ✅ Generate summary reports (both on-screen and file)
- ✅ Generate detailed exam results with color-coded scores
- ✅ Comprehensive input validation and error handling
- ✅ Modern color-coded console interface
- ✅ Professional table formatting in output files
- ✅ Real-time statistics display (student count, exam count)
- ✅ Validated input for all entries (IDs, names, numbers, etc.)
- ✅ Automatic mark adjustment for practical exams
- ✅ Reflection-based data access for flexible exam handling
- ✅ Run a System Simulation (option 8) demonstrating admin, lecturers, course, modules, enrollments, assessments, and averages

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
- `CYAN`: Practical exam type indicators

### Class Diagram

```text
+-----------------------------------------+
|              ExamManagement             |
+-----------------------------------------+
| - students : List<Student>              |
| - examResults : List<ExamResult>        |
| - scanner : Scanner                     |
+-----------------------------------------+
| + main(args : String[]) : void          |
| - findStudentById(...): Student         |
| - printSummaryResult(...): void         |
| - printDetailedResults(...): void       |
| - printSummaryResultOnScreen(...):void  |
| - printDetailedResultsOnScreen(...):void|
| - clearScreen() : void                  |
| - printBanner() : void                  |
| - printMenu(stu:int, ex:int): void      |
| - pause(scanner: Scanner): void         |
| + getExamSubject(exam: Exam): String    |
| + getExamId(exam: Exam): int            |
| - runSystemSimulation() : void          |
+-----------------------------------------+

              1      *
ExamManagement ─────────▶ Student
              1      *
ExamManagement ─────────▶ ExamResult


+-----------------------------------------------------------------+
|                           Student                               |
+-----------------------------------------------------------------+
| - studentId : int                                               |
| - firstName : String                                            |
| - surname : String                                              |
| - exams : List<Exam>                                            |
+-----------------------------------------------------------------+
| + Student(id:int, fn:String, sn:String) throws StudentException |
| + addExam(exam : Exam) : void                                   |
| + getStudentId() : int                                          |
| + getFirstName() : String                                       |
| + getSurname() : String                                         |
| + isValidStudentId(id:String):boolean [static]                  |
| + isValidStudentName(n:String):boolean [static]                 |
+-----------------------------------------------------------------+

              1      0..*
Student ───────────────▶ Exam


+-------------------------------------+
|             ExamResult              |
+-------------------------------------+
| - student : Student                 |
| - exam : Exam                       |
| - score : int                       |
+-------------------------------------+
| + getStudent() : Student            |
| + getExam() : Exam                  |
| + getScore() : int                  |
+-------------------------------------+

ExamResult ─────────▶ Student
ExamResult ─────────▶ Exam


+-------------------------------------+
|           Exam (abstract)           |
+-------------------------------------+
| - examId : int                      |
| - subject : String                  |
+-------------------------------------+
| + calculateScore() : double         |
+-------------------------------------+

                 ▲
                 │
       ┌─────────┼───────────────────────────────────────────┬────────────────────────────────────────┐
       │                                                     │                                        │
+-------------------------------------+   +-------------------------------------+   +-------------------------------------+
|           MultipleChoice            |   |               Essay                 |   |              Practical              |
+-------------------------------------+   +-------------------------------------+   +-------------------------------------+
| - duration : int                    |   | - duration : int                    |   | - implementation : int              |
| - correctAnswers : int              |   | - essayAnswer : String              |   | - viva : int                        |
| - noQuestions : int                 |   | - grammar : int                     |   | - totalMarks : int                  |
|                                     |   | - content : int                     |   |                                     |
|                                     |   | - wordLimit : int                   |   |                                     |
+-------------------------------------+   +-------------------------------------+   +-------------------------------------+
| + calculateScore() : double         |   | + calculateScore() : double         |   | + calculateScore() : double         |
+-------------------------------------+   +-------------------------------------+   +-------------------------------------+


+---------------------------+        +------------------------+
|      StudentException     |        |      ExamException     |
+---------------------------+        +------------------------+
| extends Exception         |        | extends Exception      |
+---------------------------+        +------------------------+

Note:
- Exam.examId and Exam.subject are accessed via reflection in ExamManagement.
```

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

## User Manual

### Starting the Application

1. Open your terminal or command prompt
2. Navigate to the project directory:
   ```bash
   cd "c:\Users\Sergiy\Desktop\Updated CA1 Softweare Development\ExamResults"
   ```
3. Run the application:
   ```bash
   java ExamResults.ExamManagement
   ```

The application will display a colorful ASCII banner and main menu.

### Main Menu Navigation

The main menu displays:
- **Option 1-8**: Main menu choices
- **Student count**: Number of registered students
- **Exam Results count**: Total exam records
- **Colored indicators**: 
  - Green numbers = Available options
  - Red number = Exit option
  - Yellow text = Statistics

To select an option, type the number (1-8) and press Enter.

### Step-by-Step Guide

#### 1. Adding a Student

**When to use**: Before adding any exam results, you must first register the student.

**Steps**:
1. Select option `1` from the main menu
2. Enter **Student ID** (numeric only, e.g., `12345`)
   - Must be a positive number
   - Cannot be empty
   - Example: `1001`

3. Enter **First Name** (alphabetic only)
   - No numbers or special characters
   - 2-30 characters
   - Example: `John`

4. Enter **Surname** (alphabetic only)
   - No numbers or special characters
   - 2-30 characters
   - Example: `Smith`

5. Press Enter after each input

**Success**: You'll see a green box confirming "Student added successfully!"

**Example**:
```
Enter Student ID: 1001
Enter First Name: John
Enter Surname: Smith

┌─────────────────────────────────────────────────────────────┐
│  [OK] SUCCESS: Student added successfully!                  │
└─────────────────────────────────────────────────────────────┘
```

---

#### 2. Adding a Multiple Choice Exam

**When to use**: To record results for MCQ/objective type exams.

**Steps**:
1. Select option `2` from the main menu
2. Enter **Student ID** of an existing student (e.g., `1001`)
3. Enter **Exam ID** (unique number for this exam, e.g., `101`)
4. Enter **Subject** (alphabetic with spaces, e.g., `Mathematics`)
5. Enter **Duration** in minutes (30-180, e.g., `120`)
6. Enter **Total Questions** (10-50, e.g., `40`)
7. Enter **Correct Answers** (must be ≤ total questions, e.g., `35`)

**Automatic Calculation**: The system automatically calculates the score percentage.

**Success**: Green box shows "Multiple Choice Exam added successfully!" with the calculated score.

**Example**:
```
Enter Student ID: 1001
Enter Exam ID: 101
Enter Subject: Mathematics
Enter Duration (in minutes): 120
Enter Total Questions: 40
Enter Correct Answers: 35

┌─────────────────────────────────────────────────────────────┐
│  [OK] SUCCESS: Multiple Choice Exam added successfully!     │
│  Score: 87.5%                                               │
└─────────────────────────────────────────────────────────────┘
```

---

#### 3. Adding an Essay Exam

**When to use**: To record essay/written exam results with grammar and content evaluation.

**Steps**:
1. Select option `3` from the main menu
2. Enter **Student ID** (existing student)
3. Enter **Exam ID** (unique)
4. Enter **Subject** (e.g., `English Literature`)
5. Enter **Duration** in minutes (30-180)
6. Enter **Essay Answer** (any text, cannot be empty)
7. Enter **Grammar Marks** (numeric, e.g., `45`)
8. Enter **Content Marks** (numeric, e.g., `38`)
9. Enter **Word Limit** (500-10000, e.g., `1500`)

**Automatic Calculation**: Score is calculated based on grammar and content marks.

**Example**:
```
Enter Student ID: 1001
Enter Exam ID: 102
Enter Subject: English Literature
Enter Duration (in minutes): 90
Enter Essay Answer: [Student's essay text here]
Enter Grammar Marks: 45
Enter Content Marks: 38
Enter Word Limit: 1500

┌─────────────────────────────────────────────────────────────┐
│  [OK] SUCCESS: Essay Exam added successfully!               │
│  Score: 83%                                                 │
└─────────────────────────────────────────────────────────────┘
```

---

#### 4. Adding a Practical Exam

**When to use**: To record practical/lab exam results with implementation and viva components.

**Special Feature**: No duration/time limit required for practical exams!

**Steps**:
1. Select option `4` from the main menu
2. Enter **Student ID** (existing student)
3. Enter **Exam ID** (unique)
4. Enter **Subject** (e.g., `Programming`)
5. Enter **Implementation Marks** (e.g., `40`)
6. Enter **Viva Marks** (e.g., `15`)
7. Enter **Total Marks** (e.g., `60`)

**Auto-Adjustment**: If implementation + viva marks exceed total marks, the system automatically adjusts total marks to match the sum.

**Automatic Calculation**: Score = (implementation + viva) / total marks × 100%

**Example**:
```
Enter Student ID: 1001
Enter Exam ID: 103
Enter Subject: Programming
Enter Implementation Marks: 40
Enter Viva Marks: 15
Enter Total Marks: 60

┌─────────────────────────────────────────────────────────────┐
│  [OK] SUCCESS: Practical Exam added successfully!           │
│  Score: 91.67%                                              │
└─────────────────────────────────────────────────────────────┘
```

**Note**: If you entered Total Marks = 50, but Implementation (40) + Viva (15) = 55, the system will automatically adjust Total Marks to 55.

---

#### 5. Viewing Summary Results

**When to use**: To see a quick overview of all exam records.

**Steps**:
1. Select option `5` from the main menu
2. The system displays a table on screen with:
   - Student ID
   - Student Name
   - Exam ID
   - Subject (abbreviated)
3. The system also saves this data to `summary_result.txt`

**On-Screen Display**: Colorful table with borders
**File Output**: Plain text table in summary_result.txt

**Example Output**:
```
╔════════════════════════════════════════════════════════════════════════════════╗
║                          [=] SUMMARY RESULTS                                   ║
╠════════════════════════════╦══════════════════════════════╦═══════════╦════════╣
║ Student ID                 ║ Name                         ║ Exam ID   ║ Subj   ║
╠════════════════════════════╬══════════════════════════════╬═══════════╬════════╣
║ 1001                       ║ John Smith                   ║ 101       ║ Math   ║
║ 1001                       ║ John Smith                   ║ 102       ║ Engl   ║
║ 1001                       ║ John Smith                   ║ 103       ║ Prog   ║
╚════════════════════════════╩══════════════════════════════╩═══════════╩════════╝
Total records: 3
```

---

#### 6. Viewing Detailed Results

**When to use**: To see complete exam information including scores and exam types.

**Steps**:
1. Select option `6` from the main menu
2. The system displays a detailed table with:
   - Student ID
   - Student Name
   - Exam ID
   - Subject (full name)
   - Exam Type (Multi/Essay/Practical)
   - Score percentage (color-coded)
3. The system also saves this data to `detailed_results.txt`

**Color Coding**:
- **Green scores** (≥70%): Excellent performance
- **Yellow scores** (50-69%): Good performance
- **Red scores** (<50%): Needs improvement

**Example Output**:
```
╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗
║                                      [=] DETAILED RESULTS                                             ║
╠══════════════╦══════════════════════════════════╦═══════════╦═══════════════════════╦═════════╦═══════╣
║ Student ID   ║ Name                             ║ Exam ID   ║ Subject               ║ Type    ║ Score ║
╠══════════════╬══════════════════════════════════╬═══════════╬═══════════════════════╬═════════╬═══════╣
║ 1001         ║ John Smith                       ║ 101       ║ Mathematics           ║ Multi   ║  87%  ║
║ 1001         ║ John Smith                       ║ 102       ║ English Literature    ║ Essay   ║  83%  ║
║ 1001         ║ John Smith                       ║ 103       ║ Programming           ║ Practical║ 92% ║
╚══════════════╩══════════════════════════════════╩═══════════╩═══════════════════════╩═════════╩═══════╝
Total records: 3
```

---

#### 7. Exiting the Application

**Steps**:
1. Select option `7` from the main menu
2. The application closes immediately
3. All data in memory is lost (only saved reports remain)

**Important**: Make sure to generate reports (options 5 & 6) before exiting if you want to keep the data!

---

#### 8. Running the System Simulation (Demo)

**When to use**: To see a demonstration of the system's capabilities with simulated data.

**Steps**:
1. Select option `8` from the main menu

**What it does**:
  - Creates a mock admin and lecturers
  - Defines a course (BSc in Computing) with modules: PROG101, DSA201, SE202, DBS102
  - Enrolls demo students (Jane Doe, Mark Lee, Sara Khan)
  - Adds assessments (Multiple Choice, Essay, Practical)
  - Prints:
    - Modules & assigned lecturers
    - Enrolled students
    - Assessments by module with scores
    - Per-module averages
    - Per-student averages (color-coded)
    - Overall course average

**Purpose**: Demonstrates realistic interactions and verifies that the system’s scoring, aggregation, and reporting work end-to-end.

## Tips and Best Practices

1. **Always add students first**: You cannot add exam results without a registered student.

2. **Use unique Exam IDs**: Each exam should have a different ID to avoid confusion.

3. **Keep Student IDs consistent**: Use the same Student ID for all exams of the same student.

4. **Generate reports regularly**: Save your work by generating summary and detailed reports frequently.

5. **Check color codes**: Green = success, Red = error, Yellow = warning/info.

6. **Read error messages**: They provide specific guidance on what needs to be corrected.

7. **For Practical exams**: Don't worry about the total marks - the system auto-adjusts if needed.

8. **Press Enter to continue**: After any operation, press Enter to return to the main menu.

### Common Error Messages

| Error Message | Cause | Solution |
|--------------|-------|----------|
| "Invalid student ID" | Non-numeric or empty input | Enter numbers only (e.g., 1001) |
| "Invalid first name" | Contains numbers/symbols | Use letters only (e.g., John) |
| "Student not found" | Student ID doesn't exist | Add the student first (option 1) |
| "Invalid duration" | Duration not 30-180 minutes | Enter a value between 30 and 180 |
| "Invalid correct answers" | More than total questions | Correct answers ≤ total questions |
| "Essay answer cannot be empty" | No text entered | Type some text for the essay |
| "Implementation and viva marks must be non-negative" | Negative numbers entered | Enter 0 or positive numbers |
| "Invalid choice. Please enter a number between 1 and 8" | Wrong menu option | Type a number from 1 to 8 |

**General Troubleshooting**:
- If you see a red error message, read it carefully
- Check that you're entering the correct data type (numbers vs. text)
- Make sure the student exists before adding exams
- Press Enter after each input
- If stuck, restart the application

## Usage

The system provides a menu-driven interface with the following options:

1. **[+] Add Student** - Register a new student with ID, first name, and surname
2. **[*] Add Multiple Choice Exam Result** - Record MC exam with questions and answers
3. **[*] Add Essay Exam Result** - Record essay exam with grammar, content, and word count
4. **[*] Add Practical Exam Result** - Record practical exam with implementation and viva marks (no duration)
5. **[=] Print Summary Result** - Generate and display summary table
6. **[=] Print Detailed Results** - Generate and display detailed results with scores
7. **[X] Quit** - Exit the application
8. **[>] Run System Simulation (demo)** - Generate demo data (admin, lecturers, course/modules, assessments) and print module averages, per-student averages, and overall course average

### Input Validation

- **Student IDs**: Must be numeric, positive, and not empty
- **Names**: Must be alphabetic (2-30 characters), no numbers or special characters
- **Exam IDs**: Must be numeric and positive
- **Subjects**: Must be alphabetic with optional spaces
- **Duration**: Must be numeric, between 30-180 minutes (not applicable for Practical exams)
- **Multiple Choice**: 
  - Total questions: 10-50
  - Correct answers: Must not exceed total questions
- **Essay**:
  - Word limit: 500-10000 words
  - Answer: Cannot be empty
  - Grammar/Content marks: Must be numeric and positive
- **Practical**:
  - Implementation marks: Must be non-negative
  - Viva marks: Must be non-negative
  - Total marks: Automatically adjusted if less than sum of implementation + viva
  - No time limit (duration field not used)
- **All numeric fields**: Validated for correct format and valid range

## System Simulation (Demo)
- How to run: start the app and choose option `8` from the main menu.
- What it does:
  - Creates a mock admin and lecturers
  - Defines a course (BSc in Computing) with modules: PROG101, DSA201, SE202, DBS102
  - Enrolls demo students (Jane Doe, Mark Lee, Sara Khan)
  - Adds assessments (Multiple Choice, Essay, Practical)
  - Prints:
    - Modules & assigned lecturers
    - Enrolled students
    - Assessments by module with scores
    - Per-module averages
    - Per-student averages (color-coded)
    - Overall course average
- Purpose: Demonstrates realistic interactions and verifies that the system’s scoring, aggregation, and reporting work end-to-end.

## Project Structure

```
ExamResults/
├── Student.java              # Student entity with validation
├── Exam.java                 # Abstract base class for exams
├── MultipleChoice.java       # Multiple choice exam implementation
├── Essay.java                # Essay exam implementation
├── Practical.java            # Practical exam implementation (NEW)
├── ExamResult.java           # Result entity linking student and exam
├── ExamManagement.java       # Main application with UI
├── StudentException.java     # Custom student exception
├── ExamException.java        # Custom exam exception
├── Scorable.java            # Interface for scorable entities
├── Printable.java           # Interface for printable entities
├── summary_result.txt       # Generated summary report
├── detailed_results.txt     # Generated detailed report
└── README.md                # This file
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
- ✓ Automatic correction for practical exam total marks
- ✓ Reflection-based field access for exam data
- Use the System Simulation (option 8) to quickly verify scoring logic and aggregated statistics across modules and students.

## Data Storage

Results are stored in two professionally formatted text files:

### summary_result.txt
- Clean table layout with borders
- Columns: Student ID, Name, Exam ID, Subject
- Total record count at bottom

### detailed_results.txt
- Comprehensive table with all exam details
- Columns: Student ID, Name, Exam ID, Subject, Exam Type, Score
- Exam types: Multi Choice, Essay, Practical
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
1               | John Smith                     | 1            | Math           
1               | John Smith                     | 2            | Programming    
================================================================================
Total Records: 2
================================================================================
```

### Detailed Results File
```
====================================================================================================
                                  DETAILED RESULTS REPORT                                          
====================================================================================================
Student ID   | Name                      | Exam ID    | Subject            | Exam Type     | Score  
----------------------------------------------------------------------------------------------------
1            | John Smith                | 1          | Math               | Multi Choice  |    97%
1            | John Smith                | 2          | Programming        | Practical     |    85%
====================================================================================================
Total Records: 2
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
- Reflection API for dynamic field access
