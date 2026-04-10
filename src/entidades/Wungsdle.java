package entidades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import gui.InterfazInicio;

public class Wungsdle 
{
	private Usuario usuario;
	private Palabra palabra; //
	
	public Wungsdle()
	{
		this.usuario = new Usuario();
		this.palabra = new Palabra();
	}

	
	public void crearPalabra(String idiomaElegido, String dificultadElegida) {
		String rutaRecurso = rutaTxtSegunSeleccion(idiomaElegido, dificultadElegida);
		palabra.setPalabra(palabraAleatoriaDesdeRecurso(rutaRecurso));
	}
	
	public void crearUsuario(String nombre) {
		Usuario usuario = new Usuario(nombre);
	}
	
	
	
	public boolean comparaPalabraUsuario(String palabraUsuario)
	{
		return this.palabra.compararPalabra(palabraUsuario);
	}
	
	public int consultarIntentoUsuario()
	{
		return this.usuario.getIntento();
	}
	
	
	//descontar intento
	public void descontarIntento() {
		usuario.descontarIntento();
	}
	
	
	
	//Metodo para verificar la palabra
	public String[] evaluarIntento(String intento) 
	{
	    String palabraSecreta = palabra.devolverPalabra();
	    String[] resultado = new String[5];

	    for (int i = 0; i < 5; i++) 
	    {
	        char letraIntento = intento.charAt(i);
	        char letraSecreta = palabraSecreta.charAt(i);

	        if (letraIntento == letraSecreta) 
	        {
	            resultado[i] = "VERDE";
	        } 
	        else if (palabraSecreta.contains(String.valueOf(letraIntento))) 
	        {
	            resultado[i] = "AMARILLO";
	        } 
	        else 
	        {
	            resultado[i] = "GRIS";
	        }
	    }

	    return resultado;
	}
	
	//Para obtener la palabra secreta y mostarla en la pantalla final
	public String getPalabraSecreta() {
	    return palabra.devolverPalabra();
	}
	
	public String rutaTxtSegunSeleccion(String idiomaElegido, String dificultadElegida) {
		boolean esEspanol = (idiomaElegido != null) && idiomaElegido.startsWith("Español");
		boolean esFacil = (dificultadElegida != null) && dificultadElegida.startsWith("Facil");

		String lang = esEspanol ? "ES" : "EN";
		String dif = esFacil ? "listaPalabrasFaciles" : "listaPalabraSDificiles";

		return "/recursos/" + dif + "_" + lang + ".txt";
	}

	public String palabraAleatoriaDesdeRecurso(String rutaRecurso) {
		List<String> palabras = new ArrayList<>();

		try (InputStream is = InterfazInicio.class.getResourceAsStream(rutaRecurso)) {
			if (is == null) {
				throw new IllegalArgumentException("No se encontró el recurso: " + rutaRecurso);
			}

			try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
				String line;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					if (!line.isEmpty()) {

						palabras.add(line);
					}
				}
			}
		} catch (Exception ex) {
			throw new RuntimeException("Error leyendo el recurso: " + rutaRecurso, ex);
		}

		if (palabras.isEmpty()) {
			throw new IllegalStateException("El archivo está vacío: " + rutaRecurso);
		}

		int idx = ThreadLocalRandom.current().nextInt(palabras.size());
		return palabras.get(idx);
	}
	public String devolverNombreUsuario() {
		return usuario.getNombre();
	}


	public Long retornarTiempoUsuario() {
		return usuario.getTiempoRespuesta();
	}
	
	public void asignarTiempoRespuesta(Long tiempo) {
		usuario.setTiempoRespuesta(tiempo);
	}


	public void asignarNombreUsuario(String text) {
		usuario.setNombre(text);
	}


}
