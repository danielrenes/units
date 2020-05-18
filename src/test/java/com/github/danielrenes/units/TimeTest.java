package com.github.danielrenes.units;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class TimeTest {
    @Test
    public void testCreatedInstancesAreCached() {
        Time time1 = Time.of(12, TimeUnit.HOUR);
        Time time2 = Time.of(12, TimeUnit.HOUR);
        assertSame(time1, time2);
    }

    @Test
    public void testUnitConversion() {
        Time time = Time.of(39282, TimeUnit.SECOND);
        assertEquals(654.7, time.getValue(TimeUnit.MINUTE), 10e-6);
    }

    @Test
    public void testOrdering() {
        Time smallest = Time.of(2384842, TimeUnit.SECOND);
        Time middle = Time.of(9383, TimeUnit.HOUR);
        Time biggest = Time.of(9283382, TimeUnit.MINUTE);

        List<Time> times = Arrays.asList(middle, biggest, smallest);
        Collections.sort(times);

        assertEquals(smallest, times.get(0));
        assertEquals(middle, times.get(1));
        assertEquals(biggest, times.get(2));
    }

    @Test
    public void testAddition() {
        Time time1 = Time.of(23, TimeUnit.HOUR);
        Time time2 = Time.of(12, TimeUnit.MINUTE);
        assertEquals(Time.of(23 * 60 + 12, TimeUnit.MINUTE), time1.add(time2));
    }

    @Test
    public void testSubtraction() {
        Time time1 = Time.of(4932, TimeUnit.MILLISECOND);
        Time time2 = Time.of(3, TimeUnit.SECOND);
        assertEquals(Time.of(1932, TimeUnit.MILLISECOND), time1.subtract(time2));
    }
}