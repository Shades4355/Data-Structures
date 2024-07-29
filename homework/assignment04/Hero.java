// File name:   Hero.java
// Written by:  Shades Meyers
// Description: A template for the Player
// Challenges:  
// Time Spent:  1 h 34 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-27 SM      File created
// 2024-July-28 SM      Started fleshing out class
//                      Inspiration taken from my previous work
//                          https://github.com/Shades4355/D20_Combat


import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.Scanner;

public class Hero extends Entity {
    // Variables
    private Tree<String, Integer> inventory;
    private int xp;

    // Constructors
    Hero(String name) {
        super(name, 1, 10, 6, 4, 1, 2, 2);
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
            this.levelUp();
            this.xp -= threshold;
            threshold = 7 + this.level;
        }
    }
    
    // Level up
        // Level up
    public void levelUp() {
        System.out.println("\nLevel Up!");
        String[] availableStats = {"str", "dex", "con"};
        System.out.println("Pick a stat to increase:");
        
        for (String stat : availableStats) {
            System.out.println("\t" + stat);
        }

        Scanner input = new Scanner(System.in);

        String choice = "";
        while (!contains(availableStats, choice)) {
            System.out.print(">> ");
            choice = input.nextLine().toLowerCase();
        }

        this.incStats(choice);
        input.close();
    }
        // Increase Stats
    public void incStats(String stat) {
        if (stat.equals("str")) {
            this.str += 1;
        } else if (stat.equals("dex")) {
            this.dex += 1;
        } else if (stat.equals("con")) {
            this.con += 1;
            // TODO: update hit points and max HP
        }
    }
        // Show Inventory
    public void showInv(Consumer<Start> turn, Enemy enemy) {
        System.out.println("\nInventory:");
        inventory.forEach((n) -> {
            System.out.println(n.getKey() + ": " + n.getValue());
        });
        Scanner input = new Scanner(System.in);
        String choice = "";
        ArrayList<String> invList = new ArrayList<>();

        inventory.forEach((n) -> invList.add(n.getKey()));
        invList.add("back");

        while (!contains(invList, choice)){
            System.out.print(">> ");
            choice = input.nextLine();
        }

        input.close();

        if (!choice.equals("back")) {
            if (choice.equals("Healing Potion")) {
                this.hitPoints += new Dice(4).roll(2);

                if (this.hitPoints > this.maxHP) {
                    this.hitPoints = this.maxHP;
                }
            } else {
                this.attack(enemy);
                inventory.add(choice, inventory.search(choice).getValue() - 1);
            }
            input.close();
        } else {
            turn.playerTurn.accept(this, enemy);
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
