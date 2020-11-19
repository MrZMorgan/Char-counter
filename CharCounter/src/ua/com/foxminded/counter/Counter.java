package ua.com.foxminded.counter;

import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    public Set<Character> getUniqueCharsFromString(String line) {
        Set<Character> chars = new LinkedHashSet<>();

        for (int i = 0; i < line.length(); i++) {
            chars.add(line.charAt(i));
        }

        return chars;
    }
    
    public Map<Character, Integer> createResultMap(String line, Set<Character> uniqueChars) {
        char[] chars = line.toCharArray();

        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        int counter = 0;

        for (Character uniqueChar : uniqueChars) {
            for (char character : chars) {
                if (character == uniqueChar) {
                    counter++;
                }
            }
            resultMap.put(uniqueChar, counter);
            counter = 0;
        }

        return resultMap;
    }
}
