import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class List {
   private class Node {
      int data;
      Node next = null;
      Node(int data) {
         data = data;
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
   int index () {}

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
   void moveFront () {}

   /* If List is non-empty, places the cursor under the back element,
      ootherwise does nothing */
   void moveBack () {}

   /* If cursor is defined and not at back, moves cursor one step toward
      back of this List, if cursor is defined and at back, cursor becomes
      undefined, if cursor is undefined does nothing */
   void moveNext () {}

   /* Insert new element into this List.  If List is non-empty,
      insertion takes place before front element */
   void prepend (int data) {}

   /* Insert new element into this List.  If List is non-empty, 
      insertion takes place after back element. */
   void append (int data) {}

   /* Insert new element before cursor.
      Pre: length() > 0, index() >= 0 */
   void insertBefore (int data) {}

   /* Inserts new element after cursor.
      Pre: length() > 0, index() > = 0 */
   void insertAfter (int data) {}

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
   public String toString() {}

   /* Returns a new List representing the same integer sequence as this
      List.  The cursor in the new list is undefined, regardless of the 
      state of the cursor in this List.  This List is unchanged */
   List copy () {}

   /* Returns a new List which is the concatenation of 
      this list followed by L.  The cursor in the new List 
      is undefined, regardless of the states of the cursors
      in this List and L.  The states of this List and L are 
      unchanged */
   List concat (List L) {}
}


