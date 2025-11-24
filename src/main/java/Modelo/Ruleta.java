package Modelo;

import java.util.Random;

/**
 * Clase Ruleta
 * 
 * Representa la lógica del juego de la ruleta.
 * Se encarga de generar el número ganador, evaluar si una apuesta es ganadora
 * y registrar los resultados en el repositorio.
 */
public class Ruleta {
    private final Random random = new Random();
    private final Historial.IRepositorioResultados repositorio;

    public Ruleta(Historial.IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public int girarRuleta() {
        return random.nextInt(37); // 0-36
    }

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        if (tipo == TipoApuesta.NUMERO)
            return false; // Simplificación: solo apuestas simples por ahora
        if (numero == 0)
            return false; // La casa gana con 0 en apuestas simples

        boolean esRojo = esRojo(numero);
        boolean esPar = (numero % 2 == 0);

        return switch (tipo) {
            case ROJO -> esRojo;
            case NEGRO -> !esRojo;
            case PAR -> esPar;
            case IMPAR -> !esPar;
            default -> false;
        };
    }

    public static boolean esRojo(int numero) {
        int[] rojos = { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
        for (int r : rojos) {
            if (r == numero)
                return true;
        }
        return false;
    }

    public void registrarResultado(int numero, int monto, boolean acierto, TipoApuesta tipo) {
        Resultado resultado = new Resultado(numero, monto, acierto, tipo);
        repositorio.agregarResultado(resultado);
    }
}
