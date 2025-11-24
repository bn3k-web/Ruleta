package Vista;

import Controlador.SessionController;
import Historial.IRepositorioResultados;
import Modelo.Resultado;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

/**
 * Clase VentanaHistorial
 * 
 * Esta clase representa la ventana que muestra el historial de resultados.
 * Muestra una tabla con los detalles de cada jugada realizada.
 */
public class VentanaHistorial {

    private final JFrame frame = new JFrame("Historial de Resultados - Casino Black Cat");
    private final JTable tablaHistorial = new JTable();
    private final JButton btnCerrar = new JButton("Cerrar");

    private final IRepositorioResultados repositorio;
    private final SessionController session;

    public VentanaHistorial(IRepositorioResultados repositorio, SessionController session) {
        this.repositorio = repositorio;
        this.session = session;
        inicializarVentana();
        cargarDatos();
    }

    private void inicializarVentana() {
        frame.setSize(850, 550);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Panel con gradiente de fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth(), h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, new Color(15, 32, 39), 0, h, new Color(32, 58, 67));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        panelFondo.setLayout(null);
        panelFondo.setBounds(0, 0, 850, 550);
        frame.add(panelFondo);

        // Título
        JLabel titulo = new JLabel("Historial de Jugadas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(new Color(52, 152, 219));
        titulo.setBounds(50, 25, 750, 45);
        panelFondo.add(titulo);

        // Panel contenedor de la tabla
        JPanel panelTabla = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(44, 62, 80, 220));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        panelTabla.setLayout(new BorderLayout());
        panelTabla.setBounds(50, 90, 750, 380);
        panelTabla.setOpaque(false);
        panelFondo.add(panelTabla);

        // Configurar tabla
        tablaHistorial.setModel(new DefaultTableModel(
                new Object[] { "Fecha y Hora", "Número Ganador", "Tipo Apuesta", "Monto", "Resultado" },
                0));
        tablaHistorial.setEnabled(false);
        tablaHistorial.setBackground(new Color(52, 73, 94));
        tablaHistorial.setForeground(new Color(236, 240, 241));
        tablaHistorial.setGridColor(new Color(44, 62, 80));
        tablaHistorial.setRowHeight(35);
        tablaHistorial.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tablaHistorial.setSelectionBackground(new Color(41, 128, 185));
        tablaHistorial.setSelectionForeground(Color.WHITE);

        // Encabezado de tabla
        JTableHeader header = tablaHistorial.getTableHeader();
        header.setBackground(new Color(30, 39, 46));
        header.setForeground(new Color(236, 240, 241));
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setPreferredSize(new Dimension(header.getWidth(), 40));

        // Centrar celdas y colorear según resultado
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);

                // Colorear la columna de resultado
                if (column == 4 && value != null) {
                    if (value.toString().equals("GANO")) {
                        c.setForeground(new Color(46, 204, 113));
                        setFont(new Font("Segoe UI", Font.BOLD, 13));
                    } else if (value.toString().equals("PERDIO")) {
                        c.setForeground(new Color(231, 76, 60));
                        setFont(new Font("Segoe UI", Font.BOLD, 13));
                    }
                } else {
                    c.setForeground(new Color(236, 240, 241));
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }

                if (!isSelected) {
                    c.setBackground(new Color(52, 73, 94));
                }

                return c;
            }
        };

        for (int i = 0; i < tablaHistorial.getColumnCount(); i++) {
            tablaHistorial.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tablaHistorial);
        scroll.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        panelTabla.add(scroll, BorderLayout.CENTER);

        // Botón cerrar
        btnCerrar.setBounds(325, 485, 200, 45);
        btnCerrar.setBackground(new Color(231, 76, 60));
        btnCerrar.setForeground(Color.WHITE);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCerrar.setBorder(BorderFactory.createEmptyBorder());
        btnCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(192, 57, 43));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrar.setBackground(new Color(231, 76, 60));
            }
        });
        btnCerrar.addActionListener(e -> frame.dispose());
        panelFondo.add(btnCerrar);
    }

    private void cargarDatos() {
        DefaultTableModel model = (DefaultTableModel) tablaHistorial.getModel();
        model.setRowCount(0);

        List<Resultado> historial = repositorio.obtenerHistorial();
        if (historial.isEmpty()) {
            model.addRow(new Object[] { "(sin registros)", "-", "-", "-", "-" });
            return;
        }

        for (Resultado r : historial) {
            model.addRow(new Object[] {
                    r.getFechaHora(),
                    r.getNumeroGanador(),
                    r.getTipoApuesta(),
                    "$" + r.getMontoApostado(),
                    r.isAcierto() ? "GANO" : "PERDIO"
            });
        }
    }

    public void mostrar() {
        if (!session.hayUsuario()) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión para ver el historial.", "Acceso Denegado",
                    JOptionPane.WARNING_MESSAGE);
            frame.dispose();
            return;
        }
        frame.setVisible(true);
    }
}
