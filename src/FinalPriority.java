import java.util.List;
import java.util.Date;

public class FinalPriority {
    private List<SubjectData> subjects;
    private List<AssignmentData> assignments;
    private List<CalenderData> schedules;

    public FinalPriority(SubjectProvider subjectProvider, AssignmentProvider assignmentProvider, CalenderProvider calenderProvider) {
        this.subjects = subjectProvider.getSubjects();
        this.assignments = assignmentProvider.getAssignments();
        this.schedules = calenderProvider.getSchedules();
    }

    public void calcPriority() {
        double subjectImportance = subjects.getImportance();
        double assignmentImportance = assignments.getImportance();
        Date assignmentRecDeadline = assignments.getRecDeadline();
    }

    public static void main(String[] args) {
        SubjectProvider subjectProvider = new SubjectProvider();
        AssignmentProvider assignmentProvider = new AssignmentProvider();
        CalenderProvider calenderProvider = new CalenderProvider();

        FinalPriority finalPriority = new FinalPriority(subjectProvider, assignmentProvider, calenderProvider);
        finalPriority.calcPriority();
    }
}
