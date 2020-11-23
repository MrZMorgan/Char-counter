package ua.com.foxminded.interfaces;

import java.util.Map;

public interface Cacheble {
    Map<String, String> getCache();
    void put(String key, String value);
    boolean isPresented(String line);
    String getValueFromCache(String line);
}
