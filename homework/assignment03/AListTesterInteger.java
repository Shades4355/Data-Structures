
// Program Description:  The program will implement an index-based List with a Node based Singly Linked List.
// File name: ListTester1.java (List client)
// File description: This is a tester for an index-based List Class implemented using generics.
// Files in this program:  
//      To be written by student: DLL.java, AList.java
//      Provided by professor:    DLLADT.java, DLLNode.java
//                                AListTesterPerson.java, AListTesterInteger.java, Person.java
//      Note: DO NOT MODIFY the provided files 
// Revision History:
// Date:                   By:               Action:
// ---------------------------------------------------
// 3/20/18              mb          created

public class AListTesterInteger
{
    public static void main (String[] args){
    int i,j;
    AList<Integer> L = new AList<>();
    Integer n;
    try {
        System.out.println ("Testing add(element):");
        System.out.println (L); // this one is EMPTY
        n = 11; L.add(n); 
        System.out.print("Item added: " + n + ". "); System.out.println (L);
        n = 33; L.add(n);
        System.out.print("Item added: " + n + ". "); System.out.println (L);
        n = 55; L.add( n);
        System.out.print("Item added: "+ n + ". ");  System.out.println (L);
        n = 77; L.add( n);
        System.out.print("Item added: "+ n + ". ");  System.out.println (L);
        
        System.out.println ("\nTesting add(index, element):");
        n = 200; L.add( 2, n);
        System.out.print("Item inserted at index 2: "+ n + ". ");  System.out.println (L);
        n = 400; L.add( 4, n);
        System.out.print("Item inserted at index 4: "+ n + ". ");  System.out.println (L);

        System.out.println ("\nTesting get(index):");
        System.out.println("There are " + L.size() + " items in the list: ");	
        for ( i=0; i<L.size(); i++)
            System.out.print("Item("+i+"): " + L.get(i)+". ");
        System.out.println();
        
        L.add( 22); //note: here, int 22 is being converted to Integer 22 here
        L.add( 44);
        L.add( 66);
        L.add( 88);
        System.out.println("Items added: 22, 44, 66, 88.");  System.out.println (L);
        System.out.println("There are " + L.size() + " items in the list,");
                
        System.out.println ("\nTesting set(index, element):");
        L.set( 4, 22);
        System.out.print("Set Item(4) to 22. ");  System.out.println (L);         
        L.set( 8, 44);
        System.out.print("Set Item(8) to 44. ");  System.out.println (L);     
        //If uncommented, this should force an index out of bounds exception:
        //L.set( 20, 55);
        
        System.out.println ("\nTesting contains(element):");
        n = 77; 
        if (L.contains(n)) System.out.println("The AList contains "+ n + ". ");  
        else System.out.println("The AList does not contain "+ n + ". ");
 
        n = 123; 
        if (L.contains(n)) System.out.println("The AList contains "+ n + ". ");  
        else System.out.println("The AList does not contain "+ n + ". "); 

        System.out.println ("\nTesting remove(index):");
        System.out.print("Removed Item(3): " + L.remove(3) +". ");System.out.println (L);
        System.out.print("Removed Item(5): " + L.remove(5) +". ");System.out.println (L);
        //If uncommented, this should force an index out of bounds exception:
        // System.out.print("Removed Item(15): " + L.remove(15) +". ");System.out.println (L);      
        System.out.println("There are " + L.size() + " items in the list. ");
        
        System.out.println ("\nTesting remove(element):");
        n = 11; //Note: this will remove the element containing element n
        if (L.remove(n)) {System.out.print("Removed item containing " + n +". ");System.out.println (L);}
        else System.out.println("Cannot remove " + n +". Not in AList.");
        
        n = 99; 
        if (L.remove(n)) System.out.println("Removed item containing " + n +". ");
        else System.out.println("Cannot remove " + n +". Not in AList.");
  
        System.out.println ("\nTesting indexOf(element):");
        n = 44; i = L.indexOf(n);
        if (i>=0) System.out.println("The first occurrence of " +n+" is at index:  " + i);  // uses comparison!  use .equals!
        else System.out.println("The AList does not contain " + n);

        n = 10; i = L.indexOf(n);
        if (i>=0) System.out.println("The first occurrence of " +n+" is at index:  " + i);  // uses comparison!  use .equals!
        else System.out.println("The AList does not contain "+ n + ". ");
        System.out.println();

        for ( i=0; i<L.size(); i++)
            L.set(i,i); //first parameter is index, second parameter is element (coerced)
        System.out.println("Reset AList elements to 0-6."); System.out.println (L);      
        System.out.println("Is the list empty?  " + L.isEmpty());
        System.out.println();
        
        j=L.size();
        for ( i=0; i<j; i++)
        {   System.out.print("Removed Item(0): " + L.remove(0) +" "); System.out.println (L);
        }
        System.out.println("Is the list empty?  " + L.isEmpty());      
        
// If uncommented, this should force an exception:
//        System.out.println("The next remove(0) should force an exception: ");
//        System.out.print("Removed item(0): " + L.remove(0) + ". ");System.out.println (L);
    }
    catch (Exception e)
       {System.out.println ("Error: "+ e.getMessage());}
   }
}