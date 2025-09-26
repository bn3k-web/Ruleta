package Modelo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estadisticas {
    public int totalJugadas;
    public int victorias;
    public double porcentajeVictorias;
    public int rachaMaxima;
    public TipoApuesta tipoMasJugado;
    public Estadisticas() {}

    public int calcularTotalJugadas(List<Resultado> historial) {
        return historial.size();
    }

    public int calcularVictorias(List<Resultado> historial) {
        return (int) historial.stream()
                .filter(Resultado::isAcierto)
                .count();
    }

    public TipoApuesta calcularTipoMasJugado(List<Resultado> historial) {
        Map<TipoApuesta, Long> conteo = historial.stream()
                .collect(Collectors.groupingBy(
                        Resultado::getTipoApuesta,
                        Collectors.counting()
                ));

        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}