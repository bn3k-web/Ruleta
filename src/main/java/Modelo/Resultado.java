package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase Resultado
 * 
 * Representa el resultado de una ronda de juego.
 * Almacena la fecha, el número ganador, el monto apostado, si hubo acierto y el
 * tipo de apuesta.
 */
public class Resultado {
    private final String fechaHora;
    private final int numeroGanador;
    private final int montoApostado;
    private final boolean acierto;
    private final TipoApuesta tipoApuesta;

    public Resultado(int numero, int monto, boolean acierto, TipoApuesta tipo) {
        this.fechaHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.numeroGanador = numero;
        this.montoApostado = monto;
        this.acierto = acierto;
        this.tipoApuesta = tipo;
    }

    // Constructor para cargar desde archivo (String)
    public Resultado(String linea) {
        String[] partes = linea.split(",");
        // Intento de detectar formato: si el primero es fecha (contiene -) o numero
        if (partes[0].contains("-")) {
            // Formato nuevo: Fecha, Numero, Monto, Acierto, Tipo
            this.fechaHora = partes[0];
            this.numeroGanador = Integer.parseInt(partes[1]);
            this.montoApostado = Integer.parseInt(partes[2]);
            this.acierto = Boolean.parseBoolean(partes[3]);
            this.tipoApuesta = TipoApuesta.valueOf(partes[4]);
        } else {
            // Formato antiguo/existente: Numero, Monto, Acierto, Tipo, Fecha
            this.numeroGanador = Integer.parseInt(partes[0]);
            this.montoApostado = Integer.parseInt(partes[1]);
            this.acierto = Boolean.parseBoolean(partes[2]);
            this.tipoApuesta = TipoApuesta.valueOf(partes[3]);
            this.fechaHora = partes[4];
        }
    }

    public static Resultado fromCSV(String linea) {
        return new Resultado(linea);
    }

    public String toCSV() {
        return String.join(",",
                fechaHora,
                String.valueOf(numeroGanador),
                String.valueOf(montoApostado),
                String.valueOf(acierto),
                tipoApuesta.name());
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public int getMontoApostado() {
        return montoApostado;
    }

    public boolean isAcierto() {
        return acierto;
    }

    public TipoApuesta getTipoApuesta() {
        return tipoApuesta;
    }
}
