package Vista;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial{
    private final JFrame frame = new JFrame("Historial - Casino Black Cat");
    private final JButton btnSalir = new JButton("Salir");

    public VentanaHistorial() {
        configurarVentana();
        configurarEventos();
    }
    private void configurarEventos() {
        btnSalir.addActionListener(e -> new VentanaMenu().mostrarVentana());
        btnSalir.addActionListener(e -> cerrarVentana());
    }
    private void configurarVentana() {
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));
        frame.add(new JLabel("Historial - Casino Black Cat"));
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