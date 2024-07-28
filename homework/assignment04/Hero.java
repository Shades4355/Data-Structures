// File name:   Hero.java
// Written by:  Shades Meyers
// Description: A template for the Player
// Challenges:  
// Time Spent:  28 minutes + ( - )
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-27 SM      File created
// 2024-July-28 SM      Started fleshing out class
//                      Inspiration taken from my previous work
//                          https://github.com/Shades4355/D20_Combat


import java.util.Scanner;

public class Hero extends Entity {
    // Variables
    private Tree<String, Integer> inventory;
    private int xp;

    // Constructors
    Hero(String name) {
        super(name, 1, 10, 4, 4, 1, 2, 2);
        this.inventory = new Tree<String, Integer>(new Pairs<String, Integer>("Bombs", 2));
        this.inventory.add("Bow", 1);
        this.inventory.add("Arrows", 5);
        this.inventory.add("Healing Potions", 3);
        this.xp = 0;
    }

    // Methods
    // Accessors & Mutators
        // XP
    public int getXP() { return this.xp; }
        // Inventory
    public void getInventory() { System.out.println(inventory); }
    public void checkInventory() {
        Scanner input = new Scanner(System.in);
        int invCap = 7 + this.str;

        while (invCap < this.inventory.size()) {
            System.out.println("\nYour bag has grown too heavy; pick an item to discard:");
            this.inventory.forEach((n) -> System.out.println(n));

            String choice = "";
            while (!inventory.contains(choice)) {
                System.out.print(">> ");
                choice = input.nextLine();
            }
            inventory.remove(choice);
        } 
        input.close();
    }

    // Combat
        // Gain XP
    public void gainXP(int xpGained) {
        this.xp += xpGained;

        int threshold = 7 + this.level;
        while (this.xp >= threshold) {
            this.level += 1;
            this.xp -= threshold;
        }
    }
}
