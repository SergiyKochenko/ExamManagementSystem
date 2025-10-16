package ExamResults;

class Essay extends Exam implements Scorable {
    private String essayAnswer;
    private int grammar;
    private int content;
    private int wordLimit;

    public Essay(int examId, String subject, int duration, String essayAnswer, int grammar, int content, int wordLimit) throws ExamException {
        super(examId, subject, duration);
        if (essayAnswer.split("\\s+").length < 0) {
            throw new ExamException("Word count should be greater than or equal to zero");
        }
        if (wordLimit < 500 || wordLimit > 10000) {
            throw new ExamException("Word limit should be between 500 and 10000");
        }
        this.essayAnswer = essayAnswer;
        this.grammar = grammar;
        this.content = content;
        this.wordLimit = wordLimit;
    }
    public int getWordCount() throws ExamException{
        if (essayAnswer == null || essayAnswer.isEmpty()){
            return 0;
        }
        essayAnswer = essayAnswer.trim();
        // Splitting the string into words
        String[] words = essayAnswer.split("\\s+");
        //get the number of wordCount
        if (words.length > 0) return words.length;
        throw new ExamException("EssayAnswer can not be empty ");

    }
    public double gradeEssay() throws ExamException {
        int wordCount = 0;
        //double gradeForWordCount;
        wordCount = this.getWordCount();
        double i = (double) (wordCount)/wordLimit;
        i = 100 - Math.abs(i)*100;
        if (i < 6.0){
            return  20.0;
        }else if(i < 19.0) {
            return 10.0;
        }else{
            return 0.0;
        }

    }

    public void displayExamDetails() {
        System.out.println("Exam Type: Essay");
        System.out.println("Exam ID: " + getExamId());
        System.out.println("Subject: " + getSubject());
        System.out.println("Duration: " + getDuration() + " minutes");
        System.out.println("Word Limit: " + wordLimit);
        System.out.println("Grammar Marks: " + grammar);
        System.out.println("Content Marks: " + content);
        System.out.println("Word Count: " + essayAnswer.split("\\s+").length);
    }

    @Override
    public double calculateScore() {
        try {
            return (((double) this.grammar * 0.35) + ((double) this.content * 0.35) + this.gradeEssay());
        } catch (ExamException e) {
            throw new RuntimeException(e);
        }
    }
}
