package Modelo;

import Controlador.RuletaController;
import Controlador.SessionController;
import Vista.VentanaMenu;
import Vista.VentanaRuleta;
import Vista.VentanaHistorial;
import Historial.IRepositorioResultados;

import javax.swing.*;

public class Menu {
    private final VentanaMenu menu;
    private final SessionController session;
    private final IRepositorioResultados repositorio;

    // ✅ Constructor recibe el repositorio (NO lo crea)
    public Menu(VentanaMenu menu, SessionController session, IRepositorioResultados repositorio) {
        this.menu = menu;
        this.session = session;
        this.repositorio = repositorio;
        configurarEventos();
    }

    private void configurarEventos() {
        menu.getBtnSalir().addActionListener(e -> salirMenu());
        menu.getBtnHistorial().addActionListener(e -> abrirHistorial());
        menu.getBtnJugar().addActionListener(e -> jugarRuleta());
    }

    private void salirMenu() {
        session.cerrarSesion();
        JOptionPane.showMessageDialog(menu.getFrame(),
                "Sesión cerrada correctamente. ¡Hasta pronto!",
                "Salir", JOptionPane.INFORMATION_MESSAGE);
        menu.getFrame().dispose();
    }

    private void abrirHistorial() {
        VentanaHistorial vistaHistorial = new VentanaHistorial(repositorio);
        vistaHistorial.mostrar();
    }

    private void jugarRuleta() {
        if (!session.hayUsuario()) {
            JOptionPane.showMessageDialog(menu.getFrame(),
                    "Debe iniciar sesión para jugar.",
                    "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // ✅ Usar el mismo repositorio inyectado
        Ruleta modeloRuleta = new Ruleta(repositorio);
        VentanaRuleta vistaRuleta = new VentanaRuleta();

        new RuletaController(modeloRuleta, vistaRuleta, session);
        vistaRuleta.mostrar();
    }
}