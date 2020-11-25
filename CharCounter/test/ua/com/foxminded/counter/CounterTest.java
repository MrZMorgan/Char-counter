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
    private Cache cacheMock;
    private Counter counter;
    private Counter counterMock;
    private Formatable formatter;
    private Formatable formatterMock;
    private String line;
    private String expectedResult;
    private CharCounterFacade facade;
    private CountedDTO dto;

    @BeforeEach
    void setUp() {
        cacheMock = mock(Cache.class);
        counter = new Counter();
        formatter = new Formatter();
        counterMock = mock(Counter.class);
        formatterMock = mock(Formatter.class);
        dto = createDto();

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
        String actualResult = formatter.formatResultForLine(dto.getResultMap());
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void shouldPutResultInCache() {
        facade = new CharCounterFacade(counter, formatter, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(false);

        facade.printResultForOneLine(line);

        verify(cacheMock, times(0)).getValueFromCache(line);
        verify(cacheMock, times(1)).put(line, dto.getResultMap());
    }

    @Test
    void shouldGetResultFromCache() {
        facade = new CharCounterFacade(counterMock, formatter, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(true);
        when(cacheMock.getValueFromCache(line))
                .thenReturn(dto.getResultMap());

        facade.printResultForOneLine(line);

        verify(cacheMock, times(1)).getValueFromCache(line);
        verify(cacheMock, times(0)).put(line, dto.getResultMap());
        verify(counterMock, times(0)).createCountedDtoForLine(line);
    }

    @Test
    void orderInteractionTestForPutResultInCache() {
        facade = new CharCounterFacade(counterMock, formatterMock, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(false);
        when(counterMock.createCountedDtoForLine(line))
                .thenReturn(dto);
        when(formatterMock.formatResultForLine(dto.getResultMap()))
                .thenReturn(expectedResult);

        facade.printResultForOneLine(line);

        InOrder inOrder = inOrder(counterMock, cacheMock, formatterMock);

        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(counterMock).createCountedDtoForLine(line);
        inOrder.verify(cacheMock).put(dto.getLine(), dto.getResultMap());
        inOrder.verify(formatterMock).formatResultForLine(dto.getResultMap());
    }

    @Test
    void orderInteractionTestForGetResultFromCache() {
        facade = new CharCounterFacade(counterMock, formatterMock, cacheMock);

        when(cacheMock.isPresented(line))
                .thenReturn(true);

        facade.printResultForOneLine(line);

        InOrder inOrder = inOrder(counterMock, cacheMock, formatterMock);

        inOrder.verify(cacheMock).isPresented(line);
        inOrder.verify(cacheMock).getValueFromCache(line);
        inOrder.verify(formatterMock).formatResultForLine(cacheMock.getValueFromCache(line));
    }

    CountedDTO createDto() {
        CountedDTO dto = new CountedDTO();

        dto.setLine(line);

        Map<Character, Integer> resultMap = new LinkedHashMap<>();
        resultMap.put('h', 1);
        resultMap.put('e', 1);
        resultMap.put('l', 3);
        resultMap.put('o', 2);
        resultMap.put(',', 1);
        resultMap.put(' ', 1);
        resultMap.put('w', 1);
        resultMap.put('r', 1);
        resultMap.put('d', 1);
        resultMap.put('!', 1);

        dto.setResultMap(resultMap);
        return dto;
    }

}