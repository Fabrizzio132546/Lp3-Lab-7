package actividad7;

import javax.swing.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class DemoJFileChooser extends JFrame {
    private final JTextArea areaSalida;

    public DemoJFileChooser() throws IOException {
        super("Demo de JFileChooser");
        areaSalida = new JTextArea();
        add(new JScrollPane(areaSalida));
        analizarRuta();
    }

    public void analizarRuta() throws IOException {
        Path ruta = obtenerRutaArchivoDirectorio();

        if (ruta != null && Files.exists(ruta)) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%s:%n", ruta.getFileName()));
            builder.append(String.format("%s un directorio%n", Files.isDirectory(ruta) ? "Es" : "No es"));
            builder.append(String.format("%s una ruta absoluta%n", ruta.isAbsolute() ? "Es" : "No es"));
            builder.append(String.format("Última modificación: %s%n", Files.getLastModifiedTime(ruta)));
            builder.append(String.format("Tamaño: %s bytes%n", Files.size(ruta)));
            builder.append(String.format("Ruta: %s%n", ruta));
            builder.append(String.format("Ruta absoluta: %s%n", ruta.toAbsolutePath()));

            if (Files.isDirectory(ruta)) {
                builder.append(String.format("%nContenido del directorio:%n"));
                try (DirectoryStream<Path> flujo = Files.newDirectoryStream(ruta)) {
                    for (Path p : flujo) {
                        builder.append(String.format("%s%n", p.getFileName()));
                    }
                }
            }

            areaSalida.setText(builder.toString());
        } else if (ruta != null) {
            JOptionPane.showMessageDialog(this,
                    ruta.getFileName() + " no existe.",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private Path obtenerRutaArchivoDirectorio() {
        JFileChooser selectorArchivos = new JFileChooser();
        selectorArchivos.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int resultado = selectorArchivos.showOpenDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            System.exit(0);
        }

        return selectorArchivos.getSelectedFile().toPath();
    }
}
