import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class Lex {
   public static void main(String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments");
      readFile(args[0]);
   }
   static void readFile(String filename) {
      try {
         FileReader file = new FileReader(filename);
         BufferedReader buffer = new BufferedReader(file);
         String line;
         int length = 0;
         while ((line = buffer.readLine()) != null) ++length; 
      }
      catch (FileNotFoundException ex) {
         err.printf("File: %s not found\n", filename);
      }
      catch (IOException ex) {
         err.printf("Error reading file: %s\n", filename);
      }
   }
}


                                       

