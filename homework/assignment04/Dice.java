// File name:   Dice.java
// Written by:  Shades Meyers
// Description: A class for dice rolls
// Challenges:  
// Time Spent:  4 minutes
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-28 SM      File created


import java.util.Random;

public class Dice {
    // Variables
    private int max, min;
    private Random random = new Random();

    // Constructors
    Dice(int diceSize) {
        this.max = diceSize;
        this.min = 1;
    }

    // Methods
    public int roll(int numOfDice) {
        int total = 0;

        for (int i = 0; i < numOfDice; i++) {
            total += (random.nextInt(this.max - this.min + 1) + min);
        }
        return total;
    }
}
