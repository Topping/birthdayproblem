package com.example;

public class Main {
    private static final int SAMPLES = 100_000;
    private static final int DAYS_IN_YEAR = 365;
    private static final int GROUP_SIZE = 40;

    public static void main(String[] args) {
        int samples = 100_000;
        BitSetBirthdays bitset = new BitSetBirthdays(DAYS_IN_YEAR, GROUP_SIZE);
        BooleanArrayBirthdays boolArray = new BooleanArrayBirthdays(DAYS_IN_YEAR, GROUP_SIZE);
        ThreadLocalLoopBirthdays loop = new ThreadLocalLoopBirthdays(DAYS_IN_YEAR, GROUP_SIZE);
        var bsMatches = bitset.test(samples) * 100;
        var boolMatches = boolArray.test(samples) * 100;
        var loopMatches = loop.test(samples) * 100;
        System.out.println("Bitset: " + bsMatches + "%");
        System.out.println("Boolean array: " + boolMatches + "%");
        System.out.println("Loops: " + loopMatches + "%");
    }
}