package ua.com.foxminded.counter;

import org.junit.jupiter.api.BeforeEach;
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
    Cache cacheMock;
    Counter counter;
    Counter counterMock;
    Formatable formatter;
    Formatable formatterMock;
    String line;
    String expectedResult;
    CharCounterFacade facade;

    @BeforeEach
    void setUp() {
        cacheMock = mock(Cache.class);
        counter = new Counter();
        formatter = new Formatter();
        counterMock = mock(Counter.class);
        formatterMock = mock(Formatter.class);

        line = "hello, world!";
        expectedResult = "\"h\" - 1\n" +
                         "\"e\" - 1\n" +
                         "\"l\" - 3\n" +
                         "\"o\" - 2\n" +
                         "\",\" - 1\n" +
                         "\" \" - 1\n" +
                         "\"w\" - 1\n" +
                         "\"r\" - 1\n" +
                         "\"d\" - 1\n" +
                         "\"!\" - 1\n";
    }

    @Test
    void shouldCreateRightResultForLine() {
        String actualResult = formatter.formatResultForLine(counter.createCountedDtoForLine(line));
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldPutResultInCache() {
        facade = new CharCounterFacade(counter, formatter, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(false);

        facade.printResultForOneLine(line);

        verify(cacheMock, times(0)).getValueFromCache(line);
        verify(cacheMock, times(1)).put(line, expectedResult);
    }

    @Test
    void shouldGetResultFromCache() {
        facade = new CharCounterFacade(counter, formatter, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(true);
        when(cacheMock.getValueFromCache(line))
                .thenReturn(expectedResult);

        facade.printResultForOneLine(line);

        verify(cacheMock, times(1)).getValueFromCache(line);
        verify(cacheMock, times(0)).put(line, expectedResult);

    }

    @Test
    void orderInteractionTestForPutResultInCache() {
        CountedDTO dto = createDto();
        facade = new CharCounterFacade(counterMock, formatterMock, cacheMock);

        when(counterMock.createCountedDtoForLine(line))
                .thenReturn(dto);
        when(cacheMock.isPresented(line))
                .thenReturn(false);
        when(formatterMock.formatResultForLine(dto))
                .thenReturn(expectedResult);

        facade.printResultForOneLine(line);

        InOrder inOrder = inOrder(counterMock, cacheMock, formatterMock);

        inOrder.verify(counterMock).createCountedDtoForLine(line);
        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(formatterMock).formatResultForLine(dto);
        inOrder.verify(cacheMock).put(line, expectedResult);
    }

    @Test
    void orderInteractionTestForGetResultFromCache() {
        CountedDTO dto = createDto();
        facade = new CharCounterFacade(counterMock, formatterMock, cacheMock);

        when(counterMock.createCountedDtoForLine(line))
                .thenReturn(dto);

        when(cacheMock.isPresented(line))
                .thenReturn(true);

        facade.printResultForOneLine(line);

        InOrder inOrder = inOrder(counterMock, cacheMock, formatterMock);

        inOrder.verify(counterMock).createCountedDtoForLine(line);
        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(cacheMock).getValueFromCache(line);
    }

    CountedDTO createDto() {
        CountedDTO dto = new CountedDTO();

        dto.setLine(line);

        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put('h', 1);
        resultMap.put('e', 1);
        resultMap.put('l', 3);
        resultMap.put(',', 2);
        resultMap.put(' ', 1);
        resultMap.put('w', 1);
        resultMap.put('r', 1);
        resultMap.put('d', 1);
        resultMap.put('!', 1);

        dto.setResultMap(resultMap);
        return dto;
    }
}