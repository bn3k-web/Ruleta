package Vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import Modelo.Usuario;

//Clase VentanaRegistro//
class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaRegistro() {
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(new JLabel("Nombre completo:"));
        frame.add(txtNombre);
        frame.add(new JLabel(""));
        frame.add(btnRegistrar);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(this::registrarUsuario);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void registrarUsuario(ActionEvent e) {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame,
                    "Todos los campos son obligatorios",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));
        JOptionPane.showMessageDialog(frame,
                "Usuario registrado con éxito ✅",
                "Registro", JOptionPane.INFORMATION_MESSAGE);

        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}