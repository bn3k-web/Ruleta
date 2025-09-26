package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");

    public VentanaMenu() {
        configurarVentana();
        configurarEventos();
    }
    private void configurarEventos() {
        //btnInicio.addActionListener(this::registrarUsuario);
        btnJugar.addActionListener(e -> new VentanaRuleta().mostrarVentana());
        btnJugar.addActionListener(e -> cerrarVentana());
        btnHistorial.addActionListener(e -> new VentanaHistorial().mostrarVentana());
        btnHistorial.addActionListener(e -> cerrarVentana());
        btnSalir.addActionListener(e -> new VentanaLogin().mostrarVentana());
        btnSalir.addActionListener(e -> cerrarVentana());

    }
    private void configurarVentana() {
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        frame.add(new JLabel("Ruleta - Casino Black Cat"));
        frame.add(new JLabel("Bienvenido al menú principal.\n" +
                "Abajo tienes:\n" +
                "-Jugar: abre la ventana de juego\n" +
                "-Historial: Abre la ventana de historial \n" +
                "-Salir: CIerra sesion y vuelve al login. "));
        frame.add(btnInicio);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnSalir);
        frame.setLocationRelativeTo(null);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public void cerrarVentana() {
        frame.dispose();
    }
}