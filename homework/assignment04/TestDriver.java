// File name: TestDriver.java
// Written by: Shades Meyers
// Description: A Driver file for testing Tree.java
// Challenges:
// Time Spent: 15 minutes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-25 SM      File created


public class TestDriver {
    public static void main(String[] args) {
        Tree<String, Integer> inventory = new Tree<String, Integer>();

        inventory.add(new Pairs<String, Integer>("Healing Potions", 3));

        System.out.println(inventory);
        // expected outcome:
        // {{Healing Potions: 3}}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        inventory.add(new Pairs<String,Integer>("Arrows", 10));
        System.out.println(inventory);
        // expected outcome:
        // {{Arrows: 10}, {Healing Potions: 3}}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        inventory.add("Bombs", 4);
        System.out.println(inventory);
        // expected outcome:
        // {{Arrows: 10}, {Bombs: 4}, {Healing Potions: 3}}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        inventory.add("Arrows", 15);
        System.out.println(inventory);
        // expected outcome:
        // {{Arrows: 15}, {Bombs: 4}, {Healing Potions: 3}}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        System.out.println(); // line break
        inventory.forEach((n) -> System.out.println(n));
        System.out.println(); // line break
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
        Node<String, Integer> arrows = inventory.search("Arrows");
        inventory.add("Arrows", arrows.getValue() - 1);
        System.out.println(inventory);
        // expected outcome:
        // {{Arrows: 14}, {Bombs: 4}, {Healing Potions: 3}}
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//        
        Node<String, Integer> healPots = inventory.search("Healing Potions");
        inventory.add("Healing Potions", healPots.getValue() - 3);
        System.out.println(inventory);
        // expected outcome:
        // {{Arrows: 14}, {Bombs: 4}}

    } // end main method
} // end program