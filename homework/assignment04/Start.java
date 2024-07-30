// File name:   Start.java
// Written by:  Shades Meyers
// Description: The Driver for assignment 04
// Challenges:  Working with Scanner is always...an experience
// Time Spent:  2 h 24 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-28 SM      File created
// 2024-July-29 SM      Bug squashing
//                      Added dropItems() method


import java.util.ArrayList;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        // Variables
        Hero player;
        Scanner input = new Scanner(System.in);
        String name;

        // Start Game
        System.out.println("What's your name, hero?");
        System.out.print(">> ");
        name = input.nextLine();

        player = new Hero(name);

        start(player, input);

        input.close();
        System.out.println("\nCongratulations, " + player.getName() + ", you made it to level " + player.getLevel() + "!");
    } // end Main

    public static void start(Hero player, Scanner input) {
        Enemy[] enemyList = {
                new Enemy("Goblin", 1, 4, 4, 0, 1, 1, 1, 1),
                new Enemy("Kobold", 1, 6, 4, 1, 0, 2, 1, 2),
                new Enemy("Dragonling", 3, 6, 3, 2, 3, 2, 2, 5) };

        while (player.isAlive()) {
            Enemy enemy = enemyList[new Dice(enemyList.length).roll(1) - 1];
            System.out.println("\nA " + enemy.getName() + " attacked " + player.getName() + "!");

            while (enemy.isAlive() && player.isAlive()) {
                playerTurn(player, enemy, input);
                if (enemy.isAlive()) {
                    enemy.attack(player);
                }
            } // End while Enemy is alive loop

            System.out.println("\n" + player.getName() + " defeated the " + enemy.getName() + "!");
            dropItems(enemy, player);
            System.out.println(player.getName() + " gained " + enemy.grantXP() + " XP");
            player.gainXP(enemy.grantXP(), input);
        } // End while Player is alive loop
    }
    public static void playerTurn(Hero player, Enemy enemy, Scanner input) {
        // Variables
        String[] COMBAT_ACTIONS = {"Inventory", "Quit"};
        String choice;
        String MAIN_COMBAT_DISPLAY_PIC =
          "\n##############"
        + "\n# Inventory  #"
        + "\n#### Quit ####";

        System.out.println(MAIN_COMBAT_DISPLAY_PIC);

        choice = "";
        while (!contains(COMBAT_ACTIONS, choice)) {
            System.out.print(">> ");
            choice = input.nextLine();
        }

        if (choice.equals("Quit")) {
            System.out.println("Goodbye...");
            System.exit(0);
        } else {
            player.showInv(enemy, input);
        }
    } // End Player Turn

    public static boolean contains(String[] arr, String search) {
        for (String action : arr) {
            if (search.equals(action)) {
                return true;
            }
        }
        return false;
    }

    // Item drops - in a full fledge game this would be unique to each enemy type
    public static void dropItems(Enemy enemy, Hero player) {
        // Can't create a generic array of Pairs<>, so using an ArrayList
        ArrayList<Pairs<String, Integer>> dropTable = new ArrayList<Pairs<String, Integer>>(); 
        dropTable.add(new Pairs<String,Integer>("Bombs", 3));
        dropTable.add(new Pairs<String,Integer>("Bombs", 5));
        dropTable.add(new Pairs<String,Integer>("Bow and Arrows", 5));
        dropTable.add(new Pairs<String,Integer>("Healing Potions", 1));
        dropTable.add(new Pairs<String,Integer>("Throwing Knives", 3));
        dropTable.add(new Pairs<String,Integer>("Throwing Knives", 5));
        
        Pairs<String, Integer> item = dropTable.get(new Dice(dropTable.size()).roll(1) - 1);
        
        System.out.println(enemy.getName() + " dropped " + item.getValue() + " " + item.getKey());
        if (!player.getInventory().contains(item.getKey())) {
            player.getInventory().add(item);
        } else {
            player.getInventory().add(item.getKey(), player.getInventory().search(item.getKey()).getValue() + item.getValue());
        }
    }
}
