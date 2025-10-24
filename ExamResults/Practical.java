package ExamResults;

/*
 Minimal, self-contained Practical + Exam + ExamException.
 Practical is public (one public class per file). Exam and ExamException are package-private.
*/

public class Practical extends Exam {
    private int implementation;
    private int viva;
    private int totalMarks;

    public Practical(int examId, String subject, int implementation, int viva, int totalMarks) throws ExamException {
        super(examId, subject, 30);
        if (implementation < 0 || viva < 0)
            throw new ExamException("Implementation and viva marks must be non-negative.");
        // Automatically adjust totalMarks if needed
        int minTotal = implementation + viva;
        if (totalMarks < minTotal) {
            totalMarks = minTotal;
        }
        if (totalMarks <= 0)
            throw new ExamException("Total marks must be positive.");
        this.implementation = implementation;
        this.viva = viva;
        this.totalMarks = totalMarks;
    }

    @Override
    public double calculateScore() {
        return ((double)(implementation + viva) / totalMarks) * 100.0;
    }

    @Override
    public void displayExamDetails() {
        System.out.println("Practical Exam Details");
        System.out.println("----------------------");
        System.out.println("Exam ID: " + getExamId());
        System.out.println("Subject: " + getSubject());
        System.out.println("Implementation Marks: " + implementation + " / " + totalMarks);
        System.out.println("Viva Marks: " + viva + " / " + totalMarks);
        System.out.println("Calculated Score: " + String.format("%.2f", calculateScore()) + "%");
        // Duration is intentionally NOT displayed for Practical exam.
    }
}

// Abstract Exam base class with getters used elsewhere.
// Kept package-private so file can contain multiple classes.
abstract class Exam {
    protected int examId;
    protected String subject;
    protected int duration;

    public Exam(int examId, String subject, int duration) {
        this.examId = examId;
        this.subject = subject;
        this.duration = duration;
    }

    // NEW: convenience ctor for exams without a time limit
    public Exam(int examId, String subject) {
        this(examId, subject, -1);
    }

    public int getExamId() {
        return examId;
    }

    public String getSubject() {
        return subject;
    }

    public int getDuration() {
        return duration;
    }

    // subclasses must implement
    public abstract double calculateScore();

    // default detail display (override when needed)
    public void displayExamDetails() {
        System.out.println("Exam ID: " + getExamId());
        System.out.println("Subject: " + getSubject());
        // Only show duration if it is set (>= 0). Practical uses -1 to indicate no limit.
        if (duration >= 0) {
            System.out.println("Duration: " + getDuration() + " minutes");
        }
    }
}

// Simple ExamException used by Practical and Exam.
class ExamException extends Exception {
    public ExamException(String message) {
        super(message);
    }
}
