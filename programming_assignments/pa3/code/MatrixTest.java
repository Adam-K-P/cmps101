import static java.lang.System.out;
import static java.lang.System.err;
import java.io.*;

class MatrixTest {

   public static void main (String[] args) {
      Matrix m = new Matrix(1000000);
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
      out.printf("%s\n", Ma.toString());

      out.printf("%s\n", m.toString());

      Matrix pl = Ma.add(m);
      out.printf("%s\n", pl.toString());

      Matrix sub = pl.sub(pl);
      out.printf("%s\n", sub.toString());

      Matrix tr = sub.transpose();
      out.printf("%s\n", tr.toString());

      Matrix mul = m.mult(m);
      out.printf("%s\n", mul.toString());

   }
}
