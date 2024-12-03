import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BaseDatos {

    private static final String ARCHIVO_BASE_DATOS = "base_de_datos.txt";
    private static JTable tabla;
    private static DefaultTableModel modeloTabla;

    // Crear la tabla gráfica en la interfaz
    public static void crearTabla() {
        String[] columnas = {"Puntaje", "Tiempo (segundos)"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);

        // Mostrar la tabla en un JFrame
        JFrame frame = new JFrame("Tabla de Puntajes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new JScrollPane(tabla));
        frame.setVisible(true);

        // Cargar los puntajes desde el archivo a la tabla
        cargarPuntajesATabla();
    }

    // Guardar un puntaje en el archivo y en la tabla
    public static void guardarPuntaje(int puntaje, int tiempo) {
        String registro = puntaje + "," + tiempo;

        // Guardar en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_BASE_DATOS, true))) {
            writer.write(registro);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar en la base de datos: " + e.getMessage());
        }

        // Agregar a la tabla gráfica
        Object[] nuevoRegistro = {puntaje, tiempo};
        modeloTabla.addRow(nuevoRegistro);
    }

    // Mostrar puntajes en consola
    public static void mostrarPuntajesEnConsola() {
        System.out.println("Puntajes almacenados:");
        List<String> registros = leerTodos();
        for (String registro : registros) {
            String[] datos = registro.split(",");
            System.out.println("Puntaje: " + datos[0] + ", Tiempo: " + datos[1] + " segundos");
        }
    }

    // Leer todos los puntajes desde el archivo
    public static List<String> leerTodos() {
        List<String> registros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO_BASE_DATOS))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                registros.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer la base de datos: " + e.getMessage());
        }
        return registros;
    }

    // Cargar puntajes desde el archivo a la tabla
    public static void cargarPuntajesATabla() {
        List<String> registros = leerTodos();
        for (String registro : registros) {
            String[] datos = registro.split(",");
            if (datos.length == 2) {
                Object[] fila = {Integer.parseInt(datos[0]), Integer.parseInt(datos[1])};
                modeloTabla.addRow(fila);
            }
        }
    }
}
