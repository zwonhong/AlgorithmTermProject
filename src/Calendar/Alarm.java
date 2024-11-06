package Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Date;

class Event {
    private String name;
    private long time;  // 이벤트 시간 (밀리초)

    public Event(String name, long time) {
        this.name = name;
        this.time = time;
    }
    
    public String getName() {
        return name;
    }
    
    public long getTime() {
        return time;
    }
}

public class Alarm {
    private List<Event> events;

    public Alarm() {
        this.events = new ArrayList<>();
    }
    
    public void addEvent(String eventName, long eventTimeInMillis) {
        events.add(new Event(eventName, eventTimeInMillis));
    }
    
    public List<Event> getEventsInWindow(long selectedTime, long windowSizeInMillis) {
        List<Event> eventsInWindow = new ArrayList<>();
        
        for (Event event : events) {
            long eventTime = event.getTime();
            if (Math.abs(eventTime - selectedTime) <= windowSizeInMillis) {
                eventsInWindow.add(event);
            }
        }
        
        return eventsInWindow;
    }
    
    private static void displayEventsInWindow(List<Event> events, SimpleDateFormat dateFormat, long windowSize) {
        System.out.println("선택된 시간으로부터 " + (windowSize / 60000) + "분 내의 이벤트:");
        for (Event event : events) {
            String formattedTime = dateFormat.format(new Date(event.getTime()));  // 밀리초를 사람이 읽을 수 있는 시간으로 변환
            System.out.println(" - " + event.getName() + " at " + formattedTime);
        }
    }

    public static void main(String[] args) {
        try {
            Alarm reminder = new Alarm();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // 이벤트 추가
            reminder.addEvent("중요 회의", dateFormat.parse("2024-11-05 15:10:00").getTime());
            reminder.addEvent("회의", dateFormat.parse("2024-11-05 10:00:00").getTime());
            reminder.addEvent("점심 약속", dateFormat.parse("2024-11-05 12:30:00").getTime());
            reminder.addEvent("운동", dateFormat.parse("2024-11-05 18:30:00").getTime());

            // 사용자가 선택한 시간과 윈도우 크기 설정
            String userSelectTime = "2024-11-05 15:00:00";
            long selectedTime = dateFormat.parse(userSelectTime).getTime();
            long windowSize = TimeUnit.MINUTES.toMillis(10);

            // 윈도우 내의 이벤트 가져오기
            List<Event> eventsInWindow = reminder.getEventsInWindow(selectedTime, windowSize);

            // 이벤트 출력
            displayEventsInWindow(eventsInWindow, dateFormat, windowSize);

        } catch (ParseException e) {
            System.out.println("날짜 형식이 잘못되었습니다: " + e.getMessage());
        }
    }
}
