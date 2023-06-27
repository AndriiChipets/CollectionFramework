package ua.prom.roboticsdmc.cache;

public interface CacheProvider<K, V> {
    
    boolean isPresent(K key);

    void put(K key, V value);

    V getValue(K key);
}
