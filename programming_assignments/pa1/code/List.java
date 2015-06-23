import static java.lang.System.out;
import static java.lang.System.err;

class List {
   private class Node {
      String line;
      Node next = null;
      Node (String line) {
      }
   }

   //********** Fields **********
   Node front  = null;
   Node back   = null;
   Node cursor = null;

   //********** Access functions **********
   int length () {}
   int index () {}
   int front () {}
   int back () {}
   boolean equals (List L) {}

   //********** Manipulation procedures **********
   void clear () {}
   void moveFront () {}
   void moveBack () {}
   void moveNext () {}
   void prepend (int data) {}
   void append (int data) {}
   void insertBefore (int data) {}
   void insertAfter (int data) {}
   void deleteFront () {}
   void deleteBack () {}
   void delete () {}

   //Other methods
   public String toString() {}
   List copy () {}
   List concat (List L) {}




}


