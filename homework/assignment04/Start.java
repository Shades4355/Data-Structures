// File name:   Start.java
// Written by:  Shades Meyers
// Description: The Driver for assignment 04
// Challenges:  
// Time Spent:  1 h 19 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-28 SM      File created


import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        // Variables
        Hero player;
        Scanner input = new Scanner(System.in);
        String name;
        Enemy[] enemyList = {
            new Enemy("Goblin", 1, 4, 4, 0, 1, 1, 1, 1),
            new Enemy("Kobold", 1, 6, 4, 1, 0, 2, 1, 2),
            new Enemy("Dragonling", 3, 6, 3, 2, 3, 2, 2, 5)};

        // Start Game
        System.out.println("What's your name, hero?");
        System.out.print(">> ");
        name = input.nextLine();

        player = new Hero(name);

        while (player.isAlive()) {
            Enemy enemy = enemyList[new Dice(enemyList.length).roll(1) - 1];
            System.out.println("A " + enemy.getName() + " attacked you!");
            
            while (enemy.isAlive() && player.isAlive()) {
                playerTurn(player, enemy);
                enemy.attack(player);
            }
        }

        System.out.println("\nCongratulations, " + player.getName() + ", you made it to level " + player.getLevel() + "!");
    } // end Main

    public static void playerTurn(Hero player, Enemy enemy) {
        // Variables
        Scanner input = new Scanner(System.in);
        String[] COMBAT_ACTIONS = {"inventory", "quit"};
        String MAIN_COMBAT_DISPLAY_PIC =
          "\n##############"
        + "\n# inventory  #"
        + "\n#### quit ####";

        // Start
        System.out.println(MAIN_COMBAT_DISPLAY_PIC);

        String choice = "";
        while (!contains(COMBAT_ACTIONS, choice)) {
            System.out.print(">> ");
            choice = input.nextLine();
        }

        if (choice.equals("quit")) {
            input.close();
            System.out.println("Goodbye...");
            System.exit(0);
        } else {
            player.showInv(enemy);
        }
        input.close();
    } // End Player Turn

    public static boolean contains(String[] arr, String search) {
        for (String action : arr) {
            if (search.equals(action)) {
                return true;
            }
        }
        return false;
    }
}
