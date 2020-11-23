package ua.com.foxminded.counter;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.InOrder;
import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.facade.CharCounterFacade;
import ua.com.foxminded.formatter.Formatter;
import ua.com.foxminded.interfaces.Formatable;
import java.util.LinkedHashMap;
import java.util.Map;

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



    @Test
    void interactWithCacheTest() {
        Cache cacheMock = mock(Cache.class);
        Counter counter = new Counter();
        Formatable formatter = new Formatter();

        String line = "hello";
        String expectedResult = "\"h\" - 1\n" +
                "\"e\" - 1\n" +
                "\"l\" - 2\n" +
                "\"o\" - 1\n";

        CountedDTO dto = createDto();
        CharCounterFacade facade = new CharCounterFacade(counter, formatter, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(false);

        facade.printResultForOneLine(line);

        // empty cache
        verify(cacheMock, times(0)).getValueFromCache(line);

        when(cacheMock.isPresented(line))
                .thenReturn(true);
        when(cacheMock.getValueFromCache(line))
                .thenReturn(expectedResult);

        facade.printResultForOneLine(line);
        facade.printResultForOneLine(line);

        // not empty cache
        verify(cacheMock, times(2)).getValueFromCache(line);

    }

    @Test
    void orderInteractionTest() {
        Cache cacheMock = mock(Cache.class);
        Counter counterMock = mock(Counter.class);
        Formatable formatter = mock(Formatter.class);

        String line = "hello";
        String expectedResult = "\"h\" - 1\n" +
                                "\"e\" - 1\n" +
                                "\"l\" - 2\n" +
                                "\"o\" - 1\n";

        CountedDTO dto = createDto();
        CharCounterFacade facade = new CharCounterFacade(counterMock, formatter, cacheMock);

        // empty cache order interaction test

        when(counterMock.createCountedDtoForLine(line))
                .thenReturn(dto);
        when(cacheMock.isPresented(line))
                .thenReturn(false);
        when(formatter.formatResultForLine(dto))
                .thenReturn(expectedResult);

        facade.printResultForOneLine(line);

        InOrder inOrder = inOrder(counterMock, cacheMock, formatter);

        inOrder.verify(counterMock).createCountedDtoForLine(line);
        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(formatter).formatResultForLine(dto);
        inOrder.verify(cacheMock).put(line, expectedResult);

        // not empty cache order interaction test

        when(cacheMock.isPresented(line))
                .thenReturn(true);

        facade.printResultForOneLine(line);

        inOrder.verify(counterMock).createCountedDtoForLine(line);
        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(cacheMock).getValueFromCache(line);
    }

    CountedDTO createDto() {
        CountedDTO dto = new CountedDTO();

        dto.setLine("hello");

        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put('h', 1);
        resultMap.put('e', 1);
        resultMap.put('l', 2);
        resultMap.put('0', 1);

        dto.setResultMap(resultMap);
        return dto;
    }
}