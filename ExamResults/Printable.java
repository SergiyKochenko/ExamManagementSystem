package ExamResults;

import java.io.IOException;

interface Printable {
    void printSummaryResult() throws IOException;
    void printDetailedResults() throws IOException;
}
