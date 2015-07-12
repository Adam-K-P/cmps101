/* Adam Pinarbasi
   akpinarb
   pa3           */

import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;
import java.lang.String;
import java.util.Scanner;
import java.util.regex.*;

class Sparse {

   public static void main (String[] args) {
      if (args.length != 2) throw new RuntimeException
                            ("Usage: Sparse [infile] [outfile]\n");
      try { 
         Scanner infile = new Scanner(new File(args[0])); 
         Matrix matx = formMatrix(infile);
      }
      catch (FileNotFoundException ex) 
         { err.printf("File %s: not found\n", args[0]); }
   }

   static Matrix formMatrix(Scanner infile) {
      Matrix matx = new Matrix(1);
      List list = new List();
      int size = getSize(infile);
      out.printf("Sparse test\n");
      return matx;
   }

   /* static boolean checkInput(Scanner infile) {*/


   static int getSize(Scanner infile) {
      String firstline;
      if (infile.hasNextLine()) firstline = infile.nextLine();
      else throw new RuntimeException("Illegal input file format\n");
      int[] sizes;
      if (Pattern.matches("[0-9]++ [0-9]++ [0-9]++", firstline)) {
         String test[] = firstline.split(" ");

      }
      else throw new RuntimeException("Illegal input file format\n"); 
      return size;
   }
}
