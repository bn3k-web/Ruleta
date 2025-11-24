package Vista;

import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

/**
 * Clase VentanaRegistro
 * 
 * Esta clase representa la ventana de registro de nuevos usuarios.
 * Permite ingresar un nombre de usuario, contraseña y nombre real para crear
 * una cuenta.
 */
public class VentanaRegistro {
    private final SessionController session;
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public VentanaRegistro(SessionController session) {
        this.session = session;
        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 550);
        frame.setLayout(null);
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
        panelFondo.setBounds(0, 0, 450, 550);
        frame.add(panelFondo);

        // Estilos modernos
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 28);
        Color colorTexto = new Color(236, 240, 241);
        Color colorInput = new Color(44, 62, 80);

        // Título
        JLabel lblTitulo = new JLabel("Crear Cuenta", SwingConstants.CENTER);
        lblTitulo.setFont(fontTitulo);
        lblTitulo.setForeground(new Color(52, 152, 219));
        lblTitulo.setBounds(50, 30, 350, 40);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Únete al Casino Black Cat", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(new Color(189, 195, 199));
        lblSubtitulo.setBounds(50, 75, 350, 25);
        panelFondo.add(lblSubtitulo);

        // Campo Usuario
        agregarCampo(panelFondo, "Usuario:", 125, txtUsuario, fontLabel, colorTexto, colorInput);

        // Campo Contraseña
        agregarCampo(panelFondo, "Contraseña:", 215, txtClave, fontLabel, colorTexto, colorInput);

        // Campo Nombre
        agregarCampo(panelFondo, "Nombre:", 305, txtNombre, fontLabel, colorTexto, colorInput);

        // Botón Registrar con hover
        btnRegistrar.setBounds(75, 405, 300, 45);
        btnRegistrar.setBackground(new Color(46, 204, 113));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder());
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(39, 174, 96));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(46, 204, 113));
            }
        });
        panelFondo.add(btnRegistrar);

        // Botón Cancelar con hover
        btnCancelar.setBounds(75, 465, 300, 45);
        btnCancelar.setBackground(new Color(231, 76, 60));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnCancelar.setBorder(BorderFactory.createEmptyBorder());
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(192, 57, 43));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCancelar.setBackground(new Color(231, 76, 60));
            }
        });
        panelFondo.add(btnCancelar);
    }

    private void agregarCampo(JPanel panel, String etiqueta, int y, JComponent campo, Font font, Color colorTexto,
            Color colorInput) {
        JLabel lbl = new JLabel(etiqueta);
        lbl.setBounds(75, y, 300, 25);
        lbl.setFont(font);
        lbl.setForeground(colorTexto);
        panel.add(lbl);

        campo.setBounds(75, y + 30, 300, 40);
        campo.setBackground(colorInput);
        campo.setForeground(Color.WHITE);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        if (campo instanceof JTextField) {
            ((JTextField) campo).setCaretColor(new Color(52, 152, 219));
        }
        panel.add(campo);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrar());
        btnCancelar.addActionListener(e -> frame.dispose());
    }

    private void registrar() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText().trim();

        try {
            session.registrarUsuario(usuario, clave, nombre);
            JOptionPane.showMessageDialog(frame, "Usuario registrado exitosamente.", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        } catch (IllegalArgumentException | IllegalStateException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
