package Gestores;
import ClasesBase.*;

import java.util.ArrayList;

public class GestorTransacciones {
    private ArrayList<Transaccion> listaTransacciones;
    private int contadorIdTransaccion;
    private GestorCuentas gCuentas;
    private GestorClientes gClientes;
    private GestorTitularidades gTitularidades;

    public GestorTransacciones(GestorCuentas gCuentas, GestorClientes gClientes, GestorTitularidades gTitularidades) {
        this.gCuentas = gCuentas;
        this.gClientes = gClientes;
        this.gTitularidades = gTitularidades;
        this.listaTransacciones = new ArrayList<>();
        this.contadorIdTransaccion = 1;
    }






    // -- METODO DEPOSITAR --
    public void procesarDeposito(Usuario usuarioActual, String numeroCuenta, String monto, String dniCliente, String claveCuenta) {
        // Validar permisos básicos
        if (!(usuarioActual instanceof Cliente) && 
            !(usuarioActual instanceof Empleado) && 
            !(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Usuario no autorizado");
            return;
        }
        System.out.println("\n════════════════════════════════════");
        System.out.println("               DEPOSITO             ");
        System.out.println("════════════════════════════════════");

        // Validar y parsear datos
        int numCuenta = validarStringNumericoInt(numeroCuenta);
        double montoDeposito = validarStringNumericoDouble(monto);
        int dniCl = validarStringNumericoInt(dniCliente);
        int clave = validarStringNumericoInt(claveCuenta);

        if (numCuenta == -1) {
            System.out.println("\nERROR DEPOSITO: Número de cuenta inválido");
            return;
        }
        if (montoDeposito <= 0) {
            System.out.println("\nERROR DEPOSITO: Monto debe ser mayor a 0");
            return;
        }
        if (dniCl == -1) {
            System.out.println("\nERROR DEPOSITO: DNI del cliente inválido");
            return;
        }
        if (clave == -1 || clave < 1000 || clave > 9999) {
            System.out.println("\nERROR DEPOSITO: Clave debe ser de 4 dígitos");
            return;
        }

        // Buscar cuenta y cliente
        Cuenta cuenta = gCuentas.buscarCuenta(numCuenta);
        Cliente cliente = gClientes.buscarCliente(dniCl);

        if (cuenta == null) {
            System.out.println("\nERROR DEPOSITO: Cuenta no encontrada");
            return;
        }
        if (cliente == null) {
            System.out.println("\nERROR DEPOSITO: Cliente no encontrado");
            return;
        }

        // Validar clave de la cuenta
        if (cuenta.getClave() != clave) {
            System.out.println("\nERROR DEPOSITO: Clave incorrecta");
            return;
        }

        // Validar titularidad
        Titularidad titularidad = gTitularidades.buscarTitularidad(cliente, cuenta);
        if (titularidad == null) {
            System.out.println("\nERROR DEPOSITO: El cliente no es titular de esta cuenta");
            return;
        }

        // Validaciones específicas por tipo de usuario
        if (usuarioActual instanceof Cliente) {
            Cliente clienteActual = (Cliente) usuarioActual;
            // Un cliente solo puede depositar en sus propias cuentas
            if (clienteActual.getDni() != cliente.getDni()) {
                System.out.println("\nERROR DEPOSITO: Solo puedes depositar en tus propias cuentas");
                return;
            }
        }

        // Procesar DEPOSITO
        Deposito deposito;
        if (usuarioActual instanceof Empleado) {
            deposito = new Deposito(cliente, (Empleado) usuarioActual, cuenta, montoDeposito, contadorIdTransaccion);
        } else {
            deposito = new Deposito(cliente, cuenta, montoDeposito, contadorIdTransaccion);
        }

        deposito.procesar();
        cliente.añadirTransaccion(deposito);
        cuenta.añadirTransaccion(deposito);
        listaTransacciones.add(deposito);
        contadorIdTransaccion++;

        System.out.println("\nDEPOSITO EXITOSO");
        System.out.println(deposito);
    }







    public void procesarRetiro(Usuario usuarioActual, String numeroCuenta, String monto, String dniCliente, String claveCuenta) {
        // Validar permisos básicos
        if (!(usuarioActual instanceof Cliente) && 
            !(usuarioActual instanceof Empleado) && 
            !(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Usuario no autorizado");
            return;
        }
        System.out.println("\n════════════════════════════════════");
        System.out.println("                RETIRO              ");
        System.out.println("════════════════════════════════════");

        // Validar y parsear datos
        int numCuenta = validarStringNumericoInt(numeroCuenta);
        double montoRetiro = validarStringNumericoDouble(monto);
        int dniCl = validarStringNumericoInt(dniCliente);
        int clave = validarStringNumericoInt(claveCuenta);

        if (numCuenta == -1) {
            System.out.println("\nERROR RETIRO: Número de cuenta inválido");
            return;
        }
        if (montoRetiro <= 0) {
            System.out.println("\nERROR RETIRO: Monto debe ser mayor a 0");
            return;
        }
        if (dniCl == -1) {
            System.out.println("\nERROR RETIRO: DNI del cliente inválido");
            return;
        }
        if (clave == -1 || clave < 1000 || clave > 9999) {
            System.out.println("\nERROR RETIRO: Clave debe ser de 4 dígitos");
            return;
        }

        // Buscar cuenta y cliente
        Cuenta cuenta = gCuentas.buscarCuenta(numCuenta);
        Cliente cliente = gClientes.buscarCliente(dniCl);

        if (cuenta == null) {
            System.out.println("\nERROR RETIRO: Cuenta no encontrada");
            return;
        }
        if (cliente == null) {
            System.out.println("\nERROR RETIRO: Cliente no encontrado");
            return;
        }

        // Validar clave de la cuenta
        if (cuenta.getClave() != clave) {
            System.out.println("\nERROR RETIRO: Clave incorrecta");
            return;
        }

        // Validar titularidad
        Titularidad titularidad = gTitularidades.buscarTitularidad(cliente, cuenta);
        if (titularidad == null) {
            System.out.println("\nERROR RETIRO: El cliente no es titular de esta cuenta");
            return;
        }

        // Validar saldo suficiente
        if (cuenta.getSaldo() < montoRetiro) {
            System.out.println("\nERROR RETIRO: Saldo insuficiente");
            System.out.println("Saldo disponible: S/" + cuenta.getSaldo());
            return;
        }

        // Validaciones específicas por tipo de usuario
        if (usuarioActual instanceof Cliente) {
            Cliente clienteActual = (Cliente) usuarioActual;
            
            // Un cliente solo puede retirar de sus propias cuentas
            if (clienteActual.getDni() != cliente.getDni()) {
                System.out.println("\nERROR RETIRO: Solo puedes retirar de tus propias cuentas");
                return;
            }
        }

        // Procesar retiro
        Retiro retiro;
        if (usuarioActual instanceof Empleado) {
            retiro = new Retiro(cliente, (Empleado) usuarioActual, cuenta, montoRetiro, contadorIdTransaccion, clave);
        } else {
            retiro = new Retiro(cliente, null, cuenta, montoRetiro, contadorIdTransaccion, clave);
        }

        retiro.procesar();
        cliente.añadirTransaccion(retiro);
        cuenta.añadirTransaccion(retiro);
        listaTransacciones.add(retiro);
        contadorIdTransaccion++;

        System.out.println("\n✓ RETIRO EXITOSO");
        System.out.println(retiro);
    }




    // -- METODO TRANSFERENCIA -- (Empleado y Admin tienen acceso)

    
    
    public void procesarTransferencia(Empleado usuarioActual, String numeroCuentaOrigen, String numeroCuentaDestino, String monto, String dniCliente, String claveCuenta) {
        
        // Validar permisos
        if (!(usuarioActual instanceof Empleado) && 
            !(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Usuario no autorizado");
            return;
        }

        System.out.println("\n════════════════════════════════════");
        System.out.println("             TRANSACCION            ");
        System.out.println("════════════════════════════════════");

        // Validar y parsear datos
        int numCuentaOrigen = validarStringNumericoInt(numeroCuentaOrigen);
        int numCuentaDestino = validarStringNumericoInt(numeroCuentaDestino);
        double montoTransf = validarStringNumericoDouble(monto);
        int dniCl = validarStringNumericoInt(dniCliente);
        int clave = validarStringNumericoInt(claveCuenta);

        if (numCuentaOrigen == -1 || numCuentaDestino == -1) {
            System.out.println("\nERROR TRANSFERENCIA: Números de cuenta inválidos");
            return;
        }
        if (numCuentaOrigen == numCuentaDestino) {
            System.out.println("\nERROR TRANSFERENCIA: No puedes transferir a la misma cuenta");
            return;
        }
        if (montoTransf <= 0) {
            System.out.println("\nERROR TRANSFERENCIA: Monto debe ser mayor a 0");
            return;
        }
        if (dniCl == -1) {
            System.out.println("\nERROR TRANSFERENCIA: DNI inválido");
            return;
        }
        if (clave == -1 || clave < 1000 || clave > 9999) {
            System.out.println("\nERROR TRANSFERENCIA: Clave debe ser de 4 dígitos");
            return;
        }

        // Buscar cuentas
        Cuenta cuentaOrigen = gCuentas.buscarCuenta(numCuentaOrigen);
        Cuenta cuentaDestino = gCuentas.buscarCuenta(numCuentaDestino);
        Cliente cliente = gClientes.buscarCliente(dniCl);

        if (cuentaOrigen == null || cuentaDestino == null) {
            System.out.println("\nERROR TRANSFERENCIA: Una o ambas cuentas no existen");
            return;
        }
        if (cliente == null) {
            System.out.println("\nERROR TRANSFERENCIA: Cliente no encontrado");
            return;
        }

        // Validar clave
        if (cuentaOrigen.getClave() != clave) {
            System.out.println("\nERROR TRANSFERENCIA: Clave incorrecta");
            return;
        }

        // Validar titularidad de cuenta origen
        Titularidad titularidadOrigen = gTitularidades.buscarTitularidad(cliente, cuentaOrigen);
        if (titularidadOrigen == null) {
            System.out.println("\nERROR TRANSFERENCIA: No eres titular de la cuenta origen");
            return;
        }

        // Validar saldo
        if (cuentaOrigen.getSaldo() < montoTransf) {
            System.out.println("\nERROR TRANSFERENCIA: Saldo insuficiente");
            System.out.println("Saldo disponible: S/" + cuentaOrigen.getSaldo());
            return;
        }

         Transferencia transferencia = new Transferencia(cliente, usuarioActual, cuentaOrigen, cuentaDestino, montoTransf, contadorIdTransaccion);
        

        transferencia.procesar();
        cliente.añadirTransaccion(transferencia);
        cuentaOrigen.añadirTransaccion(transferencia);
        cuentaDestino.añadirTransaccion(transferencia);
        listaTransacciones.add(transferencia);
        contadorIdTransaccion++;

        System.out.println("\n✓ TRANSFERENCIA EXITOSA");
        System.out.println(transferencia);
        System.out.println("Saldo cuenta origen: S/" + cuentaOrigen.getSaldo());
        System.out.println("Saldo cuenta destino: S/" + cuentaDestino.getSaldo());
    }






    // ========== CONSULTAR MOVIMIENTOS ==========
    
    public void mostrarMovimientosCuenta(Usuario usuarioActual, String numeroCuenta, String dniCliente) {
        int numCuenta = validarStringNumericoInt(numeroCuenta);
        int dniCl = validarStringNumericoInt(dniCliente);

        System.out.println("\n════════════════════════════════════");
        System.out.println("        MOVIMIENTOS DE CUENTA       ");
        System.out.println("════════════════════════════════════");

        if (numCuenta == -1) {
            System.out.println("\nERROR: Número de cuenta inválido");
            return;
        }
        if (dniCl == -1) {
            System.out.println("\nERROR: DNI inválido");
            return;
        }

        Cuenta cuenta = gCuentas.buscarCuenta(numCuenta);
        Cliente cliente = gClientes.buscarCliente(dniCl);

        if (cuenta == null) {
            System.out.println("\nERROR: Cuenta no encontrada");
            return;
        }
        if (cliente == null) {
            System.out.println("\nERROR: Cliente no encontrado");
            return;
        }

        // Validar permisos
        if (usuarioActual instanceof Cliente) {
            Cliente clienteActual = (Cliente) usuarioActual;
            if (clienteActual.getDni() != cliente.getDni()) {
                System.out.println("\nERROR: Solo puedes ver tus propios movimientos");
                return;
            }
        }

        // Validar titularidad
        Titularidad titularidad = gTitularidades.buscarTitularidad(cliente, cuenta);
        if (titularidad == null) {
            System.out.println("\nERROR: El cliente no es titular de esta cuenta");
            return;
        }

        // Mostrar movimientos
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     MOVIMIENTOS DE LA CUENTA " + numCuenta + "     ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("Titular: " + cliente.getNombres());
        System.out.println("Saldo actual: S/" + cuenta.getSaldo());
        System.out.println("\n--- HISTORIAL DE TRANSACCIONES ---");

        if (cuenta.getTransacciones().isEmpty()) {
            System.out.println("No hay transacciones registradas en la cuenta");
        } else {
            for (Transaccion t : cuenta.getTransacciones()) {
                System.out.println("\n" + t);
            }
        }
    }


    public void mostrarMovimientosCliente(Usuario usuarioActual, String dniCliente) {
        int dni = validarStringNumericoInt(dniCliente);

        System.out.println("\n════════════════════════════════════");
        System.out.println("        MOVIMIENTOS DE CLIENTE      ");
        System.out.println("════════════════════════════════════");

        if(dni==-1) {
            System.out.println("\nERROR MOSTRAR MOVIMIENTOS CLIENTE: Dni no valido");
            return;
        }

        Cliente clienteL = gClientes.buscarCliente(dni);

        if (clienteL==null) {
            System.out.println("\nERROR MOSTRAR MOVIMIENTOS CLIENTE: Cliente no existe");
            return;
        }

        if (usuarioActual instanceof Cliente) {
            if (usuarioActual.getDni() != clienteL.getDni()) {
                System.out.println("\nERROR MOSTRAR MOVIEMIENTOS CLIENTE: Dni no correspondiente");
                return;
            }
        }

        listarMovimientosCliente(clienteL);
    }



    public void listarMovimientosCliente(Cliente cliente) {

        ArrayList<Transaccion> clTransacciones = cliente.getTransaccionesCliente();

        if (clTransacciones.isEmpty()) {
            System.out.println("No hay transacciones del cliente registradas en el sistema");
        }
        else {
            for (Transaccion transaccion : clTransacciones) {
                System.out.println("\n" + transaccion);
            }
        }
    }


    // Ver todas las transacciones (solo Admin)
    public void listarTodasTransacciones(Usuario usuarioActual) {
        if (!(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Solo los administradores pueden ver todas las transacciones");
            return;
        }

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         TODAS LAS TRANSACCIONES        ║");
        System.out.println("╚════════════════════════════════════════╝");

        if (listaTransacciones.isEmpty()) {
            System.out.println("No hay transacciones registradas en el sistema");
        } 
        else {
            for (Transaccion transaccion : listaTransacciones) {
                System.out.println("\n" + transaccion);
                System.out.println("-".repeat(50));
            }
            System.out.println("\nTotal de transacciones: " + listaTransacciones.size());
        }
    }

    // ========== MÉTODOS AUXILIARES ==========
    
    private int validarStringNumericoInt(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (Exception e) {
            return -1;
        }
    }

    private double validarStringNumericoDouble(String numero) {
        try {
            double valor = Double.parseDouble(numero);
            return valor > 0 ? valor : -1;
        } catch (Exception e) {
            return -1;
        }
    }

    public ArrayList<Transaccion> getListaTransacciones() {
        return listaTransacciones;
    }

    public int getContadorIdTransaccion() {
        return contadorIdTransaccion;
    }
}