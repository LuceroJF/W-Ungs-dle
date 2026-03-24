package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Juego {
	String palabraIngresadaUsuario;
	String palabraSecreta;
	//Todas las listas tienen 20 palabras
	List<String> palabrasFaciles = new ArrayList<>();
	List<String> palabrasMedias = new ArrayList<>();
	List<String> palabrasDificiles = new ArrayList<>();
	
	
	public Juego(String dificultad, String idioma) {
		cargarPalabraSecreta(dificultad,idioma);
	}
	
	public void cargarPalabraSecreta(String dificultad, String idioma) {
		cargarPalabrasConIdiomaElegido(idioma);
		dificultadElegida(dificultad);
	}
	
	public void cargarPalabrasConIdiomaElegido(String idioma) {
		if(idioma.equals("Español - ES")) {
			palabrasFaciles.addAll(Arrays.asList("CASAS", "PERRO", "GATOS", "LIBRO", "PLANO",
					"MESAS", "SILLA", "MANOS", "RADIO", "ARBOL",
					"FLORES", "COCHE", "FRUTA", "JUEGO", "NUEVO",
					"CLASE", "MUNDO", "CAMPO", "PADRE", "MADRE"));
		    palabrasMedias.addAll(Arrays.asList("BRISA", "VOCAL", "TIGRE", "FUEGO", "GOLPE",
		    		"BARCO", "QUESO", "HUEVO", "DULCE", "CALOR",
		    		"VERDE", "BARRO", "TRUCO", "NOCHE", "PLAZA",
		    		"SUENO", "BRAZO", "JUNTO", "LEJOS", "GRITO"));
		    palabrasDificiles.addAll(Arrays.asList("AXIAL", "KIWIS", "ZORRO", "JABON", "CAUCE",
		    		"PIZCA", "REHEN", "QUINO", "NANDU", "OXIDO",
		    		"JUDIO", "MEZCLA", "FLUJO", "BODAS", "VORAZ",
		    		"GENIO", "EXITO", "BRUJO", "KOALA", "JEQUE"));
		}
		if(idioma.equals("Ingles - EN")) {
			palabrasFaciles.addAll(Arrays.asList("HOUSE", "APPLE", "SMILE", "BREAD", "LIGHT", 
				    "WATER", "NIGHT", "TABLE", "MUSIC", "GREEN", 
				    "PHONE", "CLOUD", "HEART", "MONEY", "PARTY", 
				    "PLANT", "STORY", "WORLD", "PAPER", "BEACH"));
		    palabrasMedias.addAll(Arrays.asList("BLOCK", "FLAME", "GRAPE", "BRICK", "CLOCK", 
		    	    "DREAM", "SHARK", "SNAKE", "TRAIN", "VOICE", 
		    	    "BRAVE", "FRESH", "GHOST", "LUNCH", "SHAPE", 
		    	    "TRUCK", "MOUSE", "PIANO", "STICK", "SWEET"));
		    palabrasDificiles.addAll(Arrays.asList("JAZZY", "PROXY", "WHACK", "QUART", "KNACK", 
		    	    "ZONAL", "FJORD", "LYNCH", "MYTHS", "VIXEN", 
		    	    "WALTZ", "BLITZ", "JOKER", "KYLIX", "PIXIE", 
		    	    "QUIRK", "SWIRL", "TOXIC", "YACHT", "ZEBRA"));
		}
	}
	public void dificultadElegida(String dificultad) {
		Random rand = new Random();
	    int indiceAleatorio = rand.nextInt(palabrasFaciles.size());
	    
	    if(dificultad == "Facil - Easy") {
	    	palabraSecreta = palabrasFaciles.get(indiceAleatorio);
	    }
	    if(dificultad == "Medio - Medium") {
	    	palabraSecreta = palabrasMedias.get(indiceAleatorio);
	    }
	    if(dificultad == "Dificil - Hard") {
	    	palabraSecreta = palabrasDificiles.get(indiceAleatorio);
	    }
	}
	
	/////////////////////////////////////////////////////////////////////////////////TEST///////////////////////////////////////////////////////////////////////////////////
	public String getPalabraSecreta() {
		return palabraSecreta;
	}
}
