/* Adam Pinarbasi
   akpinarb
   pa1            */
import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class List {

   private class Node {
      int data;
      int index;
      Node next;
      Node prev;
      Node(int dataIn) {
         data  = dataIn;
         index = 0;
         next  = null;
         prev  = null;
      }

      // toString
      // Overriden toString method for class Node
      public String toString() {
         return String.valueOf(data);
      }
   }

   //********** Constructor **********

   List () {
      front  = null;
      back   = null;
      cursor = null;
      length = 0;
   }

   //********** Fields **********

   private Node front;
   private Node back;
   private Node cursor;
   private int length;

   //********** Access functions **********

   // length
   /* Returns the number of elements in this List */
   int length () { return length; }

   // index
   /* If cursor is defined, returns the index of the cursor element,
      otherwise returns -1 */
   int index () {
      return (cursor == null ? -1 : cursor.index);
   }

   // front
   /* Returns front element.  Pre: length() > 0 */
   int front () {
      if (length == 0) throw new RuntimeException
                       ("front(): length is 0\n");
      return front.data;
   }

   // back
   /* Returns back element. Pre: lenght() > 0 */
   int back () {
      if (length == 0) throw new RuntimeException
                       ("back(): length is 0\n");
      return back.data;
   }

   // get
   /* Returns element on cursor */
   int get () {
      if (cursor == null) throw new RuntimeException
                          ("cursorData(): cursor is null\n");
      return cursor.data;
   }

   // equals
   /* Returns true if this List and L are the same integer
      sequence. The cursor is ignored in both lists */
   boolean equals (List L) {
      if (L == null || length != L.length) return false;
      Node thisNode = front;
      for (Node LNode = L.front; LNode != null && thisNode != null; 
                LNode = LNode.next) {
         if (thisNode.data != LNode.data) return false;
         thisNode = thisNode.next;
      }
      return true;
   }

   //********** Manipulation procedures **********

   // clear
   /* Resets this List to its original empty state. */
   void clear () {
      front  = null;
      back   = null;
      cursor = null;
      length = 0;
   }

   // moveFront
   /* If List is non-empty, places the cursor under the front element, 
      otherwise does nothing */
   void moveFront () {
      if (length == 0) return;
      cursor = front;
   }

   // moveBack
   /* If List is non-empty, places the cursor under the back element,
      ootherwise does nothing */
   void moveBack () {
      if (length == 0) return;
      cursor = back;
   }

   // movePrev
   /* If cursor is defined and not at front, moves cursor one step toward
      front of this List, if cursor is defined and at front, cursor becomes
      undefined, if cursor is undefined does nothing */
   void movePrev () {
      if (cursor == null) return;
      if (cursor == front) { cursor = null; return; }
      cursor = cursor.prev;
   }


   // moveNext
   /* If cursor is defined and not at back, moves cursor one step toward
      back of this List, if cursor is defined and at back, cursor becomes
      undefined, if cursor is undefined does nothing */
   void moveNext () {
      if (cursor == null) return;
      if (cursor == back) { cursor = null; return; }
      cursor = cursor.next;
   }

   // prepend
   /* Insert new element into this List.  If List is non-empty,
      insertion takes place before front element */
   void prepend (int data) {
      Node prep = new Node(data);
      if (front == null) { front = prep; back = prep; }
      else {
         for (Node curr = front.next; curr != null; curr = curr.next) 
            ++curr.index;
         prep.next  = front;
         front.prev = prep;
         front      = prep;
      }
      ++length;
   }

   // append
   /* Insert new element into this List.  If List is non-empty, 
      insertion takes place after back element. */
   void append (int data) { 
      Node app = new Node(data);
      if (front == null) { 
         front = app;
         back  = app;
      }
      else {
         app.index = back.index + 1;
         app.prev  = back;
         back.next = app;
         back      = app;
      }
      ++length;
   }

   // insertBefore
   /* Insert new element before cursor.
      Pre: length() > 0, index() >= 0 */
   void insertBefore (int data) {
      if (cursor == null) throw new RuntimeException
                          ("insertBefore: cursor is null\n");
      Node insb  = new Node(data);
      insb.index = cursor.index;
      insb.next  = cursor;
      insb.prev  = cursor.prev;

      if (cursor.prev == null) front = insb;
      else cursor.prev.next = insb;

      cursor.prev = insb;
      for (Node curr = cursor; curr != null; curr = curr.next)
         ++curr.index;
      ++length;
   }

   // insertAfter
   /* Inserts new element after cursor.
      Pre: length() > 0, index() > = 0 */
   void insertAfter (int data) {
      if (cursor == null) throw new RuntimeException
                          ("insertAfter: cursor is null\n");
      Node insa  = new Node(data);
      insa.index = cursor.index;
      insa.prev  = cursor;
      insa.next  = cursor.next;

      if (cursor.next == null) back = insa; 
      else cursor.next.prev = insa;

      cursor.next = insa;
      for (Node curr = insa; curr != null; curr = curr.next)
         ++curr.index;
      ++length;
   }

   // deleteFront
   /* Deletes the front element. Pre: length() > 0 */
   void deleteFront () {
      if (length == 0) throw new RuntimeException
                       ("deleteFront: list is empty\n");
      front      = front.next;
      front.prev = null;
      for (Node curr = front; curr != null; curr = curr.next) 
         --curr.index; 
      --length;
   }

   // deleteBack
   /* Deletes the back element.  Pre: length() > 0 */
   void deleteBack () {
      if (length == 0) throw new RuntimeException
                       ("deleteBack: list is empty\n");
      back      = back.prev;
      back.next = null;
      --length;
   }

   // delete
   /* Deletes cursor element, making cursor undefined. 
      Pre: length() > 0, index() >= 0 */
   void delete () {
      if (cursor == null) throw new RuntimeException
                          ("delete: cursor is null\n");
      for (Node curr = cursor.next; curr != null; curr = curr.next) 
         --curr.index;
      cursor.prev.next = cursor.next;
      cursor.next.prev = cursor.prev;
      cursor = null;
      --length;
   }

   //********** Other methods **********

   // toString
   /* Overrides Object's toString method.  Returns a String
      representation of this List consisting of a space 
      separated sequence of integers, with front on left. 
      Returns null if list is empty */
   public String toString() {
      String list = front.toString(); 
      for (Node curr = front.next; curr != null; curr = curr.next) 
         list += " " + curr.toString();
      return list;
   }

   // copy
   /* Returns a new List representing the same integer sequence as this
      List.  The cursor in the new list is undefined, regardless of the 
      state of the cursor in this List.  This List is unchanged */
   List copy () {
      List copy = new List();  
      for (Node curr = front; curr != null; curr = curr.next) 
         copy.append(curr.data);
      return copy;
   }

   // concat
   /* Returns a new List which is the concatenation of 
      this list followed by L.  The cursor in the new List 
      is undefined, regardless of the states of the cursors
      in this List and L.  The states of this List and L are 
      unchanged */
   List concat (List L) {
      List cat = copy();
      for (Node curr = L.front; curr != null; curr = curr.next)
         cat.append(curr.data);
      return cat;
   }
}


