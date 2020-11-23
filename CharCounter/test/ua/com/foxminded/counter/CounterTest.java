package ua.com.foxminded.counter;

import org.junit.jupiter.api.Test;

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
        String actualResult = formatter.formatResultForLine(counter.createCountedDtoForLine("hello, world!"));

        assertEquals(expectedResult, actualResult);
    }

//    @Test
//    void cacheSizeShouldBeRightSize() {
//        Counter counter = new Counter();
//        Formatable formatter = new Formatter();
//        Cache cache = new Cache();
//
//        int expectedCacheLength = 8;
//
//        formatter.printResult(counter.createCountedDtoForLine("hello"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("world"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("bread"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("word"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("hello"), cache);
//        formatter.printResult(counter.createCountedDtoForLine(" hello"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("world "), cache);
//        formatter.printResult(counter.createCountedDtoForLine("hello"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("hello"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("guinea pig"), cache);
//        formatter.printResult(counter.createCountedDtoForLine("ello"), cache);
//
//        int actualCacheLength = cache.getCacheMap().size();
//
//        assertEquals(expectedCacheLength, actualCacheLength);
//    }
}