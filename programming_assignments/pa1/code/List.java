import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class List {
   private class Node {
      int data;
      int index;
      Node next;
      Node prev;
      Node(int data) {
         data = data;
         index = 0;
         next = null;
         prev = null;
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

   /* Returns the number of elements in this List */
   int length () {
      return length;
   }

   /* If cursor is defined, returns the index of the cursor element,
      otherwise returns -1 */
   int index () {
      if (cursor == null) return -1;
      else return cursor.index;
   }

   /* Returns front element.  Pre: length() > 0 */
   int front () {
      return front.data;
   }

   /* Returns back element. Pre: lenght() > 0 */
   int back () {
      return back.data;
   }

   /* Returns true if this List and L are the same integer
      sequence. The cursor is ignofred in both lists */
   boolean equals (List L) {
      if (length != L.length) return false;
      Node thisNode = front;
      for (Node LNode = L.front; LNode != null && thisNode != null; 
                LNode = LNode.next) {
         if (thisNode.data != LNode.data) return false;
         thisNode = thisNode.next;
      }
      return true;
   }

   //********** Manipulation procedures **********

   /* Resets this List to its original empty state. */
   void clear () {
      front = null;
      back = null;
      cursor = null;
      length = 0;
   }

   /* If List is non-empty, places the cursor under the front element, 
      otherwise does nothing */
   void moveFront () {
      if (length == 0) return;
      else cursor = front;
   }

   /* If List is non-empty, places the cursor under the back element,
      ootherwise does nothing */
   void moveBack () {
      if (length == 0) return;
      else cursor = back;
   }

   /* If cursor is defined and not at back, moves cursor one step toward
      back of this List, if cursor is defined and at back, cursor becomes
      undefined, if cursor is undefined does nothing */
   void moveNext () {
      if (cursor == null) return;
      if (cursor == back) cursor = null;
      cursor = cursor.next;
   }

   /* Insert new element into this List.  If List is non-empty,
      insertion takes place before front element */
   void prepend (int data) {
      Node prep = new Node(data);
      if (front == null) {
         front = prep;
         back  = prep;
      }
      else {
         prep.next  = front;
         front.prev = prep;
         front      = prep;
      }
      ++length;
   }

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
   }

   /* Insert new element before cursor.
      Pre: length() > 0, index() >= 0 */
   void insertBefore (int data) {
      if (cursor == null) throw new RuntimeException
                          ("insertBefore: cursor is null\n");
      Node insb = new Node(data);
      insb.index = cursor.index - 1;
      insb.next = cursor;
      insb.prev = cursor.prev;
      cursor.prev = insb;
   }

   /* Inserts new element after cursor.
      Pre: length() > 0, index() > = 0 */
   void insertAfter (int data) {
      if (cursor == null) throw new RuntimeException
                          ("insertAfter: cursor is null\n");
      Node insa = new Node(data);
      insa.index = cursor.index + 1;
      insa.prev = cursor;
      insa.next = cursor.next;
      cursor.next = insa;
   }

   /* Deletes the front element. Pre: length() > 0 */
   void deleteFront () {}

   /* Deletes the back element.  Pre: length() > 0 */
   void deleteBack () {}

   /* Deletes cursor element, making cursor undefined. 
      Pre: length() > 0, index() >= 0 */
   void delete () {}


   //********** Other methods **********

   /* Overrides Object's tpString method.  Returns a String
      representation of this List consisting of a space 
      separated sequence of integers, with front on left */
   public String toString() {
      return null;
   }

   /* Returns a new List representing the same integer sequence as this
      List.  The cursor in the new list is undefined, regardless of the 
      state of the cursor in this List.  This List is unchanged */
   List copy () {
      return null;
   }

   /* Returns a new List which is the concatenation of 
      this list followed by L.  The cursor in the new List 
      is undefined, regardless of the states of the cursors
      in this List and L.  The states of this List and L are 
      unchanged */
   List concat (List L) {
      return null;
   }
}


