package Vista;

import javax.swing.*;
import static java.awt.Color.BLACK;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Modelo.Ruleta - Casino Black Cat");
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");
    private final JLabel lblExplicacionTitulo = new JLabel("Modelo.Ruleta - Casino Black Cat");
    private final JTextArea lblExplicacionCuerpo = new JTextArea("""
            Bienvenido al menú principal
            
            A la izquierda tienes:
            
            Jugar: Abre la ventana de juego
            
            Historial: Abre la ventana de historial
            
            Salir: Cierra sesión y vuelve al login""");

    public VentanaMenu() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        frame.add(btnInicio);
        frame.add(btnJugar);
        frame.add(btnHistorial);
        frame.add(btnSalir);

        btnInicio.setBounds(10, 100, 80, 40);
        btnJugar.setBounds(10, 150, 80, 40);
        btnHistorial.setBounds(10, 200, 80, 40);
        btnSalir.setBounds(10, 250, 80, 40);

        frame.add(lblExplicacionTitulo);
        lblExplicacionTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblExplicacionTitulo.setBounds(150, 50, 150, 50);

        frame.add(lblExplicacionCuerpo);
        lblExplicacionCuerpo.setBounds(100, 100, 200, 250);
        lblExplicacionCuerpo.setBorder(BorderFactory.createLineBorder(BLACK));
        lblExplicacionCuerpo.setEditable(false);
        lblExplicacionCuerpo.setOpaque(false);
        lblExplicacionCuerpo.setLineWrap(true);
        lblExplicacionCuerpo.setWrapStyleWord(true);

        mostrarMenu();
    }

    public void mostrarMenu() {
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }


    public JFrame getFrame() { return frame; }
    public JButton getBtnSalir() { return btnSalir; }
    public JButton getBtnJugar() { return btnJugar; }
    public JButton getBtnHistorial() { return btnHistorial; }
}
