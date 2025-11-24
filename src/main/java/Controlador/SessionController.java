package Controlador;

import Modelo.Usuario;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase SessionController
 * 
 * Esta clase gestiona la sesión de los usuarios y el registro de nuevos
 * usuarios.
 * Mantiene un registro de los usuarios registrados y del usuario que ha
 * iniciado sesión actualmente.
 */
public class SessionController {
    private final Map<String, Usuario> usuariosRegistrados = new HashMap<>();
    private Usuario usuarioActual;
    private final String ARCHIVO_USUARIOS = "usuarios.csv";

    public SessionController() {
        cargarUsuarios();
    }

    public void registrarUsuario(String u, String p, String n) {
        if (u == null || u.isBlank() || p == null || p.isBlank() || n == null || n.isBlank())
            throw new IllegalArgumentException("Datos requeridos");

        if (usuariosRegistrados.containsKey(u)) {
            throw new IllegalStateException("Usuario ya registrado");
        }

        Usuario nuevoUsuario = new Usuario(u, p, n);
        usuariosRegistrados.put(u, nuevoUsuario);
        guardarUsuarios();
    }

    public void iniciarSesion(String u, String p) {
        Usuario usuario = usuariosRegistrados.get(u);
        if (usuario == null || !usuario.validarCredenciales(u, p)) {
            throw new IllegalStateException("Credenciales incorrectas");
        }
        this.usuarioActual = usuario;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public String getNombreUsuario() {
        return hayUsuario() ? usuarioActual.getNombre() : "";
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void cerrarSesion() {
        guardarUsuarios(); // Guardar saldo al salir
        usuarioActual = null;
    }

    private void cargarUsuarios() {
        java.io.File archivo = new java.io.File(ARCHIVO_USUARIOS);
        if (!archivo.exists())
            return;

        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = Usuario.fromCSV(linea);
                if (u != null) {
                    usuariosRegistrados.put(u.getUsuario(), u);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
        }
    }

    private void guardarUsuarios() {
        try (java.io.BufferedWriter bw = new java.io.BufferedWriter(new java.io.FileWriter(ARCHIVO_USUARIOS))) {
            for (Usuario u : usuariosRegistrados.values()) {
                bw.write(u.toCSV());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}