package com.github.danielrenes.units;

public interface Unit<T extends Unit<T>> {
    String displayName();
    double convert(double value, T targetUnit);
}
