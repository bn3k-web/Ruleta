package Modelo;

import Historial.IRepositorioResultados;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstadisticasTest {

    @Test
    void testEstadisticasCalculanRachaYTipoMasJugado() {
        // Arrange
        IRepositorioResultados repositorioMock = new IRepositorioResultados() {
            @Override
            public void agregarResultado(Resultado resultado) {
            }

            @Override
            public List<Resultado> obtenerHistorial() {
                List<Resultado> historial = new ArrayList<>();
                // Racha de 2 victorias
                historial.add(new Resultado(1, 100, true, TipoApuesta.ROJO));
                historial.add(new Resultado(3, 100, true, TipoApuesta.ROJO));
                // Derrota
                historial.add(new Resultado(2, 100, false, TipoApuesta.NEGRO));
                // Null (debe ser ignorado)
                historial.add(null);
                // Victoria aislada
                historial.add(new Resultado(5, 100, true, TipoApuesta.ROJO));
                return historial;
            }
        };

        Estadisticas estadisticas = new Estadisticas(repositorioMock);

        // Act
        estadisticas.calcularEstadisticas();

        // Assert
        assertEquals(3, estadisticas.getVictorias()); // 2 + 1
        assertEquals(2, estadisticas.getRachaMaxima()); // La primera racha fue de 2
        assertEquals(TipoApuesta.ROJO, estadisticas.getTipoMasJugado()); // 3 ROJO vs 1 NEGRO
    }
}
