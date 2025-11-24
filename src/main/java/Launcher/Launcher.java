package Launcher;

import Controlador.SessionController;
import Vista.VentanaLoginRuleta;
import Historial.IRepositorioResultados;
import Historial.RepositorioArchivo;

public class Launcher {
    public static void main(String[] args) {
        // ✅ Caso 6: Red de seguridad global
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            javax.swing.JOptionPane.showMessageDialog(null,
                    "Ocurrió un error inesperado: " + e.getMessage(),
                    "Error Crítico", javax.swing.JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        });

        IRepositorioResultados repositorioGlobal = new RepositorioArchivo("historial.csv");

        SessionController session = new SessionController();
        // Usuarios cargados desde archivo, no es necesario registrar manualmente

        VentanaLoginRuleta login = new VentanaLoginRuleta(session, repositorioGlobal);
        login.mostrarVentana();
    }
}