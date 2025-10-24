package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final List<Resultado> historial = new ArrayList<>();
    private double saldo;
    private final String username;
    private final String password;
    private final String nombre;

    // Constructor principal
    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 1000.0; // saldo inicial
    }

    // Validar credenciales
    public boolean validarCredenciales(String u, String p) {
        return this.username.equals(u) && this.password.equals(p);
    }

    // Actualiza el saldo después de una apuesta
    public void actualizarSaldo(int montoApostado, boolean gano) {
        if (gano) {
            this.saldo += montoApostado; // ganó: suma el monto
        } else {
            this.saldo -= montoApostado; // perdió: resta el monto
        }
        if (this.saldo < 0) this.saldo = 0; // evitar saldo negativo
    }

    // Registrar resultado de una jugada
    public void agregarResultado(Resultado r) {
        historial.add(r);
        actualizarSaldo(r.getMontoApostado(), r.isAcierto());
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

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
