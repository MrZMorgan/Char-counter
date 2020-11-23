package ua.com.foxminded.interfaces;

import ua.com.foxminded.counter.CountedDTO;

public interface Formatable {
    String formatResultForLine(CountedDTO dto);
}
