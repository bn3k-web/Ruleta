package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import Modelo.Usuario;

// -------- Clase VentanaLogin -----------//
public class VentanaLogin {
    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistro = new JButton("Registrarse");

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("benya", "1234", "Administrador"));
        USUARIOS.add(new Usuario("test", "abcd", "Tester"));
        USUARIOS.add(new Usuario("pedrito", "pass", "Pedro Navaja"));
    }
    private void configurarVentana() {
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistro);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> new VentanaMenu().mostrarVentana());
        btnIngresar.addActionListener(e -> cerrarVentana());
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
    public void cerrarVentana() {
        frame.dispose();
    }
    private void login(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Bienvenido, " + nombre + " 🎰",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            // Aquí deberías abrir la ventana principal del juego
        } else {
            JOptionPane.showMessageDialog(frame,
                    "Usuario o clave incorrectos",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user.getNombre();
            }
        }
        return "";
    }
    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro().mostrarVentana();
    }
}