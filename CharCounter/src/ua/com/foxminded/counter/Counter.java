package ua.com.foxminded.counter;

import java.util.*;

public class Counter {
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

    public CountedDTO createCountedDtoForLine(String line) {
        CountedDTO dto = new CountedDTO();

        dto.setLine(line);
        Set<Character> charsUnique = getUniqueCharsFromString(line);
        dto.setResultMap(createResultMap(line, charsUnique));

        return dto;
    }
}
