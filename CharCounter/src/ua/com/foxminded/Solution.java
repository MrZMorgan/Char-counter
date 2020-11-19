package ua.com.foxminded;

import ua.com.foxminded.counter.Counter;
import ua.com.foxminded.facade.CharCounterFacade;

public class Solution {
    public static void main(String[] args) {
        CharCounterFacade facade = new CharCounterFacade(new Counter());
        facade.printResult();
    }
}
