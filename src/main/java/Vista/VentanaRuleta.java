package Vista;

import Modelo.TipoApuesta;
import javax.swing.*;
import java.awt.*;

/**
 * Clase VentanaRuleta
 * 
 * Esta clase representa la interfaz gráfica del juego de ruleta.
 * Permite al usuario realizar apuestas, girar la ruleta y ver los resultados.
 */
public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JTextField txtApuesta = new JTextField();
    private final JComboBox<TipoApuesta> cboTipo = new JComboBox<>(TipoApuesta.values());
    private final JButton btnGirar = new JButton("GIRAR RULETA");
    private final JTextArea txtResultado = new JTextArea();
    private final JLabel lblSaldo = new JLabel("Saldo: $0");

    public VentanaRuleta() {
        inicializarVentana();
        inicializarComponentes();
    }

    private void inicializarVentana() {
        frame.setVisible(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(900, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Panel con gradiente de fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(15, 32, 39), 0, h, new Color(32, 58, 67));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setBounds(0, 0, 900, 700);
        frame.add(panelFondo);
    }

    private void inicializarComponentes() {
        JPanel panelFondo = (JPanel) frame.getContentPane().getComponent(0);

        // Título decorativo
        JLabel lblTitulo = new JLabel("Ruleta del Gato Negro", SwingConstants.CENTER);
        lblTitulo.setBounds(150, 20, 600, 50);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(46, 204, 113));
        panelFondo.add(lblTitulo);

        // Panel de saldo
        JPanel panelSaldo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(46, 204, 113, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        panelSaldo.setLayout(null);
        panelSaldo.setBounds(50, 90, 250, 60);
        panelSaldo.setOpaque(false);
        panelFondo.add(panelSaldo);

        lblSaldo.setBounds(0, 0, 250, 60);
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        panelSaldo.add(lblSaldo);

        // Panel de apuesta
        JPanel panelApuesta = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(44, 62, 80, 220));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panelApuesta.setLayout(null);
        panelApuesta.setBounds(50, 170, 800, 100);
        panelApuesta.setOpaque(false);
        panelFondo.add(panelApuesta);

        // Etiquetas y campos
        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(30, 25, 100, 30);
        lblMonto.setForeground(new Color(236, 240, 241));
        lblMonto.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelApuesta.add(lblMonto);

        txtApuesta.setBounds(130, 25, 150, 40);
        txtApuesta.setBackground(new Color(52, 73, 94));
        txtApuesta.setForeground(Color.WHITE);
        txtApuesta.setCaretColor(new Color(46, 204, 113));
        txtApuesta.setFont(new Font("Segoe UI", Font.BOLD, 18));
        txtApuesta.setHorizontalAlignment(JTextField.CENTER);
        txtApuesta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(46, 204, 113), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panelApuesta.add(txtApuesta);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(310, 25, 80, 30);
        lblTipo.setForeground(new Color(236, 240, 241));
        lblTipo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelApuesta.add(lblTipo);

        cboTipo.setBounds(390, 25, 180, 40);
        cboTipo.setBackground(new Color(52, 73, 94));
        cboTipo.setForeground(Color.WHITE);
        cboTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cboTipo.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 2));
        panelApuesta.add(cboTipo);

        // Botón girar
        btnGirar.setBounds(600, 20, 180, 50);
        btnGirar.setBackground(new Color(231, 76, 60));
        btnGirar.setForeground(Color.WHITE);
        btnGirar.setFocusPainted(false);
        btnGirar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGirar.setBorder(BorderFactory.createEmptyBorder());
        btnGirar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGirar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGirar.setBackground(new Color(192, 57, 43));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGirar.setBackground(new Color(231, 76, 60));
            }
        });
        panelApuesta.add(btnGirar);

        // Panel de resultados
        JPanel panelResultados = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(44, 62, 80, 220));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panelResultados.setLayout(new BorderLayout());
        panelResultados.setBounds(50, 290, 800, 360);
        panelResultados.setOpaque(false);
        panelFondo.add(panelResultados);

        // Título del panel de resultados
        JLabel lblTituloResultados = new JLabel("Historial de Jugadas", SwingConstants.CENTER);
        lblTituloResultados.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTituloResultados.setForeground(new Color(236, 240, 241));
        lblTituloResultados.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        panelResultados.add(lblTituloResultados, BorderLayout.NORTH);

        // Área de resultados
        txtResultado.setEditable(false);
        txtResultado.setBackground(new Color(30, 39, 46));
        txtResultado.setForeground(new Color(236, 240, 241));
        txtResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        txtResultado.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JScrollPane scrollResultados = new JScrollPane(txtResultado);
        scrollResultados.setBorder(BorderFactory.createEmptyBorder());
        scrollResultados.setOpaque(false);
        scrollResultados.getViewport().setOpaque(false);
        panelResultados.add(scrollResultados, BorderLayout.CENTER);
    }

    // Mostrar ventana
    public void mostrar() {
        frame.setVisible(true);
    }

    // Getters (para el controlador)
    public JFrame getFrame() {
        return frame;
    }

    public JTextField getTxtApuesta() {
        return txtApuesta;
    }

    public JComboBox<TipoApuesta> getCboTipo() {
        return cboTipo;
    }

    public JButton getBtnGirar() {
        return btnGirar;
    }

    public JTextArea getTxtResultado() {
        return txtResultado;
    }

    public JLabel getLblSaldo() {
        return lblSaldo;
    }

    // Validación de apuesta
    public boolean validarApuesta(double saldoUsuario) {
        String textoMonto = txtApuesta.getText().trim();

        if (textoMonto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe ingresar un monto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            int monto = Integer.parseInt(textoMonto);

            if (monto <= 0) {
                JOptionPane.showMessageDialog(frame, "El monto debe ser positivo.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return false;
            }

            if (monto > saldoUsuario) {
                JOptionPane.showMessageDialog(frame, "Saldo insuficiente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return false;
            }

            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
