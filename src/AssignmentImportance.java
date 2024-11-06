import java.util.ArrayList;
import java.util.List;

public class AssignmentImportance {
    private List<AssignmentData> assignments;
    private List<SubjectData> subjects;

    public AssignmentImportance(AssignmentProvider assignmentProvider, SubjectProvider subjectProvider) {
        this.assignments = assignmentProvider.getAssignments();
        this.subjects = subjectProvider.getSubjects();
    }

    private double getRatioByName(String subjectName) {
        for (SubjectData subject : subjects) {
            if (subject.getSubjectName().equals(subjectName)) {
                return subject.getAssignmentRatio();
            }
        }
        return 0.0; // 과목이 존재하지 않는 경우 0.0 반환
    }

    public void calcImportance() {
        for (AssignmentData assignment : assignments) {
            double importance = 0.0;
            double subjectRatio = getRatioByName(assignment.getSubjectName());
            double currentAssignmentRatio = assignment.getCurrentRatio() * subjectRatio;
            double penalty = 1.0 - assignment.getLatePenalty();
            if(assignment.getIsAlter()) penalty *= 10;
            /*
            1. 과목별 과제비중, 전체 과제 중 현재 과제의 비중 계산
            2. 대체과제 여부
            3. 과제 예상 소요시간 - 이건 데드라인 클래스로 넘기는게 나을듯
            4. late 패널티
            */
            // 중요도 계산 예시
            importance = currentAssignmentRatio * 0.3 + penalty * 0.5;
            assignment.setImportance(importance);
        }
    }

    public static void main(String[] args) {
        AssignmentProvider assignmentProvider = new AssignmentProvider();
        SubjectProvider subjectProvider = new SubjectProvider();

        AssignmentImportance assignmentImportance = new AssignmentImportance(assignmentProvider, subjectProvider);
        assignmentImportance.calcImportance();
    }
}

