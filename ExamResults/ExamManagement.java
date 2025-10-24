package ExamResults;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
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
    public static final String MAGENTA = "\u001B[35m";
    public static final String BRIGHT_GREEN = "\u001B[92m";
    public static final String BRIGHT_BLUE = "\u001B[94m";
    public static final String BRIGHT_CYAN = "\u001B[96m";

    
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        List<ExamResult> examResults = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            printBanner();
            printMenu(students.size(), examResults.size());
            System.out.print(ORANGE + "Enter your choice (1-8): " + RESET);

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 8) {
                    System.out.println(RED + "Invalid choice. Please enter a number between 1 and 8." + RESET);
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
                            System.out.println();
                            System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
                            System.out.println(BRIGHT_GREEN + "│  [OK] SUCCESS: Student added successfully!                  │" + RESET);
                            System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
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
                                System.out.println();
                                System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  [OK] SUCCESS: Multiple Choice Exam added successfully!     │" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  Score: " + BOLD + score + "%" + RESET + BRIGHT_GREEN + "                                                  │" + RESET);
                                System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please enter only numeric characters and try again." + RESET);
                            scanner.nextLine();
                        }
                        pause(scanner);
                        break;

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
                                System.out.println();
                                System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  [OK] SUCCESS: Essay Exam added successfully!               │" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  Score: " + BOLD + score + "%" + RESET + BRIGHT_GREEN + "                                                  │" + RESET);
                                System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
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
                        // Add Practical Exam
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

                            // --- Duration prompt and validation are skipped for Practical Exam ---

                            System.out.print("Enter Implementation Marks: ");
                            String implInput = scanner.nextLine().trim();
                            if (implInput.isEmpty() || !implInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid marks. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int implementation = Integer.parseInt(implInput);

                            System.out.print("Enter Viva Marks: ");
                            String vivaInput = scanner.nextLine().trim();
                            if (vivaInput.isEmpty() || !vivaInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid marks. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int viva = Integer.parseInt(vivaInput);

                            System.out.print("Enter Total Marks: ");
                            String totalInput = scanner.nextLine().trim();
                            if (totalInput.isEmpty() || !totalInput.matches("\\d+")) {
                                System.out.println(RED + "Invalid total marks. Please enter only numeric characters." + RESET);
                                pause(scanner);
                                break;
                            }
                            int totalMarks = Integer.parseInt(totalInput);

                            try {
                                Exam exam = new Practical(examId, subject, implementation, viva, totalMarks);
                                student.addExam(exam);
                                double score = ((Practical) exam).calculateScore();
                                ExamResult result = new ExamResult(student, exam, (int) score);
                                examResults.add(result);
                                System.out.println();
                                System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  [OK] SUCCESS: Practical Exam added successfully!           │" + RESET);
                                System.out.println(BRIGHT_GREEN + "│  Score: " + BOLD + score + "%" + RESET + BRIGHT_GREEN + "                                              │" + RESET);
                                System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
                            } catch (ExamException e) {
                                System.out.println(e.getMessage());
                            }
                        } catch (Exception e) {
                            System.out.println(RED + "Invalid input. Please try again." + RESET);
                            scanner.nextLine();
                        }
                        pause(scanner);
                        break;

                    case 5:
                        try {
                            printSummaryResult(examResults);
                            printSummaryResultOnScreen(examResults);
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        pause(scanner);
                        break;

                    case 6:
                        try {
                            printDetailedResults(examResults);
                            printDetailedResultsOnScreen(examResults);
                        } catch (IOException e) {
                            System.out.println("Error writing to file: " + e.getMessage());
                        }
                        pause(scanner);
                        break;

                    case 7:
                        scanner.close();
                        return;

                    case 8:
                        runSystemSimulation();
                        pause(scanner);
                        break;

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
        System.out.println();
        System.out.println(BRIGHT_CYAN + "╔════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_CYAN + "║ " + BOLD + BRIGHT_BLUE + "                         [=] SUMMARY RESULTS                                   " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "╠════════════════════════════╦══════════════════════════════╦═══════════╦════════╣" + RESET);
        System.out.println(BRIGHT_CYAN + "║ " + BOLD + YELLOW + "Student ID" + RESET + "                 " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "Name" + RESET + "                         " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "Exam ID" + RESET + "   " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "Subj" + RESET + "   " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "╠════════════════════════════╬══════════════════════════════╬═══════════╬════════╣" + RESET);

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String name = student.getFirstName() + " " + student.getSurname();
            String subj = getExamSubject(exam);
            if (subj.length() > 4) subj = subj.substring(0, 4);
            System.out.println(String.format(BRIGHT_CYAN + "║ " + WHITE + "%-26d " + BRIGHT_CYAN + "║ " + WHITE + "%-28s " + BRIGHT_CYAN + "║ " + WHITE + "%-9d " + BRIGHT_CYAN + "║ " + WHITE + "%-4s   " + BRIGHT_CYAN + "║" + RESET,
                    student.getStudentId(), name, getExamId(exam), subj));
        }

        System.out.println(BRIGHT_CYAN + "╚════════════════════════════╩══════════════════════════════╩═══════════╩════════╝" + RESET);
        System.out.println(YELLOW + "Total records: " + BOLD + examResults.size() + RESET);
        System.out.println();
    }

    private static void printDetailedResultsOnScreen(List<ExamResult> examResults) {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "╔═══════════════════════════════════════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_CYAN + "║ " + BOLD + BRIGHT_BLUE + "                                     [=] DETAILED RESULTS                                             " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "╠══════════════╦══════════════════════════════════╦═══════════╦═══════════════════════╦═════════╦═══════╣" + RESET);
        System.out.println(String.format(BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-12s" + RESET + " " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-32s" + RESET + " " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-9s" + RESET + " " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-21s" + RESET + " " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-7s" + RESET + " " + BRIGHT_CYAN + "║ " + BOLD + YELLOW + "%-5s" + RESET + " " + BRIGHT_CYAN + "║" + RESET,
                "Student ID", "Name", "Exam ID", "Subject", "Type", "Score"));
        System.out.println(BRIGHT_CYAN + "╠══════════════╬══════════════════════════════════╬═══════════╬═══════════════════════╬═════════╬═══════╣" + RESET);

        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String examTypeLabel;
            String examTypeColor;
            if (exam instanceof MultipleChoice) {
                examTypeLabel = "Multi";
                examTypeColor = BRIGHT_GREEN;
            } else if (exam instanceof Essay) {
                examTypeLabel = "Essay";
                examTypeColor = MAGENTA;
            } else if (exam instanceof Practical) {
                examTypeLabel = "Practical";
                examTypeColor = CYAN;
            } else {
                examTypeLabel = "Other";
                examTypeColor = WHITE;
            }
            String name = student.getFirstName() + " " + student.getSurname();
            String subject = getExamSubject(exam);
            if (subject.length() > 21) subject = subject.substring(0, 21);
            
            String scoreColor;
            int score = result.getScore();
            if (score >= 70) scoreColor = BRIGHT_GREEN;
            else if (score >= 50) scoreColor = YELLOW;
            else scoreColor = RED;
            
            System.out.println(String.format(BRIGHT_CYAN + "║ " + WHITE + "%-12d " + BRIGHT_CYAN + "║ " + WHITE + "%-32s " + BRIGHT_CYAN + "║ " + WHITE + "%-9d " + BRIGHT_CYAN + "║ " + WHITE + "%-21s " + BRIGHT_CYAN + "║ " + examTypeColor + "%-7s" + RESET + " " + BRIGHT_CYAN + "║ " + scoreColor + "%3d%%" + RESET + "  " + BRIGHT_CYAN + "║" + RESET,
                    student.getStudentId(), name, getExamId(exam), subject, examTypeLabel, score));
        }

        System.out.println(BRIGHT_CYAN + "╚══════════════╩══════════════════════════════════╩═══════════╩═══════════════════════╩═════════╩═══════╝" + RESET);
        System.out.println(YELLOW + "Total records: " + BOLD + examResults.size() + RESET);
        System.out.println();
    }


    private static void printSummaryResult(List<ExamResult> examResults) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("summary_result.txt"));
        
        // Header
        writer.write("================================================================================\n");
        writer.write("                          SUMMARY RESULTS REPORT                                \n");
        writer.write("================================================================================\n");
        writer.write(String.format("%-15s | %-30s | %-12s | %-15s\n", 
                "Student ID", "Name", "Exam ID", "Subject"));
        writer.write("--------------------------------------------------------------------------------\n");

        // Data rows
        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String fullName = student.getFirstName() + " " + student.getSurname();
            String subject = getExamSubject(exam);
            
            writer.write(String.format("%-15d | %-30s | %-12d | %-15s\n",
                    student.getStudentId(), 
                    fullName.length() > 30 ? fullName.substring(0, 27) + "..." : fullName,
                    getExamId(exam), 
                    subject.length() > 15 ? subject.substring(0, 12) + "..." : subject));
        }
        
        // Footer
        writer.write("================================================================================\n");
        writer.write(String.format("Total Records: %d\n", examResults.size()));
        writer.write("================================================================================\n");

        writer.close();
        System.out.println();
        System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(BRIGHT_GREEN + "│  [OK] Summary results saved to 'summary_result.txt'         │" + RESET);
        System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
    }

    private static void printDetailedResults(List<ExamResult> examResults) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("detailed_results.txt"));
        
        // Header
        writer.write("====================================================================================================\n");
        writer.write("                                  DETAILED RESULTS REPORT                                          \n");
        writer.write("====================================================================================================\n");
        writer.write(String.format("%-12s | %-25s | %-10s | %-18s | %-13s | %-7s\n",
                "Student ID", "Name", "Exam ID", "Subject", "Exam Type", "Score"));
        writer.write("----------------------------------------------------------------------------------------------------\n");

        // Data rows
        for (ExamResult result : examResults) {
            Student student = result.getStudent();
            Exam exam = result.getExam();
            String examType = "Other";
            if (exam instanceof MultipleChoice) examType = "Multi Choice";
            else if (exam instanceof Essay) examType = "Essay";
            else if (exam instanceof Practical) examType = "Practical";
            String fullName = student.getFirstName() + " " + student.getSurname();
            String subject = getExamSubject(exam);
            
            writer.write(String.format("%-12d | %-25s | %-10d | %-18s | %-13s | %6d%%\n",
                    student.getStudentId(), 
                    fullName.length() > 25 ? fullName.substring(0, 22) + "..." : fullName,
                    getExamId(exam), 
                    subject.length() > 18 ? subject.substring(0, 15) + "..." : subject,
                    examType, 
                    result.getScore()));
        }
        
        // Footer
        writer.write("====================================================================================================\n");
        writer.write(String.format("Total Records: %d\n", examResults.size()));
        writer.write("====================================================================================================\n");

        writer.close();
        System.out.println();
        System.out.println(BRIGHT_GREEN + "┌─────────────────────────────────────────────────────────────┐" + RESET);
        System.out.println(BRIGHT_GREEN + "│  [OK] Detailed results saved to 'detailed_results.txt'      │" + RESET);
        System.out.println(BRIGHT_GREEN + "└─────────────────────────────────────────────────────────────┘" + RESET);
    }

    // UI helpers
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void printBanner() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "╔═══════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + BRIGHT_GREEN + "   ███████╗██╗  ██╗ █████╗ ███╗   ███╗    ███████╗██╗   ██╗███████╗   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + BRIGHT_GREEN + "   ██╔════╝╚██╗██╔╝██╔══██╗████╗ ████║    ██╔════╝╚██╗ ██╔╝██╔════╝   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + YELLOW + "   █████╗   ╚███╔╝ ███████║██╔████╔██║    ███████╗ ╚████╔╝ ███████╗   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + YELLOW + "   ██╔══╝   ██╔██╗ ██╔══██║██║╚██╔╝██║    ╚════██║  ╚██╔╝  ╚════██║   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + ORANGE + "   ███████╗██╔╝ ██╗██║  ██║██║ ╚═╝ ██║    ███████║   ██║   ███████║   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + ORANGE + "   ╚══════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚═╝    ╚══════╝   ╚═╝   ╚══════╝   " + BRIGHT_CYAN + " ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + CYAN + "                                                                       " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + WHITE + "             " + BOLD + ">> STUDENT EXAMINATION MANAGEMENT SYSTEM <<" + RESET + WHITE + "             " + BRIGHT_CYAN + "  ║" + RESET);
        System.out.println(BRIGHT_CYAN + "╚═══════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }

    private static void printMenu(int studentsCount, int examsCount) {
        System.out.println(BRIGHT_CYAN + "╔═══════════════════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BRIGHT_CYAN + "║" + BOLD + BRIGHT_BLUE + "                           MAIN MENU                                   " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "╠═══════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "1." + WHITE + " [+] Add Student" + "                                                 " + BRIGHT_CYAN + "  ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "2." + WHITE + " [*] Add Multiple Choice Exam Result" + "                         " + BRIGHT_CYAN + "      ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "3." + WHITE + " [*] Add Essay Exam Result" + "                                   " + BRIGHT_CYAN + "      ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "4." + WHITE + " [*] Add Practical Exam Result" + "                               " + BRIGHT_CYAN + "      ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "5." + WHITE + " [=] Print Summary Result" + "                                     " + BRIGHT_CYAN + "     ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_GREEN + "6." + WHITE + " [=] Print Detailed Results" + "                                   " + BRIGHT_CYAN + "     ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + RED + "7." + WHITE + " [X] Quit" + "                                                      " + BRIGHT_CYAN + "    ║" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + BRIGHT_BLUE + "8." + WHITE + " [>] Run System Simulation (demo)" + "                             " + BRIGHT_CYAN + "     ║" + RESET);
        System.out.println(BRIGHT_CYAN + "╠═══════════════════════════════════════════════════════════════════════╣" + RESET);
        System.out.println(BRIGHT_CYAN + "║  " + YELLOW + "Students: " + BOLD + BRIGHT_GREEN + String.format("%-3d", studentsCount) + RESET + 
                          YELLOW + "  |  Exam Results: " + BOLD + BRIGHT_GREEN + String.format("%-3d", examsCount) + RESET + 
                          "                                  " + BRIGHT_CYAN + "║" + RESET);
        System.out.println(BRIGHT_CYAN + "╚═══════════════════════════════════════════════════════════════════════╝" + RESET);
        System.out.println();
    }
    private static void pause(Scanner scanner) {
        System.out.println();
        System.out.print(DIM + ">> Press Enter to continue..." + RESET);
        scanner.nextLine();
    }

    // Helper to retrieve the 'subject' field from Exam instances even if Exam
    // does not expose a public getter; uses reflection to search the class hierarchy.
    public static String getExamSubject(Exam exam) {
        if (exam == null) return "";
        try {
            Class<?> cls = exam.getClass();
            while (cls != null) {
                try {
                    java.lang.reflect.Field f = cls.getDeclaredField("subject");
                    f.setAccessible(true);
                    Object value = f.get(exam);
                    return value == null ? "" : value.toString();
                } catch (NoSuchFieldException e) {
                    cls = cls.getSuperclass();
                }
            }
        } catch (Exception e) {
            // fallback to empty string on any reflection issue
        }
        return "";
    }

    // Helper to retrieve the 'examId' field from Exam instances using reflection.
    public static int getExamId(Exam exam) {
        if (exam == null) return -1;
        try {
            Class<?> cls = exam.getClass();
            while (cls != null) {
                try {
                    java.lang.reflect.Field f = cls.getDeclaredField("examId");
                    f.setAccessible(true);
                    Object value = f.get(exam);
                    if (value instanceof Number) return ((Number) value).intValue();
                    try {
                        return Integer.parseInt(String.valueOf(value));
                    } catch (Exception ex) {
                        return -1;
                    }
                } catch (NoSuchFieldException e) {
                    cls = cls.getSuperclass();
                }
            }
        } catch (Exception e) {
            // fallback to -1 on any reflection issue
        }
        return -1;
    }

    // Demo simulation without introducing new classes/files.
    private static void runSystemSimulation() {
        System.out.println();
        System.out.println(BRIGHT_CYAN + "===== System Simulation: Students, Lecturers, Admin, Courses, Modules, Assessments =====" + RESET);

        // Mock admin, lecturers, course, and modules (as strings/maps)
        String admin = "Alice Admin (Admin ID: 9001)";
        String courseCode = "BSC-COMP";
        String courseTitle = "BSc (Hons) in Computing";

        String[] moduleCodes = {"PROG101", "DSA201", "SE202", "DBS102"};
        String[] moduleTitles = {"Programming 1", "Data Structures", "Software Engineering", "Databases Fundamentals"};
        String[] lecturers = {"Dr. Smith", "Dr. Smith", "Prof. Johnson", "Dr. Garcia"};

        Map<String, String> moduleTitleMap = new HashMap<>();
        Map<String, String> moduleLecturerMap = new HashMap<>();
        for (int i = 0; i < moduleCodes.length; i++) {
            moduleTitleMap.put(moduleCodes[i], moduleTitles[i]);
            moduleLecturerMap.put(moduleCodes[i], lecturers[i]);
        }

        System.out.println(YELLOW + "Admin: " + WHITE + admin + RESET);
        System.out.println(BRIGHT_CYAN + "Course: " + WHITE + courseCode + " - " + courseTitle + RESET);

        System.out.println();
        System.out.println(BRIGHT_CYAN + "Modules & Lecturers" + RESET);
        for (String code : moduleCodes) {
            System.out.println("  " + code + " - " + moduleTitleMap.get(code) + " | Lecturer: " + moduleLecturerMap.get(code));
        }

        // Create sample students
        List<Student> simStudents = new ArrayList<>();
        try {
            simStudents.add(new Student(2001, "Jane", "Doe"));
            simStudents.add(new Student(2002, "Mark", "Lee"));
            simStudents.add(new Student(2003, "Sara", "Khan"));
        } catch (StudentException e) {
            System.out.println(RED + e.getMessage() + RESET);
            return;
        }

        System.out.println();
        System.out.println(BRIGHT_CYAN + "Enrolled Students" + RESET);
        for (Student s : simStudents) {
            System.out.println("  " + s.getStudentId() + " - " + s.getFirstName() + " " + s.getSurname());
        }

        // Assessments grouped per module
        Map<String, List<ExamResult>> moduleResults = new HashMap<>();
        for (String code : moduleCodes) moduleResults.put(code, new ArrayList<>());

        List<ExamResult> allResults = new ArrayList<>();

        try {
            // Jane
            Exam e1 = new MultipleChoice(1, "PROG101 - Programming 1", 60, 42, 50); // 84%
            simStudents.get(0).addExam(e1);
            int sc1 = (int) ((MultipleChoice) e1).calculateScore();
            ExamResult r1 = new ExamResult(simStudents.get(0), e1, sc1);
            moduleResults.get("PROG101").add(r1); allResults.add(r1);

            Exam e2 = new Essay(2, "SE202 - Software Engineering", 90, "Solid design and documentation.", 43, 45, 2000);
            simStudents.get(0).addExam(e2);
            int sc2 = (int) ((Essay) e2).calculateScore();
            ExamResult r2 = new ExamResult(simStudents.get(0), e2, sc2);
            moduleResults.get("SE202").add(r2); allResults.add(r2);

            // Mark
            Exam e3 = new Practical(3, "DSA201 - Data Structures", 36, 12, 50); // 96%
            simStudents.get(1).addExam(e3);
            int sc3 = (int) ((Practical) e3).calculateScore();
            ExamResult r3 = new ExamResult(simStudents.get(1), e3, sc3);
            moduleResults.get("DSA201").add(r3); allResults.add(r3);

            Exam e4 = new MultipleChoice(4, "DBS102 - Databases Fundamentals", 45, 32, 40); // 80%
            simStudents.get(1).addExam(e4);
            int sc4 = (int) ((MultipleChoice) e4).calculateScore();
            ExamResult r4 = new ExamResult(simStudents.get(1), e4, sc4);
            moduleResults.get("DBS102").add(r4); allResults.add(r4);

            // Sara
            Exam e5 = new Essay(5, "PROG101 - Programming 1", 75, "Good fundamentals with examples.", 40, 42, 1500);
            simStudents.get(2).addExam(e5);
            int sc5 = (int) ((Essay) e5).calculateScore();
            ExamResult r5 = new ExamResult(simStudents.get(2), e5, sc5);
            moduleResults.get("PROG101").add(r5); allResults.add(r5);

            Exam e6 = new Practical(6, "SE202 - Software Engineering", 28, 14, 50);
            simStudents.get(2).addExam(e6);
            int sc6 = (int) ((Practical) e6).calculateScore();
            ExamResult r6 = new ExamResult(simStudents.get(2), e6, sc6);
            moduleResults.get("SE202").add(r6); allResults.add(r6);

        } catch (ExamException ex) {
            System.out.println(RED + ex.getMessage() + RESET);
            return;
        }

        System.out.println();
        System.out.println(BRIGHT_CYAN + "Assessments by Module" + RESET);
        double total = 0; int count = 0;
        for (String code : moduleCodes) {
            String title = moduleTitleMap.get(code);
            System.out.println(YELLOW + "> " + code + " - " + title + RESET);
            List<ExamResult> results = moduleResults.get(code);
            if (results.isEmpty()) {
                System.out.println("  (no results)");
                continue;
            }
            int modSum = 0;
            for (ExamResult r : results) {
                Student s = r.getStudent();
                Exam ex = r.getExam();
                String subj = getExamSubject(ex);
                System.out.println("  - " + s.getFirstName() + " " + s.getSurname()
                        + " | ExamID: " + getExamId(ex)
                        + " | Subject: " + subj
                        + " | Score: " + r.getScore() + "%");
                modSum += r.getScore();
            }
            double modAvg = (double) modSum / results.size();
            System.out.printf("  Module Average: %.2f%%%n", modAvg);
            total += modSum; count += results.size();
        }

        System.out.println();
        System.out.println(BRIGHT_CYAN + "Per-Student Averages" + RESET);
        for (Student s : simStudents) {
            int sSum = 0, sCnt = 0;
            for (ExamResult r : allResults) {
                if (r.getStudent().getStudentId() == s.getStudentId()) {
                    sSum += r.getScore(); sCnt++;
                }
            }
            double avg = sCnt == 0 ? 0.0 : (double) sSum / sCnt;
            String color = avg >= 70 ? BRIGHT_GREEN : (avg >= 50 ? YELLOW : RED);
            System.out.printf("  %s%s %s%s: %.2f%%%s%n",
                    color, s.getFirstName(), s.getSurname(), RESET, avg, RESET);
        }

        System.out.println();
        double courseAvg = count == 0 ? 0.0 : total / count;
        System.out.printf(BRIGHT_BLUE + "Overall Course Average: " + BOLD + "%.2f%%%s%n", courseAvg, RESET);

        System.out.println(BRIGHT_GREEN + "[OK] Simulation complete." + RESET);
    }
}
