package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase VentanaMenu
 * 
 * Esta clase representa el menú principal de la aplicación.
 * Proporciona acceso a las funcionalidades principales: Jugar, Historial y
 * Salir.
 */
public class VentanaMenu {
    private final JFrame frame = new JFrame("Ruleta - Casino Black Cat");
    private final JButton btnInicio = new JButton("Inicio");
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnSalir = new JButton("Salir");

    public VentanaMenu() {
        inicializarVentana();
    }

    private void inicializarVentana() {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 480);
        frame.setResizable(false);

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
        panelFondo.setBounds(0, 0, 650, 480);
        frame.add(panelFondo);

        // Título principal
        JLabel lblTitulo = new JLabel("Casino Black Cat", SwingConstants.CENTER);
        lblTitulo.setBounds(200, 30, 400, 50);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(46, 204, 113));
        panelFondo.add(lblTitulo);

        // Subtítulo
        JLabel lblSubtitulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        lblSubtitulo.setBounds(200, 85, 400, 30);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        lblSubtitulo.setForeground(new Color(189, 195, 199));
        panelFondo.add(lblSubtitulo);

        // Panel de información
        JPanel panelInfo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(44, 62, 80, 200));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
            }
        };
        panelInfo.setLayout(null);
        panelInfo.setBounds(200, 140, 400, 280);
        panelInfo.setOpaque(false);
        panelFondo.add(panelInfo);

        // Texto de bienvenida
        JLabel lblBienvenida = new JLabel("¡Bienvenido!", SwingConstants.CENTER);
        lblBienvenida.setBounds(20, 20, 360, 30);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setForeground(new Color(236, 240, 241));
        panelInfo.add(lblBienvenida);

        // Descripción
        JTextArea txtDescripcion = new JTextArea(
                "Selecciona una opción del menú lateral:\n\n" +
                        "Jugar - Comienza una nueva partida\n\n" +
                        "Historial - Revisa tus jugadas anteriores\n\n" +
                        "Salir - Cierra sesión y vuelve al login");
        txtDescripcion.setBounds(30, 60, 340, 190);
        txtDescripcion.setEditable(false);
        txtDescripcion.setOpaque(false);
        txtDescripcion.setForeground(new Color(189, 195, 199));
        txtDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        panelInfo.add(txtDescripcion);

        // Botones laterales con estilo moderno
        configurarBotonModerno(btnInicio, 30, 140, new Color(52, 73, 94), panelFondo);
        configurarBotonModerno(btnJugar, 30, 200, new Color(46, 204, 113), panelFondo);
        configurarBotonModerno(btnHistorial, 30, 260, new Color(52, 152, 219), panelFondo);
        configurarBotonModerno(btnSalir, 30, 320, new Color(231, 76, 60), panelFondo);
    }

    private void configurarBotonModerno(JButton btn, int x, int y, Color colorBase, JPanel panel) {
        btn.setBounds(x, y, 150, 50);
        btn.setBackground(colorBase);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder());
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        Color colorHover = colorBase.darker();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorHover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(colorBase);
            }
        });

        panel.add(btn);
    }

    public void mostrarMenu() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Getters
    public JFrame getFrame() {
        return frame;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnJugar() {
        return btnJugar;
    }

    public JButton getBtnHistorial() {
        return btnHistorial;
    }
}