package Modelo;

public class Usuario {
    private double saldo;
    private final String username;
    private final String password;
    private final String nombre;

    // Constructor principal
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 1000; // saldo inicial
    }

    // Validar credenciales
    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    // ✅ Solo actualiza saldo (NO guarda historial)
    public void actualizarSaldo(int montoApostado, boolean gano) {
        if (gano) {
            this.saldo += montoApostado;
        } else {
            this.saldo -= montoApostado;
        }
        if (this.saldo < 0) this.saldo = 0; // evitar saldo negativo
    }

    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public double getSaldo() {
        return saldo;
    }
    
    public String getUsername() {
        return username;
    }
}