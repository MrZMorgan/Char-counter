package ua.com.foxminded.facade;

import ua.com.foxminded.counter.Counter;

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
        String line = readString();
        while (!line.equals("")) {
            counter.printResult(line);
            line = readString();
        }
    }
}
