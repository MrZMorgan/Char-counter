package ua.com.foxminded.formatter;

import ua.com.foxminded.interfaces.Formatable;

import java.util.Map;

public class Formatter implements Formatable {
    public String formatResultForLine(Map<Character, Integer> resultMap) {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : resultMap.entrySet()) {
            result.append(String.format("\"%c\" - %d\n", entry.getKey(), entry.getValue()));
        }

        return result.toString();
    }
}
