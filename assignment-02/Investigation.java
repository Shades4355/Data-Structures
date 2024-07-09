// File name:   Investigation.java
// Written by:  Shades Meyers
// Description: child of Articles 
// Challenges:  
// Time Spent:  3 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Added constructor


public class Investigation extends Article {
    // Constructors
    Investigation(String fileType, String title, String author, 
            String dateCreated, String lastChanged) throws InvalidArticle {
        super(fileType, title, author, dateCreated, lastChanged);
        setState("Investigation");
    }
    
} // End Program
