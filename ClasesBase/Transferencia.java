
package ClasesBase;

public class Transferencia extends Transaccion {
    private Cuenta cuentaDestino;

    public Transferencia(Cliente cliente, Empleado empleado, Cuenta cuentaOrigen, Cuenta cuentaDestino, double monto, int idTransaccion) {
        super(cliente, empleado, cuentaOrigen, monto, idTransaccion);
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public void procesar() {

        cuenta.setSaldo(cuenta.getSaldo() - monto);
        
        cuentaDestino.setSaldo(cuentaDestino.getSaldo() + monto);
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    @Override
    public String toString() {
        return "--- TRANSFERENCIA ---" +
               "\nID Transacción: " + idTransaccion +
               "\nMonto: S/" + monto +
               "\nCuenta Origen: " + cuenta.getNumeroCuenta() +
               "\nCuenta Destino: " + cuentaDestino.getNumeroCuenta() +
               "\nFecha: " + getFechaFormateada() +
               (cliente != null ? "\nCliente: " + cliente.getNombres() : "") +
               (empleado != null ? "\nGestionado por: " + empleado.getNombres() : "");
    }
}