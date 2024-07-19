package homework.assignment03;

// Program Description:  The program will implement an Index-based List with a Node based Singly Linked List.
// File name: ListTester2.java (List client)
// File description: This is a tester for an Index-based List Class implemented using generics.
// Files in this program:  
//      To be written by student: DLL.java, AList.java
//      Provided by professor:    DLLADT.java, DLLNode.java
//                                AListTesterPerson.java, AListTesterInteger.java, Person.java
//      Note: DO NOT MODIFY the provided files 
// Revision History:
// Date:                   By:               Action:
// ---------------------------------------------------
// 3/20/18               mb          created

public class AListTesterPerson
{
	public static void main (String[] args){
    	AList<Person> L = new AList<>();
        int i, j;
    	Person a, b, c, d, e, f, g, h, k, l, m, p;
        a = new Person ("Amy");
        b = new Person ("Bob"); 
        c = new Person ("Cal");
        d = new Person ("Dot");
        e = new Person ("Eli");
        f = new Person ("Fay");
        g = new Person ("Gil");
        h = new Person ("Hop");
        k = new Person ("Kim");     
        l = new Person ("Lee");
        m = new Person ("Mak");       
     try{
        System.out.println ("Testing add(element):");
        System.out.println (L); // this one is EMPTY
        L.add(a); System.out.print("Item added: " + a + ". "); System.out.println (L);
        L.add(b); System.out.print("Item added: " + b + ". "); System.out.println (L);
        L.add(c); System.out.print("Item added: " + c + ". "); System.out.println (L);
        L.add(d); System.out.print("Item added: " + d + ". "); System.out.println (L);

        System.out.println ("\nTesting add(index, element):");
        L.add( 2, e); System.out.print("Item inserted at index 2: "+ e + ". ");  System.out.println (L);
        L.add( 4, f); System.out.print("Item inserted at index 4: "+ f + ". ");  System.out.println (L);
 
        System.out.println ("\nTesting get(index):");
        System.out.println("There are " + L.size() + " items in the list: ");	
        for ( i=0; i<L.size(); i++)
            System.out.print("Item("+i+"): " + L.get(i)+". ");
        System.out.println();
        
        L.add(g); 
        L.add(h);
        L.add(k);
        L.add(l);
        System.out.println("Items added: Gil,Hop,Kim,Lee.");  System.out.println (L);
        System.out.println("There are " + L.size() + " items in the list,");
                
        System.out.println ("\nTesting set(index, element):");
        L.set( 4, g); System.out.print("Set Item(4) to "+g+".");  System.out.println (L);         
        L.set( 8, h); System.out.print("Set Item(8) to "+h+".");  System.out.println (L);     
        //If uncommented, this should force an index out of bounds exception:
        //L.set( 20, m);
        
        System.out.println ("\nTesting contains(element):");
        if (L.contains(d)) System.out.println("The AList contains "+ d + ". ");  
        else System.out.println("The AList does not contain "+ d + ". ");
 
        if (L.contains(m)) System.out.println("The AList contains "+ m + ". ");  
        else System.out.println("The AList does not contain "+ m + ". "); 

        System.out.println ("\nTesting remove(index):");
        System.out.print("Removed Item(3): " + L.remove(3) +". ");System.out.println (L);
        System.out.print("Removed Item(5): " + L.remove(5) +". ");System.out.println (L);
        
        //If uncommented, this should force an index out of bounds exception:
        //System.out.print("Removed Item(15): " + L.remove(15) +". ");System.out.println (L);      
        System.out.println("There are " + L.size() + " items in the list. ");
        
        System.out.println ("\nTesting remove(element):");
        if (L.remove(a)) {System.out.print("Removed item containing " + a +". ");System.out.println (L);}
        else System.out.println("Cannot remove " + a +". Not in AList.");
        
        if (L.remove(m)) System.out.println("Removed item containing " + m +". ");
        else System.out.println("Cannot remove " + m +". Not in AList.");
  
        System.out.println ("\nTesting indexOf(element):");
        i = L.indexOf(g);
        if (i>=0) System.out.println("The first occurrence of " +g+" is at index:  " + i);  // uses comparison!  use .equals!
        else System.out.println("The AList does not contain " + g);

        i = L.indexOf(m);
        if (i>=0) System.out.println("The first occurrence of " +m+" is at index:  " + i);  // uses comparison!  use .equals!
        else System.out.println("The AList does not contain "+ m + ". ");
        System.out.println();
      
        j=L.size();
        for ( i=0; i<j; i++)
        {   System.out.print("Removed Item(0): " + L.remove(0) +" "); System.out.println (L);
        }
        System.out.println("Is the list empty?  " + L.isEmpty());      
        
// The following should force an exception if uncommented:
//        System.out.println("The next remove(0) should force an exception: ");
//        System.out.print("Removed item(0): " + L.remove(0) + ". ");System.out.println (L);
    }
    catch (Exception x)
       {System.out.println ("Error: "+ x.getMessage());}
   }
}