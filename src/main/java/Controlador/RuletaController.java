package Controlador;

import Modelo.Ruleta;
import Modelo.TipoApuesta;
import Modelo.Resultado;
import Modelo.Usuario;
import Vista.VentanaRuleta;

import javax.swing.*;
import java.util.Objects;

public class RuletaController {
    private final SessionController session;
    private final Ruleta ruleta;
    private final VentanaRuleta ventanaRuleta;

    public RuletaController(Ruleta ruleta, VentanaRuleta vista, SessionController session) {
        this.ruleta = ruleta;
        this.ventanaRuleta = vista;
        this.session = session;
        lectorEventos();
        actualizarInfoUsuario();
    }

    private void lectorEventos() {
        ventanaRuleta.getBtnGirar().addActionListener(e -> jugarRuleta());
    }

    private void actualizarInfoUsuario() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario != null) {
            ventanaRuleta.getLblSaldo().setText("Saldo: $" + String.format("%.2f", usuario.getSaldo()));
        }
    }

    private void jugarRuleta() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) {
            JOptionPane.showMessageDialog(ventanaRuleta.getFrame(), "Debe iniciar sesión para jugar.", "Error de Sesión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int monto = Integer.parseInt(ventanaRuleta.getTxtApuesta().getText());
            TipoApuesta tipo = (TipoApuesta) Objects.requireNonNull(ventanaRuleta.getCboTipo().getSelectedItem());
            if (monto <= 0) {
                JOptionPane.showMessageDialog(ventanaRuleta.getFrame(), "El monto debe ser positivo.", "Error de Apuesta", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (usuario.getSaldo() < monto) {
                JOptionPane.showMessageDialog(ventanaRuleta.getFrame(), "Saldo insuficiente.");
                return;
            }
            int numeroGanador = ruleta.girarRuleta();
            boolean acierto = ruleta.evaluarResultado(numeroGanador, tipo);
            Resultado resultado = new Resultado(numeroGanador, monto, acierto, tipo);
            ruleta.registrarResultado(numeroGanador, monto, acierto);
            usuario.agregarResultado(resultado);
            mostrarResultado(numeroGanador, tipo, monto, acierto);
            actualizarInfoUsuario();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(ventanaRuleta.getFrame(), "Ingrese un monto numérico válido.", "Error de Entrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarResultado(int numero, TipoApuesta tipo, int monto, boolean acierto) {
        String color = Ruleta.esRojo(numero) ? "Rojo" : (numero == 0 ? "Verde (0)" : "Negro");
        String texto = String.format("--- Ronda #%d ---\n", session.getUsuarioActual().getHistorial().size());
        texto += String.format("Número ganador: %d (%s)\n", numero, color);
        texto += String.format("Apuesta: %s (Monto: $%d)\n", tipo.name(), monto);
        texto += acierto ? "¡¡¡GANASTE!!!" : "PERDISTE.";

        ventanaRuleta.getTxtResultado().setText(texto);
    }


}