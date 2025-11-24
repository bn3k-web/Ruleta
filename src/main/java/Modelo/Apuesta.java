package Modelo;

public class Apuesta {
    private final int monto;
    private final TipoApuesta tipo;

    public Apuesta(int monto, TipoApuesta tipo) {
        this.monto = monto;
        this.tipo = tipo;
    }

    public int getMonto() {
        return monto;
    }

    public TipoApuesta getTipo() {
        return tipo;
    }
}
