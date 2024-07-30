// File name:   Hero.java
// Written by:  Shades Meyers
// Description: A template for the Player
// Challenges:  None
// Time Spent:  1 h 42 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-27 SM      File created
// 2024-July-28 SM      Started fleshing out class
//                      Inspiration taken from my previous work
//                          https://github.com/Shades4355/D20_Combat
// 2024-July-29 SM      Bug squashing
//                      Added a "Struggle" attack


import java.util.ArrayList;
import java.util.Scanner;

public class Hero extends Entity {
    // Variables
    private Tree<String, Integer> inventory;
    private int xp;

    // Constructors
    Hero(String name) {
        super(name, 1, 10, 6, 4, 1, 2, 2);
        this.inventory = new Tree<String, Integer>(new Pairs<String, Integer>("Bow and Arrows", 5));
        this.inventory.add("Bombs", 2);
        this.inventory.add("Healing Potions", 3);
        this.xp = 0;
    }

    // Methods
    // Accessors & Mutators
        // Level & XP
    public int getLevel() { return this.level; }
    public int getXP() { return this.xp; }
        // Inventory
    public Tree<String, Integer> getInventory() { return inventory; }
    public void checkInventory(Scanner input) {
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
    }

    // Combat
        // Gain XP
    public void gainXP(int xpGained, Scanner input) {
        this.xp += xpGained;

        int threshold = 3 + this.level;
        while (this.xp >= threshold) {
            this.levelUp(input);
            this.xp -= threshold;
            threshold = 7 + this.level;
        }
        Start.start(this, input);
    }
    
    // Level up
        // Level up
    public void levelUp(Scanner input) {
        int bonusHP = hitDice.roll(1) + this.con;
        this.maxHP += bonusHP;
        this.hitPoints += bonusHP;
        System.out.println("\nLevel Up!");
        String[] availableStats = {"str", "dex", "con"};
        System.out.println("Pick a stat to increase:");

        for (String stat : availableStats) {
            System.out.println(stat);
        }

        String choice = "";
        while (!contains(availableStats, choice)) {
            System.out.print(">> ");
            choice = input.nextLine().toLowerCase();
        }

        this.incStats(choice, input);
    }
        // Increase Stats
    public void incStats(String stat, Scanner input) {
        if (stat.equals("str")) {
            this.str += 1;
        } else if (stat.equals("dex")) {
            this.dex += 1;
        } else if (stat.equals("con")) {
            this.con += 1;
            this.maxHP += this.level;
            this.hitPoints += this.level;
        }
        this.gainXP(0, input);
    }
        // Show Inventory
    public void showInv(Enemy enemy, Scanner input) {
        System.out.println("\nInventory:");
        inventory.forEach((n) -> {
            System.out.println(n.getKey() + ": " + n.getValue());
        });
        if (inventory.size() == 0 || (inventory.size() == 1 && inventory.contains("Healing Potions"))) {
            System.out.println("Struggle");
        }
        System.out.println("Back");
        String choice = "";
        ArrayList<String> invList = new ArrayList<>();

        inventory.forEach((n) -> invList.add(n.getKey()));
        if (inventory.size() == 0 || (inventory.size() == 1 && inventory.contains("Healing Potions"))) {
            invList.add("Struggle");
        }
        invList.add("Back");

        while (!contains(invList, choice)){
            System.out.print(">> ");
            choice = input.nextLine();
        }

        if (!choice.equals("Back")) {
            if (choice.equals("Healing Potions")) {
                this.hitPoints += new Dice(4).roll(2);

                if (this.hitPoints > this.maxHP) {
                    this.hitPoints = this.maxHP;
                }
                System.out.println("\n" + this.name + " healed up to " + this.hitPoints);
            } else if (choice.equals("Struggle")) {
                this.attack(enemy);
                this.attack(this);
            } else {
                this.attack(enemy);
                inventory.add(choice, inventory.search(choice).getValue() - 1);
            }
        } else {
            Start.playerTurn(this, enemy, input);
        }
    }

    // Misc Methods
    public boolean contains(String[] arr, String search) {
        if (arr.length == 0) {
            return false;
        }

        for (String content : arr) {
            if (content.equals(search)) {
                return true;
            }
        }
        return false;
    }
    public boolean contains(ArrayList<String> arr, String search) {
        if (arr.size() == 0) {
            return false;
        }

        for (String content : arr) {
            if (content.equals(search)) {
                return true;
            }
        }
        return false;
    }
} // End Class
