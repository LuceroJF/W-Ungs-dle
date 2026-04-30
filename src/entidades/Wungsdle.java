package entidades;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import javax.swing.*;
import javax.swing.Timer;

import gui.InterfazFinal;
import gui.InterfazInicio;
import gui.InterfazWungsdle;

public class Wungsdle 
{
	private Usuario usuario;
	private Palabra palabra; //
	private String palabraUsuario;
	private String idiomaActual;
	private String dificultadActual;
	private Icon retornarLogoIdiomaActual;
	private boolean intentoActualUsuario;
	private boolean fueInvalido=false;
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
	
	
	public String getTimeMilis(Long ms) {
	    long seg = (ms / 1000) % 60;
	    long min = (ms / (1000 * 60)) % 60;
	    return String.format("%02d:%02d", min, seg);
	}

    public int getPuntosUsuario() {
        return usuario.getPuntos();
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
	

	
	public String rutaTxtSegunSeleccion(String idiomaElegido, String dificultadElegida) {
		boolean esEspanol = (idiomaElegido != null) && idiomaElegido.startsWith("Español");
		boolean esFacil = (dificultadElegida != null) && dificultadElegida.startsWith("Facil");

		String lang = esEspanol ? "ES" : "EN";
		String dif = esFacil ? "listaPalabrasFaciles" : "listaPalabraSDificiles";

		return "/recursos/" + dif + "_" + lang + ".txt";
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
	
    
    public Icon retornarLogoIdioma() {
		if(this.getIdiomaActual().startsWith("English")) {
		return (retornarLogoIdiomaActual = new ImageIcon(InterfazInicio.class.getResource("/recursos/Logo.png")));
	}
	else {
		return (retornarLogoIdiomaActual =new ImageIcon(InterfazInicio.class.getResource("/recursos/LogoEspaniol.png")));
	}
    }
    
    


 /////////////////////////////////////////////////////////////////////////////////////// Necesario crear una nueva clase que los contenga ///////////////////////////
    
	public String getPalabraSecreta() {
	    return palabra.getPalabra();
	}

	public String getPalabraUsuario() {
		return palabraUsuario;
		
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
    
    
    
	public boolean comparaPalabraUsuario(String palabraUsuario)
	{
		return this.palabra.compararPalabra(palabraUsuario);
	}
	
	public int consultarIntentoUsuario() {	
		return this.usuario.getIntento();
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
			this.setTiempoRespuesta(latencia);
			this.guardarResultado();
			SwingUtilities.invokeLater(() -> {
				InterfazFinal fin = new InterfazFinal(juego, this.getNombreUsuario(),
						this.getPalabraSecreta(), false, this);
				fin.setVisible(true);
			});
		}
		return intentoUsuario;
	}
	
   public boolean devolverIntentoInvalido(){
	   return this.fueInvalido;
   }
	
	public void intentoUsuarioValido(Long tiempoInicio, InterfazWungsdle actual) {
		// TIEMPO
		System.out.println("llegue1");
		long tiempoFinal = System.currentTimeMillis();
		long latenciaMs = tiempoFinal-tiempoInicio;

		long minutos = (latenciaMs / 1000) / 60;
		long segundos = (latenciaMs / 1000) % 60;

		this.setTiempoRespuesta(latenciaMs);
		// punto si gana
		this.sumarPuntosGanador();
		// guardar
		this.guardarResultado();
		System.out.println("llegue2");
		SwingUtilities.invokeLater(() -> {
			InterfazFinal fin = new InterfazFinal(actual, this.getNombreUsuario(),
					this.getPalabraSecreta(), true, this);
			fin.setVisible(true);

		});
	}

	public void descontarIntento() {
		usuario.descontarIntento();
	}
	
	public String[] retornarColorLetra(String intento)
	{
	    palabraUsuario = intento;
	    String palabraSecreta = palabra.getPalabra();
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
    
    
    
    public boolean esPalabraValida(String palabra) {
    	return palabra.length() == 5 && verificarSiExiste(palabra, idiomaActual, dificultadActual);
    }	
    	
	
public void acertoUsuario(boolean intento) {
	intentoActualUsuario = intento;
}
    
    public boolean estadoActualPartida(String palabraUsuario, Long tiempoInicio, InterfazWungsdle actual) {

		fueInvalido=false;		
    	boolean acerto = false;
				if(this.esPalabraValida(palabraUsuario)) {
					System.out.println("llegue35");
					// ================== GANA ==================
					if (intentoActualUsuario) {
						System.out.println("llegue0");
                    intentoUsuarioValido(tiempoInicio, actual);
                    acerto = true;
					}

					// ================== PIERDE ==================
					consultarIntentoUsuarioPartida(tiempoInicio, actual);
				} else {
					
					fueInvalido = true;

				}
				return acerto;
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

///////////////////////////////////////// Solo TEST //////////////////////////////


public String[] evaluarColorLetra(String intento, String secretaTest) 
{
	palabraUsuario = intento;
    String palabraSecreta = secretaTest;
    String[] resultado = new String[5];
    boolean[] usadaEnSecreta = new boolean[5];

    // Verificamos los Verdes
    for (int i = 0; i < 5; i++)
    {
        if (intento.charAt(i) == palabraSecreta.charAt(i))
        {
            resultado[i] = "VERDE";
            usadaEnSecreta[i] = true;
        }
    }

    // Verificamos los Amarillos Y Grises
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