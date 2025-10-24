package Vista;

import Historial.IRepositorioResultados;
import Modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaHistorial {

    private final JFrame frame = new JFrame("Historial de Resultados - Casino Black Cat");
    private final JTable tablaHistorial = new JTable();
    private final JButton btnCerrar = new JButton("Cerrar");

    private final IRepositorioResultados repositorio;

    public VentanaHistorial(IRepositorioResultados repositorio) {
        this.repositorio = repositorio;
        inicializarVentana();
        cargarDatos();
    }

    private void inicializarVentana() {
        frame.setSize(700, 400);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel titulo = new JLabel("📜 Historial de Jugadas", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Configurar tabla
        tablaHistorial.setModel(new DefaultTableModel(
                new Object[]{"Fecha y Hora", "Número Ganador", "Tipo Apuesta", "Monto", "Resultado"},
                0
        ));
        tablaHistorial.setEnabled(false);
        JScrollPane scroll = new JScrollPane(tablaHistorial);

        // Botón cerrar
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnCerrar);
        btnCerrar.addActionListener(e -> frame.dispose());

        frame.add(titulo, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelBoton, BorderLayout.SOUTH);
    }

    private void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) tablaHistorial.getModel();
        model.setRowCount(0); // limpia tabla

        List<Resultado> historial = repositorio.obtenerHistorial();
        if (historial.isEmpty()) {
            model.addRow(new Object[]{"(sin registros)", "-", "-", "-", "-"});
            return;
        }

        for (Resultado r : historial) {
            model.addRow(new Object[]{
                    r.getFechaHora(),
                    r.getNumeroGanador(),
                    r.getTipoApuesta(),
                    "$" + r.getMontoApostado(),
                    r.isAcierto() ? "GANÓ 🟢" : "PERDIÓ 🔴"
            });
        }
    }

    public void mostrar() {
        frame.setVisible(true);
    }
}
