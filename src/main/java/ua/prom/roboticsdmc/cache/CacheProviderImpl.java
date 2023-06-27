package ua.prom.roboticsdmc.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class CacheProviderImpl<K, V> implements CacheProvider<K, V> {

    private Map<K, V> cache;

    public CacheProviderImpl(int capacity) {
        cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return cache.size() > capacity;
            }
        };
    }

    @Override
    public boolean isPresent(K key) {
        return cache.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public V getValue(K key) {
        return cache.get(key);
    }
}
