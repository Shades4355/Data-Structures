// File name: Pairs.java
// Written by: Shades Meyers
// Description: A Pairs class for storing key-value pairs
// Challenges:  Implementing compareTo() for generic E
// Time Spent: 48 minutes 
//
// Revision history:
// Date: By: Action:
// -------------------------------
// 2024-July-24 SM File created


public class Pairs<E, T> implements Comparable<E> {
    // Variables
    private E key;
    private T value;

    // Constructor
    Pairs(E key, T value) {
        this.key = key;
        this.value = value;
    }

    // Accessors and Mutators
    // Key
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
    public int compareTo(Pairs<E, T> element2) {
        return this.compareTo(element2.getKey());
    }
    public int compareTo(E key2) {
        E key1 = this.getKey();

        if (key2 == null) {
            throw new NullPointerException();
        } else if (key1 > key2) {
            return 1;
        } else if (key1 == key2) {
            return 0;
        } else {
            return -1;
        }
    }
} // end Pairs