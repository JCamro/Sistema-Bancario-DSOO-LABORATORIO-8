import java.util.Scanner;
import ClasesBase.*;
import GestorPrincipal.Banco;
import InterfazUsuario.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Banco banco = new Banco();
    private static MenuEntradas menuHandler;
    private static NavegacionMenu NavegacionMenu;
    private static Usuario usuarioActual = null;
    
    public static void main(String[] args) {
        // Inicializar handlers
        menuHandler = new MenuEntradas(scanner, banco);
        NavegacionMenu = new NavegacionMenu(scanner, menuHandler);
        
        // Datos de prueba
        inicializarDatosPrueba();
        
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║   BIENVENIDO AL SISTEMA BANCARIO  ║");
        System.out.println("╚═══════════════════════════════════╝");
        
        boolean continuar = true;
        while (continuar) {
            banco.mostrarMenuPrincipal();
            String opcion = scanner.nextLine();
            
            switch (opcion.toUpperCase()) {
                case "1":
                    loginCliente();
                    break;
                case "2":
                    loginEmpleado();
                    break;
                case "3":
                    loginAdmin();
                    break;
                case "Q":
                    System.out.println("\n¡Gracias por usar nuestro sistema bancario!");
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
        scanner.close();
    }

    private static void inicializarDatosPrueba() {
        banco.getgEmpleados().setAdmin(new Admin("admin", 10000000, 18, "admin", "123", -100));
        
        banco.getgClientes().registrarCliente("Josue Camero", "61217911", "18", "1@c.com", "123");
        banco.getgClientes().registrarCliente("Luciana V", "61217912", "18", "2@c.com", "123");
        banco.getgClientes().registrarCliente("Jose Ludeña", "61217913", "18", "3@c.com", "123");
        
        banco.getgCuentas().abrirCuenta("61217911", "1111", "Ahorro");
        banco.getgTitularidades().agregarTitularidad("61217912", "100");
        banco.getgTitularidades().agregarTitularidad("61217913", "100");
        
        banco.getgCuentas().abrirCuenta("61217912", "1111", "Ahorro");
        
        banco.getgEmpleados().registrarEmpleado(banco.getgEmpleados().getAdmin(), "Mario", "61217911", "18", "1@e.com", "123");
        banco.getgEmpleados().registrarEmpleado(banco.getgEmpleados().getAdmin(), "Manuel", "61217912", "18", "2@e.com", "123");
    }

    // ========== MÉTODOS DE LOGIN ==========
    
    private static void loginCliente() {
        System.out.println("\n--- LOGIN CLIENTE ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Cliente("", 0, 0, "", ""), correo, contraseña);
        
        if (usuarioActual != null) {
            System.out.println("\n✓ Login exitoso");
            NavegacionMenu.mostrarMenuCliente((Cliente) usuarioActual);
            usuarioActual = null;
        } else {
            System.out.println("\nCredenciales incorrectas");
        }
    }

    private static void loginEmpleado() {
        System.out.println("\n--- LOGIN EMPLEADO ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Empleado("", 0, 0, "", "", -1), correo, contraseña);
        
        if (usuarioActual != null && !(usuarioActual instanceof Admin)) {
            System.out.println("\n---- ✓ Login exitoso ----");
            NavegacionMenu.mostrarMenuEmpleado((Empleado) usuarioActual);
            usuarioActual = null;
        } else {
            System.out.println("\n---- Credenciales incorrectas ----");
        }
    }

    private static void loginAdmin() {
        System.out.println("\n--- LOGIN ADMINISTRADOR ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Admin("", 0, 0, "", "", -1), correo, contraseña);
        
        if (usuarioActual != null) {
            System.out.println("\n✓ Login exitoso");
            NavegacionMenu.mostrarMenuAdmin((Admin) usuarioActual);
            usuarioActual = null;
        } else {
            System.out.println("\nCredenciales incorrectas");
        }
    }
}