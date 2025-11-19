package InterfazUsuario;

import java.util.Scanner;
import ClasesBase.*;


public class NavegacionMenu {
    private Scanner scanner;
    private MenuEntradas MenuEntradas;
    
    public NavegacionMenu(Scanner scanner, MenuEntradas MenuEntradas) {
        this.scanner = scanner;
        this.MenuEntradas = MenuEntradas;
    }
    
    // ========== MENÚ CLIENTE ==========
    
    public void mostrarMenuCliente(Cliente cliente) {
        boolean continuar = true;
        
        while (continuar) {
            cliente.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion.toUpperCase()) {
                case "1":
                    MenuEntradas.procesarDeposito(cliente);
                    break;
                case "2":
                    MenuEntradas.procesarRetiro(cliente);
                    break;
                case "3":
                    MenuEntradas.consultarTransaccionesCliente(cliente);
                    break;
                case "4":
                    MenuEntradas.consultarMovimientosCuenta(cliente);
                    break;
                case "5":
                    MenuEntradas.consultarMisCuentas(cliente);
                    break;
                case "6":
                    MenuEntradas.consultarCuenta(cliente);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== MENÚ EMPLEADO ==========
    
    public void mostrarMenuEmpleado(Empleado empleado) {
        boolean continuar = true;
        
        while (continuar) {
            empleado.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion.toUpperCase()) {
                case "1":
                    MenuEntradas.registrarCliente();
                    break;
                case "2":
                    MenuEntradas.consultarTransaccionesCliente(empleado);
                    break;
                case "3":
                    MenuEntradas.consultarCliente();
                    break;
                case "4":
                    MenuEntradas.eliminarCliente();
                    break;
                case "5":
                    MenuEntradas.abrirCuenta();
                    break;
                case "6":
                    MenuEntradas.agregarTitular();
                    break;
                case "7":
                    MenuEntradas.consultarMovimientosCuenta(empleado);
                    break;
                case "8":
                    MenuEntradas.mostrarTitularesDeCuenta();
                    break;
                case "9":
                    MenuEntradas.consultarMisCuentas(empleado);
                    break;
                case "10":
                    MenuEntradas.consultarCuenta(empleado);
                    break;
                case "11":
                    MenuEntradas.eliminarCuenta();
                    break;
                case "12":
                    MenuEntradas.procesarDeposito(empleado);
                    break;
                case "13":
                    MenuEntradas.procesarRetiro(empleado);
                    break;
                case "14":
                    MenuEntradas.procesarTransferencia(empleado);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== MENÚ ADMIN ==========
    
    public void mostrarMenuAdmin(Admin admin) {
        boolean continuar = true;
        
        while (continuar) {
            admin.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion.toUpperCase()) {
                case "1":
                    MenuEntradas.registrarCliente();
                    break;
                case "2":
                    MenuEntradas.consultarTransaccionesCliente(admin);
                    break;
                case "3":
                    MenuEntradas.consultarCliente();
                    break;
                case "4":
                    MenuEntradas.listarClientes();
                    break;
                case "5":
                    MenuEntradas.eliminarCliente();
                    break;
                case "6":
                    MenuEntradas.registrarEmpleado(admin);
                    break;
                case "7":
                    MenuEntradas.consultarEmpleado();
                    break;
                case "8":
                    MenuEntradas.listarEmpleados();
                    break;
                case "9":
                    MenuEntradas.eliminarEmpleado(admin);
                    break;
                case "10":
                    MenuEntradas.abrirCuenta();
                    break;
                case "11":
                    MenuEntradas.agregarTitular();
                    break;
                case "12":
                    MenuEntradas.consultarMovimientosCuenta(admin);
                    break;
                case "13":
                    MenuEntradas.mostrarTitularesDeCuenta();
                    break;
                case "14":
                    MenuEntradas.consultarMisCuentas(admin);
                    break;
                case "15":
                    MenuEntradas.listarCuentasBanco();
                    break;
                case "16":
                    MenuEntradas.consultarCuenta(admin);
                    break;
                case "17":
                    MenuEntradas.eliminarCuenta();
                    break;
                case "18":
                    MenuEntradas.procesarDeposito(admin);
                    break;
                case "19":
                    MenuEntradas.procesarRetiro(admin);
                    break;
                case "20":
                    MenuEntradas.procesarTransferencia(admin);
                    break;
                case "21":
                    MenuEntradas.verTodasTransacciones(admin);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }
}
