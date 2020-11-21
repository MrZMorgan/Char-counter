package ua.com.foxminded.interfaces;

import ua.com.foxminded.counter.CountedDTO;

public interface Formatable {
    String createResultForLine(CountedDTO dto);

    void printResult(CountedDTO dto, Cacheble cache);
}
