import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class MatrixTest {

   public static void main (String[] args) {
      Matrix m = new Matrix(3);
      int size = m.getSize();
      out.printf("size = %d\n", size);

      m.changeEntry(0,0,-1);
      m.changeEntry(0,1,2);
      m.changeEntry(0,2,3);
      m.changeEntry(1,0,4);
      m.changeEntry(1,1,5);
      m.changeEntry(1,2,6);
      m.changeEntry(2,0,7);
      m.changeEntry(2,1,8);
      m.changeEntry(2,2,9);

      out.printf("\n");
      Matrix Ma = m.scalarMult(2);
      String temp = Ma.toString();
      out.printf("%s\n", temp);

      String blah = m.toString();
      out.printf("%s\n", blah);

      Matrix pl = Ma.add(m);
      String he = pl.toString();
      out.printf("%s\n", he);

      Matrix sub = pl.sub(Ma);
      String su = sub.toString();
      out.printf("%s\n", su);

      Matrix tr = sub.transpose();
      String te = tr.toString();
      out.printf("%s\n", te);

      Matrix mul = m.mult(sub);
      String mu = mul.toString();
      out.printf("%s\n", mu);

   }
}
