package com.example;

import java.util.BitSet;
import java.util.Random;

public class BitSetBirthdays {
    private final int daysInYear;
    private final int groupSize;
    private final Random random;

    public BitSetBirthdays(int daysInYear, int groupSize) {
        this.daysInYear = daysInYear;
        this.groupSize = groupSize;
        this.random = new Random();
    }

    public double test(int sampleCount) {
        double matches = 0;
        for(int i = 0; i < sampleCount; i++) {
            if (sample()) matches++;
        }
        return matches / sampleCount;
    }

    private boolean sample() {
        var birthdays = new BitSet(daysInYear); // Each bit represents a day of the year
        for(int j = 0; j < groupSize; j++) { // Every member of the group has a birthday
            var birthday = random.nextInt(0, daysInYear + 1); // Generate the birthday
            if (birthdays.get(birthday)) { // If someone else already has that birthday, we found a match
                return true;
            } else {
                birthdays.set(birthday); // Nobody else had that birthday, set the bit-flag for that day to true
            }
        }
        return false; // No overlapping birthdays
    }
}
