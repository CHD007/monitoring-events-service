package com.chernyshov777;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingEventsCounterService extends AbstractEventsCounterService {

    public BlockingEventsCounterService() {
        eventsCountWithinLastMinute = new LinkedBlockingDeque<>();
        eventsCountWithinLastHour = new LinkedBlockingDeque<>();
        eventsCountWithinLastDay = new LinkedBlockingDeque<>();
    }

    @Override
    public void addEvent(Long timeInMilliseconds) {
        eventsCountWithinLastMinute.add(timeInMilliseconds);
        eventsCountWithinLastHour.add(timeInMilliseconds);
        eventsCountWithinLastDay.add(timeInMilliseconds);
    }

    @Override
    protected void deleteOldElementsFromDeque(Queue<Long> dequeToUpdate, long timeDifference) {
        long currentTime = System.currentTimeMillis();
        dequeToUpdate.removeIf(a -> currentTime - a > timeDifference);
    }
}
