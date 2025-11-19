package Gestores;

import java.util.*;

import ClasesBase.Cliente;
import ClasesBase.Cuenta;
import ClasesBase.Titularidad;
import ClasesBase.Usuario;


public class GestorCuentas {
    ArrayList<Cuenta> listaCuentas;

    private GestorTitularidades gTitularidades;
    private GestorClientes gClientes;
    private int contadorNroCuenta = 100;

    public GestorCuentas(GestorTitularidades gTitularidades, GestorClientes gClientes) {
        this.gTitularidades = gTitularidades;
        this.gClientes = gClientes;
        listaCuentas = new ArrayList<>();
    }

    // -- METODO ABRIR CUENTA, CREAR PRIMERA TITULARIDAD --

    public void abrirCuenta(String dni, String clave, String tipo) {

        System.out.println("\n════════════════════════════════════");
        System.out.println("         CREACION DE CUENTA         ");
        System.out.println("════════════════════════════════════");

        int dniCliente = validarStringNumericoInt(dni);
        int claveCuenta = validarStringNumericoInt(clave);
        
        if (dniCliente == -1) {
            System.out.println("\nERROR CREACION DE CUENTA: DNI solo debe tener caracteres numericos");
            return;
        }

        Cliente cliente = gClientes.buscarCliente(dniCliente);

        if (cliente==null) {
            System.out.println("\nERROR CREACION DE CUENTA: Usuario no encontrado ");
            return;
        }
        if (!validarStringNoVacio(tipo)) {
            System.out.println("\nERROR CREACION DE CUENTA: Tipo de cuenta no debe estar vacio ");
            return;
        }
        if (claveCuenta<1000 || claveCuenta>9999) {
            System.out.println("\nERROR CREACION DE CUENTA: CLAVE debe tener solo 4 digitos");
            return;
        }

        Cuenta cuenta = new Cuenta(contadorNroCuenta, tipo, 0, claveCuenta);
        listaCuentas.add(cuenta);

        int nroTitularidad = gTitularidades.getContadorIds();

        Titularidad nTitularidad = new Titularidad("Primario", cliente, cuenta, nroTitularidad);
        gTitularidades.agregarPrimeraTitularidad(nTitularidad);

        contadorNroCuenta++;
        
        System.out.println("\n"+cuenta);
        System.out.println("\n"+nTitularidad);

    }

    // -- ELIMINAR CUENTA --

    public void eliminarCuenta(String dni, String num, String clave) {
        System.out.println("\n════════════════════════════════════");
        System.out.println("        ELIMINACION DE CUENTA       ");
        System.out.println("════════════════════════════════════");

        // Parseando argumentos ingresados 
        int numCuenta = validarStringNumericoInt(num);
        int dniCliente = validarStringNumericoInt(dni);
        int claveCuenta = validarStringNumericoInt(clave);

        if (numCuenta==-1) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de numero de cuenta solo deben de ser numeros"); 
            return;
        }
        if (dniCliente==-1) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de DNI solo deben de ser numeros"); 
            return;
        }
        
        // Validando existencia de cliente y cuenta
        Cliente cliente = gClientes.buscarCliente(dniCliente);
        Cuenta cuenta = buscarCuenta(numCuenta);

        if (cliente==null) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Cliente no registrado en el sistema"); 
            return;
        }
        if (cuenta==null) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Cuenta no registrada en el sistema"); 
            return;
        }

        // Validando titularidad relacionada entre cuenta y cliente
        Titularidad titularidadCl = gTitularidades.buscarTitularidad(cliente, cuenta);
        if (titularidadCl==null) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: NO EXISTE TITULARIDAD ASOCIADA CON ESTA CUENTA"); 
            return;
        }

        // Validando clave para eliminacion
        if (claveCuenta==-1) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de contraseña de cuenta solo son 4 numeros"); 
            return;
        }
        if (cuenta.getClave()!=claveCuenta) {
            System.out.println("\nERROR ELIMINACION DE CUENTA: Clave incorrecta"); 
            return;
        }

        // Se elimina las titularidades del sistema
        for (Titularidad titularidad : gTitularidades.listarTitularesDeCuenta(cuenta)) {
            gTitularidades.eliminarTitularidad(titularidad);    
        }
        
        System.out.println("\n"+cuenta);
        listaCuentas.remove(cuenta);
    }



    // -- MOSTRAR CUENTA --

    public void mostrarCuenta(Usuario usuarioActual, String numeroCuenta) {
        System.out.println("\n════════════════════════════════════");
        System.out.println("           MOSTRAR CUENTA           ");
        System.out.println("════════════════════════════════════");
        
        int numCuenta = validarStringNumericoInt(numeroCuenta);
        Cuenta cuenta = buscarCuenta(numCuenta);
        
        if (cuenta==null) {
            System.out.println("\nERROR MOSTRAR CUENTA: La cuenta ingresada no existe");
            return;
        }

        if (usuarioActual instanceof Cliente) {
            Cliente cliente = (Cliente) usuarioActual;
            Titularidad titularidad = gTitularidades.buscarTitularidad(cliente, cuenta);

            if (titularidad==null) {
                System.out.println("\nERROR MOSTRAR CUENTA: No existe titularidad con esta cuenta");
                return;
            }
            else {
                System.out.println("\n"+cuenta);
            }
        }
        
        else {
            System.out.println("\n"+cuenta);
        }
    }


    // -- MOSTRAR CUENTAS --

    public void mostrarCuentas() {
        System.out.println("\n════════════════════════════════════");
        System.out.println("          CUENTAS BANCARIAS         ");
        System.out.println("════════════════════════════════════");

        if (listaCuentas.isEmpty()) {
            System.out.println("\nNO HAY CUENTAS REGISTRADAS");
        }
        else {
            for (Cuenta cuenta : listaCuentas) {
                System.out.println("\n"+cuenta);
            }
        }
    }

    // -- MOSTRAR CUENTAS DE CLIENTE --

    public void mostrarCuentasCliente(Usuario usuarioActual, String dniCliente) {
        System.out.println("\n════════════════════════════════════");
        System.out.println("          CUENTAS DE CLIENTE        ");
        System.out.println("════════════════════════════════════");

        int dniCl = validarStringNumericoInt(dniCliente);
        Cliente cliente = gClientes.buscarCliente(dniCl);
        
        if (cliente==null) {
            System.out.println("\nERROR MOSTRAR CUENTAS DE CLIENTE: No hay registro de cliente con DNI ingresado");
            return;
        }
        if (usuarioActual instanceof Cliente) {
            if (usuarioActual.getDni() != dniCl) {
                System.out.println("\nERROR MOSTRAR CUENTSA DE CLIENTE: Permisos no concedidos, solo puedes ver tus cuentas");
                return;
            }
        }

        ArrayList<Titularidad> titularidadsCliente = gTitularidades.listarTitularidadesDeCliente(cliente);
        if (titularidadsCliente.isEmpty()) {
            System.out.println("\nEl cliente no tiene cuentas registradas");
        }
        else  {
            for (Titularidad titularidad : gTitularidades.listarTitularidadesDeCliente(cliente)) {
                System.out.println("\n"+titularidad.getCuenta());
            }
        }
    }




    // Metodos para la logica del gestor
    public Cuenta buscarCuenta(int numeroCuenta) {
        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

    public int validarStringNumericoInt(String numero) {
        int numeroParseado;
        try {
            numeroParseado = Integer.parseInt(numero);
            return numeroParseado;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean validarStringNoVacio(String palabra) {
        if (palabra == null || palabra.trim().isEmpty()) return false;
        return true;
    }

}
