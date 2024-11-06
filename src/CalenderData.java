import java.util.Date;

public class CalenderData {
    private String scheduleName;
    private Date scheduleDate;

    public CalenderData(String scheduleName, Date scheduleDate) {
        this.scheduleName = scheduleName;
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleName() {
        return scheduleName;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }
}
