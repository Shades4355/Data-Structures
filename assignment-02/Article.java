// File name:   Article.java
// Written by:  Shades Meyers
// Description: Parent class for Investigation, Draft, and SendToPress
// Challenges:  
// Time Spent:  1 h 8 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-08 SM      Created file
// 2024-July-09 SM      Started fleshing out class


import java.util.Arrays;

public class Article {
    // Variables
    protected String state, fileType, title, author, lastChanged, dateCreated;

    // Constructors
    Article(String fileType, String title, String author, 
            String dateCreated, String lastChanged) throws InvalidArticle {
        setFileType(fileType);
        setTitle(title);
        setAuthor(author);
        setLastChanged(lastChanged);
        this.dateCreated = dateCreated;
    }

    // Accessors & Mutators
    // state
    public String getState() { return this.state; }
    public void setState(String state) throws InvalidArticle {
        if (Arrays.asList("Investigation", "Draft", "Send to Press").contains(state)) {
            this.state = state;
        } else {
            throw new InvalidArticle("Invalid state");
        }
    }
    // fileType
    public String getFileType() { return fileType; }
    public void setFileType(String fileType) throws InvalidArticle {
        // check if 
        if (Arrays.asList("PDF", "DOC", "DOCX", "GDOC").contains(fileType)) {
            this.fileType = fileType;
        } else {
            throw new InvalidArticle("Invalid file type");
        }
        
    }
    // Title
    public String getTitle() { return this.title; }
    public void setTitle(String title) throws InvalidArticle {
        if (title.length() <= 0) {
            throw new InvalidArticle("Invalid title");
        } else {
            this.title = title;
        }
    }
    // Author
    public String getAuthor() { return this.author; }
    public void setAuthor(String author) throws InvalidArticle {
        if (author.length() > 0) {
            this.author = author;
        } else {
            throw new InvalidArticle("Invalid author");
        }
    }
    // lastChanged
    public String getLastChanged() { return this.lastChanged; }
    public void setLastChanged(String newDate) {
        this.lastChanged = newDate;
    }
    // dateCreated
    public String getDateCreated() { return this.dateCreated;}


} // End Program