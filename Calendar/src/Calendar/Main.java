package Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 이벤트 생성
            List<Event> events = new ArrayList<>();
            events.add(new Event("중요 회의", dateFormat.parse("2024-11-05 15:30:00").getTime()));
            events.add(new Event("수업", dateFormat.parse("2024-11-05 14:00:00").getTime()));
            events.add(new Event("운동", dateFormat.parse("2024-11-05 18:30:00").getTime()));

            // SlidingWindowReminder 예제
            SlidingEvent slidingWindowReminder = new SlidingEvent();
            for (Event event : events) {
                slidingWindowReminder.addEvent(event);
            }

            // 사용자가 선택한 시간과 윈도우 크기 설정
            String userSelectTime = "2024-11-05 15:00:00";
            long selectedTime = dateFormat.parse(userSelectTime).getTime();
            long windowSize = TimeUnit.MINUTES.toMillis(10);

            // 윈도우 내의 이벤트 가져오기
            List<Event> eventsInWindow = slidingWindowReminder.getEventsInWindow(selectedTime, windowSize);
            System.out.println("선택된 시간으로부터 " + (windowSize / 60000) + "분 내의 이벤트:");
            if (eventsInWindow.isEmpty()) {
                System.out.println("해당 시간 내에 이벤트가 없습니다.");
            } else {
                for (Event event : eventsInWindow) {
                    String formattedTime = dateFormat.format(new Date(event.getTime()));
                    System.out.println(" - " + event.getName() + " at " + formattedTime);
                }
            }

            // ScheduledReminder 예제
            ScheduledReminder scheduledReminder = new ScheduledReminder(events);
            scheduledReminder.scheduleEventAlarms();

        } catch (ParseException e) {
            System.out.println("날짜 형식이 잘못되었습니다: " + e.getMessage());
        }
    }
}
