package Gestores;

import java.util.ArrayList;
import java.util.regex.Pattern;

import ClasesBase.Admin;
import ClasesBase.Empleado;
import ClasesBase.Usuario;

public class GestorEmpleados {

    private ArrayList<Empleado> listaEmpleados;
    private Admin adminBando;
    private int contadorIds;
    
    public GestorEmpleados() {
        this.listaEmpleados = new ArrayList<>();
        contadorIds=1;
    }

    public Admin getAdmin() {
        return adminBando;
    }
    public void setAdmin(Admin adminBando) {
        this.adminBando = adminBando;
    }


    // Registrar nuevo empleado (solo Admin puede hacerlo)
    public void registrarEmpleado(Empleado usuarioActual, String nombres, String dni, String edad, String correo, String contraseña) {
        // Validar permisos
        if (!(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Solo los administradores pueden registrar empleados");
            return;
        }
        
        System.out.println("\n════════════════════════════════════");
        System.out.println("        REGISTRO DE EMPLEADO        ");
        System.out.println("════════════════════════════════════");

        // Validaciones
        if (!validarStringNoVacio(nombres)) {
            System.out.println("\nERROR: Nombres no deben estar vacíos");
            return;
        }

        int dniEmpleado = validarStringNumericoInt(dni);
        int edadEmpleado = validarStringNumericoInt(edad);

        if (dniEmpleado == -1 || edadEmpleado == -1) {
            System.out.println("\nERROR: DNI y edad deben ser numéricos");
            return;
        }

        if (dniEmpleado < 10000000 || dniEmpleado > 99999999) {
            System.out.println("\nERROR: DNI debe tener 8 dígitos");
            return;
        }

        if (buscarEmpleadoPorDni(dniEmpleado) != null) {
            System.out.println("\nERROR: DNI ya registrado en el sistema");
            return;
        }

        if (edadEmpleado < 18 || edadEmpleado > 120) {
            System.out.println("\nERROR: Edad no válida");
            return;
        }

        
        if (buscarEmpleadoPorCorreo(correo) != null) {
            System.out.println("\nERROR: Correo ya registrado");        //VALIDAR CORREO
            return;
        }

        if (!validarStringNoVacio(contraseña)) {
            System.out.println("\nERROR: Contraseña no válida");
            return;
        }

        Empleado nuevoEmpleado = new Empleado(nombres, dniEmpleado, edadEmpleado, correo, contraseña, contadorIds);
        listaEmpleados.add(nuevoEmpleado);
        System.out.println(nuevoEmpleado);
    }




    // -- ELIMINAR EMPLEADO -- 
    
    public void eliminarEmpleado(Usuario usuarioActual, String dni) {
        if (!(usuarioActual instanceof Admin)) {
            System.out.println("\nERROR: Solo los administradores pueden eliminar empleados");
            return;
        }
        System.out.println("\n════════════════════════════════════");
        System.out.println("          ELIMINAR EMPLEADO         ");
        System.out.println("════════════════════════════════════");

        int dniEmpleado = validarStringNumericoInt(dni);
        if (dniEmpleado == -1) {
            System.out.println("\nERROR: DNI debe ser numérico");
            return;
        }

        Empleado empleado = buscarEmpleadoPorDni(dniEmpleado);
        
        if (empleado == null) {
            System.out.println("\nERROR: Empleado no encontrado");
            return;
        }

        listaEmpleados.remove(empleado);
        System.out.println("\n✓ Empleado eliminado: " + empleado.getNombres());
    }


    // -- LISTAR EMPLEADOS --
    public void listarEmpleados() {
        System.out.println("\n════════════════════════════════════");
        System.out.println("          LISTA DE EMPLEADOS        ");
        System.out.println("════════════════════════════════════");
        
        if (listaEmpleados.isEmpty()) {
            System.out.println("No hay empleados registrados");
        }
        else {
            for (Empleado empleado : listaEmpleados) {
            System.out.println("\n"+empleado);
            }
        }
    }
    
    // -- MOSTRAR EMPLEADO --
    public void mostrarEmpleado(String dniEmpleado) {
        int dniEmpl = validarStringNumericoInt(dniEmpleado);
        Empleado empleado = buscarEmpleadoPorDni(dniEmpl);
        
        if (empleado == null) {
            System.out.println("\nERROR MOSTRAR EMPLEADO: No hay registro de empleado con ese Dni");
            return;
        }
        System.out.println(empleado);
    }
    
    private int validarStringNumericoInt(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (Exception e) {
            return -1;
        }
    }
    
    private boolean validarStringNoVacio(String palabra) {
        return palabra != null && !palabra.trim().isEmpty();
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

    
    // Buscar empleado por correo
    public Usuario buscarEmpleadoPorCorreo(String correo) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getCorreo().equals(correo)) {
                return empleado;
            }
        }
        return null;
    }

    // Buscar empleado por DNI
    public Empleado buscarEmpleadoPorDni(int dni) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getDni() == dni) {
                return empleado;
            }
        }
        return null;
    }
    
    // Buscar empleado por ID
    public Empleado buscarEmpleadoPorID(int id) {
        for (Empleado empleado : listaEmpleados) {
            if (empleado.getIdEmpleado() == id) {
                return empleado;
            }
        }
        return null;
    }
}
