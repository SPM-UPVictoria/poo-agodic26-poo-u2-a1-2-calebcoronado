package edu.upv.streamflow.interfaces;
import edu.upv.streamflow.modelo.Cliente;
import edu.upv.streamflow.excepciones.TransaccionRechazadaException;

public interface Facturable {
    double calcularMontoFacturable();
    void procesarCobro(Cliente cliente) throws TransaccionRechazadaException;
}
