package ua.com.foxminded.formatter;

import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.interfaces.Formatable;

import java.util.Map;

public class Formatter implements Formatable {
    public String formatResultForLine(CountedDTO dto) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> resultMap = dto.getResultMap();

        for (Map.Entry<Character, Integer> entry : resultMap.entrySet()) {
            result.append(String.format("\"%c\" - %d\n", entry.getKey(), entry.getValue()));
        }

        return result.toString();
    }
}
