package InterfacesUsuarios;
import ClasesBase.*;
import GestorPrincipal.*;

public class InterfazBanco {
    private Banco banco = new Banco();
    

    public Usuario autentificarUsuario(Usuario tipoUsuario, String correo, String contraseña) {
        if (tipoUsuario instanceof Cliente) {
            
            Cliente cliente = banco.getgClientes().buscarClienteCorreo(correo);
            if (cliente==null) {
                return null;
            }
            if (!cliente.getContraseña().equals(contraseña)) {
                return null;
            }

            return cliente;
        }

        else if (tipoUsuario instanceof Empleado) {
            Empleado empleado = (Empleado) banco.getgEmpleados().buscarEmpleadoPorCorreo(correo);
            if (empleado==null) {
                return null;
            }
            if (!empleado.getContraseña().equals(contraseña)) {
                return null;
            }

            return empleado;
        }
        
        else if (tipoUsuario instanceof Admin) {
            Admin adminB = (Admin) banco.getgEmpleados().getAdmin();
            
            if (!adminB.getCorreo().equals(correo)) {
                return null;
            }
            
            if (!adminB.getContraseña().equals(contraseña)) {
                return null;
            }

            return adminB;
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
