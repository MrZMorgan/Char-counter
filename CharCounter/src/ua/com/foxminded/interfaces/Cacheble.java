package ua.com.foxminded.interfaces;

import java.util.Map;

public interface Cacheble {
    Map<String, Map<Character, Integer>> getCache();
    void put(String key, Map<Character, Integer> value);
    boolean isPresented(String line);
    Map<Character, Integer> getValueFromCache(String line);
}
