package ua.com.foxminded.counter;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.facade.CharCounterFacade;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.interfaces.Formatable;

import static org.junit.jupiter.api.Assertions.*;

class CounterTest {

    @Test
    void createResultForLineShouldCreateRightResult() {
        String expectedResult = "\"h\" - 1\n" +
                                "\"e\" - 1\n" +
                                "\"l\" - 3\n" +
                                "\"o\" - 2\n" +
                                "\",\" - 1\n" +
                                "\" \" - 1\n" +
                                "\"w\" - 1\n" +
                                "\"r\" - 1\n" +
                                "\"d\" - 1\n" +
                                "\"!\" - 1\n";

        Counter counter = new Counter();
        Formatable formatter = new Formatter();
        String actualResult = formatter.createResultForLine(counter.createCountedDtoForLine("hello, world!"));

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void cacheSizeShouldBeRightSize() {

    }
}