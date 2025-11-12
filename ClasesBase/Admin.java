package ClasesBase;

public class Admin extends Empleado {

    public Admin(String nombres, int dni, int edad, String correo, String contraseña, int idAdmin) {
        super(nombres, dni, edad, correo, contraseña, idAdmin);
    }

    @Override
    public void mostrarPermisos() {
            System.out.println("\n════════════════════════════════════");
            System.out.println("    MENÚ ADMINISTRADOR: " + getNombres());
            System.out.println("════════════════════════════════════");
            System.out.println("1. Registrar Empleado");
            System.out.println("2. Eliminar Empleado");
            System.out.println("3. Registrar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Consultar Cliente");
            System.out.println("6. Abrir Cuenta");
            System.out.println("7. Eliminar Cuenta");
            System.out.println("8. Consultar Cuenta");
            System.out.println("9. Procesar Depósito");
            System.out.println("10. Procesar Retiro");
            System.out.println("11. Procesar Transferencia");
            System.out.println("12. Agregar Titular a Cuenta");
            System.out.println("13. Ver Todas las Transacciones");
            System.out.println("14. Consultar Movimientos de Cuenta");
            System.out.println("15. Consultar Transacciones de Cliente");
            System.out.println("16. Consultar Lista de Clientes");
            System.out.println("17. Consultar Lista de Empleados");
            System.out.println("18. Mostrar Empleado");
            System.out.println("19. Consultar Lista de Cuentas");
            System.out.println("20. Consultar Cuentas de cliente");
            System.out.println("Q. Cerrar Sesión");
    }
}
