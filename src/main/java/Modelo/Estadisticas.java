package Modelo;

import Historial.IRepositorioResultados;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Estadisticas {
    private final IRepositorioResultados repositorio;

    private int totalJugadas;
    private int victorias;
    private double porcentajeVictorias;
    private int rachaMaxima;
    private TipoApuesta tipoMasJugado;

    public Estadisticas(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    // Calcula todas las estadísticas en base al historial del repositorio
    public void calcularEstadisticas() {
        List<Resultado> historial = repositorio.obtenerHistorial();

        totalJugadas = calcularTotalJugadas(historial);
        victorias = calcularVictorias(historial);
        porcentajeVictorias = calcularPorcentajeVictorias();
        rachaMaxima = calcularRachaMaxima(historial);
        tipoMasJugado = calcularTipoMasJugado(historial);
    }

    public int calcularTotalJugadas(List<Resultado> historial) {
        return historial.size();
    }

    public int calcularVictorias(List<Resultado> historial) {
        return (int) historial.stream()
                .filter(Resultado::isAcierto)
                .count();
    }

    public double calcularPorcentajeVictorias() {
        if (totalJugadas == 0) return 0.0;
        return (victorias * 100.0) / totalJugadas;
    }

    public int calcularRachaMaxima(List<Resultado> historial) {
        int racha = 0;
        int rachaMax = 0;
        for (Resultado r : historial) {
            if (r.isAcierto()) {
                racha++;
                if (racha > rachaMax) rachaMax = racha;
            } else {
                racha = 0;
            }
        }
        return rachaMax;
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

    // Getters para mostrar resultados
    public int getTotalJugadas() { return totalJugadas; }
    public int getVictorias() { return victorias; }
    public double getPorcentajeVictorias() { return porcentajeVictorias; }
    public int getRachaMaxima() { return rachaMaxima; }
    public TipoApuesta getTipoMasJugado() { return tipoMasJugado; }

    @Override
    public String toString() {
        return "📊 Estadísticas:\n" +
                "- Total jugadas: " + totalJugadas + "\n" +
                "- Victorias: " + victorias + "\n" +
                "- Porcentaje de victorias: " + String.format("%.2f", porcentajeVictorias) + "%\n" +
                "- Racha máxima: " + rachaMaxima + "\n" +
                "- Tipo más jugado: " + (tipoMasJugado != null ? tipoMasJugado : "N/A");
    }
}
