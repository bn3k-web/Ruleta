package Vista;

import Historial.IRepositorioResultados;

import javax.swing.*;
import java.awt.*;

import static java.awt.Color.BLACK;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel lblExplicacionTitulo = new JLabel("Ruleta - Casino Black Cat");
    private final JTextArea lblExplicacionCuerpo = new JTextArea("""
            Bienvenido al menú principal
            
            A la izquierda tienes:
            
            Jugar: Abre la ventana de juego
            
            Historial: Abre la ventana de historial
            
            Salir: Cierra sesión y vuelve al login""");

    public VentanaMenu() {
        inicializarVentana();
    }

    private void inicializarVentana() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Botones
        frame.add(btnInicio);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnSalir);

        btnInicio.setBounds(10, 100, 80, 40);
        btnJugar.setBounds(10, 150, 80, 40);
        btnHistorial.setBounds(10, 200, 80, 40);
        btnSalir.setBounds(10, 250, 80, 40);

        // Título
        frame.add(lblExplicacionTitulo);
        lblExplicacionTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblExplicacionTitulo.setBounds(100, 30, 200, 40);
        lblExplicacionTitulo.setFont(new Font("Arial", Font.BOLD, 14));

        // Texto explicativo
        frame.add(lblExplicacionCuerpo);
        lblExplicacionCuerpo.setBounds(100, 80, 250, 250);
        lblExplicacionCuerpo.setBorder(BorderFactory.createLineBorder(BLACK));
        lblExplicacionCuerpo.setEditable(false);
        lblExplicacionCuerpo.setOpaque(false);
        lblExplicacionCuerpo.setLineWrap(true);
        lblExplicacionCuerpo.setWrapStyleWord(true);
    }

    public void configurarEventos(IRepositorioResultados repositorio) {
        // Botón HISTORIAL
        btnHistorial.addActionListener(e -> {
            VentanaHistorial ventanaHistorial = new VentanaHistorial(repositorio);
            ventanaHistorial.mostrar();
        });
        // Botón SALIR
        btnSalir.addActionListener(e -> {
            frame.dispose(); // cierra la ventana actual
            JOptionPane.showMessageDialog(null, "Has cerrado sesión");
        });
    }

    public void mostrarMenu() {
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Getters
    public JFrame getFrame() { return frame; }
    public JButton getBtnSalir() { return btnSalir; }
    public JButton getBtnJugar() { return btnJugar; }
    public JButton getBtnHistorial() { return btnHistorial; }
}
