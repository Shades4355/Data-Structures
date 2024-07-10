// File name:   Driver.java
// Written by:  Shades Meyers
// Description: Client-side program for organizing News Articles 
// Challenges:  Sorting a list of Objects
// Time Spent:  3 h 11 min + 
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Added constructor
//                      Started coding


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // variables
        Scanner input = new Scanner(System.in);
        String answer, fileType, title, author, lastChanged, dateCreated, sortOption;
        Article article;
        ArrayList<Article> articleList = new ArrayList<>();
        String[] acceptedFileTypes = {"PDF", "DOC", "DOCX", "GDOC"};
        
        System.out.println("Welcome!");

        // continue running program until exited
        while (true) {
            // offer to add articles to list
            while (true) {
                System.out.println("\nWould you like to add an Article? (y/n)");
                System.out.print(">> ");

                answer = input.nextLine();

                // if not adding a new Article, skip the rest of this section
                if (!answer.toLowerCase().equals("y")) {
                    System.out.println("\nMoving on to show the current list of Articles");
                    break;
                }

                // create Articles
                while (true) {
                    System.out.println("\nWhat type of Article do you wish to add? (Investigation, Draft, Send to Press)");
                    System.out.print(">> ");

                    answer = input.nextLine();

                    // If article type is invalid, re-prompt for Article type
                    if (!Arrays.asList("investigation", "draft", "send to press").contains(answer.toLowerCase())) {
                        System.out.println("I'm sorry, that Article type is not recognized");
                        continue;
                    }

                    // Prompt for fileType, title, author, lastChanged, and dateCreated
                    // fileType
                    while (true) {
                        System.out.println("\nWhat type of File is this article?");
                        System.out.print("Accepted formats are: ");
                        for (int i = 0; i < acceptedFileTypes.length; i++) {
                            System.out.print(acceptedFileTypes[i]);
                            if (i < acceptedFileTypes.length - 1) {
                                System.out.print(", ");
                            }
                        }
                        System.out.print("\n>> ");
                        fileType = input.nextLine();

                        // if input invalid, re-prompt
                        if (!testArrayContents(acceptedFileTypes, fileType)) {
                            System.out.println("I'm sorry, that file type is not on the recognized list");
                            continue;
                        } else {
                            break;
                        }
                    }

                    // title
                    while (true) {
                        String correct;

                        System.out.println("\nWhat is the Article's title?");
                        System.out.print(">> ");

                        title = input.nextLine();

                        System.out.println("Is \"" + title + "\" correct? (y/n)");
                        System.out.print(">> ");

                        correct = input.nextLine();

                        if (!correct.toLowerCase().equals("y")) {
                            System.out.println("Ok; let's try again");
                            continue;
                        } else {
                            break;
                        }
                    }
                    
                    // author
                    while (true) {
                        String correct;

                        System.out.println("\nWhat is the Author's name?");
                        System.out.print(">> ");

                        author = input.nextLine();

                        System.out.println("Is \"" + author + "\" correct? (y/n)");
                        System.out.print(">> ");

                        correct = input.nextLine();

                        if (!correct.toLowerCase().equals("y")) {
                            System.out.println("Ok; let's try again");
                            continue;
                        } else {
                            break;
                        }
                    }
                    
                    // lastChanged
                    while (true) {
                        String correct;

                        System.out.println("\nWhat is the date it was last changed (yyyy-mm-dd)");
                        System.out.print(">> ");

                        lastChanged = input.nextLine();

                        System.out.println("Is \"" + lastChanged + "\" correct? (y/n)");
                        System.out.print(">> ");

                        correct = input.nextLine();

                        if (!correct.toLowerCase().equals("y")) {
                            System.out.println("Ok; let's try again");
                            continue;
                        } else {
                            break;
                        }
                    }
                    
                    // dateCreated
                    while (true) {
                        String correct;

                        System.out.println("\nWhat is the date it was created? (yyyy-mm-dd)");
                        System.out.print(">> ");

                        dateCreated = input.nextLine();

                        System.out.println("Is \"" + dateCreated + "\" correct? (y/n)");
                        System.out.print(">> ");

                        correct = input.nextLine();

                        if (!correct.toLowerCase().equals("y")) {
                            System.out.println("Ok; let's try again");
                            continue;
                        } else {
                            break;
                        }
                    }

                    // If Article type is valid, create Article
                    while (true) {
                        try {
                            if (answer.toLowerCase().equals("investigation")) {
                                article = new Investigation(fileType, title, author, dateCreated, lastChanged);
                            } else if (answer.toLowerCase().equals("draft")) {
                                article = new Draft(fileType, title, author, dateCreated, lastChanged);
                            } else {
                                article = new SendToPress(fileType, title, author, dateCreated, lastChanged);
                            }
                        } catch (InvalidArticle e) {
                            System.err.println(e);
                            continue;
                        }
                        break;
                    }

                    // double check that article is defined
                    if (article != null) {
                        break;
                    }
                }

                // add Article to list
                articleList.add(article);

                // return to top to prompt for another Article entry
            }

            // verify article(s) have been added to list
            if (articleList.size() <= 0) {
                System.out.println("Article List is empty\n");

                // prompt to restart program
                System.out.println("Would you like to start again? (y/n)");
                System.out.print(">> ");

                answer = input.nextLine();

                // if answer isn't "y", close program
                if (!answer.toLowerCase().equals("y")) {
                    System.out.println("Goodbye!");
                    input.close();
                    System.exit(0);
                }

                continue;
            }

            // sort ArrayList
            // prompt for how to sort
            while (true) {
                System.out.println("\nHow would you like the list sorted?");
                System.out.println("Options: state, file type, title, author, last changed, and date created");
                System.out.print(">> ");

                sortOption = input.nextLine();

                if (Arrays.asList("state", "file type", "title", "author", "last changed", "date created").contains(sortOption.toLowerCase())) {
                    break;
                } else {
                    System.out.println("Please try again");
                }
            }

            // sort articleList
            articleList = articleSorter(sortOption, articleList);
            
            // If articles are in list, display list
            for (int i = 0; i < articleList.size(); i++) {
                System.out.printf("%d.) ", i + 1);
                System.out.println(articleList.get(i).getTitle());
                System.out.println("     " + articleList.get(i).getAuthor() + " (" + articleList.get(i).getState() + ")");
                System.out.println("     " + articleList.get(i).getDateCreated());
                System.out.println("     " + articleList.get(i).getLastChanged());
                System.out.println("     " + articleList.get(i).getFileType());
            }
            
            // prompt to restart program
            System.out.println("Would you like to start again? (y/n)");
            System.out.print(">> ");
            
            answer = input.nextLine();

            // if answer isn't "y", close program
            if (!answer.toLowerCase().equals("y")) {
                System.out.println("Goodbye!");
                input.close();
                System.exit(0);
            }
        } // end core while loop
    } // end main

    public static boolean testArrayContents(String[] array, String searchFor) {
        for (String element : array) {
            if (element.toLowerCase().equals(searchFor.toLowerCase())) {
                return true;
            }
        }
        return false;
    } // end testArrayContents

    public static ArrayList<Article> articleSorter(String sortOption, ArrayList<Article> arr) {
        // variables
        ArrayList<Article> sortedArray;

        // copy arr to sortedArray
        sortedArray = arr;

        // sort sortedArray
        // sort options: state, file type, title, author, last changed, and date created
        if (sortOption.toLowerCase() == "state") {
            sortedArray.sort((obj1, obj2) -> obj1.getState().compareTo(obj2.getState()));
        } else if (sortOption.toLowerCase() == "file type") {
            sortedArray.sort((obj1, obj2) -> obj1.getFileType().compareTo(obj2.getFileType()));
        } else if (sortOption.toLowerCase() == "title") {
            sortedArray.sort((obj1, obj2) -> obj1.getTitle().compareTo(obj2.getTitle()));
        } else if (sortOption.toLowerCase() == "author") {
            sortedArray.sort((obj1, obj2) -> obj1.getAuthor().compareTo(obj2.getAuthor()));
        } else if (sortOption.toLowerCase() == "last changed") {
            sortedArray.sort((obj1, obj2) -> obj1.getLastChanged().compareTo(obj2.getLastChanged()));
        } else {
            sortedArray.sort((obj1, obj2) -> obj1.getDateCreated().compareTo(obj2.getDateCreated()));
        }

        return sortedArray; 
    } // end articleSorter
} // End Program