package Calendar;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledReminder {
    private List<Event> events;
    private ScheduledExecutorService scheduler;

    public ScheduledReminder(List<Event> events) {
        this.events = events;
        this.scheduler = Executors.newScheduledThreadPool(1);  // 스케줄러 초기화
    }

    public void scheduleEventAlarms() {
        long now = System.currentTimeMillis();

        for (Event event : events) {
            long eventTime = event.getTime();
            long timeBeforeEvent = eventTime - now - TimeUnit.MINUTES.toMillis(10);  // 10분 전

            if (timeBeforeEvent > 0) {
                scheduler.schedule(() -> {
                    System.out.println("알림: '" + event.getName() + "' 이벤트가 10분 후에 시작합니다!");
                }, timeBeforeEvent, TimeUnit.MILLISECONDS);
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}
