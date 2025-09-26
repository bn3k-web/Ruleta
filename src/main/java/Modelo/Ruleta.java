package Modelo;

public class Ruleta {
    private static final int[] NUMEROS_ROJOS = {
            1, 3, 5, 7, 9, 12, 14, 16, 18,
            19, 21, 23, 25, 27, 30, 32, 34, 36
    };

    public boolean evaluarResultado(int numero, TipoApuesta tipo) {
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> !esRojo(numero);
            case PAR -> numero != 0 && numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
    }

    private boolean esRojo(int numero) {
        for (int rojo : NUMEROS_ROJOS) {
            if (rojo == numero) {
                return true;
            }
        }
        return false;

    }
}
