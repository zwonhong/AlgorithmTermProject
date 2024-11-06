import java.util.List;

public class SubjectImportance {
    private List<SubjectData> subjects;
    private int maxCreditHours = 3;

    public SubjectImportance(SubjectProvider dataProvider) {
        this.subjects = dataProvider.getSubjects();
    }

    public void calcImportance() {
        for (SubjectData subject : subjects) {
            double creditScore = (double) subject.getCreditHours() / maxCreditHours; // 0.33, 0.66, 1.0
            double preferenceScore = subject.getPreferenceLevel(); // 0.80 ~ 1.0
            double majorScore = subject.isMajor() ? 1.0 : 0.8; // major : not major
            double assignmentRatio = subject.getAssignmentRatio() * 4.0;
            double importance = (creditScore * majorScore) + preferenceScore + assignmentRatio;
            // expected 1.09 ~ 3.0
            subject.setImportance(importance);
        }
    }

    public static void main(String[] args) {
        SubjectProvider dataProvider = new SubjectProvider();
        SubjectImportance subjectImportance = new SubjectImportance(dataProvider);

        subjectImportance.calcImportance();
    }
}
