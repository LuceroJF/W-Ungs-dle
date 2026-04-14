package entidades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import gui.InterfazInicio;

public class Wungsdle 
{
	private Usuario usuario;
	private Palabra palabra; //
	private String palabraUsuario;
	private String idiomaActual;
	private String dificultadActual;
	//===========================CONSTRUCTOR===========================//
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
		dificultadActual=dificultadElegida;
	}
	
		//============================SETTER Y GETTERS===========================//
	public String getNombreUsuario() {
		return usuario.getNombre();
	}

	public Long getTiempoUsuario() {
		return usuario.getTiempoRespuesta();
	}
	
	public void setTiempoRespuesta(Long tiempo) {
		usuario.setTiempoRespuesta(tiempo);
	}


	public void setNombreUsuario(String text) {
		usuario.setNombre(text);
	}
	
	public String getPalabraSecreta() {
	    return palabra.getPalabra();
	}
	
	public String getTimeMilis(Long ms) {
	    long seg = (ms / 1000) % 60;
	    long min = (ms / (1000 * 60)) % 60;
	    return String.format("%02d:%02d", min, seg);
	}

    public int getPuntosUsuario() {
        return usuario.getPuntos();
    }


	public String getPalabraUsuario() {
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

	public String getTextoBotonRanking() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Reset Ranking";
	    }
	    return "Reiniciar Ranking";
	}
	

	public String getTextoMensajeRanking() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Reset Done!";
	    }
	    return "Reinicio Realizado!";
	}

	public String getTextoMensajeConfig() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "New Configuration saved correctly";
	    }
	    return "Nueva Configuración guardada correctamente";
		
	}
	
	public String getTextoMensajeErrorNombre() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Incorrect name entered. Please try again.";
	    }
	    return "Nombre ingresado incorrectamente, por que favor vuelva a intentarlo";
	}
		
	//===============================METODOS=============================//	
	public void crearNombreUsuario(String nombre) {
		usuario.crearNombreUsuario(nombre);
	}
	
	public void nombreUsuarioVacio() {
		if(usuario.estaVacioNombre()) {
			throw new IllegalArgumentException("palabra vacia");
		}
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
	
	public String rutaTxtSegunSeleccion(String idiomaElegido, String dificultadElegida) {
		boolean esEspanol = (idiomaElegido != null) && idiomaElegido.startsWith("Español");
		boolean esFacil = (dificultadElegida != null) && dificultadElegida.startsWith("Facil");

		String lang = esEspanol ? "ES" : "EN";
		String dif = esFacil ? "listaPalabrasFaciles" : "listaPalabraSDificiles";

		return "/recursos/" + dif + "_" + lang + ".txt";
	}
	
    //Elige una palabra random de los txt de palabrass
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

    //Suma puntos dependiendo el resultado
    public void sumarPuntosPorResultado(String[] resultado) {
        for(int i = 0; i<resultado.length; i++) {
            if(resultado[i].equals("VERDE")) {
                usuario.sumarPuntos(3);                
            }else if(resultado[i].equals("AMARILLO")) {
                usuario.sumarPuntos(1);
            }
        }
    }
    
    //Si gana
    public void sumarPuntosGanador() {
        usuario.sumarPuntos(10);
    }

    //Guardar resultado
    public void guardarResultado() {
        String nombre = usuario.getNombre();
        int puntos = usuario.getPuntos();
        int tiempo = (int) (usuario.getTiempoRespuesta()/1000);
        
        Ranking.guardarPuntaje(nombre, puntos, tiempo);
    }
	
////////////////////////////////////////METODOS DE UTILIDAD //////////////////////////////////////////

	public void alertError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error",
		JOptionPane.ERROR_MESSAGE);
	}
	public void alertValidacion(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Validación",
		JOptionPane.INFORMATION_MESSAGE);
	}
}
