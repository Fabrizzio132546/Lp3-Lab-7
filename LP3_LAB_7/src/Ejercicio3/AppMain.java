/*************************************************************************************
ARCHIVO	: AppMain.java
FECHA	: 15/10/2025
*************************************************************************************/
package Ejercicio3;

public class AppMain {
    public static void main(String[] args) {
        EmpleadoModelo modelo = new EmpleadoModelo();
        EmpleadoVista vista = new EmpleadoVista();
        EmpleadoControlador controlador = new EmpleadoControlador(modelo, vista);

        controlador.iniciar(); 
    }
}