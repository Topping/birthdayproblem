package com.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BooleanSupplier;
import java.util.stream.IntStream;

public class ThreadLocalLoopBirthdays {
    private final int daysInYear;
    private final int groupSize;

    public ThreadLocalLoopBirthdays(int daysInYear, int groupSize) {
        this.daysInYear = daysInYear;
        this.groupSize = groupSize;
    }

    public double test(int sampleCount) {
        return sample(sampleCount, this::sameBirthday);
    }

    private boolean sameBirthday() {
        int[] birthdays = ThreadLocalRandom.current().ints(groupSize, 0, daysInYear).toArray();
        for(int i = 0; i < groupSize; i++) {
            for(int j = i+1; j < groupSize; j++) {
                if(birthdays[i] == birthdays[j]) return true;
            }
        }
        return false;
    }

    private double sample(int sampleCount, BooleanSupplier function) {
        return IntStream.range(0, sampleCount).mapToDouble(i -> function.getAsBoolean() ? 1 : 0).average().orElseThrow();
    }
}
