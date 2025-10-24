package Launcher;

import Controlador.SessionController;
import Vista.VentanaLoginRuleta;

public class Launcher {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        session.registrarUsuario("admin", "1234", "Administrador");
        session.registrarUsuario("Juanin", "abcd", "Juanin Harry");
        VentanaLoginRuleta login = new VentanaLoginRuleta(session);
        login.mostrarVentana();
    }
}
