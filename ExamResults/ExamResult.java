package ExamResults;

class ExamResult implements Comparable<ExamResult> {
    private Student student;
    private Exam exam;
    private int score;

    public ExamResult(Student student, Exam exam, int score) {
        this.student = student;
        this.exam = exam;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Exam getExam() {
        return exam;
    }

    public int getScore() {
        return score;
    }

    public int compareTo(ExamResult other) {
        return Integer.compare(this.score, other.score);
    }

}
