package com.chernyshov777;

import java.util.Deque;
import java.util.Iterator;

public abstract class AbstractEventsCounterService implements EventsCounter {
    protected Deque<Long> eventsCountWithinLastMinute;
    protected Deque<Long> eventsCountWithinLastHour;
    protected Deque<Long> eventsCountWithinLastDay;

    protected final static long ONE_MINUTE_IN_MILLISECONDS = 60 * 1000;
    protected final static long ONE_HOUR_IN_MILLISECONDS = 60 * ONE_MINUTE_IN_MILLISECONDS;
    protected final static long ONE_DAY_IN_MILLISECONDS = 24 * ONE_HOUR_IN_MILLISECONDS;


    @Override
    public void addEvent(Long timeInMilliseconds) {
        Long lastAddedTime = eventsCountWithinLastDay.peekLast();
        if (lastAddedTime != null && timeInMilliseconds - lastAddedTime < 0) {
            throw new IllegalArgumentException("The new event time should be greater than existing one's");
        }
        eventsCountWithinLastMinute.add(timeInMilliseconds);
        eventsCountWithinLastHour.add(timeInMilliseconds);
        eventsCountWithinLastDay.add(timeInMilliseconds);
    }

    @Override
    public long getEventsCountWithinLastMinute() {
        deleteOldElementsFromDeque(eventsCountWithinLastMinute, ONE_MINUTE_IN_MILLISECONDS);
        return eventsCountWithinLastMinute.size();
    }

    @Override
    public long getEventsCountWithinLastHour() {
        deleteOldElementsFromDeque(eventsCountWithinLastHour, ONE_HOUR_IN_MILLISECONDS);
        return eventsCountWithinLastHour.size();
    }

    @Override
    public long getEventsCountWithinLastDay() {
        deleteOldElementsFromDeque(eventsCountWithinLastDay, ONE_DAY_IN_MILLISECONDS);
        return eventsCountWithinLastDay.size();
    }

    @Override
    public void clear() {
        eventsCountWithinLastMinute.clear();
        eventsCountWithinLastHour.clear();
        eventsCountWithinLastDay.clear();
    }

    /**
     * Remove old elements from deque if difference between its values and current time
     * is greater than given timeDifference.
     *
     * @param dequeToUpdate     deque which elements will be removed
     * @param timeDifference    time difference which is used in condition for remove
     */
    private void deleteOldElementsFromDeque(Deque<Long> dequeToUpdate, long timeDifference) {
        long currentTime = System.currentTimeMillis();
        Iterator<Long> iterator = dequeToUpdate.iterator();
        while (iterator.hasNext()) {
            Long eventTime = iterator.next();
            if (currentTime - eventTime > timeDifference) {
                iterator.remove();
            } else {
                break;
            }
        }
    }
}
