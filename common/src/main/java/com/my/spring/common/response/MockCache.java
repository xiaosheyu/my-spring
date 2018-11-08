package com.my.spring.common.response;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MockCache {
    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    public static void set(String key, Object val) {
        cache.put(key, val);
    }

    public static Object get(String key) {
        return cache.get(key);
    }

}
