import java.lang.reflect.InvocationTargetException;

public class Driver {
    public static void main(String[] args) {
        DLL<String> list1 = new DLL<String>();
        DLL<String> list2 = new DLL<String>(new DLLNode<String>("node 1"));

        DLLNode<String> node2 = new DLLNode<String>("node 2");
        DLLNode<String> node3 = new DLLNode<String>("node 3");
        DLLNode<String> node4 = new DLLNode<String>("node 4");
        DLLNode<String> node5 = new DLLNode<String>("node 5");

        // list 1
        System.out.println("List 1 is empty: " + list1.isEmpty());

        list1.addFirst(node2);
        list1.add(node3);
        System.out.println("2 Nodes added");
        
        System.out.println("List 1 is empty: " + list1.isEmpty());
        System.out.println("Fist element is: " + list1.first());
        System.out.println("Last element is: " + list1.last());
        System.out.println("List size: " + list1.size());

        System.out.println();

        // list 2
        System.out.println("List 2 is empty: " + list2.isEmpty());

        list2.addFirst(node4);
        list2.addLast(node5);
        System.out.println("2 Nodes added");

        System.out.println("List 1 is empty: " + list2.isEmpty());
        System.out.println("Fist element is: " + list2.first());
        System.out.println("Second element is: " + list2.get(1));
        System.out.println("Last element is: " + list2.last());
        System.out.println("List size: " + list2.size());


        // forEach
        System.out.println("\nforEach test:");
        list2.forEach((n) -> System.out.println(n));    

        // error: Can only iterate over an array or an instance of java.lang.Iterable
        // for (DLLNode<String> node : list2) {
        //     System.out.println(node);
        // }

        // my first attempt at iteration
        // System.out.println("\niterator2:");
        // DLLNode<String>[] iterable = list2.iterator2();
        // for (DLLNode<String> node : iterable) {
        // System.out.println(node.getElement());
        // }

        // written with SI's help (90% SI; 10% me)
        System.out.println("\nIterable test:");
        Object iterableList = list2.iterator();
        try {
            while ((boolean) iterableList.getClass().getMethod("hasNext").invoke(iterableList)) {
                String string = (String) iterableList.getClass().getMethod("next").invoke(iterableList);
                System.out.println(string);
            }
        } catch (NoSuchMethodException e) {
            System.err.println(e);
        } catch (IllegalAccessException j) {
            System.err.println(j);
        } catch (InvocationTargetException k) {
            System.err.println(k);
        }
    } // end main
} // end program
