package actividad7;

import javax.swing.JFrame;
import java.io.IOException;

public class PruebaJFileChooser {
    public static void main(String[] args) throws IOException {
        DemoJFileChooser aplicacion = new DemoJFileChooser();
        aplicacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aplicacion.setSize(400, 400);
        aplicacion.setVisible(true);
    }
}
