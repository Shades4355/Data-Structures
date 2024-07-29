// File name:   Enemy.java
// Written by:  Shades Meyers
// Description: A template for Enemies
// Challenges:  
// Time Spent:  32 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-27 SM      File created
// 2024-July-28 SM      Started fleshing out class
//                      Moved most methods to a parent class
//                          (Entity.java)


public class Enemy extends Entity{
    // Variables (see Entity)
    private int grantXP;

    // Constructors
    Enemy(String name, int level, int hitDice, int damageDie, int armor, int str, int dex, int con, int grantXP) {
        super(name, level, hitDice, damageDie, armor, str, dex, con);
        this.grantXP = grantXP;
    }

    // Methods
    public int grantXP() { return this.grantXP; }
    
} // End class
