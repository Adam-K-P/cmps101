class Queue {
         private class Node {
                 int data;
                 Node next;
                 Node(int data) {
                         data = data;
                         next = null;
                 }
                 public String toString() {
                         out.printf("%d\n", data);
                 }
         }

         //Fields
         private Node front;
         private Node back;
         private int length;

         // Constructors
         Queue() {
                 front = null;
                 back = null;
                 length = 0;
         }
         // Access functions
         int getFront() { return front.data; }
         int getLength() { return length; }
         boolean isEmpty() { return length == 0; }

         // Manipulation procedures
         void Enqueue(int data) {
                 Node insert = new Node();
                 insert.data = data;
                 back.next = insert;
         }
         void Dequeue() { front = front.next; }
         // other methods
         // toString: Overrides Object's toString method.
         public String toString() {
                 for(Node print = front; print != null; print = print.next) 
                         print.toString();
         }
}
