package ClasesBase;
import java.util.*;

public class Cliente extends Usuario {
    private ArrayList<Cuenta> cuentasCliente;

    public Cliente(String nombres, int dni, int edad, String correo, String contraseña) {
        super(nombres, dni, edad, correo, contraseña);
        this.cuentasCliente = new ArrayList<>();
    }

    public void añadirCuenta(Cuenta cuenta) {
        cuentasCliente.add(cuenta);
    }
    
    @Override
    public void mostrarPermisos() {
        System.out.println(
            "\nBIENVENIDO "+nombres+
            "\n1. MOSTRAR CUENTAS"+
            "\n2. DEPOSITAR"+
            "\n3. RETIRAR"+
            "\n4. CONSULTAR SALDO"
        );    
    }

    public String toString() {
        return "\n---Informacion Cliente---"+
                "\nNombres y Apellidos: "+nombres+
                "\nDNI: "+dni+
                "\nEdad: "+edad+
                "\nCorreo: "+correo; 
    }
        
}
