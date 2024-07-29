// File name:   Entity.java
// Written by:  Shades Meyers
// Description: A parent class for Heros and Enemies
// Challenges:  
// Time Spent:  20 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-28 SM      File created
//                      Inspiration taken from my previous work
//                          https://github.com/Shades4355/D20_Combat


public class Entity {
    // Variables
    protected String name;
    protected int level, hitPoints, maxHP, armor, str, dex, con;
    protected Dice hitDice, damageDie, d20;
    protected boolean alive;

    // Constructors
    Entity(String name, int level, int hitDice, int damageDie, int armor, int str, int dex, int con) {
        this.alive = true;
        this.name = name;
        this.level = level;
        this.armor = armor;
        this.hitDice = new Dice(hitDice);
        this.str = str;
        this.dex = dex;
        this.con = con;
        this.damageDie = new Dice(damageDie);
        this.maxHP = this.calcMaxHP();
        this.hitPoints = getMaxHP();
        d20 = new Dice(20);
    }

    // Methods
    // Accessors & Mutators
        // Alive
    public boolean isAlive() { return this. alive; }
        // Name
    public String getName() { return this.name; }
        // AC
    public int getAC() { return 10 + this.armor + this.dex; }
        // Hit Points
    public int getHP() {
        if (this.hitPoints < 0) {
            setHP(0);
        }
        return this.hitPoints;
    }
    private void setHP(int newHP) { this.hitPoints = newHP; }
    private int getMaxHP() { return maxHP; }
    private int calcMaxHP() {
        return (this.level * this.con)
                + hitDice.roll(this.level);
    }
        // Stats
    public int getStr() { return this.str; }
    public void setStr(int newStr) { this.str = newStr; }
    public int getDex() { return this.dex; }
    public void setDex(int newDex) { this.dex = newDex; }
    public int getCon() { return this.con; }
    public void setCon(int newCon) { this.con = newCon; }
    
    // Combat
        // Attacking
    public void attack(Entity target) {
        int attackRoll = d20.roll(1) + this.getStr();

        if (attackRoll >= target.getAC()) {
            int damage = this.damageDie.roll(1);
            target.takeDamage(damage);
        } else {
            System.out.println(this.name + " missed " + target.getName());
        }
    }
        // Taking Damage
    public void takeDamage(int damage) {
        this.hitPoints -= damage;

        if (this.hitPoints <= 0) {
            this.setHP(0);
            this.alive = false;
        } else {
            System.out.println(this.name + " took " + damage + " damage, and has " + this.getHP() + " hit points left.");
        }
    }

} // End Class
