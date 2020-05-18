package com.github.danielrenes.units;

public enum TimeUnit implements Unit<TimeUnit> {
    NANOSECOND("nanoseconds", Constants.NANOSECOND_BASE_VALUE),
    MICROSECOND("microseconds", Constants.MICROSECOND_BASE_VALUE),
    MILLISECOND("milliseconds", Constants.MILLISECOND_BASE_VALUE),
    SECOND("seconds", Constants.SECOND_BASE_VALUE),
    MINUTE("minutes", Constants.MINUTE_BASE_VALUE),
    HOUR("hours", Constants.HOUR_BASE_VALUE);

    private static final class Constants {
        private static final long NANOSECOND_BASE_VALUE   = 1L;
        private static final long MICROSECOND_BASE_VALUE  = NANOSECOND_BASE_VALUE * 1000L;
        private static final long MILLISECOND_BASE_VALUE  = MICROSECOND_BASE_VALUE * 1000L;
        private static final long SECOND_BASE_VALUE       = MILLISECOND_BASE_VALUE * 1000L;
        private static final long MINUTE_BASE_VALUE       = SECOND_BASE_VALUE * 60;
        private static final long HOUR_BASE_VALUE         = MINUTE_BASE_VALUE * 60;
    }

    private final String displayName;
    private final long baseValue;

    TimeUnit(String displayName, long baseValue) {
        this.displayName = displayName;
        this.baseValue = baseValue;
    }

    @Override
    public String displayName() {
        return displayName;
    }

    @Override
    public double convert(double value, TimeUnit targetUnit) {
        return (double) baseValue / targetUnit.baseValue * value;
    }
}
