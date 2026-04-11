package entidades;

import java.io.*;
import java.util.*;

public class Ranking {

    // ================== OBTENER ARCHIVO ==================
    private static File obtenerArchivo() {
        File archivo = new File("puntajes.txt");
        return archivo;
    }

    // ================== GUARDAR ==================
    public static void guardarPuntaje(String nombre, int puntos, int tiempo) {
        File archivo = obtenerArchivo();

        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(nombre + "," + puntos + "," + tiempo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== LEER ==================
    public static List<String> leerPuntajes() {
        List<String> lista = new ArrayList<>();

        File archivo = obtenerArchivo();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ================== ORDENAR ==================
    public static List<String> obtenerRankingOrdenado() {

        List<String> puntajes = leerPuntajes();

        puntajes.sort((a, b) -> {
            String[] pA = a.split(",");
            String[] pB = b.split(",");

            int puntosA = Integer.parseInt(pA[1]);
            int puntosB = Integer.parseInt(pB[1]);

            if (puntosA != puntosB) {
                return puntosB - puntosA; 
            }

            int tiempoA = Integer.parseInt(pA[2]);
            int tiempoB = Integer.parseInt(pB[2]);

            return tiempoA - tiempoB; 
        });

        return puntajes;
    }

    // ================== RESETEAR ==================
    public static void resetearPuntajes() {
        File archivo = obtenerArchivo();

        try (FileWriter fw = new FileWriter(archivo)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}