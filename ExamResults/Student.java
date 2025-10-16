package ExamResults;

import java.util.ArrayList;
import java.util.List;

class Student {
    private int studentId;
    private String studentName;
    private List<Exam> examsTaken;

    public Student(int studentId, String studentName) throws StudentException {
        if (studentName.length() < 2 || studentName.length() > 30) {
            throw new StudentException("Student name should be between 2 and 30 characters in length");
        }
        this.studentId = studentId;
        this.studentName = studentName;
        this.examsTaken = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
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

    public static boolean isValidStudentName(String studentName) {
        return studentName.matches("[a-zA-Z]+");
    }

}
