package ua.com.foxminded.facade;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.interfaces.Formatable;

import java.util.Map;

import static ua.com.foxminded.Main.readString;

public class CharCounterFacade {
    private final Counter counter;
    private final Formatable formatter;
    private final Cache cache;

    public CharCounterFacade(Counter counter, Formatable formatter, Cache cache) {
        this.counter = counter;
        this.formatter = formatter;
        this.cache = cache;
    }



    public void printResult() {
        System.out.println("Type any sting and press \"Enter\" button\n" +
                           "Do not type anything and press the \"Enter\" button to close the program");
        String line = readString();

        while (!line.equals("")) {
            boolean foundValueInCache = false;
            CountedDTO dto = counter.createCountedDtoForLine(line);
            for (Map.Entry<String, String> entryCache : cache.getCacheMap().entrySet()) {
                if (entryCache.getKey().equals(dto.getLine())) {
                    System.out.println(entryCache.getValue());
                    foundValueInCache = true;
                }
            }

            if (!foundValueInCache) {
                String result = formatter.formatResultForLine(dto);
                cache.getCacheMap().put(dto.getLine(), result);
                System.out.println(result);
            }
            line = readString();
        }
    }
}
