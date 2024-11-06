import java.util.Date;

public class AssignmentData {
    private String subjectName;
    private int numOfAssignment;
    private double currentRatio;
    private double latePenalty;
    private int isAlter; // 0 = non, 1 = midterm, 2 = final
    private Date deadline;
    private double expectedPeriod;
    private double importance;

    public AssignmentData(String subjectName, int numOfAssignment, double currentRatio, 
                    double latePenalty, int isAlter, Date deadline, double importance) {
        this.subjectName = subjectName;
        this.numOfAssignment = numOfAssignment;
        this.currentRatio = currentRatio; // 모를경우 numOfAssignment/전체 Assignment의 수..? 보수적으로 0.1..?
        this.latePenalty = latePenalty; // 늦은 제출일 경우 0점인지 n%만 인정하는지 (0.0 ~ 0.n)
        this.isAlter = isAlter;
        this.deadline = deadline;
        this.expectedPeriod = expectedPeriod;
        this.importance = 0.0;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getNumOfAssignment() {
        return numOfAssignment;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public double getLatePenalty() {
        return latePenalty;
    }

    public int getIsAlter() {
        return isAlter;
    }

    public Date getDeadline() {
        return deadline;
    }

    public double getPeriod() {
        return expectedPeriod;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }
}
