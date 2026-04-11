package entidades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

import gui.InterfazInicio;

public class Wungsdle 
{
	private Usuario usuario;
	private Palabra palabra; //
	private String palabraUsuario;
	private String idiomaActual;
	private String dificultadActual;
	
	public Wungsdle()
	{
		usuario = new Usuario();
		palabra = new Palabra();
		idiomaActual = "Español - ES";
		dificultadActual = "Facil - Easy";
	}

	
	public void crearPalabra(String idiomaElegido, String dificultadElegida) {
		String rutaRecurso = rutaTxtSegunSeleccion(idiomaElegido, dificultadElegida);
		palabra.setPalabra(palabraAleatoriaDesdeRecurso(rutaRecurso));
		//Acá sigilosamente me robo la dificultad
		dificultadActual=dificultadElegida;
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
	

	public void descontarIntento() {
		usuario.descontarIntento();
	}
	
	
	public String[] evaluarColorLetra(String intento) 
	{
		palabraUsuario = intento;
	    String palabraSecreta = palabra.getPalabra();
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
	

	public String getPalabraSecreta() {
	    return palabra.getPalabra();
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


	public String devolverPalabraUsuario() {
		return palabraUsuario;
		
	}
	public void setIdiomaActual(String idiomaActual) {
		this.idiomaActual=idiomaActual;
		
	}
	
	public String getIdiomaActual() {
		return idiomaActual;
	}
	
	public String getDificultadActual() {
		return dificultadActual;
	}

	public String getTextoBotonInicio() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Start Game";
	    }
	    return "Iniciar Juego";
	}
	
	public String getTextoBotonVolver() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Return";
	    }
	    return "Volver";
	}

	public String getTextoConfiguracion() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Configuration";
	    }
	    return "Configuración";
	}
	public String getTextoIdioma() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Select Language";
	    }
	    return "Seleccionar Idioma";
	}
	public String getTextoDificultad() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Select Difficulty";
	    }
	    return "Seleccionar Dificultad";
	}
	
	public String getTextoIngresarNombre() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Enter your name";
	    }
	    return "Ingrese su nombre";
	}
	
	public String getTextoComenzarJuego() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Start";
	    }
	    return "Comenzar";
	}
	
	public String getTextoBotonGuardar() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Apply Configuration";
	    }
	    return "Aplicar Configuración";
	}

	
	///////////////////////////////////////////////////////////////////////////////// T E S T I N G ///////////////////////////////////////////////////////////////////////////////
	
	
	//Este hay que modificarlo porque la verdad es rarísimo, pero nada, en resumen lo que hace es verificar si la palabra que recibe está en los TXT
	public boolean verificarSiExiste(String intento, String idioma, String dificultad) {
	    String ruta = rutaTxtSegunSeleccion(idioma, dificultad);
	    try (InputStream is = InterfazInicio.class.getResourceAsStream(ruta);
	         BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
	        
	        String linea;
	        while ((linea = br.readLine()) != null) {
	            if (linea.trim().equalsIgnoreCase(intento.trim())) {
	                return true;
	            }
	        }
	    } catch (Exception ex) {
	        System.err.println("Error al validar palabra: " + ex.getMessage());
	    }
	    
	    return false;
	}
	
	
	
	///////////////////// V METODO DUPLICADO SOLO CON FINES DE TESTING V ////////////////////////////////////////////
	public String[] evaluarColorLetra(String intento, String secretaTest) 
	{
		palabraUsuario = intento;
	    String palabraSecreta = secretaTest;
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
	///////////////////// ^ METODO DUPLICADO SOLO CON FINES DE TESTING  ^ ////////////////////////////////////////////

	//////////////////////////////////////// METODOS DE UTILIDAD //////////////////////////////////////////

	public void alertError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	public void alertValidacion(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Validación",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public String getTimeMilis(Long ms) {
	    long seg = (ms / 1000) % 60;
	    long min = (ms / (1000 * 60)) % 60;
	    return String.format("%02d:%02d", min, seg);
	}


}
