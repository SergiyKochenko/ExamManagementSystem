package ExamResults;

import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentId;
    private String firstName;
    private String surname;
    private List<Exam> examsTaken;

    public Student(int studentId, String firstName, String surname) throws StudentException {
        if (firstName.length() < 2 || firstName.length() > 30 || surname.length() < 2 || surname.length() > 30) {
            throw new StudentException("First name and surname should be between 2 and 30 characters in length");
        }
        this.studentId = studentId;
        this.firstName = firstName;
        this.surname = surname;
        this.examsTaken = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getStudentName() {
        return firstName + " " + surname;
    }

    public List<Exam> getExamsTaken() {
        return examsTaken;
    }

    public void addExam(Exam exam) {
        examsTaken.add(exam);
    }

    public static boolean isValidStudentId(String studentId) {
        return studentId.matches("\\d+");
    }

    public static boolean isValidStudentName(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
