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
        CountedDTO dto = counter.createCountedDtoForLine(line);
        if (cache.isPresented(line)) {
            System.out.println(cache.getValueFromCache(line));
//            System.out.println(cache.getCache().size()); //длина кэша
        } else {
            String result = formatter.formatResultForLine(dto);
            cache.put(line, result);
            System.out.println(result);
//            System.out.println(cache.getCache().size()); //длина кэша
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
