import java.util.Scanner;
import ClasesBase.*;
import GestorPrincipal.Banco;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Banco banco = new Banco();
    private static Usuario usuarioActual = null;
    
    public static void main(String[] args) {

        banco.getgClientes().registrarCliente("Josue Camero", "61217911", "18", "jcam@gmail.com", "123");
        banco.getgEmpleados().registrarEmpleado(banco.getgEmpleados().getAdmin(), "Mario", "61217911", "18", "2", "123");
        banco.getgEmpleados().setAdmin(new Admin("admin", 10000000, 18, "admin", "123", -100));

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║    BIENVENIDO AL SISTEMA BANCARIO  ║");
        System.out.println("╚════════════════════════════════════╝");
        
        boolean continuar = true;
        while (continuar) {
            banco.mostrarMenuPrincipal();
            String opcion = scanner.nextLine();
            
            switch (opcion) {
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

    // ========== LOGIN CLIENTE ==========
    private static void loginCliente() {
        System.out.println("\n--- LOGIN CLIENTE ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Cliente("", 0, 0, "", ""), correo, contraseña);
        
        if (usuarioActual != null) {
            System.out.println("\n✓ Login exitoso");
            menuCliente((Cliente) usuarioActual);
        } else {
            System.out.println("\nCredenciales incorrectas");
        }
    }

    // ========== LOGIN EMPLEADO ==========
    private static void loginEmpleado() {
        System.out.println("\n--- LOGIN EMPLEADO ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Empleado("", 0, 0, "", "", -1), correo, contraseña);
        
        if (usuarioActual != null && !(usuarioActual instanceof Admin)) {
            System.out.println("\n---- ✓ Login exitoso ----");
            menuEmpleado((Empleado) usuarioActual);
        } else {
            System.out.println("\n---- Credenciales incorrectas ----");
        }
    }

    // ========== LOGIN ADMIN ==========
    private static void loginAdmin() {
        System.out.println("\n--- LOGIN ADMINISTRADOR ---");
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();

        usuarioActual = banco.autentificarUsuario(new Admin("", 0, 0, "", "", -1) , correo, contraseña);
        
        if (usuarioActual != null) {
            System.out.println("\n✓ Login exitoso");
            menuAdmin((Admin) usuarioActual);
        } else {
            System.out.println("\nCredenciales incorrectas");
        }
    }

    // ========== MENÚ CLIENTE ==========
    private static void menuCliente(Cliente cliente) {
        boolean continuar = true;
        
        while (continuar) {
            cliente.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine().toUpperCase();
            
            switch (opcion) {
                case "1":
                    procesarDeposito(cliente);
                    break;
                case "2":
                    procesarRetiro(cliente);
                    break;
                case "3":
                    consultarTransaccionesCliente(cliente);
                    break;
                case "4":
                    consultarMovimientosCuenta(cliente);
                    break;
                case "5":
                    consultarMisCuentas(cliente);
                    break;
                case "6":
                    consultarCuenta(cliente);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    usuarioActual = null;
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== MENÚ EMPLEADO ==========
    private static void menuEmpleado(Empleado empleado) {
        boolean continuar = true;
        
        while (continuar) {
            empleado.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    registrarCliente();
                    break;
                case "2":
                    eliminarCliente();
                    break;
                case "3":
                    consultarCliente();
                    break;
                case "4":
                    abrirCuenta();
                    break;
                case "5":
                    eliminarCuenta();
                    break;
                case "6":
                    procesarDeposito(empleado);
                    break;
                case "7":
                    procesarRetiro(empleado);
                    break;
                case "8":
                    procesarTransferencia(empleado);
                    break;
                case "9":
                    agregarTitular();
                    break;
                case "10":
                    consultarTransaccionesCliente(empleado);
                    break;
                case "11":
                    consultarMovimientosCuenta(empleado);
                    break;
                case "12":
                    consultarCuenta(empleado);
                    break;
                case "13":
                    consultarMisCuentas(empleado);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    usuarioActual = null;
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== MENÚ ADMIN ==========
    private static void menuAdmin(Admin admin) {
        boolean continuar = true;
        
        while (continuar) {
            admin.mostrarPermisos();
            System.out.print("\nSeleccione una opción: ");
            String opcion = scanner.nextLine();
            
            switch (opcion) {
                case "1":
                    registrarEmpleado(admin);
                    break;
                case "2":
                    eliminarEmpleado(admin);
                    break;
                case "3":
                    registrarCliente();
                    break;
                case "4":
                    eliminarCliente();
                    break;
                case "5":
                    consultarCliente();
                    break;
                case "6":
                    abrirCuenta();
                    break;
                case "7":
                    eliminarCuenta();
                    break;
                case "8":
                    consultarCuenta(admin);
                    break;
                case "9":
                    procesarDeposito(admin);
                    break;
                case "10":
                    procesarRetiro(admin);
                    break;
                case "11":
                    procesarTransferencia(admin);
                    break;
                case "12":
                    agregarTitular();
                    break;
                case "13":
                    verTodasTransacciones(admin);
                    break;
                case "14":
                    consultarMovimientosCuenta(admin);
                    break;
                case "15":
                    consultarTransaccionesCliente(admin);
                    break;
                case "16":
                    banco.getgClientes().listarClientes();
                    break;
                case "17":
                    banco.getgEmpleados().listarEmpleados();
                    break;
                case "18":
                    consultarEmpleado();
                    break;
                case "19":
                    banco.getgCuentas().mostrarCuentas();
                    break;
                case "20":
                    consultarMisCuentas(admin);
                    break;
                case "Q":
                    System.out.println("\n✓ Sesión cerrada");
                    usuarioActual = null;
                    continuar = false;
                    break;
                default:
                    System.out.println("\nOpción inválida");
            }
        }
    }

    // ========== FUNCIONES PARA GESTOR CLIENTES ==========

    private static void registrarCliente() {

        System.out.print("\nNombres y Apellidos: ");
        String nombres = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        banco.getgClientes().registrarCliente(nombres, dni, edad, correo, contraseña);
    }

    private static void consultarCliente() {
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().mostrarCliente(dni);
    }

    private static void eliminarCliente() {
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().eliminarCliente(dni);
    }

    // ========== FUNCIONES PARA EMPLEADOS ==========

    private static void registrarEmpleado(Admin admin) {
        System.out.print("\nNombres y Apellidos: ");
        String nombres = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Edad: ");
        String edad = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String contraseña = scanner.nextLine();
        
        banco.getgEmpleados().registrarEmpleado(admin, nombres, dni, edad, correo, contraseña);
    }

    private static void eliminarEmpleado(Admin admin) {
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().eliminarEmpleado(admin, dni);
    }
    
    private static void consultarEmpleado() {
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().mostrarEmpleado(dni);
    }

    // ========== FUNCIONES PARA CUENTAS ==========

    private static void abrirCuenta() {
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta (4 dígitos): ");
        String clave = scanner.nextLine();
        System.out.print("Tipo de cuenta (Ahorro/Corriente): ");
        String tipo = scanner.nextLine();
        
        banco.getgCuentas().abrirCuenta(dni, clave, tipo);
    }

    private static void eliminarCuenta() {
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Número de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgCuentas().eliminarCuenta(dni, numeroCuenta, clave);
    }
    
    private static void consultarCuenta(Usuario usuario) {
        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();

        banco.getgCuentas().mostrarCuenta(usuario, numeroCuenta);
    }

    private static void consultarMisCuentas(Usuario usuario) {
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            banco.getgCuentas().mostrarCuentasCliente(usuario, String.valueOf(cliente.getDni()));
        }
        else {
            System.out.print("\nDNI del Cliente: ");
            String dni = scanner.nextLine();
            banco.getgCuentas().mostrarCuentasCliente(usuario, dni);
        }
    }

    // ========== FUNCIONES PARA TITULARIDADES ==========

    private static void agregarTitular() {
        System.out.println("\n--- AGREGAR TITULAR ---");
        System.out.print("DNI del nuevo titular: ");
        String dni = scanner.nextLine();
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        banco.getgTitularidades().agregarTitularidad(dni, numeroCuenta);
    }

    // ========== FUNCIONES PARA TRANSACCIONES ==========

    private static void procesarDeposito(Usuario usuario) {

        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a depositar: ");
        String monto = scanner.nextLine();
        System.out.print("DNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarDeposito(usuario, numeroCuenta, monto, dni, clave);
    }

    private static void procesarRetiro(Usuario usuario) {

        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        String monto = scanner.nextLine();
        System.out.print("DNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarRetiro(usuario, numeroCuenta, monto, dni, clave);
    }

    private static void procesarTransferencia(Empleado usuario) {
        
        System.out.print("\nNúmero de Cuenta Origen: ");
        String cuentaOrigen = scanner.nextLine();
        System.out.print("Número de Cuenta Destino: ");
        String cuentaDestino = scanner.nextLine();
        System.out.print("Monto a transferir: ");
        String monto = scanner.nextLine();
        System.out.print("DNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarTransferencia(usuario, cuentaOrigen, cuentaDestino, monto, dni, clave);
    }




    private static void consultarMovimientosCuenta(Usuario usuarioActual) {
        System.out.print("Número de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        if (usuarioActual instanceof Cliente) {
            banco.getgTransacciones().mostrarMovimientosCuenta(usuarioActual, numeroCuenta, String.valueOf(usuarioActual.getDni()));
        }
        else {
            System.out.print("DNI del Cliente: ");
            String dni = scanner.nextLine();
            
            banco.getgTransacciones().mostrarMovimientosCuenta(usuarioActual, numeroCuenta, dni);

        }
    }


    private static void verTodasTransacciones(Admin admin) {
        banco.getgTransacciones().listarTodasTransacciones(admin);
    }



    private static void consultarTransaccionesCliente(Usuario usuarioActual) {
        if (usuarioActual instanceof Cliente) {
            banco.getgTransacciones().mostrarMovimientosCliente(usuarioActual, String.valueOf(usuarioActual.getDni()));
        }
        else {
            System.out.print("\nDNI: ");
            String dni = scanner.nextLine();
            
            banco.getgTransacciones().mostrarMovimientosCliente(usuarioActual, dni);
        }
    }
    
    
}