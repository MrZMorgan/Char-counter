package ua.com.foxminded.counter;

import java.lang.reflect.Array;
import java.util.*;

public class Counter {
    private final Map<String, String> cache = new LinkedHashMap<>();

    public Set<Character> getUniqueCharsFromString(String line) {
        Set<Character> charsUnique = new LinkedHashSet<>();

        for (int i = 0; i < line.length(); i++) {
            charsUnique.add(line.charAt(i));
        }

        return charsUnique;
    }
    
    public Map<Character, Integer> createResultMap(String line, Set<Character> uniqueChars) {
        char[] chars = line.toCharArray();

        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        int counter = 0;

        for (Character uniqueChar : uniqueChars) {
            for (char character : chars) {
                if (character == uniqueChar) {
                    counter++;
                }
            }
            resultMap.put(uniqueChar, counter);
            counter = 0;
        }

        return resultMap;
    }

    public void printResult() {
        System.out.println("Type any text and press \"Enter\" button.\n" +
                "Click \"Enter\" button without typing any text to close app.");

        String line = readString();
        OUTER: while (!line.equals("")) {
            for (Map.Entry<String, String> entryCache : cache.entrySet()) {
                if (entryCache.getKey().equals(line)) {
                    System.out.println(entryCache.getValue());
                    line = readString();
                    continue OUTER;
                }
            }

            StringBuilder result = new StringBuilder();
            createResultForLine(line, result);

            System.out.println(result.toString());
            line = readString();
        }
    }

    public void createResultForLine(String line, StringBuilder result) {
        Set<Character> charsUnique = getUniqueCharsFromString(line);
        Map<Character, Integer> resultMap = createResultMap(line, charsUnique);

        for (Map.Entry<Character, Integer> entry : resultMap.entrySet()) {
            result.append(String.format("\"%c\" - %d\n", entry.getKey(), entry.getValue()));
        }

        cache.put(line, result.toString());
    }

    private String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
