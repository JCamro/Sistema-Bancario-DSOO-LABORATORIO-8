package ClasesBase;

public class Deposito extends Transaccion {

    public Deposito(Cliente cliente, Empleado empleado, Cuenta cuenta, double monto, int idTransaccion) {
        super(cliente, empleado, cuenta, monto, idTransaccion);
    }
    
    public Deposito(Cliente cliente, Cuenta cuenta, double monto, int idTransaccion) {
        super(cliente, null, cuenta, monto, idTransaccion);
    }


    @Override
    public void procesar() {
        cuenta.setSaldo(cuenta.getSaldo()+monto);
        toString();
    }

    @Override
    public String toString() {
        return "--- DEPOSITO ---"+super.toString();
    }
}
