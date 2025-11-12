package GestorPrincipal;
import Gestores.*;
import ClasesBase.*;

public class Banco {

    private GestorClientes gClientes;
    private GestorEmpleados gEmpleados;
    private GestorCuentas gCuentas;
    private GestorTitularidades gTitularidades;
    private GestorTransacciones gTransacciones;
    
    public Banco() {
        this.gClientes = new GestorClientes();
        this.gEmpleados = new GestorEmpleados();
        this.gTitularidades = new GestorTitularidades(); 
        this.gCuentas = new GestorCuentas(gTitularidades, gClientes);
        this.gTitularidades.setGestorCuentas(gCuentas);
        this.gTitularidades.setGestorClientes(gClientes);
        this.gTransacciones = new GestorTransacciones(gCuentas, gClientes, gTitularidades);
    }

    public GestorClientes getgClientes() {
        return gClientes;
    }
    public GestorCuentas getgCuentas() {
        return gCuentas;
    }
    public GestorEmpleados getgEmpleados() {
        return gEmpleados;
    }
    public GestorTitularidades getgTitularidades() {
        return gTitularidades;
    }
    public GestorTransacciones getgTransacciones() {
        return gTransacciones;
    }

    public Usuario autentificarUsuario(Usuario tipoUsuario, String correo, String contraseña) {
        if (tipoUsuario instanceof Cliente) {
            
            Cliente cliente = getgClientes().buscarClienteCorreo(correo);
            if (cliente==null) {
                return null;
            }
            if (!cliente.getContraseña().equals(contraseña)) {
                return null;
            }

            return cliente;
        }

        else if (tipoUsuario instanceof Admin) {
            
            Admin adminB = getgEmpleados().getAdmin();
            
            if (!adminB.getCorreo().equals(correo)) {
                return null;
            }
            
            if (!adminB.getContraseña().equals(contraseña)) {
                return null;
            }

            return adminB;
        }
        
        else if (tipoUsuario instanceof Empleado) {
            Empleado empleado = (Empleado) getgEmpleados().buscarEmpleadoPorCorreo(correo);
            if (empleado==null) {
                return null;
            }
            if (!empleado.getContraseña().equals(contraseña)) {
                return null;
            }

            return empleado;
        }
        
        else {
            return null;
        }

    }

    public void mostrarMenuPrincipal() {
        System.out.println("\n════════════════════════════════════");
        System.out.println("        MENÚ PRINCIPAL");
        System.out.println("════════════════════════════════════");
        System.out.println("1. Ingresar como Cliente");
        System.out.println("2. Ingresar como Empleado");
        System.out.println("3. Ingresar como Administrador");
        System.out.println("Q. Salir");
        System.out.print("\nSeleccione una opción: ");
    }
}

