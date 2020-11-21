package ua.com.foxminded.facade;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.interfaces.Cacheble;
import ua.com.foxminded.interfaces.Formatable;
import java.util.Scanner;

public class CharCounterFacade {
    Counter counter;
    Formatable formatter;
    Cache cache;

    public CharCounterFacade(Counter counter, Formatable formatter, Cache cache) {
        this.counter = counter;
        this.formatter = formatter;
        this.cache = cache;
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printResult() {
        System.out.println("Type any sting and press \"Enter\" button\n" +
                           "Do not type anything and press the \"Enter\" button to close the program");
        String line = readString();

        while (!line.equals("")) {
            CountedDTO dto = counter.createCountedDtoForLine(line);
            formatter.printResult(dto, cache);
            line = readString();
        }
    }
}
