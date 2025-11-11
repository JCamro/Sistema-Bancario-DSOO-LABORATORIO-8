package Gestores;

import java.util.*;

import ClasesBase.Cliente;
import ClasesBase.Cuenta;
import ClasesBase.Titularidad;


public class GestorCuentas {
    ArrayList<Cuenta> listaCuentas;

    private GestorTitularidades gTitularidades;
    private GestorClientes gClientes;
    private GestorTransacciones gTransacciones;
    private int contadorNroCuenta = 1;

    public GestorCuentas(GestorTitularidades gTitularidades, GestorTransacciones gTransacciones, GestorClientes gClientes) {
        this.gTitularidades = gTitularidades;
        this.gTransacciones = gTransacciones;
        this.gClientes = gClientes;
        listaCuentas = new ArrayList<>();
    }

    // -- METODO ABRIR CUENTA, CREAR PRIMERA TITULARIDAD --
    public void abrirCuenta(String dni, String clave, String tipo) {
        int dniCliente = validarStringNumericoInt(dni);
        int claveCuenta = validarStringNumericoInt(clave);
        
        if (dniCliente == -1) {System.out.println("\nERROR CREACION DE CUENTA: DNI solo debe tener caracteres numericos");return;}

        Cliente cliente = gClientes.buscarCliente(dniCliente);

        if (cliente==null) {System.out.println("\nERROR CREACION DE CUENTA: Usuario no encontrado ");return;}
        
        if (!validarStringNoVacio(tipo)) {System.out.println("\nERROR CREACION DE CUENTA: Tipo de cuenta no debe estar vacio ");return;}

        if (claveCuenta == -1) {System.out.println("\nERROR CREACION DE CUENTA: CLAVE solo debe tener caracteres numericos");return;}

        if (claveCuenta<1000 || claveCuenta>9999) {System.out.println("\nERROR CREACION DE CUENTA: CLAVE debe tener solo 4 digitos");return;}

        Cuenta cuenta = new Cuenta(contadorNroCuenta, tipo, 0, claveCuenta);
        listaCuentas.add(cuenta);

        int nroTitularidad = gTitularidades.getContadorIds();

        Titularidad nTitularidad = new Titularidad("Primario", cliente, cuenta, nroTitularidad);
        gTitularidades.agregarPrimeraTitularidad(nTitularidad);

        contadorNroCuenta++;

        System.out.println(cuenta);
        System.out.println(nTitularidad);

    }

    // -- ELIMINAR CUENTA --

    public void eliminarCuenta(String dni, String num, String clave) {
        // Parseando argumentos ingresados 
        int numCuenta = validarStringNumericoInt(num);
        int dniCliente = validarStringNumericoInt(dni);
        int claveCuenta = validarStringNumericoInt(clave);

        if (numCuenta==-1) {System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de numero de cuenta solo deben de ser numeros"); return;}
        if (dniCliente==-1) {System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de DNI solo deben de ser numeros"); return;}
        
        // Validando existencia de cliente y cuenta
        Cliente cliente = gClientes.buscarCliente(dniCliente);
        Cuenta cuenta = buscarCuenta(numCuenta);

        if (cliente==null) {System.out.println("\nERROR ELIMINACION DE CUENTA: Cliente no registrado en el sistema"); return;}
        if (cuenta==null) {System.out.println("\nERROR ELIMINACION DE CUENTA: Cuenta no registrada en el sistema"); return;}

        // Validando titularidad relacionada entre cuenta y cliente
        Titularidad titularidadCl = gTitularidades.buscarTitularidad(cliente, cuenta);
        if (titularidadCl==null) {System.out.println("\nERROR ELIMINACION DE CUENTA: NO EXISTE TITULARIDAD ASOCIADA CON ESTA CUENTA"); return;}

        // Validando clave para eliminacion
        if (claveCuenta==-1) {System.out.println("\nERROR ELIMINACION DE CUENTA: Formato de contraseña de cuenta solo son 4 numeros"); return;}
        if (cuenta.getClave()!=claveCuenta) {System.out.println("\nERROR ELIMINACION DE CUENTA: Clave incorrecta"); return;}

        // Se elimina las titularidades y la cuenta
        
        for (Titularidad titularidad : gTitularidades.listarTitularidadesCuenta(cuenta)) {
            gTitularidades.eliminarTitularidad(titularidad);    
        }

        System.out.println("\n--- ELIMINADO DE CUENTA --"+cuenta);
        listaCuentas.remove(cuenta);
    }


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
