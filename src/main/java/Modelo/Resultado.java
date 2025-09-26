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
    public int getNumeroGanador() { return numeroGanador; }
    public int getMontoApostado() { return montoApostado; }
    public boolean isAcierto() { return acierto; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
        return "N: " + numeroGanador + ", Apuesta: " + tipoApuesta + ", Monto: $" + montoApostado + (acierto ? " (GANÓ)" : " (PERDIÓ)");
    }
}
