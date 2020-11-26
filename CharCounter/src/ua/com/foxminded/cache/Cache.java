package ua.com.foxminded.cache;

import ua.com.foxminded.interfaces.Cacheble;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache implements Cacheble {
    private final Map<String, Map<Character, Integer>> cache = new LinkedHashMap<>();

    @Override
    public Map<String, Map<Character, Integer>> getCache() {
        return cache;
    }

    @Override
    public Map<Character, Integer> getValueFromCache(String key) {
        return getCache().get(key);
    }

    @Override
    public void put(String key, Map<Character, Integer> value) {
        cache.put(key, value);
    }

    @Override
    public boolean isPresented(String line) {
        boolean foundValueInCache = false;
        for (Map.Entry<String, Map<Character, Integer>> entryCache : cache.entrySet()) {
            if (entryCache.getKey().equals(line)) {
                foundValueInCache = true;
                break;
            }
        }
        return foundValueInCache;
    }
}
