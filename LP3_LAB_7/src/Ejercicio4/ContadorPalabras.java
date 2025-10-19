package ejercicio4;

import javax.swing.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ContadorPalabras {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("seleccione el archivo de texto a analizar");
        int result = fileChooser.showOpenDialog(null);

        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("no se selecciono ningun archivo. programa terminado.");
            return;
        }

        File archivo = fileChooser.getSelectedFile();

        if (!archivo.exists() || !archivo.isFile()) {
            JOptionPane.showMessageDialog(null,
                    "el archivo seleccionado no es valido.",
                    "error de archivo",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            analizarArchivo(archivo.toPath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "ocurrio un error al leer el archivo:\n" + e.getMessage(),
                    "error de lectura",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void analizarArchivo(Path rutaArchivo) throws IOException {
        int lineas = 0;
        int caracteres = 0;
        int palabras = 0;
        Map<String, Integer> frecuenciaPalabras = new HashMap<>();

        try (BufferedReader lector = Files.newBufferedReader(rutaArchivo)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                lineas++;
                caracteres += linea.length();
                StringBuilder palabraActual = new StringBuilder();
                for (char c : linea.toCharArray()) {
                    if (Character.isLetterOrDigit(c)) {
                        palabraActual.append(Character.toLowerCase(c));
                    } else if (palabraActual.length() > 0) {
                        String palabra = palabraActual.toString();
                        palabras++;
                        frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                        palabraActual.setLength(0);
                    }
                }
                if (palabraActual.length() > 0) {
                    String palabra = palabraActual.toString();
                    palabras++;
                    frecuenciaPalabras.put(palabra, frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                }
            }
        }

        double promedioPalabras = (lineas > 0) ? (double) palabras / lineas : 0;
        int maxFrecuencia = frecuenciaPalabras.values().stream().max(Integer::compareTo).orElse(0);
        List<String> palabrasFrecuentes = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frecuenciaPalabras.entrySet()) {
            if (entry.getValue() == maxFrecuencia) {
                palabrasFrecuentes.add(entry.getKey());
            }
        }

        System.out.println("resultados del analisis");
        System.out.println("archivo: " + rutaArchivo.getFileName());
        System.out.println("---------------------------------------");
        System.out.println("total de lineas: " + lineas);
        System.out.println("total de palabras: " + palabras);
        System.out.println("total de caracteres (sin saltos de linea): " + caracteres);
        System.out.printf("promedio de palabras por linea: %.2f%n", promedioPalabras);
        System.out.println("palabras mas frecuentes (" + maxFrecuencia + " veces): " + palabrasFrecuentes);
    }
}
