package Vista;

import Controlador.SessionController;
import Historial.IRepositorioResultados;
import Modelo.Menu;

import javax.swing.*;

public class VentanaLoginRuleta {
    private final SessionController session;
    private final IRepositorioResultados repositorio; // ✅ Ahora recibe el repositorio

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario:");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave:");
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");

    // ✅ Constructor actualizado - AHORA ACEPTA DOS PARÁMETROS
    public VentanaLoginRuleta(SessionController session, IRepositorioResultados repositorio) {
        this.session = session;
        this.repositorio = repositorio;

        inicializarVentana();
        configurarEventos();
    }

    private void inicializarVentana() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(null);

        frame.add(lblUsuario);
        frame.add(txtUsuario);
        frame.add(lblClave);
        frame.add(txtClave);
        frame.add(btnIngresar);

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
            JOptionPane.showMessageDialog(frame, "Debe completar todos los campos.", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (session.iniciarSesion(usuario, clave)) {
            JOptionPane.showMessageDialog(frame, 
                "Bienvenido " + session.getNombreUsuario() + " !!!");
            frame.dispose();

            // Usar el mismo repositorio en toda la aplicación
            VentanaMenu vistaMenu = new VentanaMenu();
            new Menu(vistaMenu, session, repositorio); // Pasar repositorio
            vistaMenu.mostrarMenu();

        } else {
            JOptionPane.showMessageDialog(frame, 
                "Usuario o clave incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}