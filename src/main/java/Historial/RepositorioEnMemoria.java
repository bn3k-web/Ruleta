package Historial;

import Modelo.Resultado;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {
    private final List<Resultado> historial = new ArrayList<>();

    @Override
    public void agregarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        return new ArrayList<>(historial);
    }

    @Override
    public void limpiarHistorial() {
        historial.clear();
    }
}
