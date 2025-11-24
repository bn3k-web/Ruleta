package Vista;

import Controlador.SessionController;
import Historial.IRepositorioResultados;
import Modelo.Menu;

import javax.swing.*;
import java.awt.*;

/**
 * Clase VentanaLoginRuleta
 * 
 * Esta clase representa la ventana de inicio de sesión.
 * Permite a los usuarios ingresar sus credenciales o navegar a la ventana de
 * registro.
 */
public class VentanaLoginRuleta {
    private final SessionController session;
    private final IRepositorioResultados repositorio;

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrarse");

    public VentanaLoginRuleta(SessionController session, IRepositorioResultados repositorio) {
        this.session = session;
        this.repositorio = repositorio;

        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 520);
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
        panelFondo.setBounds(0, 0, 450, 520);
        frame.add(panelFondo);

        // Estilos modernos
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 13);
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 32);
        Color colorTexto = new Color(236, 240, 241);
        Color colorInput = new Color(44, 62, 80);

        // Título con efecto
        JLabel lblTitulo = new JLabel("Casino Black Cat", SwingConstants.CENTER);
        lblTitulo.setFont(fontTitulo);
        lblTitulo.setForeground(new Color(46, 204, 113));
        lblTitulo.setBounds(50, 40, 350, 45);
        panelFondo.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(189, 195, 199));
        lblSubtitulo.setBounds(50, 90, 350, 25);
        panelFondo.add(lblSubtitulo);

        // Campo Usuario
        lblUsuario.setBounds(75, 145, 300, 25);
        lblUsuario.setFont(fontLabel);
        lblUsuario.setForeground(colorTexto);
        panelFondo.add(lblUsuario);

        txtUsuario.setBounds(75, 175, 300, 40);
        txtUsuario.setBackground(colorInput);
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(new Color(46, 204, 113));
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtUsuario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panelFondo.add(txtUsuario);

        // Campo Contraseña
        lblClave.setBounds(75, 235, 300, 25);
        lblClave.setFont(fontLabel);
        lblClave.setForeground(colorTexto);
        panelFondo.add(lblClave);

        txtClave.setBounds(75, 265, 300, 40);
        txtClave.setBackground(colorInput);
        txtClave.setForeground(Color.WHITE);
        txtClave.setCaretColor(new Color(46, 204, 113));
        txtClave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtClave.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 73, 94), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panelFondo.add(txtClave);

        // Botón Ingresar con hover
        btnIngresar.setBounds(75, 335, 300, 45);
        btnIngresar.setBackground(new Color(46, 204, 113));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder());
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(new Color(39, 174, 96));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(new Color(46, 204, 113));
            }
        });
        panelFondo.add(btnIngresar);

        // Botón Registrar con hover
        btnRegistrar.setBounds(75, 400, 300, 45);
        btnRegistrar.setBackground(new Color(52, 152, 219));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnRegistrar.setBorder(BorderFactory.createEmptyBorder());
        btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(41, 128, 185));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrar.setBackground(new Color(52, 152, 219));
            }
        });
        panelFondo.add(btnRegistrar);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void abrirRegistro() {
        new VentanaRegistro(session).mostrar();
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe completar todos los campos.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            session.iniciarSesion(usuario, clave);

            JOptionPane.showMessageDialog(frame,
                    "Bienvenido " + session.getNombreUsuario());
            frame.dispose();

            VentanaMenu vistaMenu = new VentanaMenu();
            new Menu(vistaMenu, session, repositorio);
            vistaMenu.mostrarMenu();

        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(frame,
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}