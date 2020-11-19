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
}
