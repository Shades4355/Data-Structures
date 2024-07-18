// File name:   Test.java
// Written by:  Shades Meyers
// Description: A tester file intended to test functionality
//                  of Deque.java.
// Challenges:  None
// Time Spent:  25 min
//
// Revision history:
// Date:        By:     Action:
// -------------------------------
// 2024-July-17 SM      File created
// 2024-July-18 SM      Added contents to main()


public class Test {
    // Test Deque.java
    public static void main(String[] args) {
        
        System.out.println("\nString List:");
        Deque<String> strList = new Deque<String>(); // create an empty list
        System.out.println("Is Empty: " + strList.isEmpty()); // true
        strList.addFirst("First node"); // ["First node"]
        strList.addLast("Middle node"); // ["First node", "Middle node"]
        strList.addLast("Last node"); // ["First node", "Middle node", "Last node"]
        System.out.println("Elements added");
        System.out.println("Is Empty: " + strList.isEmpty()); // false
        System.out.println("First Element: " + strList.first()); // "First node"
        System.out.println("Last Element: " + strList.last()); // "Last node"
        System.out.println("Size: " + strList.size()); // 3
        System.out.println("First Element (Removed): "
                                + strList.removeFirst()); // "First node"
        System.out.println("Last Element (Removed): " + strList.removeLast()); // "Last node"
        System.out.println("New First Element: " + strList.first()); // "Middle node"
        System.out.println("New First Element (Removed): " 
                                + strList.removeFirst()); // "Middle node"
        System.out.println("Size: " + strList.size()); // 0
        System.out.println("Last Element (Removed): "
                                + strList.removeLast()); // "null"
        System.out.println("First Element (Removed): "
                                + strList.removeFirst()); // "null"


        System.out.println("\nInteger List:");
        Deque<Integer> intList = new Deque<Integer>(5); // create list with an initial value
        System.out.println("Is Empty: " + intList.isEmpty()); // false
        System.out.println("Size: " + intList.size()); // 1
        intList.addLast(10); // [5, 10]
        intList.addFirst(-1); // [-1, 5, 10]
        intList.addLast(9); // [-1, 5, 10, 9]
        System.out.println("Elements added");
        System.out.println("Size: " + intList.size()); // 4
        System.out.println("First Element (Removed): " + intList.removeFirst()); // -1
        System.out.println("Last Element (Removed): " + intList.removeLast()); // 9
        System.out.println( "New First Element: " + intList.first()); // 5
        System.out.println("New Last Element: " + intList.last()); // 10
        System.out.println("Size: " + intList.size()); // 2

        System.out.println();

    }
}
