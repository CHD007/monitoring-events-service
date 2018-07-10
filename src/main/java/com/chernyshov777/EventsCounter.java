package com.chernyshov777;

/**
 * Implementations of this interface provides service to count events numbers.
 * Service provide method for adding events and methods to retrieve events count
 * within last minute, hour and day.
 */
public interface EventsCounter {
    long ONE_MINUTE_IN_MILLISECONDS = 60 * 1000;
    long ONE_HOUR_IN_MILLISECONDS = 60 * ONE_MINUTE_IN_MILLISECONDS;
    long ONE_DAY_IN_MILLISECONDS = 24 * ONE_HOUR_IN_MILLISECONDS;

    /**
     * Add event to counter
     *
     * @param timeInMilliseconds the number of milliseconds since January 1, 1970, 00:00:00 GMT
     *                           this parameter should be greater than one's added before
     *                           or it will throw {@link IllegalArgumentException}
     */
    void addEvent(Long timeInMilliseconds);

    /**
     * Get events count within the last minute.
     *
     * @return events count within the last minute
     */
    long getEventsCountWithinLastMinute();

    /**
     * Get events count within the last hour.
     *
     * @return events count within the last hour
     */
    long getEventsCountWithinLastHour();

    /**
     * Get events count within the last day.
     * 
     * @return events count within the last day
     */
    long getEventsCountWithinLastDay();

    /**
     * Remove all events from service
     */
    void clear();
}
