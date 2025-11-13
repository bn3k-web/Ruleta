package Modelo;

import Historial.IRepositorioResultados;
import java.util.Random;
import static Modelo.TipoApuesta.*;



public class Ruleta {
    private final Random rng = new Random();
    private final IRepositorioResultados repositorio;  // 🔹 inyección de dependencia

    public Ruleta(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    // Genera un número aleatorio entre 0 y 36
    public int girarRuleta() {
        return rng.nextInt(37);
    }

    // Evalúa si el número coincide con el tipo de apuesta
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

    // Determina si un número es rojo
    public static boolean esRojo(int n) {
        int[] NUMEROS_ROJOS = {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
        for (int rojo : NUMEROS_ROJOS) {
            if (rojo == n) return true;
        }
        return false;
    }

    public void registrarResultado(int numeroGanador, int montoApostado, boolean acierto, TipoApuesta tipo) {
        Resultado resultado = new Resultado(numeroGanador, montoApostado, acierto, tipo);
        repositorio.agregarResultado(resultado);
    }

    public IRepositorioResultados getRepositorio() {
        return repositorio;
    }
}
