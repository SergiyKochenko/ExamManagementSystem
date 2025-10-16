package ExamResults;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class ExamManagement {

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001B[33m";
    public static final String ORANGE = "\u001B[38;5;208m";

    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<ExamResult> examResults = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(GREEN+"""
            =====================================
              Welcome to Exam Management System
            =====================================
            """+RESET);
            System.out.println(BLUE + "1. Add Student");
            System.out.println("2. Add Multiple Choice Exam Result");
            System.out.println("3. Add Essay Exam Result");
            System.out.println(YELLOW + "4. Print Summary Result");
            System.out.println("5. Print Detailed Results");
            System.out.println("6. Quit");
            System.out.print(ORANGE + "Enter your choice(only numbers allowed): " + RESET);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 6) {
                    System.out.println(RED + "Invalid choice. Please enter a number between 1 and 6." + RESET);
                    continue;  // Ask for input again
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String studentIdString = scanner.nextLine();
                        if (!Student.isValidStudentId(studentIdString)) {
                            System.out.println(RED + "Invalid student ID. Please enter only numeric characters." + RESET);
                            break;
                        }

                        int studentIdInt = Integer.parseInt(studentIdString);

                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        if (!Student.isValidStudentName(firstName)) {
                            System.out.println(RED + "Invalid first name. Please enter only alphabetic characters." + RESET);
                            break;
                        }

                        System.out.print("Enter Surname: ");
                        String surname = scanner.nextLine();
                        if (!Student.isValidStudentName(surname)) {
                            System.out.println(RED + "Invalid surname. Please enter only alphabetic characters." + RESET);
                            break;
                        }

                        try {
                            Student student = new Student(studentIdInt, firstName, surname);
                            students.add(student);
                            System.out.println(ORANGE +"Student added successfully." +RESET);
                            System.out.println(BLUE + "1. Add Student" + RESET);
                        } catch (StudentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("Enter Student ID: ");
                            int studentId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Exam ID: ");
                            int examId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine();

                            System.out.print("Enter Duration (in minutes): ");
                            int duration = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Total Questions: ");
                            int noQuestions = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Correct Answers: ");
                            int correctAnswers = scanner.nextInt();
                            scanner.nextLine();

                            Student student = findStudentById(students, studentId);
                            if (student == null) {
                                System.out.println(RED + "Student not found." + RESET);
                                break;
                            }

                            try {
                                Exam exam = new MultipleChoice(examId, subject, duration, correctAnswers, noQuestions);
                                student.addExam(exam);
                                double score = ((MultipleChoice) exam).calculateScore();
                                ExamResult result = new ExamResult(student, exam, (int) score);
                                examResults.add(result);
                                System.out.println(ORANGE + "Multiple Choice Exam result added successfully." + RESET );
                                System.out.println(BLUE + "2. Add Multiple Choice Exam Result" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please enter only numeric characters and try again." + RESET);
                            scanner.nextLine();
                        }
                        break;

                    case 3:
                        try {
                            System.out.print("Enter Student ID: ");
                            int studentId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Exam ID: ");
                            int examId = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine();

                            System.out.print("Enter Duration (in minutes): ");
                            int duration = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Essay Answer: ");
                            String essayAnswer = scanner.nextLine();

                            System.out.print("Enter Grammar Marks: ");
                            int grammar = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Content Marks: ");
                            int content = scanner.nextInt();
                            scanner.nextLine();

                            System.out.print("Enter Word Limit: ");
                            int wordLimit = scanner.nextInt();
                            scanner.nextLine();

                            Student student = findStudentById(students, studentId);
                            if (student == null) {
                                System.out.println(RED + "Student not found." + RESET);
                                break;
                            }

                            try {
                                Exam exam = new Essay(examId, subject, duration, essayAnswer, grammar, content, wordLimit);
                                student.addExam(exam);
                                double score = ((Essay) exam).calculateScore();
                                ExamResult result = new ExamResult(student, exam, (int) score);
                                examResults.add(result);
                                System.out.println(ORANGE + "Essay Exam result added successfully." + RESET);
                                System.out.println(BLUE + "3. Add Essay Exam Result" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                            scanner.nextLine();
                        }
                        break;

                    case 4:
                        try {
                            printSummaryResult(examResults);
                            printSummaryResultOnScreen(examResults); // Call the new method
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            printDetailedResults(examResults);
                            printDetailedResultsOnScreen(examResults); // Call the new method
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        break;

                    case 6:
                        scanner.close();
                        return;

                    default:
                        System.out.println(RED + "Invalid choice. Please try again." + RESET);
                }

            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter only numeric characters." + RESET);
                scanner.nextLine();  // Consume invalid input
            }
        }
    }

    private static Student findStudentById(List<Student> students, int studentId) {
        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    private static void printSummaryResultOnScreen(List<ExamResult> examResults) {
        System.out.println("-------------------------------------------------------");
        System.out.println(String.format("%-22s%-30s%-25s%-25s", "Student ID", "Name", "Exam ID", "Subject"));
        System.out.println("-------------------------------------------------------");

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            // Use full name (firstName + " " + surname)
            System.out.println(String.format("%-22d%-30s%-25d%-25s",
                    student.getStudentId(), student.getFirstName() + " " + student.getSurname(),
                    exam.getExamId(), exam.getSubject()));
        }
    }

    private static void printDetailedResultsOnScreen(List<ExamResult> examResults) {
        System.out.println("-------------------------------------------------------");
        System.out.println(String.format("%-22s%-30s%-25s%-25s%-25s%-25s",
                "Student ID", "Name", "Exam ID", "Subject", "Exam Type", "Score"));
        System.out.println("-------------------------------------------------------");

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String examType = (exam instanceof MultipleChoice) ? "Multi Choice" : "Essay";
            // Use full name (firstName + " " + surname)
            System.out.println(String.format("%-22d%-30s%-25d%-25s%-25s%-25d",
                    student.getStudentId(), student.getFirstName() + " " + student.getSurname(),
                    exam.getExamId(), exam.getSubject(), examType, result.getScore()));
        }
    }


    private static void printSummaryResult(List<ExamResult> examResults) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("summary_result.txt"));
        writer.write("-------------------------------------------------------\n");
        writer.write(String.format("%-22s%-30s%-25s%-25s\n", "Student ID", "Name", "Exam ID", "Subject"));
        writer.write("-------------------------------------------------------\n");

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            // Use full name (firstName + " " + surname)
            writer.write(String.format("%-22d%-30s%-25d%-25s\n",
                    student.getStudentId(), student.getFirstName() + " " + student.getSurname(),
                    exam.getExamId(), exam.getSubject()));
        }

        writer.close();
        System.out.println(ORANGE + "Summary result written to summary_result.txt and screen display" + RESET);
    }

    private static void printDetailedResults(List<ExamResult> examResults) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("detailed_results.txt"));
        writer.write("-------------------------------------------------------\n");
        writer.write(String.format("%-22s%-30s%-25s%-25s%-25s%-25s\n",
                "Student ID", "Name", "Exam ID", "Subject", "Exam Type", "Score"));
        writer.write("-------------------------------------------------------\n");

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String examType = (exam instanceof MultipleChoice) ? "Multi Choice" : "Essay";
            // Use full name (firstName + " " + surname)
            writer.write(String.format("%-22d%-30s%-25d%-25s%-25s%-25d\n",
                    student.getStudentId(), student.getFirstName() + " " + student.getSurname(),
                    exam.getExamId(), exam.getSubject(), examType, result.getScore()));
        }

        writer.close();
        System.out.println(ORANGE + "Detailed results written to detailed_results.txt and screen display" + RESET);
    }
}
