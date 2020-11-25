package ua.com.foxminded.facade;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.interfaces.Formatable;

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

    public void printResultForOneLine(String line) {
        if (cache.isPresented(line)) {
            String result = formatter.formatResultForLine(cache.getValueFromCache(line));
            System.out.println(result);
        } else {
            CountedDTO dto = counter.createCountedDtoForLine(line);
            cache.put(dto.getLine(), dto.getResultMap());
            String result = formatter.formatResultForLine(dto.getResultMap());
            System.out.println(result);
        }
    }

    public void readStringsAndPrintResult() {
        System.out.println("Type any sting and press \"Enter\" button\n" +
                           "Do not type anything and press the \"Enter\" button to close the program");

        String line = readString();
        while (!line.equals("")) {
            printResultForOneLine(line);
            line = readString();
        }
    }
}
