package com.chernyshov777;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * Unit test for EventCounterService.
 */
public class EventCounterServiceTest {

    private EventsCounter eventsCounterService;

    @Before
    public void setUp() {
        eventsCounterService = new EventsCounterService();
    }

    @After
    public void tierDown() {
        eventsCounterService.clear();
    }

    @Test
    public void shouldReturnRightEventsCount_whenThereIsOneEvent() {
        long currentTimeMillis = System.currentTimeMillis();
        eventsCounterService.addEvent(currentTimeMillis + 100);
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastHour());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastDay());
    }

    @Test
    public void shouldReturnRightEventsCount_whenThereIsOneOldEvent() {
        long currentTimeMillis = System.currentTimeMillis();
        eventsCounterService.addEvent(currentTimeMillis - 1001 * 60);
        Assert.assertEquals(0L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastHour());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastDay());
    }

    @Test
    public void shouldNotDeleteEvent_whenEventIsNotExpire() {
        long currentTimeMillis = System.currentTimeMillis();
        // remove milliseconds to mimic getCount call delay after event adding
        eventsCounterService.addEvent(currentTimeMillis - 500);
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastHour());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastDay());
    }

    @Test
    public void shouldRemoveEventFromDayQuery_whenEventIsDayExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        // remove milliseconds to mimic getCount query delay after event adding
        eventsCounterService.addEvent(currentTimeMillis - 1001 * 60 * 60 * 24);
        eventsCounterService.addEvent(currentTimeMillis);
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastDay());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(1L, eventsCounterService.getEventsCountWithinLastHour());
    }

    @Test
    public void shouldReturnZero_whenThereIsNoEvents() {
        Assert.assertEquals(0L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(0L, eventsCounterService.getEventsCountWithinLastHour());
        Assert.assertEquals(0L, eventsCounterService.getEventsCountWithinLastDay());
    }

    @Test
    public void shouldRemoveOneEventFromMinuteQueue_whenThereIsManyEvents() throws Exception{
        long currentTimeMillis = System.currentTimeMillis();
        eventsCounterService.addEvent(currentTimeMillis - 61 * 1000);
        IntStream.range(0, 1000).forEach(a -> eventsCounterService.addEvent(currentTimeMillis + a));
        Assert.assertEquals(1000L, eventsCounterService.getEventsCountWithinLastMinute());
        Assert.assertEquals(1001L, eventsCounterService.getEventsCountWithinLastHour());
        Assert.assertEquals(1001L, eventsCounterService.getEventsCountWithinLastDay());
    }
}
