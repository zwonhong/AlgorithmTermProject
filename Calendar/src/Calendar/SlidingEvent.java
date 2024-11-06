package Calendar;

import java.util.ArrayList;
import java.util.List;

public class SlidingEvent {
    private List<Event> events;

    public SlidingEvent() {
        this.events = new ArrayList<>();
    }
    
    public void addEvent(Event event) {
        events.add(event);
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
}
