public class SubjectData {
    private String subjectName;
    private double midtermRatio;
    private double finalRatio;
    private double assignmentRatio;
    private double attendanceRatio;
    private int creditHours;
    private boolean isMajor;
    private int preferenceLevel;
    private double importance;

    public SubjectData(String subjectName, double midtermRatio, double finalRatio, double assignmentRatio, 
                       double attendanceRatio, int creditHours, boolean isMajor, int preferenceLevel) {
        this.subjectName = subjectName;
        this.midtermRatio = midtermRatio; 
        this.finalRatio = finalRatio;
        this.assignmentRatio = assignmentRatio;
        this.attendanceRatio = attendanceRatio; // upper 4 ratio's sum must be 1.0
        this.creditHours = creditHours;
        this.isMajor = isMajor;
        this.preferenceLevel = preferenceLevel; // not in excel, by user input
        // 5 stars (1 > 0.8, 2 > 0.85, 3 > 0.90, 4 > 0.95, 5 > 1.00)
        this.importance = 0.0;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public double getMidtermRatio() {
        return midtermRatio;
    }

    public double getFinalRatio() {
        return finalRatio;
    }

    public double getAssignmentRatio() {
        return assignmentRatio;
    }

    public double getAttendanceRatio() {
        return attendanceRatio;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public boolean isMajor() {
        return isMajor;
    }

    public int getPreferenceLevel() {
        return preferenceLevel;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }
}
