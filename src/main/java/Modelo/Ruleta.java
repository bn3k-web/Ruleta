package Modelo;

import java.util.Random;
import static Modelo.TipoApuesta.*;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    private static final int[] NUMEROS_ROJOS =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    private final int[] historialNumeros = new int[MAX_HISTORIAL];
    private final int[] historialApuestas = new int[MAX_HISTORIAL];
    private final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;
    private final Random rng = new Random();

    public int girarRuleta() {
        return rng.nextInt(37);
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (numero == 0) {
            return false;
        }
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
    }

    public static boolean esRojo(int n) {
        if (n == 0){
            return false;
        }
        for (int rojo : NUMEROS_ROJOS){
            if (rojo == n){
                return true;
            }
        }
        return false;
    }

    public void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }
    public int getHistorialSize() {
        return historialSize;
    }
    public int[] getHistorialNumeros() {
        return historialNumeros;
    }
    public int[] getHistorialApuestas() {
        return historialApuestas;
    }
    public boolean[] getHistorialAciertos() {
        return historialAciertos;
    }
}