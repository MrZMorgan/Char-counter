package ua.com.foxminded.facade;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.CountedDTO;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.interfaces.Cacheble;

import java.util.Scanner;

public class CharCounterFacade {

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printResult() {
        Cacheble cache = new Cache();

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
