// File name:   Pairs.java
// Written by:  Shades Meyers
// Description: A Pairs class for storing key-value pairs
// Challenges:  Implementing compareTo() for generic E
// Time Spent:  2 h 18 minutes + ( - )
//
// Revision history:
// Date:         By:    Action:
// -------------------------------
// 2024-July-24 SM      File created
// 2024-July-26 SM      Worked on compareTo with SI
// 2024-Aug-01  SM      Started converting to Skip List Node


public class Pairs<E extends Comparable<E>, T> implements Comparable<E> {
    // Variables
    private E key;
    private T value;

    // Constructor
    Pairs(E key, T value) {
        this.key = key;
        this.value = value;
    }

    // Accessors and Mutators
        // TODO: figure out how to return a Double from getKey()
    // Key
        // TODO: return Object?
    public E getKey() {
        return this.key;
    }
    public void setKey(E key) {
        this.key = key;
    }

    // Value
    public T getValue() {
        return this.value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    // Overrides
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("{" + this.key + ": " + this.value + "}");

        return new String(string);
    }
    public boolean equals(Pairs<E, T> val2) {
        return this.getKey() == val2.getKey() && this.getValue() == val2.getValue();
    }
    public int compareTo(Pairs<E, T> element) {
        return this.compareTo(element.getKey());
    }
    public int compareTo(E key2) {
        E key1 = this.getKey();

        return key1.compareTo(key2);
    }
} // end Pairs