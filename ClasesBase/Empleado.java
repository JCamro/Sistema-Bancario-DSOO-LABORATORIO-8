package ClasesBase;

public class Empleado extends Usuario {
    protected int idEmpleado;
    public Empleado(String nombres, int dni, int edad, String correo, String contraseña, int idEmpleado) {
        super(nombres, dni, edad, correo, contraseña);
        this.idEmpleado = idEmpleado;
    }
    
    public int getIdEmpleado() {return idEmpleado;}

    @Override
    public void mostrarPermisos() {
        System.out.println("\n════════════════════════════════════");
            System.out.println("    MENÚ EMPLEADO: " + getNombres());
            System.out.println("════════════════════════════════════");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Eliminar Cliente");
            System.out.println("3. Consultar Cliente");
            System.out.println("4. Abrir Cuenta");
            System.out.println("5. Eliminar Cuenta");
            System.out.println("6. Procesar Depósito");
            System.out.println("7. Procesar Retiro");
            System.out.println("8. Procesar Transferencia");
            System.out.println("9. Agregar Titular a Cuenta");
            System.out.println("10. Consultar Movimientos Cliente");
            System.out.println("11. Consultar Movimientos de Cuenta");
            System.out.println("12. Consultar Cuenta");
            System.out.println("Q. Cerrar Sesión");
        
    }

    @Override
    public String toString() {
        return "\n---Informacion Empleado---"+
                "\nNombres y Apellidos: "+nombres+
                "\nID: "+idEmpleado+
                "\nDNI: "+dni+
                "\nEdad: "+edad+
                "\nCorreo: "+correo;
    }
}
