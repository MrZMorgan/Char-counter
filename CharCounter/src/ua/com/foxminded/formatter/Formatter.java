package ua.com.foxminded.formatter;

import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.interfaces.Cacheble;
import ua.com.foxminded.interfaces.Formatable;

import java.util.Map;

public class Formatter implements Formatable {
    public String createResultForLine(CountedDTO dto) {
        StringBuilder result = new StringBuilder();
        Map<Character, Integer> resultMap = dto.getResultMap();

        for (Map.Entry<Character, Integer> entry : resultMap.entrySet()) {
            result.append(String.format("\"%c\" - %d\n", entry.getKey(), entry.getValue()));
        }

        return result.toString();
    }

    @Override
    public void printResult(CountedDTO dto, Cacheble cache) {
        boolean foundValueInCache = false;
        for (Map.Entry<String, String> entryCache : cache.getCacheMap().entrySet()) {
            if (entryCache.getKey().equals(dto.getLine())) {
                System.out.println(entryCache.getValue());
                foundValueInCache = true;
            }
        }

        if (!foundValueInCache) {
            String result = createResultForLine(dto);
            cache.getCacheMap().put(dto.getLine(), result);
            System.out.println(result);
        }
    }
}
