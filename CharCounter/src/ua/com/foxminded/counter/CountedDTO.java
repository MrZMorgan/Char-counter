package ua.com.foxminded.counter;

import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CountedDTO)) return false;
        CountedDTO dto = (CountedDTO) o;
        return Objects.equals(getLine(), dto.getLine()) &&
                Objects.equals(getResultMap(), dto.getResultMap());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLine(), getResultMap());
    }
}
