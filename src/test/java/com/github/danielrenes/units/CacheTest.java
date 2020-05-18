package com.github.danielrenes.units;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class CacheTest {
    private static final int MAX_SIZE = 4;

    private Cache<Time, TimeUnit> cache;

    @Before
    public void setUp() {
        cache = new Cache<>(MAX_SIZE);
    }

    @Test
    public void testGetReturnsEmptyOptionalForKeyNotInMap() {
        assertEquals(Optional.empty(), cache.get(12));
    }

    @Test
    public void testPutAndGet() {
        Time time = Time.of(123, TimeUnit.MILLISECOND);
        cache.put(24, time);
        Optional<Time> resOpt = cache.get(24);
        assertTrue(resOpt.isPresent());
        assertEquals(time, resOpt.get());
    }

    @Test
    public void testRemovesOldestWhenSizeLimitedIsReached() {
        List<Time> times = Arrays.asList(
                Time.of(1, TimeUnit.SECOND),
                Time.of(2, TimeUnit.SECOND),
                Time.of(3, TimeUnit.SECOND),
                Time.of(4, TimeUnit.SECOND),
                Time.of(5, TimeUnit.SECOND)
        );

        for (int i = 0; i < times.size(); i++) {
            cache.put(i, times.get(i));
        }

        assertEquals(Optional.empty(), cache.get(0));

        for (int i = 1; i < times.size(); i++) {
            Optional<Time> resOpt = cache.get(i);
            assertTrue(resOpt.isPresent());
            assertEquals(times.get(i), resOpt.get());
        }
    }
}