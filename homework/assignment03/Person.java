
// Program Description:  The program will implement an Index-based List with a Node based Singly Linked List.
// File name: Person.java 
// File description: This is a simple class to be used with one of the testers.
// Files in this program: This class is used by various projects.
// Revision History:
// Date:                   By:               Action:
// ---------------------------------------------------
// 03/15/2012       (mb)                Created
public class Person {
    static int IDgenerator = 1;
    private String name;
    private int ID;
    public Person (String sName) {        
        setName(sName); ID=IDgenerator++;}
    public int getID(){return ID;}
    public String getName(){ return name;} 
    public void setName(String sName) {name = sName;}
    public String toString (){ return  "("+name + "-" + ID +")";}
    // two Person objects are considered equal if their ID's are equal
    public Boolean equals(Person p){ return ID==p.getID(); }
}
