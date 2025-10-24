package Vista;

import Modelo.TipoApuesta;
import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("🎰 Ruleta - Casino Black Cat");
    private final JTextField txtApuesta = new JTextField();
    private final JComboBox<TipoApuesta> cboTipo = new JComboBox<>(TipoApuesta.values());
    private final JButton btnGirar = new JButton("Girar 🎡");
    private final JTextArea txtResultado = new JTextArea();
    private final JLabel lblSaldo = new JLabel("Saldo: $0");
    private final JLabel lblTitulo = new JLabel("🎲 Bienvenido a la Ruleta del Gato Negro 🐈‍⬛");

    public VentanaRuleta() {
        inicializarVentana();
        inicializarComponentes();
    }

    private void inicializarVentana() {
        frame.setVisible(false); // será mostrado por el controlador
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(20, 20, 20)); // fondo oscuro
    }

    private void inicializarComponentes() {
        // Título decorativo
        lblTitulo.setBounds(100, 10, 600, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.WHITE);

        // Campo saldo
        lblSaldo.setBounds(30, 60, 200, 30);
        lblSaldo.setForeground(Color.GREEN);
        lblSaldo.setFont(new Font("Consolas", Font.BOLD, 16));

        // Monto
        JLabel lblApuesta = new JLabel("Monto:");
        lblApuesta.setBounds(30, 110, 60, 30);
        lblApuesta.setForeground(Color.WHITE);
        lblApuesta.setFont(new Font("Arial", Font.PLAIN, 14));

        txtApuesta.setBounds(90, 110, 100, 30);
        txtApuesta.setBackground(new Color(40, 40, 40));
        txtApuesta.setForeground(Color.WHITE);
        txtApuesta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Tipo de apuesta
        cboTipo.setBounds(210, 110, 150, 30);
        cboTipo.setBackground(new Color(30, 30, 30));
        cboTipo.setForeground(Color.WHITE);

        // Botón de acción
        btnGirar.setBounds(380, 110, 100, 30);
        btnGirar.setBackground(new Color(60, 0, 0));
        btnGirar.setForeground(Color.WHITE);
        btnGirar.setFocusPainted(false);
        btnGirar.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Área de resultados
        txtResultado.setBounds(30, 170, 450, 300);
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(30, 30, 30));
        txtResultado.setForeground(Color.WHITE);
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 13));
        txtResultado.setBorder(BorderFactory.createTitledBorder("Historial de Resultados"));

        // Agregar componentes
        frame.add(lblTitulo);
        frame.add(lblSaldo);
        frame.add(lblApuesta);
        frame.add(txtApuesta);
        frame.add(cboTipo);
        frame.add(btnGirar);
        frame.add(txtResultado);
    }

    // Mostrar ventana
    public void mostrar() {
        frame.setVisible(true);
    }

    // Getters (para el controlador)
    public JFrame getFrame() { return frame; }
    public JTextField getTxtApuesta() { return txtApuesta; }
    public JComboBox<TipoApuesta> getCboTipo() { return cboTipo; }
    public JButton getBtnGirar() { return btnGirar; }
    public JTextArea getTxtResultado() { return txtResultado; }
    public JLabel getLblSaldo() { return lblSaldo; }
}
