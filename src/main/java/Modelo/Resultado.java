package Modelo;

import java.time.LocalDateTime;

public class Resultado {
    private final int numeroGanador;
    private final int montoApostado;
    private final boolean acierto;
    private final TipoApuesta tipoApuesta;
    private final LocalDateTime fechaHora;

    public Resultado(int numeroGanador, int montoApostado, boolean acierto, TipoApuesta tipoApuesta) {
        this.numeroGanador = numeroGanador;
        this.montoApostado = montoApostado;
        this.acierto = acierto;
        this.tipoApuesta = tipoApuesta;
        this.fechaHora = LocalDateTime.now();
    }

    private Resultado(int numeroGanador, int montoApostado, boolean acierto, TipoApuesta tipoApuesta, LocalDateTime fechaHora) {
        this.numeroGanador = numeroGanador;
        this.montoApostado = montoApostado;
        this.acierto = acierto;
        this.tipoApuesta = tipoApuesta;
        this.fechaHora = fechaHora;
    }

    public int getNumeroGanador() { return numeroGanador; }
    public int getMontoApostado() { return montoApostado; }
    public boolean isAcierto() { return acierto; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    public String toCSV() {
        return numeroGanador + "," +
                montoApostado + "," +
                acierto + "," +
                tipoApuesta.name() + "," +
                fechaHora.toString();
    }

    public static Resultado fromCSV(String linea) {
        String[] partes = linea.split(",");
        int numeroGanador = Integer.parseInt(partes[0]);
        int montoApostado = Integer.parseInt(partes[1]);
        boolean acierto = Boolean.parseBoolean(partes[2]);
        TipoApuesta tipoApuesta = TipoApuesta.valueOf(partes[3]);
        LocalDateTime fechaHora = LocalDateTime.parse(partes[4]);
        return new Resultado(numeroGanador, montoApostado, acierto, tipoApuesta, fechaHora);
    }

    @Override
    public String toString() {
        return "N: " + numeroGanador +
                ", Apuesta: " + tipoApuesta +
                ", Monto: $" + montoApostado +
                (acierto ? " (GANÓ)" : " (PERDIÓ)") +
                " [" + fechaHora + "]";
    }
}
