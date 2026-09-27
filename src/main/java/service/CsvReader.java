package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;

final class CsvReader {

    private CsvReader() {
    }

    static void leer(String nombre, int columnas, BiConsumer<Integer, String[]> procesarFila) {
        String ruta = "/csv/" + nombre;
        InputStream recurso = CsvReader.class.getResourceAsStream(ruta);
        if (recurso == null) {
            throw new IllegalStateException("No se encontró el recurso " + ruta);
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(recurso, StandardCharsets.UTF_8))) {
            lector.readLine(); // Encabezado
            String linea;
            int numeroLinea = 1;
            while ((linea = lector.readLine()) != null) {
                numeroLinea++;
                if (linea.isBlank()) {
                    continue;
                }
                String[] valores = linea.split(",", -1);
                if (valores.length != columnas) {
                    throw new IllegalArgumentException(
                            "Cantidad de columnas incorrecta en " + nombre + ", línea " + numeroLinea);
                }
                try {
                    procesarFila.accept(numeroLinea, valores);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(
                            "Número inválido en " + nombre + ", línea " + numeroLinea, e);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("No se pudo leer " + ruta, e);
        }
    }
}
