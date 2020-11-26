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
    
    public CountedDTO countCharacters(String line) {
        char[] chars = line.toCharArray();
        Set<Character> uniqueChars = getUniqueCharsFromString(line);

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

        CountedDTO dto = new CountedDTO();
        dto.setLine(line);
        dto.setResultMap(resultMap);

        return dto;
    }
}
