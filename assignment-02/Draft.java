// File name:   Draft.java
// Written by:  Shades Meyers
// Description: child of Articles 
// Challenges:  
// Time Spent:  2 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Added constructor


public class Draft extends Article {
    // Constructors
    Draft(String fileType, String title, String author, 
            String dateCreated, String lastChanged) throws invalidArticle {
        super(fileType, title, author, dateCreated, lastChanged);
        setState("Draft");
    }

} // End Program