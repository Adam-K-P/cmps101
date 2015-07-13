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
         if (c > size) throw new RuntimeException("Column must be < size\n");
         column = c;
         value = v;
      }

      public boolean equals (Object x) {
         return true;
      }

      public String toString () {
         String temp = new String();
         return temp;
      }
   }

   int size;
   int entries;
   List[] rows;

   //constructor
   Matrix (int n) { //makes a new n x n zero Matrix. pre: n>=1
      if (n < 1) throw new RuntimeException ("Size must be >= 1\n");
      size = n;
      rows = new List[size];
      for (int i = 0; i < n; ++i) rows[i] = new List();
      entries = 0;
   }

   //Access function
   int getSize () { //returns n, the number of rows and columns of this Matrix
      return size;
   }

   int getNNZ () { //returns the number of non-zero entries in this Matrix
      return entries;
   }

   //TODO
   public boolean equals (Object x) { //overriedes Object's equals() method
      return true;
   }

   //Manipulation procedures
   void makeZero () { //sets this Matrix to the zero state
      rows = new List[size];
   }

   //TODO
   //returns a new Matrix having the same entries as this Matrix
   Matrix copy () { 
      return this;
   }

   //changes ith row, jth column of this Matrix to x
   //pre: 1 <= i <= getSize(), 1 <= j <= getSize()
   void changeEntry (int i, int j, double x) {
      if (i > size || j > size ) 
         throw new RuntimeException("No such entry\n");
      List thisList = rows[i];
      for (thisList.moveFront(); thisList.index() >= 0; thisList.moveNext()) {
         Entry thisEntry = (Entry)thisList.get();
         if (thisEntry.column == j) { thisEntry.value = x; return; }
      }
      Entry thisEntry = new Entry(j, x);
      ++entries;
      for (thisList.moveFront(); thisList.index() >= 0; thisList.moveNext()) {
         Entry thatEntry = (Entry)thisList.get();
         if (thatEntry.column > j) 
            { thisList.insertBefore(thisEntry); return; }
      }
      thisList.append(thisEntry);
   }

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
   static void performOp (Matrix M, List thisList, List thatList, 
                          boolean add, int i) {
      for (thisList.moveFront(), thatList.moveFront(); 
           thisList.index() >= 0 || thatList.index() >= 0;
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


   //returns a new Matrix that is the sum of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix add (Matrix M) {
      if (size != M.getSize()) 
         throw new RuntimeException("Matrices must have same size\n");
      Matrix add = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         List thatList = M.rows[i];
         performOp(add, thisList, thatList, true, i);
      }
      return add;
   }

   //returns a new Matrix that is the difference of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix sub (Matrix M) {
      if (size != M.getSize())
         throw new RuntimeException("Matrices must have same size\n");
      Matrix sub = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         List thatList = M.rows[i];
         performOp(sub, thisList, thatList, false, i);
      }
      return sub;
   }

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

   //returns a new Matrix that is the product of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix mult (Matrix M) {
      if (size != M.getSize())
         throw new RuntimeException("Matrices must have same size\n");
      Matrix prod = new Matrix(size);
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0; 
              thisList.moveNext()) {
            double value = 0;
            Entry thisEntry = (Entry)thisList.get();
            List thatList = M.rows[thisEntry.column];
            for (thatList.moveFront(); thatList.index();
                 thatList.moveNext()) {
               Entry thatEntry = (Entry)thatList.get();
               if (thatEntry.column == i) 
                  value += thisEntry.value * thatEntry.value;
            }
         }
      }
      return prod;
   }

   //other functions
   public String toString () {
      String temp = new String();
      for (int i = 0; i < size; ++i) {
         List thisList = rows[i];
         for (thisList.moveFront(); thisList.index() >= 0; 
              thisList.moveNext()) {
            Entry thisEntry = (Entry)thisList.get();
            out.printf("%f ", thisEntry.value);
         }
         out.printf("\n");
      }
      return temp;
   }
}







