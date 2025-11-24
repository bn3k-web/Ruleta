package Modelo;

import Controlador.RuletaController;
import Controlador.SessionController;
import Historial.IRepositorioResultados;
import Vista.VentanaHistorial;
import Vista.VentanaMenu;
import Vista.VentanaRuleta;

/**
 * Clase Menu
 * 
 * Controla la lógica de navegación del menú principal.
 * Conecta la vista del menú con las acciones correspondientes (Jugar,
 * Historial, Salir).
 */
public class Menu {
    private final VentanaMenu vista;
    private final SessionController session;
    private final IRepositorioResultados repositorio;

    public Menu(VentanaMenu vista, SessionController session, IRepositorioResultados repositorio) {
        this.vista = vista;
        this.session = session;
        this.repositorio = repositorio;
        configurarEventos();
    }

    private void configurarEventos() {
        vista.getBtnSalir().addActionListener(e -> {
            session.cerrarSesion();
            vista.getFrame().dispose();
            // Aquí se podría volver a mostrar el login si se tuviera referencia
            System.exit(0); // Por ahora cerramos la app
        });

        vista.getBtnJugar().addActionListener(e -> abrirJuego());
        vista.getBtnHistorial().addActionListener(e -> abrirHistorial());
    }

    private void abrirJuego() {
        VentanaRuleta ventanaRuleta = new VentanaRuleta();
        Ruleta ruleta = new Ruleta(repositorio);
        new RuletaController(ruleta, ventanaRuleta, session);
        ventanaRuleta.mostrar();
    }

    private void abrirHistorial() {
        new VentanaHistorial(repositorio, session).mostrar();
    }
}