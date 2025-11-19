import java.util.Scanner;
import ClasesBase.*;
import GestorPrincipal.Banco;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Banco banco = new Banco();
    private static Usuario usuarioActual = null;
    
    public static void main(String[] args) {

        banco.getgEmpleados().setAdmin(new Admin("admin", 10000000, 18, "admin", "123", -100));

        banco.getgClientes().registrarCliente("Josue Camero", "61217911", "18", "1@c.com", "123");
        banco.getgClientes().registrarCliente("Luciana V", "61217912", "18", "2@c.com", "123");
        banco.getgClientes().registrarCliente("Jose Ludeña", "61217913", "18", "3@c.com", "123");
        
        banco.getgCuentas().abrirCuenta("61217911", "1111", "Ahorro"); //primera cuenta empieza con 100
        banco.getgTitularidades().agregarTitularidad("61217912", "100");
        banco.getgTitularidades().agregarTitularidad("61217913", "100");

        banco.getgCuentas().abrirCuenta("61217912", "1111", "Ahorro"); //segunda cuenta empieza con 101

        banco.getgEmpleados().registrarEmpleado(banco.getgEmpleados().getAdmin(), "Mario", "61217911", "18", "1@e.com", "123");
        banco.getgEmpleados().registrarEmpleado(banco.getgEmpleados().getAdmin(), "Manuel", "61217912", "18", "2@e.com", "123");

        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║    BIENVENIDO AL SISTEMA BANCARIO  ║");
        System.out.println("╚════════════════════════════════════╝");
        
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
            String opcion = scanner.nextLine();
            
            switch (opcion.toUpperCase()) {
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
            
            switch (opcion.toUpperCase()) {
                case "1":
                    registrarCliente();
                    break;
                case "2":
                    consultarTransaccionesCliente(empleado);
                    break;
                case "3":
                    consultarCliente();
                    break;
                case "4":
                    eliminarCliente();
                    break;

                case "5":
                    abrirCuenta();
                    break;
                case "6":
                    agregarTitular();
                    break;
                case "7":
                    consultarMovimientosCuenta(empleado);
                    break;
                case "8":
                    mostrarTitularesDeCuenta();
                    break;
                case "9":
                    consultarMisCuentas(empleado);
                    break;
                case "10":
                    consultarCuenta(empleado);
                    break;
                case "11":
                    eliminarCuenta();
                    break;

                case "12":
                    procesarDeposito(empleado);
                    break;
                case "13":
                    procesarRetiro(empleado);
                    break;
                case "14":
                    procesarTransferencia(empleado);
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
            
            switch (opcion.toUpperCase()) {
                case "1":
                    registrarCliente();
                    break;
                case "2":
                    consultarTransaccionesCliente(admin);
                    break;
                case "3":
                    consultarCliente();
                    break;
                case "4":
                    banco.getgClientes().listarClientes();
                    break;
                case "5":
                    eliminarCliente();
                    break;


                case "6":
                    registrarEmpleado(admin);
                    break;
                case "7":
                    consultarEmpleado();
                    break;
                case "8":
                    banco.getgEmpleados().listarEmpleados();
                    break;
                case "9":
                    eliminarEmpleado(admin);
                    break;
                
                
                
                case "10":
                    abrirCuenta();
                    break;
                case "11":
                    agregarTitular();
                    break;
                case "12":
                    consultarMovimientosCuenta(admin);
                    break;
                case "13":
                    mostrarTitularesDeCuenta();
                    break;
                case "14":
                    consultarMisCuentas(admin);
                    break;
                case "15":
                    banco.getgCuentas().mostrarCuentas();
                    break;
                case "16":
                    consultarCuenta(admin);
                    break;
                case "17":
                    eliminarCuenta();
                    break;
                
                case "18":
                    procesarDeposito(admin);
                    break;
                case "19":
                    procesarRetiro(admin);
                    break;
                case "20":
                    procesarTransferencia(admin);
                    break;
                
                case "21":
                    verTodasTransacciones(admin);
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
        System.out.print("\n--- INGRESAR DATOS REGISTRAR CLIENTE ---");
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
        System.out.print("\n--- INGRESAR DATOS CONSULTAR CLIENTE ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().mostrarCliente(dni);
    }

    private static void eliminarCliente() {
        System.out.print("\n--- INGRESAR DATOS ELIMINAR CLIENTE ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().eliminarCliente(dni);
    }

    // ========== FUNCIONES PARA EMPLEADOS ==========

    private static void registrarEmpleado(Admin admin) {
        System.out.print("\n--- INGRESAR DATOS REGISTRAR EMPLEADO ---");
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
        System.out.print("\n--- INGRESAR DATOS ELIMINAR EMPLEADO ---");
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().eliminarEmpleado(admin, dni);
    }
    
    private static void consultarEmpleado() {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR EMPLEADO ---");
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().mostrarEmpleado(dni);
    }

    // ========== FUNCIONES PARA CUENTAS ==========

    private static void abrirCuenta() {
        System.out.print("\n--- INGRESAR DATOS ABRIR CUENTA ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta (4 dígitos): ");
        String clave = scanner.nextLine();
        System.out.print("Tipo de cuenta (Ahorro/Corriente): ");
        String tipo = scanner.nextLine();
        
        banco.getgCuentas().abrirCuenta(dni, clave, tipo);
    }

    private static void eliminarCuenta() {
        System.out.print("\n--- INGRESAR DATOS ELIMINAR CUENTA ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Número de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgCuentas().eliminarCuenta(dni, numeroCuenta, clave);
    }
    
    private static void consultarCuenta(Usuario usuario) {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR CUENTA ---");
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
            System.out.print("\n--- INGRESAR DATOS CONSULTAR CUENTAS DE CLIENTE ---");
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
    
    private static void mostrarTitularesDeCuenta() {
        System.out.println("\n--- MOSTRAR TITULARES DE CUENTA ---");
        System.out.print("Numero de cuenta: ");
        String cuenta = scanner.nextLine();

        banco.getgTitularidades().mostrarTitularesDeCuenta(cuenta);
    }

    // ========== FUNCIONES PARA TRANSACCIONES ==========

    private static void procesarDeposito(Usuario usuario) {
        String numeroCuenta, monto, dni, clave;
        System.out.print("\n--- INGRESAR DATOS DEPOSITO ---");
        System.out.print("\nNúmero de Cuenta: ");
        numeroCuenta = scanner.nextLine();
        System.out.print("Monto a depositar: ");
        monto = scanner.nextLine();
        
        if (usuario instanceof Cliente) {
            dni = String.valueOf(usuario.getDni());
        }
        else {
            System.out.print("DNI del Cliente que Depositara: ");
            dni = scanner.nextLine();
        }

        System.out.print("Clave de la cuenta: ");
        clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarDeposito(usuario, numeroCuenta, monto, dni, clave);
    }

    private static void procesarRetiro(Usuario usuario) {
        String numeroCuenta, monto, dni, clave;
        System.out.print("\n--- INGRESAR DATOS RETIRO ---");
        System.out.print("\nNúmero de Cuenta: ");
        numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        monto = scanner.nextLine();
        if (usuario instanceof Cliente) {
            dni = String.valueOf(usuario.getDni());
        }
        else {
            System.out.print("DNI del Cliente que Retirara: ");
            dni = scanner.nextLine();
        }
        System.out.print("Clave de la cuenta: ");
        clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarRetiro(usuario, numeroCuenta, monto, dni, clave);
    }

    private static void procesarTransferencia(Empleado usuario) {
        System.out.print("\n--- INGRESAR DATOS TRANSFERENCIA ---");
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
        System.out.print("\n--- INGRESAR DATOS CONSULTAR MOVIMIENTOS DE CUENTA ---");
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
            System.out.print("\n--- INGRESAR DATOS CONSULTAR TRANSACCIONES CLIENTE ---");
            System.out.print("\nDNI: ");
            String dni = scanner.nextLine();
            
            banco.getgTransacciones().mostrarMovimientosCliente(usuarioActual, dni);
        }
    }
    
    
}