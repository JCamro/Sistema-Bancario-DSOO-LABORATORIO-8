package ClasesBase;
import java.util.*;

public class Cliente extends Usuario {
    private ArrayList<Transaccion> transaccionesCliente;


    public Cliente(String nombres, int dni, int edad, String correo, String contraseña) {
        super(nombres, dni, edad, correo, contraseña);
        this.transaccionesCliente = new ArrayList<>();
        
    }

    public void añadirTransaccion(Transaccion transaccion) {
        transaccionesCliente.add(transaccion);
    }
    
    public ArrayList<Transaccion> getTransaccionesCliente() {
        return transaccionesCliente;
    }



    
    @Override
    public void mostrarPermisos() {
        System.out.println("\n════════════════════════════════════");
            System.out.println("    MENÚ CLIENTE: " + getNombres());
            System.out.println("════════════════════════════════════");
            System.out.println("1. Procesar Depósito");
            System.out.println("2. Procesar Retiro");
            System.out.println("3. Ver Todas mis Transacciones");
            System.out.println("4. Consultar Movimientos de Cuenta");
            System.out.println("5. Consultar mis Cuentas");
            System.out.println("6. Consultar Cuenta");
            System.out.println("Q. Cerrar Sesión");
    }

    public String toString() {
        return "\n---Informacion Cliente---"+
                "\nNombres y Apellidos: "+nombres+
                "\nDNI: "+dni+
                "\nEdad: "+edad+
                "\nCorreo: "+correo; 
    }
        
}
