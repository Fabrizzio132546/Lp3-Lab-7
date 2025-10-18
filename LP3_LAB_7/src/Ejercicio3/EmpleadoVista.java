/*************************************************************************************
ARCHIVO	: EmpleadoVista.java
FECHA	: 15/10/2025
*************************************************************************************/
package Ejercicio3;

import java.util.List;
import java.util.Scanner;

public class EmpleadoVista {

    private Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n===== MENÚ DE EMPLEADOS =====");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar nuevo empleado");
        System.out.println("3. Buscar empleado por número");
        System.out.println("4. Buscar empleado por nombre");
        System.out.println("5. Eliminar empleado por número");
        System.out.println("6. Eliminar empleado por nombre");
        System.out.println("7. Salir");
    }
    
    public int leerNumeroEntero(String mensaje) {
        int numero = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("No puede dejar el campo vacío.");
                continue;
            }
            try {
                numero = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
        return numero;
    }

    public void mostrarEmpleados(List<Empleado> empleados) {
        System.out.println("\n===== LISTA DE EMPLEADOS =====");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Empleado e : empleados) {
                System.out.println(e);
            }
        }
    }

    public int pedirNumeroEmpleado() throws DatoInvalidoException {
        System.out.print("Ingrese el número del empleado: ");
        String entrada = sc.nextLine().trim();

        if (entrada.isEmpty()) throw new DatoInvalidoException("El número no puede estar vacío.");
        try {
            int numero = Integer.parseInt(entrada);
            if (numero <= 0) throw new DatoInvalidoException("El número debe ser positivo.");
            return numero;
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("Debe ingresar un número entero.");
        }
    }

    public String pedirNombreEmpleado() throws DatoInvalidoException {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) throw new DatoInvalidoException("El nombre no puede estar vacío.");
        return nombre;
    }

    public double pedirSueldoEmpleado() throws DatoInvalidoException {
        System.out.print("Ingrese el sueldo del empleado: ");
        String entrada = sc.nextLine().trim();
        try {
            double sueldo = Double.parseDouble(entrada);
            if (sueldo <= 0) throw new DatoInvalidoException("El sueldo debe ser mayor que cero.");
            return sueldo;
        } catch (NumberFormatException e) {
            throw new DatoInvalidoException("Debe ingresar un número decimal válido.");
        }
    }

    public void mostrarEmpleado(Empleado e) {
        if (e != null) {
            System.out.println("\nEmpleado encontrado:");
            System.out.println(e);
        } else {
            System.out.println("\nEmpleado no encontrado.");
        }
    }

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void mostrarError(String msg) {
        System.err.println(msg);
    }
}