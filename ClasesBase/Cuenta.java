package ClasesBase;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Cuenta {
    private int numeroCuenta;
    private String tipoCuenta;
    private double saldo;
    private int clave;
    private LocalDateTime fechaApertura;
    private ArrayList<Transaccion> listatransacciones = new ArrayList<>(); 
    
    public Cuenta(int numeroCuenta, String tipoCuenta, double saldo, int clave) {
        setNumeroCuenta(numeroCuenta);
        setTipoCuenta(tipoCuenta);
        setSaldo(saldo);
        setClave(clave);
        this.fechaApertura = LocalDateTime.now();
    }
    //SETTERS
    public void setNumeroCuenta(int numeroCuenta) {this.numeroCuenta = numeroCuenta;}
    public void setSaldo(double saldo) {this.saldo = saldo;}
    public void setTipoCuenta(String tipoCuenta) {this.tipoCuenta = tipoCuenta;}
    public void setClave(int clave) {this.clave = clave;}

    //GETTERS
    public LocalDateTime getFechaApertura() {return fechaApertura;}
    public int getNumeroCuenta() {return numeroCuenta;}
    public double getSaldo() {return saldo;}
    public String getTipoCuenta() {return tipoCuenta;}
    public int getClave() {return clave;}
    public ArrayList<Transaccion> getTransacciones() {return listatransacciones;}

    //AÑADE TRANSACCIONES REALIZADAS
    public void añadirTransaccion(Transaccion transaccion) {
        this.listatransacciones.add(transaccion);
    }

    public String toString() {
        return "\n---Contrato de apertura de Cuenta---"+
                "\nNum. Cuenta: "+numeroCuenta+
                "\nSaldo: "+saldo+
                "\nTipo de Cuenta: "+tipoCuenta+
                "\nFecha de Apertura: "+fechaApertura;
    }
}
