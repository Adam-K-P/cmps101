/* Adam Pinarbasi
   akpinarb
   pa1            */
import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;
import java.util.Scanner;

class Lex {

   public static void main(String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments\n");
      List list = new List();
      try { 
         Scanner in = new Scanner(new File(args[1])); 
         list = readFile(in);
      }
      catch (FileNotFoundException ex) 
         { err.printf("File %s: not found\n", args[1]); }

      out.printf("%s\n", list.toString());
   }
   
   static List readFile(Scanner in) {
      List list = new List();
      String buffer = null;
      while (in.hasNextLine()) {
         buffer = in.nextLine();
         String line = new String(buffer);
         insertLine(line, list);
      }
      return list;
   }

   static void insertLine(String line, List list) {
      if (list.length() == 0) { list.prepend(line); return; }
      list.moveFront();
      for (; list.index() >= 0; list.moveNext()) {
         if (line.compareTo((String)list.get()) == 0 ||
             line.compareTo((String)list.get())  < 0   ) 
           { list.insertBefore(line); return; }
      }
      list.moveBack();
      list.insertAfter(line);
   }
}


                                       

