package com.github.danielrenes.units;

public interface HasUnit<T extends HasUnit<T, U>, U extends Unit<U>> {
    double getValue(U targetUnit);
    T add(T other);
    T subtract(T other);
}
