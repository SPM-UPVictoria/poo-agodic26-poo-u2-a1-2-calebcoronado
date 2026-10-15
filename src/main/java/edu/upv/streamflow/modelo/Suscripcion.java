package edu.upv.streamflow.modelo;

import edu.upv.streamflow.interfaces.Facturable;
import edu.upv.streamflow.interfaces.Cancelable;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;
import edu.upv.streamflow.excepciones.SuscripcionInactivaException;

public abstract class Suscripcion implements Facturable, Cancelable {
    protected String codigoContrato;
    protected double tarifaBase;
    protected boolean activo;

    public Suscripcion(String codigoContrato, double tarifaBase) {
        if (tarifaBase < 0) {
            throw new IllegalArgumentException("La tarifa base no puede ser negativa.");
        }
        this.codigoContrato = codigoContrato;
        this.tarifaBase = tarifaBase;
        this.activo = true; // Por defecto inicia activa
    }

    public String getCodigoContrato() { return codigoContrato; }
    public double getTarifaBase() { return tarifaBase; }

    @Override
    public void cancelarServicio() {
        this.activo = false;
    }

    @Override
    public boolean estaActivo() {
        return this.activo;
    }

    @Override
    public void procesarCobro(Cliente cliente) throws TransaccionRechazadaException {
        if (!this.activo) {
            throw new SuscripcionInactivaException("No se puede cobrar un servicio inactivo.");
        }
        double monto = calcularMontoFacturable();
        cliente.debitarSaldo(monto);
    }

    @Override
    public abstract double calcularMontoFacturable();
}
