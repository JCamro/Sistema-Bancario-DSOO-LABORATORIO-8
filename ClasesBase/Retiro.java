package ClasesBase;

public class Retiro extends Transaccion {

    public Retiro(Cliente cliente, Empleado empleado, Cuenta cuenta, double monto, int idTransaccion, int clave) {
        super(cliente, empleado, cuenta, monto, idTransaccion);
    }
    
    public Retiro(Cliente cliente, Cuenta cuenta, double monto, int idTransaccion, int clave) {
        super(cliente, null, cuenta, monto, idTransaccion);
    }

    @Override
    public void procesar() {
        cuenta.setSaldo(cuenta.getSaldo()-monto);
        toString();
    }

    @Override
    public String toString() {
        return "--- RETIRO ---" + super.toString();
    }


    
}
