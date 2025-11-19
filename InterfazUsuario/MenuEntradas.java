package InterfazUsuario;

import java.util.Scanner;
import ClasesBase.*;
import GestorPrincipal.Banco;

public class MenuEntradas {
    private Scanner scanner;
    private Banco banco;
    
    public MenuEntradas(Scanner scanner, Banco banco) {
        this.scanner = scanner;
        this.banco = banco;
    }
    
    // ========== GESTIÓN DE CLIENTES ==========
    
    public void registrarCliente() {
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

    public void consultarCliente() {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR CLIENTE ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().mostrarCliente(dni);
    }

    public void eliminarCliente() {
        System.out.print("\n--- INGRESAR DATOS ELIMINAR CLIENTE ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        
        banco.getgClientes().eliminarCliente(dni);
    }

    // ========== GESTIÓN DE EMPLEADOS ==========

    public void registrarEmpleado(Admin admin) {
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

    public void eliminarEmpleado(Admin admin) {
        System.out.print("\n--- INGRESAR DATOS ELIMINAR EMPLEADO ---");
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().eliminarEmpleado(admin, dni);
    }
    
    public void consultarEmpleado() {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR EMPLEADO ---");
        System.out.print("\nDNI del Empleado: ");
        String dni = scanner.nextLine();
        
        banco.getgEmpleados().mostrarEmpleado(dni);
    }

    // ========== GESTIÓN DE CUENTAS ==========

    public void abrirCuenta() {
        System.out.print("\n--- INGRESAR DATOS ABRIR CUENTA ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Clave de la cuenta (4 dígitos): ");
        String clave = scanner.nextLine();
        System.out.print("Tipo de cuenta (Ahorro/Corriente): ");
        String tipo = scanner.nextLine();
        
        banco.getgCuentas().abrirCuenta(dni, clave, tipo);
    }

    public void eliminarCuenta() {
        System.out.print("\n--- INGRESAR DATOS ELIMINAR CUENTA ---");
        System.out.print("\nDNI del Cliente: ");
        String dni = scanner.nextLine();
        System.out.print("Número de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgCuentas().eliminarCuenta(dni, numeroCuenta, clave);
    }
    
    public void consultarCuenta(Usuario usuario) {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR CUENTA ---");
        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();

        banco.getgCuentas().mostrarCuenta(usuario, numeroCuenta);
    }

    public void consultarMisCuentas(Usuario usuario) {
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

    // ========== GESTIÓN DE TITULARIDADES ==========

    public void agregarTitular() {
        System.out.println("\n--- AGREGAR TITULAR ---");
        System.out.print("DNI del nuevo titular: ");
        String dni = scanner.nextLine();
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        banco.getgTitularidades().agregarTitularidad(dni, numeroCuenta);
    }
    
    public void mostrarTitularesDeCuenta() {
        System.out.println("\n--- MOSTRAR TITULARES DE CUENTA ---");
        System.out.print("Numero de cuenta: ");
        String cuenta = scanner.nextLine();

        banco.getgTitularidades().mostrarTitularesDeCuenta(cuenta);
    }

    // ========== GESTIÓN DE TRANSACCIONES ==========

    public void procesarDeposito(Usuario usuario) {
        System.out.print("\n--- INGRESAR DATOS DEPOSITO ---");
        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a depositar: ");
        String monto = scanner.nextLine();
        
        String dni;
        if (usuario instanceof Cliente) {
            dni = String.valueOf(usuario.getDni());
        } else {
            System.out.print("DNI del Cliente que Depositara: ");
            dni = scanner.nextLine();
        }

        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarDeposito(usuario, numeroCuenta, monto, dni, clave);
    }

    public void procesarRetiro(Usuario usuario) {
        System.out.print("\n--- INGRESAR DATOS RETIRO ---");
        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        System.out.print("Monto a retirar: ");
        String monto = scanner.nextLine();
        
        String dni;
        if (usuario instanceof Cliente) {
            dni = String.valueOf(usuario.getDni());
        } else {
            System.out.print("DNI del Cliente que Retirara: ");
            dni = scanner.nextLine();
        }
        
        System.out.print("Clave de la cuenta: ");
        String clave = scanner.nextLine();
        
        banco.getgTransacciones().procesarRetiro(usuario, numeroCuenta, monto, dni, clave);
    }

    public void procesarTransferencia(Empleado usuario) {
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

    public void consultarMovimientosCuenta(Usuario usuarioActual) {
        System.out.print("\n--- INGRESAR DATOS CONSULTAR MOVIMIENTOS DE CUENTA ---");
        System.out.print("\nNúmero de Cuenta: ");
        String numeroCuenta = scanner.nextLine();
        
        if (usuarioActual instanceof Cliente) {
            banco.getgTransacciones().mostrarMovimientosCuenta(
                usuarioActual, numeroCuenta, String.valueOf(usuarioActual.getDni())
            );
        } else {
            System.out.print("DNI del Cliente: ");
            String dni = scanner.nextLine();
            banco.getgTransacciones().mostrarMovimientosCuenta(usuarioActual, numeroCuenta, dni);
        }
    }

    public void consultarTransaccionesCliente(Usuario usuarioActual) {
        if (usuarioActual instanceof Cliente) {
            banco.getgTransacciones().mostrarMovimientosCliente(
                usuarioActual, String.valueOf(usuarioActual.getDni())
            );
        } else {
            System.out.print("\n--- INGRESAR DATOS CONSULTAR TRANSACCIONES CLIENTE ---");
            System.out.print("\nDNI: ");
            String dni = scanner.nextLine();
            banco.getgTransacciones().mostrarMovimientosCliente(usuarioActual, dni);
        }
    }

    public void verTodasTransacciones(Admin admin) {
        banco.getgTransacciones().listarTodasTransacciones(admin);
    }

    public void listarClientes() {
        banco.getgClientes().listarClientes();
    }

    public void listarEmpleados() {
        banco.getgEmpleados().listarEmpleados();
    }

    public void listarCuentasBanco(){
        banco.getgCuentas().mostrarCuentas();
    }
}