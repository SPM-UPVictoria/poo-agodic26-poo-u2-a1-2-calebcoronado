package edu.upv.streamflow.modelo;

public class SuscripcionStreamingFamiliar extends SuscripcionStreaming {

    public SuscripcionStreamingFamiliar(String codigoContrato, double tarifaBase, int perfilesAdicionales, double costoPorPerfilAdicional) {
        super(codigoContrato, tarifaBase, perfilesAdicionales, costoPorPerfilAdicional);
    }

    @Override
    public double calcularMontoFacturable() {
        if (this.perfilesAdicionales > 0) {
            // Bonifica 1 perfil extra (le restamos uno al cálculo del cobro)
            return this.tarifaBase + ((this.perfilesAdicionales - 1) * this.costoPorPerfilAdicional);
        }
        return this.tarifaBase;
    }
}
