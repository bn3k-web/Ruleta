package Modelo;

/**
 * Clase Usuario
 * 
 * Representa a un usuario del sistema.
 * Almacena la información personal (nombre, usuario, clave) y el saldo
 * disponible para apostar.
 */
public class Usuario {
    private String usuario;
    private String clave;
    private String nombre;
    private int saldo;

    public Usuario(String usuario, String clave, String nombre) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = 1000; // Saldo inicial
    }

    // Constructor completo para cargar desde archivo
    public Usuario(String usuario, String clave, String nombre, int saldo) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public boolean validarCredenciales(String u, String p) {
        return this.usuario.equals(u) && this.clave.equals(p);
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }

    public int getSaldo() {
        return saldo;
    }

    public void actualizarSaldo(int monto, boolean gano) {
        if (gano) {
            this.saldo += monto * 2; // Ganancia simple (doble de la apuesta)
        } else {
            this.saldo -= monto;
        }
    }

    public String toCSV() {
        return usuario + "," + clave + "," + nombre + "," + saldo;
    }

    public static Usuario fromCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 4)
            return null;
        return new Usuario(partes[0], partes[1], partes[2], Integer.parseInt(partes[3]));
    }
}