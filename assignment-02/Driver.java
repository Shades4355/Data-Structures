// File name:   Driver.java
// Written by:  Shades Meyers
// Description: Client-side program for organizing News Articles 
// Challenges:  
// Time Spent:  10 min + 
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Added constructor


import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String answer;
        boolean addingArticles = true;

        System.out.println("Welcome!");

        while (addingArticles){
            System.out.println("Would you like to add an Article? (y/n)");
            answer = input.nextLine();

            if (answer.toLowerCase() == "n") {
                addingArticles = false;
            }
        }
        input.close();
        System.exit(0);
    }
} // End Program