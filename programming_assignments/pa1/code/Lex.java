import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class Lex {

   public static void main(String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments\n");
      List list = new List();
      list = readFile(args[1]);
      out.printf("%s\n", list.toString());

   }
   
   static List readFile(String filename) {
      List list = new List();
      try {
         FileReader file = new FileReader(filename);
         BufferedReader buffer = new BufferedReader(file);
         for (String curr = buffer.readLine(); curr != null; 
                     curr = buffer.readLine()) {
            String line = new String(curr);
            insertLine(line, list);
         }
      }
      catch (FileNotFoundException ex) {
         err.printf("File: %s not found\n", filename);
         System.exit(1);
      }
      catch (IOException ex) {
         err.printf("Error reading file: %s\n", filename);
         System.exit(1);
      }
      return list;
   }

   static void insertLine(String line, List list) {
      if (list.length() == 0) { list.prepend(line); return; }
      list.moveFront();
      for (; list.index() >= 0; list.moveNext()) {
         if (line.compareTo(list.cursorData()) == 0) return;
         if (line.compareTo(list.cursorData())  < 0) 
            { list.insertBefore(line); return; }
      }
      list.moveBack();
      list.insertAfter(line);
   }
}


                                       

