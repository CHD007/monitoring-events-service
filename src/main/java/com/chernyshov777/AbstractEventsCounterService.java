package com.chernyshov777;

import java.util.Iterator;
import java.util.Queue;

public abstract class AbstractEventsCounterService implements EventsCounter {
    protected Queue<Long> eventsCountWithinLastMinute;
    protected Queue<Long> eventsCountWithinLastHour;
    protected Queue<Long> eventsCountWithinLastDay;

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
    protected void deleteOldElementsFromDeque(Queue<Long> dequeToUpdate, long timeDifference) {
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
