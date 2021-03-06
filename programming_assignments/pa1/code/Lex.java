/* Adam Pinarbasi
   akpinarb
   pa1            */
import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;
import java.util.Scanner;

class Lex {

   // main
   // Executes the program
   public static void main (String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments\n");
      List list = null;
      try { 
         Scanner inCount = new Scanner(new File(args[0])); 
         list = readFile(inCount, args[0], args[1]);
      }
      catch (FileNotFoundException ex) 
         { err.printf("File %s: not found\n", args[0]); }
   }
   
   // readFile
   // Reads a file, and stores it into an array (where it is then sorted)
   static List readFile (Scanner inCount, String infilename, 
                                          String outfile) {
      List list = new List();
      int lineCount = 0;
      while (inCount.hasNextLine()) 
           { inCount.nextLine(); ++lineCount; }
      inCount.close();
      putInArray(infilename, lineCount, outfile, list);
      return list;
   }

   // putInArray
   // Puts a file into a String array then sorts the array and writes
   // it to an output file
   static void putInArray (String infilename, int lineCount, 
                           String outfile , List list) {
      Scanner infile = null;
      try { infile = new Scanner(new File(infilename)); }
      catch (FileNotFoundException ex) 
         { err.printf("File: %s not found\n", infilename); }
      String[] file = new String[lineCount];
      int index = 0;
      String buffer = null;
      while (infile.hasNextLine()) {
         buffer = infile.nextLine();
         String line = new String(buffer);
         file[index++] = line;
      }
      if (file.length == 0) {
         err.printf("File: %s is empty\n", infilename);
         return;
      }
      sortArray(file, list);
      writeFile(list, outfile, file);
      infile.close();
   }

   // copyArray
   // Simply makes a copy of an array
   static String[] copyArray (String[] file) {
      String[] copy = new String[file.length];
      for (int i = 0; i < file.length; ++i) copy[i] = file[i];
      return copy;
   }

   // sortFile
   // Sorts an array into lexicographic order
   static void sortArray (String[] file, List list) {
      String[] copy = copyArray(file);
      for (int i = 0; i < file.length; ++i) {
         String smallstr = copy[i];
         for (int j = i; j < file.length; ++j) {
            if (copy[j].compareTo(copy[i])  < 0 && 
                copy[j].compareTo(smallstr) < 0   ) {
               smallstr = copy[j];
               copy[j]  = copy[i];
               copy[i]  = smallstr;
            }
         }
      }
      for (int k = 0; k < file.length; ++k) {
         for (int m  = 0; m < file.length; ++m) {
            if (file[m].compareTo(copy[k]) == 0 && 
                !inList(m, list)) { list.append(m); break; }
         }
      }
   }

   // inList
   // Checks whether a given index is in the list
   static boolean inList (int index, List list) {
      for (list.moveFront(); list.index() >= 0; list.moveNext()) 
         if (list.get() == index) return true;
      return false;
   }

   // writeFile
   // Writes a String array to an output file in the order specified
   // by list
   static void writeFile (List list, String outfilename, String[] file) {
      if (list == null) {
         err.printf("writeFile(): list is null\n");
         return;
      }
      PrintWriter outFile = null;
      try { outFile = new PrintWriter(new FileWriter(outfilename)); }
      catch (IOException ex) {
         err.printf("writeFile(): Error writing to file: %s\n",
                                                   outfilename); 
      }
      for (list.moveFront(); list.index() >= 0; list.moveNext()) 
         outFile.printf("%s\n", file[list.get()]);
      outFile.close();
   }
}


                                       

