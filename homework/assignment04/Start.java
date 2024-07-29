// File name:   Start.java
// Written by:  Shades Meyers
// Description: The Driver for assignment 04
// Challenges:  
// Time Spent:  1 h 4 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-28 SM      File created


import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        
    }
    public static void playerTurn(Hero player, Enemy enemy) {
        String[] COMBAT_ACTIONS = {"inventory", "quit"};
        String MAIN_COMBAT_DISPLAY_PIC =
          "\n##############"
        + "\n# inventory  #"
        + "\n#### quit ####";

        System.out.println();
        Scanner input = new Scanner(System.in);

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
            player.showInv(Start, enemy);
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
