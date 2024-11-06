package Calendar;

public class Event {
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
