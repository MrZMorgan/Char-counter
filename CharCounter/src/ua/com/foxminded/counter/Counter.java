package ua.com.foxminded.counter;

import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    public Set<Character> getUniqueCharsFromString(String line) {
        char[] charsIntermediate = line.toCharArray();
        Set<Character> charsSet = new LinkedHashSet<>();

        for (char c : charsIntermediate) {
            charsSet.add(c);
        }

        return charsSet;
    }
}
