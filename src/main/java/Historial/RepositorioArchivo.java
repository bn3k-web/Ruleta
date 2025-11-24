package Historial;

import Modelo.Resultado;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements IRepositorioResultados {
    private final File archivo;

    public RepositorioArchivo(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);

        // ✅ Caso 4: Verificación de archivo
        if (!archivo.exists()) {
            try {
                File parent = archivo.getParentFile();
                if (parent != null && !parent.exists())
                    parent.mkdirs();
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        } else {
            if (!archivo.isFile()) {
                System.out.println("Advertencia: La ruta no es un archivo válido.");
            }
            if (!archivo.canRead() || !archivo.canWrite()) {
                System.out.println("Advertencia: Permisos insuficientes en el archivo.");
            }
        }
    }

    @Override
    public void agregarResultado(Resultado resultado) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
            bw.write(resultado.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar resultado: " + e.getMessage());
        }
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        List<Resultado> historial = new ArrayList<>();
        if (!archivo.exists() || !archivo.isFile())
            return historial;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty())
                    continue;

                // ✅ Caso 7: Parsing robusto
                try {
                    String[] partes = linea.split(",");
                    if (partes.length < 5) { // Asumiendo 5 campos mínimos
                        System.out.println("Línea incompleta ignorada: " + linea);
                        continue;
                    }

                    Resultado r = Resultado.fromCSV(linea);
                    historial.add(r);
                } catch (NumberFormatException | java.time.format.DateTimeParseException ex) {
                    System.out.println("Error de formato en línea: " + linea);
                } catch (Exception ex) {
                    System.out.println("Error desconocido al procesar línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer historial: " + e.getMessage());
        }
        return historial;
    }

    @Override
    public void limpiarHistorial() {
        try (PrintWriter pw = new PrintWriter(archivo)) {
        } catch (IOException e) {
            System.out.println("Error al limpiar historial: " + e.getMessage());
        }
    }
}
