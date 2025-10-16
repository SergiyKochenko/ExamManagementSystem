# Exam Management System

This is a Java console application for managing student exam results. The system allows for tracking multiple-choice and essay exams, student records, and generating detailed reports.

![ExamManagement Console](screenshots/console.png)

## Features

- Add and manage student records
- Record Multiple Choice exam results
- Record Essay exam results
- Generate summary reports
- Generate detailed exam results
- Input validation and error handling
- Color-coded console interface

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

- Student names must be 2-30 characters long
- Multiple Choice exams must have 10-50 questions
- Essay word limits must be between 500-10000 words
- Exam duration must be between 30-180 minutes

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

## Data Storage

Results are stored in two formats:
- summary_result.txt - Basic exam information
- detailed_results.txt - Comprehensive exam details

## Credits

- Developed by [Your Name]
- For Code Institute Java Development Course
- Year: 2023

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

## Acknowledgments

- Code Institute for project requirements and guidance
- [Any other acknowledgments]
