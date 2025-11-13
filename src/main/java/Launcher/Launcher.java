package Launcher;

import Controlador.SessionController;
import Vista.VentanaLoginRuleta;
import Historial.IRepositorioResultados;
import Historial.RepositorioArchivo;

public class Launcher {
    public static void main(String[] args) {
        IRepositorioResultados repositorioGlobal = new RepositorioArchivo("historial.csv");
        
        
        SessionController session = new SessionController();
        session.registrarUsuario("admin", "1234", "Administrador");
        session.registrarUsuario("Benya", "abcd", "Benya Harry");
        
        VentanaLoginRuleta login = new VentanaLoginRuleta(session, repositorioGlobal);
        login.mostrarVentana();
    }
}