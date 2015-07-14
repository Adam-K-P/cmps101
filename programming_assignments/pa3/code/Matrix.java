/* Adam Pinarbasi
   akpinarb
   pa3           */

import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class Matrix {

   private class Entry {

      int column;
      double value;

      Entry (int c, double v) {
         if (c > size) throw new RuntimeException("Column must be <= size\n");
         column = c;
         value = v;
      }
   }

   int size;
   int entries;
   List[] rows;

   //Matrix
   //constructor
   Matrix (int n) { //makes a new n x n zero Matrix. pre: n>=1
      if (n < 1) throw new RuntimeException ("Size must be >= 1\n");
      size = n;
      rows = new List[size];
      for (int i = 0; i < n; ++i) rows[i] = new List();
      entries = 0;
   }

   //Access function

   //getSize
   //returns n, the number of rows and columns of this Matrix
   int getSize () { return size; }

   //getNNX
   //returns the number of non-zero entries in this Matrix
   int getNNZ () { return entries; }

   //equals
   //overriedes Object's equals() method
   public boolean equals (Object x) { 
      Matrix M = (Matrix)x;
      if (this == M) return true;
      if (size != M.getSize()) return false;
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         List thatList = M.rows[i];
         for (thisList.moveFront(), thatList.moveFront();
              thisList.index() >= 0 && thatList.index() >= 0;
              thisList.moveNext(), thatList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            Entry thatEntry = (Entry)thatList.get();
            if (thisEntry.value  != thatEntry.value ||
                thisEntry.column != thatEntry.column)
               return false;
         }
      }
      return true;
   }

   //Manipulation procedures

   //MakeZero
   //sets this Matrix to the zero state
   void makeZero () { 
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         thisList.clear();
      }
   }

   //copy
   //returns a new Matrix having the same entries as this Matrix
   Matrix copy () { 
      Matrix copy = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0;
              thisList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            copy.changeEntry(i, thisEntry.column, thisEntry.value);
         }
      }
      return copy;
   }

   //changeEntry
   //changes ith row, jth column of this Matrix to x
   //pre: 1 <= i <= getSize(), 1 <= j <= getSize()
   void changeEntry (int i, int j, double x) {
      if (i > size || j > size ) 
         throw new RuntimeException("No such entry\n");
      List thisList = rows[i];
      for (thisList.moveFront(); thisList.index() >= 0; thisList.moveNext()) {
         Entry thisEntry = (Entry)thisList.get();
         if (thisEntry.column == j) { 
            if (x == 0) { thisList.delete(); --entries; }
            else thisEntry.value = x;
            return;
         }
      }
      if (x == 0) return;
      Entry thisEntry = new Entry(j, x);
      ++entries;
      for (thisList.moveFront(); thisList.index() >= 0; thisList.moveNext()) {
         Entry thatEntry = (Entry)thisList.get();
         if (thatEntry.column > j) 
            { thisList.insertBefore(thisEntry); return; }
      }
      thisList.append(thisEntry);
   }

   //scalarMult
   //returns a new Matrix that is the scalar product of this Matrix with x
   Matrix scalarMult (double x) {
      Matrix M = new Matrix(size);
      for (int i = 0; i < M.getSize(); ++i) {
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0; 
              thisList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            M.changeEntry(i, thisEntry.column, thisEntry.value * x);
         }
      }
      return M;
   }

   //performOp
   //if add is true, performs addition, otherwise performs subtraction
   private static void performOp (Matrix M, List thisList, List thatList, 
                                  boolean add, int i) {
      for (thisList.moveFront(), thatList.moveFront(); 
           thisList.index() >= 0 && thatList.index() >= 0;
           thisList.moveNext(), thatList.moveNext()) {
          Entry thisEntry = (Entry)thisList.get();
          Entry thatEntry = (Entry)thatList.get();
          if (thisEntry.column == thatEntry.column) {
             double value = (add == true ? thisEntry.value + thatEntry.value :
                                           thisEntry.value - thatEntry.value);
             M.changeEntry(i, thisEntry.column, value);
             continue;
          }
          M.changeEntry(i, thisEntry.column, thisEntry.value);
          M.changeEntry(i, thatEntry.column, thatEntry.value);
      }
      for ( ; thisList.index() >= 0; thisList.moveNext()) {
         Entry thisEntry = (Entry)thisList.get();
         M.changeEntry(i, thisEntry.column, thisEntry.value);
      }
      for ( ; thatList.index() >=0; thatList.moveNext()) {
         Entry thatEntry = (Entry)thatList.get(); 
         M.changeEntry(i, thatEntry.column, thatEntry.value);
      }
   }


   //add
   //returns a new Matrix that is the sum of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix add (Matrix M) {
      if (size != M.getSize()) 
         throw new RuntimeException("Matrices must have same size\n");
      if (this == M) M = copy();
      Matrix add = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         List thatList = M.rows[i];
         performOp(add, thisList, thatList, true, i);
      }
      return add;
   }

   //sub
   //returns a new Matrix that is the difference of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix sub (Matrix M) {
      if (size != M.getSize())
         throw new RuntimeException("Matrices must have same size\n");
      if (this == M) M = copy();
      Matrix sub = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         List thatList = M.rows[i];
         performOp(sub, thisList, thatList, false, i);
      }
      return sub;
   }

   //transpose
   //returns a new Matrix that is the transpose of this Matrix
   Matrix transpose () {
      Matrix M = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0;
              thisList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            M.changeEntry(thisEntry.column, i, thisEntry.value);
         }
      }
      return M;
    }

   //dotProduct
   //returns dot product of two Lists
   private static double dotProduct (List thisList, List thatList) {
      double value = 0;
      for (thisList.moveFront(), thatList.moveFront();
           thisList.index() >= 0 && thatList.index() >= 0;) {
         Entry thisEntry = (Entry)thisList.get();
         Entry thatEntry = (Entry)thatList.get();
         if (thisEntry.column == thatEntry.column) {
            value += thisEntry.value * thatEntry.value;
            thisList.moveNext(); 
            thatList.moveNext();
            continue;
         }
         if (thisEntry.column > thatEntry.column) thatList.moveNext();
         else thisList.moveNext();
      }
      return value;
   }


   //mult
   //returns a new Matrix that is the product of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix mult (Matrix M) {
      if (size != M.getSize())
         throw new RuntimeException("Matrices must have same size\n");
      Matrix prod = new Matrix(size);
      Matrix tran = M.transpose(); 
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         for (int j = 0; j < thisList.length(); ++j) {
            List thatList = tran.rows[j];
            double dotp = dotProduct(thisList, thatList);
            if (dotp != 0) prod.changeEntry(i, j, dotp);
         }
      }
      return prod;
   }

   //toString
   //other functions
   public String toString () {
      String ret = new String();
      for (int i = 0; i < size; ++i) {
         boolean addn = false;
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0; 
              thisList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            ret += Double.toString(thisEntry.value) + " ";
            addn = true;
         }
         ret += addn ? "\n" : "";
      }
      return ret;
   }
}







