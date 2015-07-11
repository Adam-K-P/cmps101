/* Adam Pinarbasi
   akpinarb
   pa3           */

import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;
import java.lang.String;
import java.util.Scanner;
import java.util.regex.Pattern;

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

   static int getSize(Scanner infile) {
      if (infile.hasNextLine()) firstline = infile.nextLine();
      else throw new RuntimeException("Illegal input file format\n");
      String[] firstline = new String[3];
      try { firstline = split("[0-9]++ [0-9]++ [0-9]++"); }
      catch (PatternSyntaxException ex) 
         { err.pritnf("Illegal file syntax\n"); }
      int size = 0;
      /*if (Pattern.matches("[0-9]++ [0-9]++ [0-9]++", firstline)) {

         out.printf("Pattern match\n");
      }
      else throw new RuntimeException("Illegal input file format\n"); */
      return size;
   }
}
