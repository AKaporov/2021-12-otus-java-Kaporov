package ru.otus.cachehw;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.cachehw.exception.MyCacheEventListenerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
    //Надо реализовать эти методы
    private static final Logger logger = LoggerFactory.getLogger(MyCache.class);
    public static final String CREATED_ACTION = "CREATED";
    public static final String REMOVED_ACTION = "REMOVED";

    private final Map<K, V> cache = new WeakHashMap<>();
    private final List<HwListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        V put = cache.put(key, value);

        if (put != null) {

            onListener(key, value, CREATED_ACTION);
        }
    }

    @Override
    public void remove(K key) {
        V remove = cache.remove(key);

        if (remove != null) {
            onListener(key, remove, REMOVED_ACTION);
        }
    }

    private void onListener(K key, V value, String action) {
        for (HwListener<K, V> l : listeners) {
            try {
                l.notify(key, value, action);
            } catch (Exception e) {
                String errorMessage = getErrorMessage(action);
                logger.error(errorMessage, e);
                throw new MyCacheEventListenerException(errorMessage, e);
            }
        }
    }

    private String getErrorMessage(String action) {
        String locationAction = "неизвесный";
        if (CREATED_ACTION.equals(action)) {
            locationAction = "добавлению";
        } else if (REMOVED_ACTION.equals(action)) {
            locationAction = "изменению";
        }

        return "Ошибка в подписчике на событие по " + locationAction + " кэша.";
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
