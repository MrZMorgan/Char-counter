package ua.com.foxminded.counter;

import java.util.Map;

public class CountedDTO {
    private String line;
    private Map<Character, Integer> resultMap;

    public String getLine() {
        return line;
    }

    protected void setLine(String line) {
        this.line = line;
    }

    public Map<Character, Integer> getResultMap() {
        return resultMap;
    }

    protected void setResultMap(Map<Character, Integer> resultMap) {
        this.resultMap = resultMap;
    }
}
