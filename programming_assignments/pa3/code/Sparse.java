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
      Scanner infile = null;
      try { infile = new Scanner(new File(args[0])); }
      catch (FileNotFoundException ex) 
         { err.printf("File %s: not found\n", args[0]); }
      Matrix A = null;
      Matrix B = null;
      formMatrices(A, B, infile);
      String at = A.toString();
      String bt = B.toString();
      out.printf("%s\n", at);
      out.printf("%s\n", bt);
   }

   static void formMatrices(Matrix A, Matrix B, Scanner infile) {
      Matrix matx = new Matrix(3);
      int[] sizes = getSizes(infile);
      if (sizes[1] > (sizes[0] * sizes[0]) || 
          sizes[2] > (sizes[0] * sizes[0]))
         throw new RuntimeException("Array size too small\n");
      String buf;
      if (infile.hasNextLine()) buf = infile.nextLine();
      else throw new RuntimeException("Illegal input file format\n");
      /*if (Pattern.matches("\n", buf)) 
         throw new RuntimeException("Illegal input file format\n");*/
      A = new Matrix(sizes[0]);
      for (int i = 0; i < sizes[1]; ++i) {
         if (infile.hasNextLine()) buf = infile.nextLine();
         else throw new RuntimeException("Illegal input file format\n");
         int[] nums = checkNums(buf);
         A.changeEntry(nums[0], nums[1], nums[2]);
      }
      B = new Matrix(sizes[1]);
      for (int i = 0; i < sizes[2]; ++i) {
         if (infile.hasNextLine()) buf = infile.nextLine();
         else throw new RuntimeException("Illegal input file format\n");
         int[] nums = checkNums(buf);
         B.changeEntry(nums[0], nums[1], nums[2]);
      }
   }

   static int[] checkNums (String line) {
      String buf[];
      if (Pattern.matches("[0-9]++ [0-9]++ [0-9]++.[0-9]", line)) 
         buf = line.split(" ");
      else throw new RuntimeException("Illegal input file format\n"); 
      int[] nums = new int[3];
      for (int i = 0; i < 2; ++i) nums[i] = Integer.parseInt(buf[i]);
      nums[2] = Double.parseDouble(buf[2]);
      return nums;
   }

   static int[] getSizes(Scanner infile) {
      String firstline;
      if (infile.hasNextLine()) firstline = infile.nextLine();
      else throw new RuntimeException("Illegal input file format\n");
      String buf[];
      if (Pattern.matches("[0-9]++ [0-9]++ [0-9]++", firstline)) 
         buf = firstline.split(" ");
      else throw new RuntimeException("Illegal input file format\n"); 
      int[] sizes = new int[3];
      for (int i = 0; i < 3; ++i) sizes[i] = Integer.parseInt(buf[i]);
      return sizes;
   }
}
