package ua.com.foxminded.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache {
    private final Map<String, String> cacheMap = new LinkedHashMap<>();

    public Map<String, String> getCacheMap() {
        return cacheMap;
    }
}
