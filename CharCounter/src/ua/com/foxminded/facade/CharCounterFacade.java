package ua.com.foxminded.facade;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.formatter.Formatter;

import java.util.Scanner;

public class CharCounterFacade {
    private final Counter counter;

    public CharCounterFacade(Counter counter) {
        this.counter = counter;
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printResult() {
        Cache cache = new Cache();

        String line = readString();

        while (!line.equals("")) {
            Counter counter = new Counter();
            CountedDTO dto = counter.createCountedDtoForLine(line);
            Formatter formatter = new Formatter();
            formatter.printResult(dto, cache);
            line = readString();
        }
    }
}
