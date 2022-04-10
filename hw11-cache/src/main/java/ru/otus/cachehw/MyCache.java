package ru.otus.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final Map<K, V> cache = new WeakHashMap<>();
    private final List<HwListener<K, V>> listeners = new ArrayList<>();


    @Override
    public void put(K key, V value) {
        V put = cache.put(key, value);

        if (put != null) {
            listeners.forEach(l -> l.notify(key, value, "CREATED"));
        }
    }

    @Override
    public void remove(K key) {
        V remove = cache.remove(key);

        if (remove != null) {
            listeners.forEach(l -> l.notify(key, remove, "REMOVED"));
        }
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }
}
