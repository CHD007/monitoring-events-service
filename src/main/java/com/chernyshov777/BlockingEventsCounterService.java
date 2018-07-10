package com.chernyshov777;

import java.util.concurrent.ConcurrentLinkedDeque;

public class BlockingEventsCounterService extends AbstractEventsCounterService {
    private final static long ONE_MINUTE_IN_MILLISECONDS = 60 * 1000;
    private final static long ONE_HOUR_IN_MILLISECONDS = 60 * ONE_MINUTE_IN_MILLISECONDS;
    private final static long ONE_DAY_IN_MILLISECONDS = 24 * ONE_HOUR_IN_MILLISECONDS;

    public BlockingEventsCounterService() {
        eventsCountWithinLastMinute = new ConcurrentLinkedDeque<>();
        eventsCountWithinLastHour = new ConcurrentLinkedDeque<>();
        eventsCountWithinLastDay = new ConcurrentLinkedDeque<>();
    }
}
