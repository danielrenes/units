package com.github.danielrenes.units;

import java.util.Objects;

public final class Time implements HasUnit<Time, TimeUnit>, Comparable<Time> {
    private static final Cache<Time, TimeUnit> CACHE = new Cache<>();
    private static final TimeUnit INTERNAL_UNIT = TimeUnit.NANOSECOND;

    private final double internalValue;

    private Time(double value, TimeUnit unit) {
        internalValue = unit.convert(value, INTERNAL_UNIT);
    }

    public static Time of(double value, TimeUnit unit) {
        return getCachedOrCreateNew(value, unit);
    }

    private static Time getCachedOrCreateNew(double value, TimeUnit unit) {
        return CACHE.get(value).orElseGet(() -> createAndCache(value, unit));
    }

    private static Time createAndCache(double value, TimeUnit unit) {
        Time time = new Time(value, unit);
        CACHE.put(value, time);
        return time;
    }

    @Override
    public double getValue(TimeUnit targetUnit) {
        if (targetUnit == INTERNAL_UNIT) {
            return internalValue;
        }
        return INTERNAL_UNIT.convert(internalValue, targetUnit);
    }

    @Override
    public Time add(Time other) {
        double value = internalValue + other.internalValue;
        return getCachedOrCreateNew(value, INTERNAL_UNIT);
    }

    @Override
    public Time subtract(Time other) {
        double value = internalValue - other.internalValue;
        return getCachedOrCreateNew(value, INTERNAL_UNIT);
    }

    @Override
    public int compareTo(Time time) {
        return Double.compare(internalValue, time.internalValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return Double.compare(time.internalValue, internalValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalValue);
    }

    @Override
    public String toString() {
        return String.format("%s %s", internalValue, INTERNAL_UNIT.displayName());
    }
}
