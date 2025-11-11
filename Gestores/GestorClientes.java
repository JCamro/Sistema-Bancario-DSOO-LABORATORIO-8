package Gestores;

import java.util.ArrayList;
import java.util.regex.Pattern;

import ClasesBase.Cliente;

public class GestorClientes {
    private ArrayList<Cliente> listaClientes = new ArrayList<>();


    // -- METODO PARA REGISTRAR CLIENTE --
    public void registrarCliente(String nombres, String dni, String edad, String correo, String contraseña) {
        
        if (!validarStringNoVacio(nombres)) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Nombres no deben estar vacios"); return;}
        
        int dniCliente = validarStringNumericoInt(dni);
        int edadCliente = validarStringNumericoInt(edad);

        if (dniCliente==-1 || edadCliente==-1) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Formato de DNI y EDAD tienen que ser numeros"); return;}
        if (dniCliente<10000000 || dniCliente>99999999) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Formato de DNI debe tener 8 numeros"); return;}
        if (buscarCliente(dniCliente)!=null) {System.out.println("\nERROR AL REGISTRAR CLIENTE: DNI ingresado ya esta asociado a un Cliente existente"); return;}
        if (edadCliente < 18 || edadCliente > 120) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Edad no valida, debes ser mayor de edad"); return;}

        if (!validarStringNoVacio(correo)) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Correo ingresado no debe ser vacio"); return;}
        if (!validarFormatoCorreo(correo)) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Formato de Correo no correcto"); return;}
        if (!validarStringNoVacio(contraseña) || validarSinEspaciosVacios(contraseña)) {System.out.println("\nERROR AL REGISTRAR CLIENTE: Formato de contraseña no valido"); return;}

        Cliente cliente = new Cliente(nombres, dniCliente, edadCliente, correo, contraseña);
        System.out.println(cliente);
        listaClientes.add(cliente);
    }

    // -- METODO PARA BUSCAR CLIENTE --
    public void mostrarCliente(String dni) {
        int dniCliente = validarStringNumericoInt(dni);
        if (dniCliente==-1) {System.out.println("\nERROR MOSTRAR CLIENTE: DNI ingresado solo debe contener numeros"); return;}
        
        Cliente cliente = buscarCliente(dniCliente);
        if (cliente == null) {System.out.println("\n -- CLIENTE NO REGISTRADO --"); return;}

        System.out.println(cliente);
    }


    // -- ELIMINAR CLIENTE --
    public void eliminarCliente(String dni) {
        int dniCliente = validarStringNumericoInt(dni);
        if (dniCliente ==-1) {System.out.println("\nERROR ELIMINAR CLIENTE: DNI ingresado solo debe contener numeros"); return;}

        Cliente cliente = buscarCliente(dniCliente);

        if (cliente == null) {System.out.println("\nERROR ELIMINAR CLIENTE: Cliente no registrado"); return;}

        System.out.println("\n-- ELIMINANDO CLIENTE --"+cliente);
        listaClientes.remove(cliente);
    }


    // Metodos del Gestor
    public Cliente buscarCliente(int dni) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDni()==dni) {
                return cliente;
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

    public boolean validarFormatoCorreo(String correo) {
        if (validarSinEspaciosVacios(correo)) {return false;}
        // Regex sencillo y práctico para validar formato de correo: local@domain.tld
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return Pattern.matches(regex, correo);
    }

    public boolean validarSinEspaciosVacios(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == ' ') {
                return false;
            }
        }
        return true;
    }
}
