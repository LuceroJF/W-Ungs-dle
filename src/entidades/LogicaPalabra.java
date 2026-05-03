package entidades;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Icon;
import javax.swing.SwingUtilities;
import gui.InterfazFinal;
import gui.InterfazInicio;
import gui.InterfazWungsdle;

public class LogicaPalabra {
	private Palabra palabra; 
	private String palabraUsuario;
	private String dificultadActual;
	private String idiomaActual;
	private Usuario usuario;
	private Wungsdle wungsdle;
	private boolean fueInvalido=false;
	private boolean intentoActualUsuario= true;
	private boolean[] letrasDescubiertas = new boolean[5];
	
	public LogicaPalabra(Usuario usuarioWungsdle, Palabra palabraWungsdle, Wungsdle juego)
	{
		usuario = usuarioWungsdle;
		palabra = palabraWungsdle;
		this.wungsdle = juego;
	}

	public String getPalabraSecreta() {
	    return palabra.getPalabra();
	}

	public String getPalabraUsuario() {
		return palabraUsuario;
	}
	
	public void crearPalabra(String idiomaElegido, String dificultadElegida) {
		String rutaRecurso = rutaTxtSegunSeleccion(idiomaElegido, dificultadElegida);
		palabra.setPalabra(palabraAleatoriaDesdeRecurso(rutaRecurso));
		idiomaActual=idiomaElegido;
		dificultadActual=dificultadElegida;
	}
	
	public String rutaTxtSegunSeleccion(String idiomaElegido, String dificultadElegida) {
		boolean esEspanol = (idiomaElegido != null) && idiomaElegido.startsWith("Español");
		boolean esFacil = (dificultadElegida != null) && dificultadElegida.startsWith("Facil");

		String lang = esEspanol ? "ES" : "EN";
		String dif = esFacil ? "listaPalabrasFaciles" : "listaPalabraSDificiles";

		return "/recursos/" + dif + "_" + lang + ".txt";
	}
	
    //Elige una palabra random de los txt de palabras
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
    
	public boolean comparaPalabraUsuario(String palabraUsuario){
		return this.palabra.compararPalabra(palabraUsuario);
	}
	
	public int consultarIntentoUsuario() {	
		return this.usuario.getIntento();
	}
	
	public int consultarPuntaje() {
		return usuario.getPuntos();
	}
	
	public boolean consultarIntentoUsuarioPartida(Long tiempoInicio, InterfazWungsdle juego)
	{
		boolean intentoUsuario=false;
		if (this.consultarIntentoUsuario() > 1) {
			this.descontarIntento();
			intentoUsuario=true;
		}
		else {
			long tiempoFinal = System.currentTimeMillis();
			Long latencia = tiempoFinal - tiempoInicio;
			wungsdle.setTiempoRespuesta(latencia);
			wungsdle.guardarResultado();
			SwingUtilities.invokeLater(() -> {
				InterfazFinal fin = new InterfazFinal(juego, wungsdle.getNombreUsuario(),
						this.getPalabraSecreta(), false, wungsdle, this);
				fin.setVisible(true);
			});
		}
		return intentoUsuario;
	}
	
    public boolean getIntentoInvalido(){
    	return this.fueInvalido;
    }
	
	public void intentoUsuarioValido(Long tiempoInicio, InterfazWungsdle actual) {
		// TIEMPO
		long tiempoFinal = System.currentTimeMillis();
		long latenciaMs = tiempoFinal-tiempoInicio;

		long minutos = (latenciaMs / 1000) / 60;
		long segundos = (latenciaMs / 1000) % 60;

		wungsdle.setTiempoRespuesta(latenciaMs);
		// punto si gana
		wungsdle.sumarPuntosGanador();
		// guardar
		wungsdle.guardarResultado();
		SwingUtilities.invokeLater(() -> {
			InterfazFinal fin = new InterfazFinal(actual, wungsdle.getNombreUsuario(),
			this.getPalabraSecreta(), true, wungsdle, this);
			fin.setVisible(true);
		});
	}

	public void descontarIntento() {
		usuario.descontarIntento();
	}
	
	public String[] getColorLetra(String intento)
	{
	    palabraUsuario = intento;
	    String palabraSecreta = palabra.getPalabra();
	    String[] resultado = new String[5];
	    boolean[] usadaEnSecreta = new boolean[5];

	    // Primera pasada: VERDES
	    for (int i = 0; i < 5; i++)
	    {
	        if (intento.charAt(i) == palabraSecreta.charAt(i)){
	            resultado[i] = "VERDE";
	            usadaEnSecreta[i] = true;
	            
	            if(!letrasDescubiertas[i]) {
	            	usuario.sumarPuntos(5);
	            	letrasDescubiertas[i] = true;
	            }
	        }
	    }

	    // Segunda pasada: AMARILLOS y GRIS
	    for (int i = 0; i < 5; i++)
	    {
	        if (resultado[i] != null) continue;

	        boolean encontrada = false;
	        for (int j = 0; j < 5; j++)
	        {
	            if (!usadaEnSecreta[j] && intento.charAt(i) == palabraSecreta.charAt(j))
	            {
	                resultado[i] = "AMARILLO";
	                usadaEnSecreta[j] = true;
	                encontrada = true;
	                break;
	            }
	        }
	        if (!encontrada)
	        {
	            resultado[i] = "GRIS";
	        }
	    }
	    return resultado;
	} 
    
    
    
    public boolean isPalabraValida(String palabra) {
    	return palabra.length() == 5 && verificarSiExiste(palabra, idiomaActual, dificultadActual);
    }	
	    		
	public void acertoUsuario(boolean intento) {	
		intentoActualUsuario &= intento;
	}
	    
    public boolean estadoActualPartida(String palabraUsuario, Long tiempoInicio, InterfazWungsdle actual) {

		fueInvalido=false;		
    	boolean acerto = false;
    	
		if(this.isPalabraValida(palabraUsuario)) {
			// ================== GANA ==================
			if (intentoActualUsuario) {
				intentoUsuarioValido(tiempoInicio, actual);
				acerto = true;
			}
			// ================== PIERDE ==================
			consultarIntentoUsuarioPartida(tiempoInicio, actual);
			} else {
				fueInvalido = true;
		}
		intentoActualUsuario=true;
		return acerto;
	}
	    
    //////////////////////////////////////////////////////////////////////////////////// SOLO TEST ///////////////////////////////////////////////////////
    public String[] evaluarColorLetra(String intento, String secretaTest) 
    {
	    palabraUsuario = intento;
	    String palabraSecreta = secretaTest;
	    String[] resultado = new String[5];
	    boolean[] usadaEnSecreta = new boolean[5];

	    // Primera pasada: VERDES
	    for (int i = 0; i < 5; i++)
	    {
	        if (intento.charAt(i) == palabraSecreta.charAt(i))
	        {
	            resultado[i] = "VERDE";
	            usadaEnSecreta[i] = true;
	        }
	    }

	    // Segunda pasada: AMARILLOS y GRIS
	    for (int i = 0; i < 5; i++)
	    {
	        if (resultado[i] != null) continue;

	        boolean encontrada = false;
	        for (int j = 0; j < 5; j++)
	        {
	            if (!usadaEnSecreta[j] && intento.charAt(i) == palabraSecreta.charAt(j))
	            {
	                resultado[i] = "AMARILLO";
	                usadaEnSecreta[j] = true;
	                encontrada = true;
	                break;
	            }
	        }
	        if (!encontrada)
	        {
	            resultado[i] = "GRIS";
	        }
	    }

	    return resultado;
    }

	


}
