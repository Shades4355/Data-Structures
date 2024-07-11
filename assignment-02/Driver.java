// File name:   Driver.java
// Written by:  Shades Meyers
// Description: Client-side program for organizing News Articles 
// Challenges:  Sorting a list of Objects (was using "==" instead of ".equals()").
//              Reading in from a File.
//              First pass of code wasn't OOP enough - large and unwieldy.
//              Getting Scanner to work properly
// Time Spent:  9 h 23 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Added constructor
//                      Started coding
// 2024-July-10 SM      Con't coding


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // variables
        Scanner input = new Scanner(System.in);
        String answer, sortOption;
        Article article;
        ArrayList<Article> articleList = new ArrayList<>();
        
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
                    System.out.println("\nWould you like to manually enter an Article, or import from a folder? (manually/import)");
                    System.out.print(">> ");

                    answer = input.nextLine();

                    if (answer.toLowerCase().equals("manually")) {
                        // create Article based on user inputs
                        article = createArticle(input);
                        // add Article to list
                        articleList.add(article);
                        break;
                    } else if (answer.toLowerCase().equals("import")) {
                        // import folder containing Articles
                        final File folder = new File("./Articles");

                        if (!folder.exists()) {
                            System.out.println("Folder does not exist");
                            System.exit(1);
                        } else if (folder.exists() && !folder.isDirectory()) {
                            System.out.println("File location not a folder");
                            System.exit(1);
                        } 

                        // for each file in folder, create 1 Article and add to list
                        for (File file : folder.listFiles()) {
                            if (file.exists() && file.canRead() && !file.isDirectory()) {
                                // create array to store parsed lines from file
                                String[] elements = new String[6];
                                
                                // parse file into elements array
                                Path filePath = Paths.get(file.getAbsolutePath());

                                ArrayList<String> fileReader;
                                try {
                                    fileReader = new
                                    ArrayList<String>(Files.readAllLines(filePath));
                                } catch (IOException e) {
                                    System.out.println(e);
                                    fileReader = null;
                                }

                                int i = 0;
                                for (String line : fileReader) {
                                    elements[i] = line;
                                    i++;
                                }

                                // try to create an Article from above variables
                                try {
                                    article = new Article(elements);

                                    articleList.add(article);
                                } catch (InvalidArticle e) {
                                    System.out.println("File " + file.getName() + " could not be converted automatically; please enter manually.");
                                    System.out.println(e);
                                    continue;
                                }
                            } else {
                                if (file.exists() && !file.canRead())
                                System.out.println("File " + file.getName() + " could not be read; please enter manually.");
                            }
                        } // end For loop
                        System.out.println("\nFiles imported");
                        break;
                    } // loop to re-prompt
                } // end Create Article

                // return to top to prompt for another Article entry
            } // end Add Article to List

            // verify article(s) have been added to list
            if (articleList.size() <= 0) {
                System.out.println("Article List is empty\n");

                // prompt to restart program
                System.out.println("Would you like to start again? (y/n)");
                System.out.print(">> ");

                answer = input.nextLine();

                // if answer isn't "y", close program
                if (!answer.toLowerCase().equals("y")) {
                    System.out.println("\nGoodbye!");
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
            articleSorter(sortOption, articleList);
            
            // print ArrayList to screen
            showArticles(articleList);
            
            // prompt to change an Article's state
            while (true) {
                System.out.println("\nWould you like to change the state of an Article? (y/n)");
                System.out.print(">> ");

                answer = input.nextLine(); // TODO: crashing here on second loop

                if (!answer.toLowerCase().equals("y")) {
                    break;
                }

                // validates input is an integer
                while (true) {
                    int answerInt;
                    System.out.println("\nPlease choose an article (1 - " + articleList.size() + "; or -1 to exit)");
                    
                    System.out.print(">> ");
                    while (true) {
                        try {
                            answerInt = input.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter an integer between 1 and " + articleList.size() + " or -1");
                            continue;
                        }
                    }

                    if (answerInt == -1) {
                        input.nextLine(); // clear cache
                        break;
                    }
                    changeArticleState(articleList, answerInt - 1, input);
                }
                break;
            }

            // remove an Article from list
            while (true) {
                System.out.println("\nWould you like to delete an Article from the display list? (y/n)");
                System.out.print(">> ");

                answer = input.nextLine();

                if (answer == null) {
                    continue;
                } else if (!answer.toLowerCase().equals("y")) {
                    break;
                }

                showArticles(articleList);
                while (true) {
                    System.out.println("\nPlease choose an article (1 - " + articleList.size() + "; or -1 to exit)");

                    // validates input is an integer
                    int answerInt = -2;
                    while (true) {
                        answerInt = -2;
                        try {
                            answerInt = input.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("\nPlease enter an integer between 1 and " + articleList.size());
                            input.nextLine();
                            continue;
                        }
                    }

                    if (answerInt == -1) {
                        break;
                    } else if (answerInt >= 1 && answerInt <= articleList.size()) {
                        removeArticle(articleList, answerInt - 1);
                    }
                }
                input.nextLine();
                break;
            } // end Article removal
            
            // prompt to restart program
            System.out.println("\nWould you like to start again? (y/n)");
            System.out.print(">> ");

            answer = input.nextLine();

            // if answer isn't "y", close program
            if (!answer.toLowerCase().equals("y")) {
                System.out.println("\nGoodbye!");
                input.close();
                System.exit(0);
            }

        } // end core While loop
    } // end main

    // test is a given String is in the given Array
    public static boolean testArrayContents(String[] array, String searchFor) {
        for (String element : array) {
            if (element.toLowerCase().equals(searchFor.toLowerCase())) {
                return true;
            }
        }
        return false;
    } // end testArrayContents

    // sort given array based on a given criteria
    public static void articleSorter(String sortOption, ArrayList<Article> arr) {
        // sort Array
        // sort options: state, file type, title, author, last changed, and date created
        if (sortOption.toLowerCase().equals("state")) {
            arr.sort((obj1, obj2) -> obj1.getState().compareTo(obj2.getState()));
        } else if (sortOption.toLowerCase().equals("file type")) {
            arr.sort((obj1, obj2) -> obj1.getFileType().compareTo(obj2.getFileType()));
        } else if (sortOption.toLowerCase().equals("title")) {
            arr.sort((obj1, obj2) -> obj1.getTitle().compareTo(obj2.getTitle()));
        } else if (sortOption.toLowerCase().equals("author")) {
            arr.sort((obj1, obj2) -> obj1.getAuthor().compareTo(obj2.getAuthor()));
        } else if (sortOption.toLowerCase().equals("last changed")) {
            arr.sort((obj1, obj2) -> obj1.getLastChanged().compareTo(obj2.getLastChanged()));
        } else {
            arr.sort((obj1, obj2) -> obj1.getDateCreated().compareTo(obj2.getDateCreated()));
        } 
    } // end articleSorter

    // prompts user to enter values for each of an Article's fields
    // then attempts to create an Article
    public static Article createArticle(Scanner input) {
        // variables
        String state, fileType, title, author, lastChanged, dateCreated;
        String[] acceptedFileTypes = { "PDF", "DOC", "DOCX", "GDOC" };
        
        while (true) {
            System.out.println("\nWhat type of Article do you wish to add? (Investigation, Draft, Send to Press)");
            System.out.print(">> ");

            state = input.nextLine();

            // If article type is invalid, re-prompt for Article type
            if (!Arrays.asList("investigation", "draft", "send to press").contains(state.toLowerCase())) {
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
            try {
                return new Article(state, fileType, title, author, dateCreated, lastChanged);
            } catch (InvalidArticle e) {
                System.err.println(e);
                continue;
            }
        }
    } // end createArticle
    
    // Display ArrayList
    public static void showArticles( ArrayList<Article> articleList) {
        for (int i = 0; i < articleList.size(); i++) {
            System.out.printf("%d.) ", i + 1);
            System.out.println(articleList.get(i).getTitle() + " (" + articleList.get(i).getState() + ")");
            System.out.println("    By: " + articleList.get(i).getAuthor());
            System.out.println("    Date Created: " + articleList.get(i).getDateCreated());
            System.out.println("    Last Changed: " + articleList.get(i).getLastChanged());
            System.out.println("    File Type: " + articleList.get(i).getFileType());
        }
    }

    // change an Article's state
    public static void changeArticleState(ArrayList<Article> articleList, int index, Scanner input) {
        // variables
        String state;

        // prompt for new State
        while (true) {
            System.out.println("\nWhat state do you wish to change the Article to? (Investigation, Draft, Send to Press)");
            System.out.print(">> ");

            state = input.nextLine();

            // If article type is invalid, re-prompt for Article type
            if (!Arrays.asList("investigation", "draft", "send to press").contains(state.toLowerCase())) {
                System.out.println("I'm sorry, that Article type is not recognized");
                continue;
            }
            break;
        }

        try{
            articleList.get(index).setState(state);
        } catch (InvalidArticle e) {
            System.out.println("State could not be updated");
            System.err.println(e);
        }

        System.out.println();

        // print Article to show change
        System.out.println(articleList.get(
                index).getTitle() + " (" + articleList.get(index).getState() + ")");
        System.out.println("By: " + articleList.get(index).getAuthor());
        System.out.println("Date Created: " + articleList.get(index).getDateCreated());
        System.out.println("Last Changed: " + articleList.get(index).getLastChanged());
        System.out.println("File Type: " + articleList.get(index).getFileType());

    } // end changeArticleState

    // remove an Article from ArrayList
    public static void removeArticle( ArrayList<Article> arr, int toBeRemoved) {
        arr.remove(toBeRemoved);

        // show Article list to verify removal
        showArticles(arr);
    }
    
} // End Program