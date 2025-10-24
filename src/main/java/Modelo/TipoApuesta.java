package Modelo;

public enum TipoApuesta {
    ROJO("Rojo"),
    NEGRO("Negro"),
    PAR("Par"),
    IMPAR("Impar");

    private final String descripcion;

    TipoApuesta(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
