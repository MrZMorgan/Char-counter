package ua.com.foxminded.counter;

import java.util.Map;

public class CountedDTO {
    private String line;
    private Map<Character, Integer> resultMap;

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Map<Character, Integer> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<Character, Integer> resultMap) {
        this.resultMap = resultMap;
    }
}
