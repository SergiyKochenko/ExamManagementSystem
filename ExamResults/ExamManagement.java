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
    // Added styles/colors
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";
    public static final String DIM = "\u001B[2m";

    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<ExamResult> examResults = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            printBanner();
            printMenu(students.size(), examResults.size());
            System.out.print(ORANGE + "Enter your choice (1-6): " + RESET);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 6) {
                    System.out.println(RED + "Invalid choice. Please enter a number between 1 and 6." + RESET);
                    pause(scanner);
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter Student ID: ");
                        String studentIdString = scanner.nextLine().trim();
                        if (studentIdString.isEmpty() || !Student.isValidStudentId(studentIdString)) {
                            System.out.println(RED + "Invalid student ID. Please enter only numeric characters." + RESET);
                            pause(scanner);
                            break;
                        }

                        int studentIdInt = Integer.parseInt(studentIdString);

                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine().trim();
                        if (firstName.isEmpty() || !Student.isValidStudentName(firstName)) {
                            System.out.println(RED + "Invalid first name. Please enter only alphabetic characters." + RESET);
                            pause(scanner);
                            break;
                        }

                        System.out.print("Enter Surname: ");
                        String surname = scanner.nextLine().trim();
                        if (surname.isEmpty() || !Student.isValidStudentName(surname)) {
                            System.out.println(RED + "Invalid surname. Please enter only alphabetic characters." + RESET);
                            pause(scanner);
                            break;
                        }

                        try {
                            Student student = new Student(studentIdInt, firstName, surname);
                            students.add(student);
                            System.out.println(GREEN + "✔ Student added successfully." + RESET);
                            System.out.println(BLUE + "1. Add Student" + RESET);
                        } catch (StudentException e) {
                            System.out.println(e.getMessage());
                        }
                        pause(scanner);
                        break;

                    case 2:
                        try {
                            System.out.print("Enter Student ID: ");
                            String studentIdInput = scanner.nextLine().trim();
                            if (studentIdInput.isEmpty() || !Student.isValidStudentId(studentIdInput)) {
                                System.out.println(RED + "Invalid student ID. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int studentId = Integer.parseInt(studentIdInput);

                            Student student = findStudentById(students, studentId);
                            if (student == null) {
                                System.out.println(RED + "Student not found." + RESET);
                                pause(scanner);
                                break;
                            }

                            System.out.print("Enter Exam ID: ");
                            String examIdInput = scanner.nextLine().trim();
                            if (examIdInput.isEmpty() || !examIdInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid exam ID. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int examId = Integer.parseInt(examIdInput);

                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine().trim();
                            if (subject.isEmpty() || !subject.matches("[a-zA-Z ]+")) {
                                System.out.println(RED + "Invalid subject. Please enter only alphabetic characters and spaces." + RESET);
                                pause(scanner);
                                break;
                            }

                            System.out.print("Enter Duration (in minutes): ");
                            String durationInput = scanner.nextLine().trim();
                            if (durationInput.isEmpty() || !durationInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid duration. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int duration = Integer.parseInt(durationInput);

                            System.out.print("Enter Total Questions: ");
                            String noQuestionsInput = scanner.nextLine().trim();
                            if (noQuestionsInput.isEmpty() || !noQuestionsInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid number of questions. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int noQuestions = Integer.parseInt(noQuestionsInput);

                            System.out.print("Enter Correct Answers: ");
                            String correctAnswersInput = scanner.nextLine().trim();
                            if (correctAnswersInput.isEmpty() || !correctAnswersInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid correct answers. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int correctAnswers = Integer.parseInt(correctAnswersInput);

                            try {
                                Exam exam = new MultipleChoice(examId, subject, duration, correctAnswers, noQuestions);
                                student.addExam(exam);
                                double score = ((MultipleChoice) exam).calculateScore();
                                ExamResult result = new ExamResult(student, exam, (int) score);
                                examResults.add(result);
                                System.out.println(ORANGE + "✔ Multiple Choice Exam result added successfully." + RESET );
                                System.out.println(BLUE + "2. Add Multiple Choice Exam Result" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please enter only numeric characters and try again." + RESET);
                            scanner.nextLine();
                        }
                        pause(scanner);
                        broke: break;

                    case 3:
                        try {
                            System.out.print("Enter Student ID: ");
                            String studentIdInput = scanner.nextLine().trim();
                            if (studentIdInput.isEmpty() || !Student.isValidStudentId(studentIdInput)) {
                                System.out.println(RED + "Invalid student ID. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int studentId = Integer.parseInt(studentIdInput);

                            Student student = findStudentById(students, studentId);
                            if (student == null) {
                                System.out.println(RED + "Student not found." + RESET);
                                pause(scanner);
                                break;
                            }

                            System.out.print("Enter Exam ID: ");
                            String examIdInput = scanner.nextLine().trim();
                            if (examIdInput.isEmpty() || !examIdInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid exam ID. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int examId = Integer.parseInt(examIdInput);

                            System.out.print("Enter Subject: ");
                            String subject = scanner.nextLine().trim();
                            if (subject.isEmpty() || !subject.matches("[a-zA-Z ]+")) {
                                System.out.println(RED + "Invalid subject. Please enter only alphabetic characters and spaces." + RESET);
                                pause(scanner);
                                break;
                            }

                            System.out.print("Enter Duration (in minutes): ");
                            String durationInput = scanner.nextLine().trim();
                            if (durationInput.isEmpty() || !durationInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid duration. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int duration = Integer.parseInt(durationInput);

                            System.out.print("Enter Essay Answer: ");
                            String essayAnswer = scanner.nextLine().trim();
                            if (essayAnswer.isEmpty()) {
                                System.out.println(RED + "Essay answer cannot be empty." + RESET);
                                pause(scanner);
                                break;
                            }

                            System.out.print("Enter Grammar Marks: ");
                            String grammarInput = scanner.nextLine().trim();
                            if (grammarInput.isEmpty() || !grammarInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid grammar marks. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int grammar = Integer.parseInt(grammarInput);

                            System.out.print("Enter Content Marks: ");
                            String contentInput = scanner.nextLine().trim();
                            if (contentInput.isEmpty() || !contentInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid content marks. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int content = Integer.parseInt(contentInput);

                            System.out.print("Enter Word Limit: ");
                            String wordLimitInput = scanner.nextLine().trim();
                            if (wordLimitInput.isEmpty() || !wordLimitInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid word limit. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int wordLimit = Integer.parseInt(wordLimitInput);

                            try {
                                Exam exam = new Essay(examId, subject, duration, essayAnswer, grammar, content, wordLimit);
                                student.addExam(exam);
                                double score = ((Essay) exam).calculateScore();
                                ExamResult result = new ExamResult(student, exam, (int) score);
                                examResults.add(result);
                                System.out.println(ORANGE + "✔ Essay Exam result added successfully." + RESET);
                                System.out.println(BLUE + "3. Add Essay Exam Result" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                            scanner.nextLine();
                        }
                        pause(scanner);
                        break;

                    case 4:
                        try {
                            printSummaryResult(examResults);
                            printSummaryResultOnScreen(examResults);
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        pause(scanner);
                        break;

                    case 5:
                        try {
                            printDetailedResults(examResults);
                            printDetailedResultsOnScreen(examResults);
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        pause(scanner);
                        break;

                    case 6:
                        scanner.close();
                        return;

                    default:
                        System.out.println(RED + "Invalid choice. Please try again." + RESET);
                        pause(scanner);
                }

            } catch (InputMismatchException e) {
                System.out.println(RED + "Invalid input. Please enter only numeric characters." + RESET);
                scanner.nextLine();
                pause(scanner);
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
        System.out.println(CYAN + "┌──────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN + "│ " + BOLD + "SUMMARY RESULTS" + RESET + CYAN + "                                                              │" + RESET);
        System.out.println(CYAN + "├────────────────────────────┬──────────────────────────────┬───────────┬──────┤" + RESET);
        System.out.println(String.format("│ %-26s │ %-28s │ %-9s │ %-4s │", "Student ID", "Name", "Exam ID", "Subj"));
        System.out.println(CYAN + "├────────────────────────────┼──────────────────────────────┼───────────┼──────┤" + RESET);

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String name = student.getFirstName() + " " + student.getSurname();
            String subj = exam.getSubject();
            if (subj.length() > 4) subj = subj.substring(0, 4);
            System.out.println(String.format("│ %-26d │ %-28s │ %-9d │ %-4s │",
                    student.getStudentId(), name, exam.getExamId(), subj));
        }

        System.out.println(CYAN + "└────────────────────────────┴──────────────────────────────┴───────────┴──────┘" + RESET);
        System.out.println(DIM + "Total rows: " + examResults.size() + RESET);
    }

    private static void printDetailedResultsOnScreen(List<ExamResult> examResults) {
        System.out.println(CYAN + "┌─────────────────────────────────────────────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(CYAN + "│ " + BOLD + "DETAILED RESULTS" + RESET + CYAN + "                                                                                    │" + RESET);
        System.out.println(CYAN + "├──────────────┬──────────────────────────────────┬───────────┬───────────────────────┬─────────┬─────┤" + RESET);
        System.out.println(String.format("│ %-12s │ %-32s │ %-9s │ %-21s │ %-7s │ %-3s │",
                "Student ID", "Name", "Exam ID", "Subject", "Type", "Scr"));
        System.out.println(CYAN + "├──────────────┼──────────────────────────────────┼───────────┼───────────────────────┼─────────┼─────┤" + RESET);

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String examType = (exam instanceof MultipleChoice) ? (YELLOW + "Multi" + RESET) : (BLUE + "Essay" + RESET);
            String name = student.getFirstName() + " " + student.getSurname();
            String subject = exam.getSubject();
            if (subject.length() > 21) subject = subject.substring(0, 21);
            System.out.println(String.format("│ %-12d │ %-32s │ %-9d │ %-21s │ %-7s │ %-3d │",
                    student.getStudentId(), name, exam.getExamId(), subject, examType, result.getScore()));
        }

        System.out.println(CYAN + "└──────────────┴──────────────────────────────────┴───────────┴───────────────────────┴─────────┴─────┘" + RESET);
        System.out.println(DIM + "Total rows: " + examResults.size() + RESET);
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

    // UI helpers
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printBanner() {
        System.out.println(GREEN + "╔═══════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN  + "║" + YELLOW + " W E L C O M E   T O  " + WHITE + " E X A M   M A N A G E M E N T   S Y S T E M " + CYAN + "║" + RESET);
        System.out.println(GREEN + "╚═══════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    private static void printMenu(int studentsCount, int examsCount) {
        System.out.println(CYAN + "┌────────────────────────────── MENU ───────────────────────────────┐" + RESET);
        System.out.println(String.format("│ %-63s   │", "1. Add Student"));
        System.out.println(String.format("│ %-63s   │", "2. Add Multiple Choice Exam Result"));
        System.out.println(String.format("│ %-63s   │", "3. Add Essay Exam Result"));
        System.out.println(String.format("│ %-63s   │", "4. Print Summary Result"));
        System.out.println(String.format("│ %-63s   │", "5. Print Detailed Results"));
        System.out.println(String.format("│ %-63s   │", "6. Quit"));
        System.out.println(CYAN + "├───────────────────────────────────────────────────────────────────┤" + RESET);
        System.out.println(String.format("│ %-31s %-31s                     │", "Students: " + GREEN + studentsCount + RESET, "Exam results: " + GREEN + examsCount + RESET));
        System.out.println(CYAN + "└───────────────────────────────────────────────────────────────────┘" + RESET);
    }

    private static void pause(Scanner scanner) {
        System.out.print(DIM + "Press Enter to continue..." + RESET);
        scanner.nextLine();
    }
}
