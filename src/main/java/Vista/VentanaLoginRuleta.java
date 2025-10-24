package Vista;

import Controlador.SessionController;
import Historial.RepositorioEnMemoria;
import Modelo.Menu;

import javax.swing.*;

public class VentanaLoginRuleta {
    private final SessionController session;

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    public VentanaLoginRuleta(SessionController session) {
        this.session = session;

        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        // Añadir componentes
        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);

        // Posiciones
        lblUsuario.setBounds(50, 80, 300, 25);
        txtUsuario.setBounds(50, 120, 300, 25);
        lblClave.setBounds(50, 160, 300, 25);
        txtClave.setBounds(50, 200, 300, 25);
        btnIngresar.setBounds(50, 250, 300, 30);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
    }

    public void mostrarVentana() {
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        if (usuario.isEmpty() || clave.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Debe completar todos los campos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (session.iniciarSesion(usuario, clave)) {
            JOptionPane.showMessageDialog(frame, "Bienvenido " + session.getNombreUsuario() + " 🎰");
            frame.dispose();

            // Crear repositorio de resultados (historial)
            RepositorioEnMemoria repo = new RepositorioEnMemoria();

            // Crear e iniciar menú principal
            VentanaMenu vistaMenu = new VentanaMenu();
            vistaMenu.configurarEventos(repo); // conectar botones con historial
            new Menu(vistaMenu, session);
            vistaMenu.mostrarMenu();

        } else {
            JOptionPane.showMessageDialog(frame, "Usuario o clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
