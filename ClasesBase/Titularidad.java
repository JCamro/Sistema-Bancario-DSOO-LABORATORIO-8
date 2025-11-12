package ClasesBase;

import java.time.LocalDateTime;

public class Titularidad {
    private int idTitularidad;
    private String tipo;
    private Cliente cliente;
    private Cuenta cuenta;
    private LocalDateTime fechaCreacion;

    public Titularidad(String tipo, Cliente cliente, Cuenta cuenta, int idTitularidad) {
        this.tipo = tipo;
        this.cliente = cliente;
        this.cuenta = cuenta;
        this.fechaCreacion = LocalDateTime.now();
        this.idTitularidad = idTitularidad;
    }

    // --- Getters y Setters ---
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public int getIdTitularidad() {
        return idTitularidad;
    }
    public void setIdTitularidad(int idTitularidad) {
        this.idTitularidad = idTitularidad;
    }

    
    public String cambiarTipo(String nuevoTipo) {
        this.tipo = nuevoTipo;
        return this.tipo;
    }

    public String toString() {
        return "\n---Contrato de Titularidad---"+
                "\nFecha: "+fechaCreacion+
                "\nCliente: "+cliente.getDni()+
                "\nTipo de Titular: "+tipo+
                "\nCuenta: "+cuenta.getNumeroCuenta()+"\tTipo: "+cuenta.getTipoCuenta();
    }
}
