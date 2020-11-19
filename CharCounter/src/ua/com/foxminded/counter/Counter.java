package ua.com.foxminded.counter;

import java.util.*;

public class Counter {
    private final static Map<String, String> cache = new LinkedHashMap<>();

    private Set<Character> getUniqueCharsFromString(String line) {
        Set<Character> charsUnique = new LinkedHashSet<>();

        for (int i = 0; i < line.length(); i++) {
            charsUnique.add(line.charAt(i));
        }

        return charsUnique;
    }
    
    private Map<Character, Integer> createResultMap(String line, Set<Character> uniqueChars) {
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
    
    public String createResultForLine(String line) {
        StringBuilder result = new StringBuilder();
        Set<Character> charsUnique = getUniqueCharsFromString(line);
        Map<Character, Integer> resultMap = createResultMap(line, charsUnique);

        for (Map.Entry<Character, Integer> entry : resultMap.entrySet()) {
            result.append(String.format("\"%c\" - %d\n", entry.getKey(), entry.getValue()));
        }

        return result.toString();
    }

    public void printResult(String line) {
        boolean foundValueInCache = false;
        for (Map.Entry<String, String> entryCache : cache.entrySet()) {
            if (entryCache.getKey().equals(line)) {
                System.out.println(entryCache.getValue());
                foundValueInCache = true;
            }
        }

        if (!foundValueInCache) {
            String result = createResultForLine(line);
            cache.put(line, result);
            System.out.println(result);
        }
    }
}
