package com.chernyshov777;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Implementation of {@link EventsCounter}
 *
 * @see EventsCounter
 */
public class EventsCounterService extends AbstractEventsCounterService {

    public EventsCounterService() {
        eventsCountWithinLastMinute = new LinkedList<>();
        eventsCountWithinLastHour = new LinkedList<>();
        eventsCountWithinLastDay = new LinkedList<>();
    }

    @Override
    public void addEvent(Long timeInMilliseconds) {
        Long lastAddedTime = ((Deque<Long>)eventsCountWithinLastDay).peekLast();
        if (lastAddedTime != null && timeInMilliseconds - lastAddedTime < 0) {
            throw new IllegalArgumentException("The new event time should be greater than existing one's");
        }
        eventsCountWithinLastMinute.add(timeInMilliseconds);
        eventsCountWithinLastHour.add(timeInMilliseconds);
        eventsCountWithinLastDay.add(timeInMilliseconds);
    }
}
