package ua.com.foxminded;

import ua.com.foxminded.counter.Counter;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Counter().getUniqueCharsFromString(readString()));
    }

    public static String readString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
