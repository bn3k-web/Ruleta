package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final List<Resultado> historial = new ArrayList<>();
    private double saldo;
    private String username;
    private String password;
    private String nombre;
    private String invitado;

    public Usuario(String username, String password, String nombre) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.saldo = 1000.0;
    }
    public boolean validarCredenciales(String u, String p){
        return this.username.equals(u) && this.password.equals(p);
    }
    private void actualizarSaldo(int montoApostado, boolean gano){
        if(gano){
            this.saldo += montoApostado * 1;
        }else{
            this.saldo -= montoApostado;
        }
    }
    public String getNombre() {
        return nombre;
    }
    public double getSaldo() {
        return saldo;
    }
    public void agregarResultado(Resultado r) {
        historial.add(r);
    }
    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
