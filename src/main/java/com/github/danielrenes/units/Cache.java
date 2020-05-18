package com.github.danielrenes.units;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Map.Entry.comparingByValue;

final class Cache<T extends HasUnit<T, U>, U extends Unit<U>> {
    private static final int DEFAULT_MAX_SIZE = 50;

    private final Map<Double, CacheEntry<T>> cache = new ConcurrentHashMap<>();
    private final int maxSize;

    Cache() {
        this(DEFAULT_MAX_SIZE);
    }

    Cache(int maxSize) {
        this.maxSize = maxSize;
    }

    public void put(double key, T value) {
        cache.put(key, new CacheEntry<>(value));

        if (cache.size() > maxSize) {
            removeOldest();
        }
    }

    public Optional<T> get(double key) {
        if (!cache.containsKey(key)) {
            return Optional.empty();
        }

        return Optional.of(cache.get(key).value);
    }

    private void removeOldest() {
        cache.entrySet().stream()
                .min(comparingByValue())
                .ifPresent(entry -> cache.remove(entry.getKey()));
    }

    private static class CacheEntry<T> implements Comparable<CacheEntry<T>> {
        private final Instant createdAt = Instant.now();
        private final T value;

        private CacheEntry(T value) {
            this.value = value;
        }

        @Override
        public int compareTo(CacheEntry<T> cacheEntry) {
            return createdAt.compareTo(cacheEntry.createdAt);
        }
    }
}
