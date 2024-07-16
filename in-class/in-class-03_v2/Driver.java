
public class Driver {
    public static void main(String[] args) {
        SLLNode<String> node1 = new SLLNode<String>("First");
        SLLNode<String> node2 = new SLLNode<String>("Second");
        SLLNode<String> node3 = new SLLNode<String>("Third");

        SLL<String> sll = new SLL<String>(node1);
        sll.insert(node2);
        sll.insert(node3);

        SLLNode<String> node = sll.first();
        while (node != null) {
            System.out.println(node.getElement());
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());

        System.out.println();

        System.out.println("Last entry: " + sll.last().getElement());

        System.out.println();

        System.out.println("Add \"Zero\" to list");
        sll.insertFirst(new SLLNode<String>("Zero"));
        
        node = sll.first();
        while (node != null) {
            System.out.println(node.getElement());
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
            System.out.println(node.getElement());
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
            System.out.println(node.getElement());
            node = node.getNextNode();
        }
        System.out.println("Size: " + sll.size());
    }
}
