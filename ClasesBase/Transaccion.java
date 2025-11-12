package ClasesBase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Transaccion {
    protected double monto;
    protected Cuenta cuenta;
    protected LocalDateTime fecha;
    protected Empleado empleado;
    protected Cliente cliente;
    protected int idTransaccion;

    public Transaccion(Cliente cliente, Empleado empleado, Cuenta cuenta, double monto, int idTransaccion) {
        this.cliente = cliente;
        this.empleado = empleado;
        this.monto = monto;
        this.cuenta = cuenta;
        this.fecha = LocalDateTime.now();
        this.idTransaccion = idTransaccion;
    }

    // --- Getters y Setters ---
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    public int getIdTransaccion() {
        return idTransaccion;
    }
    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaFormateada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return fecha.format(formatter);
    }

    public void procesar() {
        System.out.println("Procesando Transaccion");
    }

    @Override
    public String toString() {
        return "\nTransaccion nro: "+idTransaccion+
               "\nMonto:" + monto + 
               "\nCuenta" + cuenta.getNumeroCuenta() +
               "\nFecha" + getFechaFormateada() +
               (cliente != null ? "\nRemitente: " + cliente.getNombres() : "") +
               (empleado != null ? "\nGestionado por: " + empleado.getNombres() : "");
    }
}
