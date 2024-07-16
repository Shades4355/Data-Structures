
public class Driver {
    public static void main(String[] args) {
        SLLNode node1 = new SLLNode("First");
        SLLNode node2 = new SLLNode("Second");
        SLLNode node3 = new SLLNode("Third");

        SLL sll = new SLL(node1);
        sll.insert(node2);
        sll.insert(node3);

        SLLNode node = sll.first();
        while (node != null) {
            System.out.println(node);
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());

        System.out.println();

        System.out.println("Last entry: " + sll.last());

        System.out.println();

        System.out.println("Add \"Zero\" to list");
        sll.insertFirst(new SLLNode("Zero"));
        
        node = sll.first();
        while (node != null) {
            System.out.println(node);
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());

        System.out.println();
        
        System.out.println("removing first");
        try {
            sll.removeFirst();
        } catch (Exception e) {
            System.out.println(e);
        }
 

        node = sll.first();
        while (node != null) {
            System.out.println(node);
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());

        System.out.println();

        System.out.println("removing last");
        try {
            sll.removeLast();
        }catch(Exception e) {
        System.out.println(e);
        }
        
        node = sll.first();
        while (node != null) {
            System.out.println(node);
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());
    }
}
