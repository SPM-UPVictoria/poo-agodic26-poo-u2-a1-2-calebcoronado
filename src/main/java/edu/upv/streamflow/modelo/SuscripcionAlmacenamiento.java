package edu.upv.streamflow.modelo;

public class SuscripcionAlmacenamiento extends Suscripcion {
    private int gigabytesConsumidos;
    private int limiteGratuitoGB;
    private double costoPorGBExtra;

    public SuscripcionAlmacenamiento(String codigoContrato, double tarifaBase, int limiteGratuitoGB, double costoPorGBExtra) {
        super(codigoContrato, tarifaBase);
        if (limiteGratuitoGB < 0 || costoPorGBExtra < 0) {
            throw new IllegalArgumentException("Los límites o costos de almacenamiento no pueden ser negativos.");
        }
        this.limiteGratuitoGB = limiteGratuitoGB;
        this.costoPorGBExtra = costoPorGBExtra;
        this.gigabytesConsumidos = 0; // Inicia en cero consumos
    }

    public void registrarConsumo(int totalGB) {
        if (totalGB < 0) {
            throw new IllegalArgumentException("El consumo registrado no puede ser negativo.");
        }
        this.gigabytesConsumidos = totalGB;
    }

    @Override
    public double calcularMontoFacturable() {
        if (this.gigabytesConsumidos > this.limiteGratuitoGB) {
            int extraGB = this.gigabytesConsumidos - this.limiteGratuitoGB;
            return this.tarifaBase + (extraGB * this.costoPorGBExtra);
        }
        return this.tarifaBase;
    }
}
