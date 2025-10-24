package Historial;

import Modelo.Resultado;
import java.util.List;

public interface IRepositorioResultados {
    void agregarResultado(Resultado resultado);  // método que usa Ruleta
    List<Resultado> obtenerHistorial();
    void limpiarHistorial();  // opcional, si la usas en otras partes
}
