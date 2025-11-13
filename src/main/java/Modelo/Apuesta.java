package Modelo;

public class Apuesta {
    private final double monto;
    private final TipoApuesta tipo;
    private final String etiqueta; // Opcional: para identificar la apuesta

    public Apuesta(double monto, TipoApuesta tipo) {
        this(monto, tipo, "Apuesta estándar");
    }

    public Apuesta(double monto, TipoApuesta tipo, String etiqueta) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de apuesta es requerido");
        }
        this.monto = monto;
        this.tipo = tipo;
        this.etiqueta = etiqueta;
    }

    public double getMonto() {
        return monto;
    }

    public TipoApuesta getTipo() {
        return tipo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public String toString() {
        return String.format("Apuesta{monto=%.2f, tipo=%s, etiqueta='%s'}",
                monto, tipo, etiqueta);
    }
}
