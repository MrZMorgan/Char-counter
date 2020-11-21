package ua.com.foxminded;

import ua.com.foxminded.cache.Cache;
import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.facade.CharCounterFacade;
import ua.com.foxminded.formatter.Formatter;

public class Solution {
    public static void main(String[] args) {
        CharCounterFacade facade = new CharCounterFacade(new Counter(), new Formatter(), new Cache());
        facade.printResult();
    }
}
