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
      entries = 0;
   }

   //Access function
   int getSize () { //returns n, the number of rows and columns of this Matrix
      return size;
   }

   int getNNZ () { //returns the number of non-zero entries in this Matrix
      return entries;
   }

   public boolean equals (Object x) { //overriedes Object's equals() method
      return true;
   }

   //Manipulation procedures
   void makeZero () { //sets this Matrix to the zero state
      rows = new List[size];
   }

   //returns a new Matrix having the same entries as this Matrix
   Matrix copy () { 
      return this;
   }

   //changes ith row, jth column of this Matrix to x
   //pre: 1 <= i <= getSize(), 1 <= j <= getSize()
   void changeEntry (int i, int j, double x) {
   }

   //returns a new Matrix that is the scalar product of this Matrix with x
   Matrix scalarMult (double x) {
      return this;
   }

   //returns a new Matrix that is the sum of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix add (Matrix M) {
      return this;
   }

   //returns a new Matrix that is the difference of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix sub (Matrix M) {
      return this;
   }

   //returns a new Matrix that is the transpose of this Matrix
   Matrix transpose () {
      return this;
    }

   //returns a new Matrix that is the product of this Matrix with M
   //pre: getSize() == M.getSize()
   Matrix mult (Matrix m) {
      return this;
   }

   //other functions
   public String toString () {
      String temp = new String();
      return temp;
   }
}







