/* Adam Pinarbasi
   akpinarb
   pa1            */
import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;
import java.util.Scanner;

class Lex {

   public static void main (String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments\n");
      List list = null;
      try { 
         Scanner inCount = new Scanner(new File(args[0])); 
         list = readFile(inCount, args[0]);
      }
      catch (FileNotFoundException ex) 
         { err.printf("File %s: not found\n", args[0]); }
      writeFile(list, args[1]);
   }
   
   static List readFile (Scanner inCount, String filename) {
      List list = new List();
      int lineCount = 0;
      while (inCount.hasNextLine()) 
           { inCount.nextLine(); ++lineCount; }
      inCount.close();
      putInArray(filename, lineCount, list);
      return list;
   }

   static void putInArray (String filename, int lineCount, 
                                            List list) {
      Scanner infile = null;
      try { infile = new Scanner(new File(filename)); }
      catch (FileNotFoundException ex) 
         { err.printf("File: %s not found\n", filename); }
      String[] file = new String[lineCount];
      int index = 0;
      String buffer = null;
      while (infile.hasNextLine()) {
         buffer = infile.nextLine();
         String line = new String(buffer);
         file[index++] = line;
      }
      if (file.length == 0) {
         err.printf("File: %s is empty\n", filename);
         return;
      }
      sortFile(file, list);
      infile.close();
   }

   static String[] copyArray (String[] file) {
      String[] copy = new String[file.length];
      for (int i = 0; i < file.length; ++i) copy[i] = file[i];
      return copy;
   }

   static void sortFile (String[] file, List list) {
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
            if (file[m].compareTo(copy[k]) == 0) 
               list.append(m);
         }
      }
   }

   static void writeFile (List list, String filename) {
      if (list == null) {
         err.printf("writeFile(): list is null\n");
         return;
      }
      try {
         PrintWriter outFile = 
                     new PrintWriter(new FileWriter(filename));
         outFile.printf("%s\n", list.toString());
         outFile.close();
      }
      catch (IOException ex) {
         err.printf("writeFile(): Error writing to file: %s\n",
                                                   filename); 
      }
   }
}


                                       

