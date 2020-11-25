package ua.com.foxminded.interfaces;

import ua.com.foxminded.counter.CountedDTO;

import java.util.Map;

public interface Formatable {
    String formatResultForLine(Map<Character, Integer> resultMap);
}
