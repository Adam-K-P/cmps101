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
      int[] sizes = getSizes(infile);
      Matrix A = new Matrix(sizes[0]);
      Matrix B = new Matrix(sizes[0]);
      formMatrices(A, B, infile, sizes);

      PrintWriter outfile = null;
      try { outfile = new PrintWriter(new FileWriter(args[1])); }
      catch (IOException ex) 
         { err.printf("main(): Error writing to file: %s\n", args[1]); }
      printResults(A, B, outfile);
      outfile.close();
   }

   //printResults
   //prints the results of the matrix functions
   static void printResults (Matrix A, Matrix B, PrintWriter outfile) {
      String at = A.toString();
      String bt = B.toString();
      outfile.printf("A has %d non-zero entries:\n%s\n", A.getNNZ(), at);
      outfile.printf("B has %d non-zero entries:\n%s\n", B.getNNZ(), bt);

      Matrix scal = A.scalarMult(1.5);
      String scalS = scal.toString();
      outfile.printf("(1.5)*A =\n%s\n", scalS);

      Matrix sum = A.add(B);
      String sumS = sum.toString();
      outfile.printf("A+B =\n%s\n", sumS);

      Matrix sumA = A.add(A);
      String sumAS = sumA.toString();
      outfile.printf("A+A =\n%s\n", sumAS);

      Matrix diff = B.sub(A);
      String diffS = diff.toString();
      outfile.printf("B-A =\n%s\n", diffS);

      Matrix diffA = A.sub(A);
      String diffAS = diffA.toString();
      outfile.printf("A-A =\n%s\n", diffAS);

      Matrix tran = A.transpose();
      String tranS = tran.toString();
      outfile.printf("Transpose(A) =\n%s\n", tranS);

      Matrix prod = A.mult(B);
      String prodS = prod.toString();
      outfile.printf("A*B =\n%s\n", prodS);

      Matrix powB = B.mult(B);
      String powBS = powB.toString();
      outfile.printf("B*B = \n%s\n", powBS);
   }


   //putEntries
   //places the entries in the matrix
   static void putEntries (Matrix M, int index, Scanner infile, int[] sizes) {
      String buf;
      if (infile.hasNextLine()) buf = infile.nextLine();
      else throw new RuntimeException("Illegal input file format\n");
      if (Pattern.matches("\n", buf)) 
         throw new RuntimeException("Illegal input file format\n");
      for (int i = 0; i < sizes[index]; ++i) {
         if (infile.hasNextLine()) buf = infile.nextLine();
         else throw new RuntimeException("Illegal input file format\n");
         double[] nums = checkNums(buf);
         M.changeEntry((int)nums[0] - 1, (int)nums[1] - 1, nums[2]);
      }
   }

   //formMatrices
   //creates the matrices to be manipulated
   static void formMatrices (Matrix A, Matrix B, Scanner infile, int[] sizes) {
      if (sizes[1] > (sizes[0] * sizes[0]) || 
          sizes[2] > (sizes[0] * sizes[0]))
         throw new RuntimeException("Array size too small\n");
      putEntries(A, 1, infile, sizes);
      putEntries(B, 2, infile, sizes);
   }

   //checkNums
   //checks to see input is valid and returns a double array
   static double[] checkNums (String line) {
      String buf[];
      if (Pattern.matches("[0-9]++ [0-9]++ (-?[0-9]++)\\.[0-9]++", line)) 
         buf = line.split(" ");
      else throw new RuntimeException("Illegal input file format\n"); 
      double[] nums = new double[3];
      for (int i = 0; i < 3; ++i) nums[i] = Double.parseDouble(buf[i]);
      return nums;
   }

   //getSizes
   //returns the sizes of the matrices
   static int[] getSizes (Scanner infile) {
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
