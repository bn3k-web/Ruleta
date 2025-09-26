package Launcher;
import Vista.VentanaLogin;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import Controlador.SessionController;
import Vista.VentanaLogin;

// ------------------ Clase Main ------------------
public class Launcher {
    public static void main(String[] args) {
        SessionController session = new SessionController();
        new VentanaLogin().mostrarVentana();
    }
}
