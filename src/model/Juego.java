package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Juego {
	String palabraIngresadaUsuario;
	String palabraElegidaPc;
	//Todas las listas tienen 20 palabras
	List<String> palabrasFaciles = new ArrayList<>();
	List<String> palabrasMedias = new ArrayList<>();
	List<String> palabrasDificiles = new ArrayList<>();
	
	
	public Juego(String dificultad) {
	    palabrasFaciles.addAll(Arrays.asList("CASAS", "PERRO", "GATOS", "LIBRO", "PLANO", "MESAS", "SILLA", "MANOS", "RADIO", "ARBOL", "FLORES", "COCHE", "FRUTA", "JUEGO", "NUEVO", "CLASE", "MUNDO", "CAMPO", "PADRE", "MADRE"));
	    palabrasMedias.addAll(Arrays.asList("BRISA", "VOCAL", "TIGRE", "FUEGO", "GOLPE", "BARCO", "QUESO", "HUEVO", "DULCE", "CALOR", "VERDE", "BARRO", "TRUCO", "NOCHE", "PLAZA", "SUENO", "BRAZO", "JUNTO", "LEJOS", "GRITO"));
	    palabrasDificiles.addAll(Arrays.asList("AXIAL", "KIWIS", "ZORRO", "JABON", "CAUCE", "PIZCA", "REHEN", "QUINO", "NANDU", "OXIDO", "JUDIO", "MEZCLA", "FLUJO", "BODAS", "VORAZ", "GENIO", "EXITO", "BRUJO", "KOALA", "JEQUE"));
	    
	    Random rand = new Random();
	    int indiceAleatorio = rand.nextInt(palabrasFaciles.size());
	    if(dificultad == "facil") {
	    	palabraElegidaPc = palabrasFaciles.get(indiceAleatorio);
	    }
	    if(dificultad == "medio") {
	    	palabraElegidaPc = palabrasMedias.get(indiceAleatorio);
	    }
	    if(dificultad == "dificil") {
	    	palabraElegidaPc = palabrasDificiles.get(indiceAleatorio);
	    }
	}
}
