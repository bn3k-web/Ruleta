package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Modelo.Usuario;

class VentanaRuleta {private final JFrame frame = new JFrame("Ruleta - Casino Never Win");
    private final JButton btnSalir = new JButton("Salir");
    private final JButton btnJugar = new JButton("Jugar")

    public VentanaRuleta() {
        configurarVentana();
        configurarEventos();
    }
    private void configurarEventos() {
        btnSalir.addActionListener(e -> new VentanaMenu().mostrarVentana());
        btnSalir.addActionListener(e -> cerrarVentana());
        //btnJugar.addActionListener(e -> );
    }
    private void configurarVentana() {
        frame.setSize(550, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));
        frame.add(new JLabel("Ruleta - Casino Never Win"));
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