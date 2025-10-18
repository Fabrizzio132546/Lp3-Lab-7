/*************************************************************************************
ARCHIVO	: EmpleadoControlador.java
FECHA	: 15/10/2025
*************************************************************************************/
package Ejercicio3;

public class EmpleadoControlador {
    private EmpleadoModelo modelo;
    private EmpleadoVista vista;
    public EmpleadoControlador(EmpleadoModelo modelo, EmpleadoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    public void iniciar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerNumeroEntero("Seleccione una opcion:");

            switch (opcion) {
                case 1 -> listarEmpleados();
                case 2 -> agregarEmpleado();
                case 3 -> buscarPorNumero();
                case 4 -> buscarPorNombre();
                case 5 -> eliminarPorNumero();
                case 6 -> eliminarPorNombre();
                case 7 -> vista.mostrarMensaje("Saliendo");
                default -> vista.mostrarError("Opción no válida.");
            }
        } while (opcion != 7);
    }
    private void listarEmpleados() {
        vista.mostrarEmpleados(modelo.getListaEmpleados());
    }
    private void agregarEmpleado() {
        try {
            int numero = vista.pedirNumeroEmpleado();
            String nombre = vista.pedirNombreEmpleado();
            double sueldo = vista.pedirSueldoEmpleado();
            Empleado nuevo = new Empleado(numero, nombre, sueldo);
            boolean agregado = modelo.agregarEmpleado(nuevo);
            if (agregado) {
                vista.mostrarMensaje("Empleado agregado exitosamente.");
            } else {
                vista.mostrarError("No se pudo agregar el empleado. Verifique los datos o si ya existe.");
            }
        } catch (DatoInvalidoException e) {
            vista.mostrarError("Error: " + e.getMessage());
        }
    }
    private void buscarPorNumero() {
        try {
            int numero = vista.pedirNumeroEmpleado();
            Empleado e = modelo.buscarPorNumero(numero);
            if (e != null) vista.mostrarEmpleado(e);
            else vista.mostrarMensaje("No se encontró ningún empleado con ese número.");
        } catch (DatoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    private void buscarPorNombre() {
        try {
            String nombre = vista.pedirNombreEmpleado();
            Empleado e = modelo.buscarPorNombre(nombre);
            if (e != null) vista.mostrarEmpleado(e);
            else vista.mostrarMensaje("No se encontró ningún empleado con ese nombre.");
        } catch (DatoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    private void eliminarPorNumero() {
        try {
            int numero = vista.pedirNumeroEmpleado();
            boolean eliminado = modelo.eliminarPorNumero(numero);
            vista.mostrarMensaje(eliminado ?
                    "Empleado eliminado correctamente." :
                    "No se encontró ningún empleado con ese número.");
        } catch (DatoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }
    private void eliminarPorNombre() {
        try {
            String nombre = vista.pedirNombreEmpleado();
            boolean eliminado = modelo.eliminarPorNombre(nombre);
            vista.mostrarMensaje(eliminado ?
                    "Empleado eliminado correctamente." :
                    "No se encontró ningún empleado con ese nombre.");
        } catch (DatoInvalidoException e) {
            vista.mostrarError(e.getMessage());
        }
    }
}