// File name:   Driver.java
// Written by:  Shades Meyers
// Description: A Tester for DLL_v2 and DLLNode classes
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-16 SM      Updated


public class Driver {
    public static void main(String[] args) {
        DLL_v2<String> list1 = new DLL_v2<String>();
        DLL_v2<String> list2 = new DLL_v2<String>(new DLLNode<String>("original node"));

        // list 1
        System.out.println("List 1 is empty: " + list1.isEmpty());

        list1.addFirst("node 1");
        list1.add("node 2");
        System.out.println("2 Nodes added");
        
        System.out.println("\nList 1 is empty: " + list1.isEmpty());
        System.out.println("Fist element is: " + list1.first());
        System.out.println("Last element is: " + list1.last());
        System.out.println("List size: " + list1.size());

        System.out.println();

        // list 2
        System.out.println("List 2 is empty: " + list2.isEmpty());

        list2.addFirst("first node");
        list2.addLast("last node");
        System.out.println("2 Nodes added");

        System.out.println("\nList 2 is empty: " + list2.isEmpty());
        System.out.println("Fist element is: " + list2.first());
        System.out.println("Second element is: " + list2.get(1));
        System.out.println("Last element is: " + list2.last());
        System.out.println("List size: " + list2.size());


        // forEach
        System.out.println("\nforEach test:");
        list2.forEach((n) -> System.out.println("\t" + n));    
    } // end main
} // end program
