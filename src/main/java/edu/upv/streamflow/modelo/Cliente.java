package edu.upv.streamflow.modelo;

import edu.upv.streamflow.excepciones.TransaccionRechazadaException;

public class Cliente {
    private String idCliente;
    private String nombre;
    private double saldoDisponible;

    public Cliente(String idCliente, String nombre, double saldoInicial) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.saldoDisponible = saldoInicial;
    }

    public String getIdCliente() { return idCliente; }
    public String getNombre() { return nombre; }
    public double getSaldoDisponible() { return saldoDisponible; }

    public void abonarSaldo(double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a abonar debe ser mayor a cero.");
        }
        this.saldoDisponible += monto;
    }

    public void debitarSaldo(double monto) throws TransaccionRechazadaException {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero.");
        }
        if (monto > this.saldoDisponible) {
            throw new TransaccionRechazadaException("Saldo insuficiente para realizar la transacción.");
        }
        this.saldoDisponible -= monto;
    }
}
