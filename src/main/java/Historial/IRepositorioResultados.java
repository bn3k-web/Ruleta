package Historial;

import Modelo.Resultado;
import java.util.List;

public interface IRepositorioResultados {
    void agregarResultado(Resultado resultado);
    List<Resultado> obtenerHistorial();
    void limpiarHistorial();
}
