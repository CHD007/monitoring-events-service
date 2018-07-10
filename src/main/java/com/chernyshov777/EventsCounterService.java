package com.chernyshov777;

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
}
