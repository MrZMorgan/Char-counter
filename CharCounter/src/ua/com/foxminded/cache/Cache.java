package ua.com.foxminded.cache;

import ua.com.foxminded.interfaces.Cacheble;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cache implements Cacheble {
    private final Map<String, String> cacheMap = new LinkedHashMap<>();

    public Map<String, String> getCacheMap() {
        return cacheMap;
    }
}
