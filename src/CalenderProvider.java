import java.util.ArrayList;
import java.util.List;

public class CalenderProvider {
    private List<CalenderData> calenders;

    public CalenderProvider() {
        calenders = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        // scan by excels.
    }

    public List<CalenderData> getSchedules() {
        return calenders;
    }
}
