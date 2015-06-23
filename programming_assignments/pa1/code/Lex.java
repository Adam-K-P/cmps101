import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class Lex {
   public static void main(String[] args) {
      if (args.length != 2) 
         throw new RuntimeException
                   ("There must be two command line arguments\n");
      readFile(args[1]);
   }
   static void readFile(String filename) {
      try {
         FileReader file = new FileReader(filename);
         BufferedReader buffer = new BufferedReader(file);
         String line;
         while ((line = buffer.readLine()) != null) 
            out.printf("%s\n", line);
      }
      catch (FileNotFoundException ex) {
         err.printf("File: %s not found\n", filename);
         System.exit(1);
      }
      catch (IOException ex) {
         err.printf("Error reading file: %s\n", filename);
         System.exit(1);
      }
   }
}


                                       

