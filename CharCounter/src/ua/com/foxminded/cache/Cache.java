package ua.com.foxminded.cache;

import ua.com.foxminded.interfaces.Cacheble;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache implements Cacheble {
    private final Map<String, String> cache = new LinkedHashMap<>();

    @Override
    public Map<String, String> getCache() {
        return cache;
    }

    public String getValueFromCache(String key) {
        return getCache().get(key);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, value);
    }

    @Override
    public boolean isPresented(String line) {
        boolean foundValueInCache = false;
        for (Map.Entry<String, String> entryCache : cache.entrySet()) {
            if (entryCache.getKey().equals(line)) {
                foundValueInCache = true;
                break;
            }
        }
        return foundValueInCache;
    }
}
