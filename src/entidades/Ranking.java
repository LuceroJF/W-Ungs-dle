package entidades;

import java.io.*;
import java.util.*;

public class Ranking {

    // ================== OBTENER ARCHIVO ==================
    private static File getArchivo() {
        File archivo = new File("puntajes.txt");
        return archivo;
    }

    // ================== GUARDAR ==================
    public static void guardarPuntaje(String nombre, int puntos, int tiempo) {
        File archivo = getArchivo();

        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(nombre + "," + puntos + "," + tiempo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ================== LEER ==================
    public static List<String> leerArchivoRanking() {
        List<String> lista = new ArrayList<>();

        File archivo = getArchivo();

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
    public static List<String> getRankingOrdenado() {
    	
        List<String> ranking = leerArchivoRanking();

        ranking.sort((a, b) -> {
            String[] datosA = a.split(",");
            String[] datosB= b.split(",");
            
            int tiempoA = Integer.parseInt(datosA[2]);
            int tiempoB = Integer.parseInt(datosB[2]);

            return tiempoA - tiempoB; 
        });

        return ranking;
    }

    // ================== RESETEAR ==================
    public static void resetearPuntajes() {
        File archivo = getArchivo();

        try (FileWriter fw = new FileWriter(archivo)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}