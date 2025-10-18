/*************************************************************************************
ARCHIVO	: EmpleadoModelo.java
FECHA	: 15/10/2025
*************************************************************************************/
package Ejercicio3;
import java.io.*;
import java.util.*;

public class EmpleadoModelo {
    private String nombreArchivo = "empleados.txt";
    private List<Empleado> listaEmpleados = new ArrayList<>();
    public EmpleadoModelo() {
        File archivo = new File(nombreArchivo);
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                System.out.println("Archivo 'empleados.txt' creado correctamente.");
            }
        } catch (IOException e) {
        	throw new RuntimeException("Error al crear el archivo: " + e.getMessage());
        }

        leerEmpleados();
    }
    public void leerEmpleados() {
        listaEmpleados.clear();

        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
            	throw new RuntimeException("Error al crear el archivo: " + e.getMessage());
            }
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                try {
                    int numero = Integer.parseInt(linea.trim());
                    String nombre = br.readLine();
                    double sueldo = Double.parseDouble(br.readLine().trim());

                    Empleado emp = new Empleado(numero, nombre, sueldo);
                    listaEmpleados.add(emp);
                } catch (Exception e) {
                	throw new RuntimeException("Error al leer datos de empleado. Se omitió una entrada.");
                }
            }
        } catch (IOException e) {
        	throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
        }
    }
    public boolean agregarEmpleado(Empleado e) {
        if (e.getNumero() <= 0) {
            return false;
        }
        if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
            return false;
        }
        if (e.getSueldo() < 0) {
            return false;
        }

        for (Empleado emp : listaEmpleados) {
            if (emp.getNumero() == e.getNumero()) {
                System.out.println("Ya existe un empleado con ese número.");
                return false;
            }
        }
        listaEmpleados.add(e);
        guardarEmpleados();
        return true;
    }
    public Empleado buscarPorNumero(int numero) {
        for (Empleado e : listaEmpleados) {
            if (e.getNumero() == numero) return e;
        }
        return null;
    }
    public Empleado buscarPorNombre(String nombre) {
        for (Empleado e : listaEmpleados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        }
        return null;
    }
    public boolean eliminarPorNumero(int numero) {
        for (Empleado e : listaEmpleados) {
            if (e.getNumero() == numero) {
                listaEmpleados.remove(e);
                guardarEmpleados();
                return true;
            }
        }
        return false;
    }
    public boolean eliminarPorNombre(String nombre) {
        for (Empleado e : listaEmpleados) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                listaEmpleados.remove(e);
                guardarEmpleados();
                return true;
            }
        }
        return false;
    }
    private void guardarEmpleados() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            for (Empleado e : listaEmpleados) {
                bw.write(String.valueOf(e.getNumero()));
                bw.newLine();
                bw.write(e.getNombre());
                bw.newLine();
                bw.write(String.valueOf(e.getSueldo()));
                bw.newLine();
            }
        } catch (IOException e) {
        	throw new RuntimeException("Error al guardar el archivo: " + e.getMessage());
        }
    }
    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
}
