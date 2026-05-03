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
	private String idiomaActual;
	private String dificultadActual;
	private Icon getLogoIdiomaActual;

	//===========================CONSTRUCTOR===========================//
	public Wungsdle()
	{
		usuario = new Usuario();
		palabra = new Palabra();
		idiomaActual = "Español - ES";
		dificultadActual = "Facil - Easy";
		
	}
	public Wungsdle(String idiomaActualReinicio, String dificultadReinicio)
	{
		usuario = new Usuario();
		palabra = new Palabra();
		idiomaActual = idiomaActualReinicio;
		dificultadActual = dificultadReinicio;
	}
	
    public LogicaPalabra comenzarLogicaPalabra() {
    	LogicaPalabra logica = new LogicaPalabra(this.usuario, this.palabra, Wungsdle.this);
		return logica;
    }

	//============================GETTERS===========================//
	public String getNombreUsuario() {
		return usuario.getNombre();
	}

	public Long getTiempoUsuario() {
		return usuario.getTiempoRespuesta();
	}
	
	public String getTimeMilis(Long ms) {
	    long seg = (ms / 1000) % 60;
	    long min = (ms / (1000 * 60)) % 60;
	    return String.format("%02d:%02d", min, seg);
	}

    public int getPuntosUsuario() {
        return usuario.getPuntos();
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
	
	public String getIntentosIdioma() {
	    if (this.idiomaActual.startsWith("English")) {
	        return "Tries";
	    }
	    return "Intentos";
	}
	
	public String getPuntosIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "points";
		}
		return "puntos";
	}
	
	public String getTiempoIdioma() {
		if(this.idiomaActual.startsWith("English")) {
			return "Time";
		}
		return "tiempo";
	}
	public String getBotonSalirIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Exit";
		}
		return "Salir";
	}
	public String getBotonReiniciarIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Reset";
		}
		return "Reiniciar";
	}
	public String getMensajeFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Good luck next time";
		}
		return "Mejor suerte la próxima";
	}
	public String getMensajeFinal2Idioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Excelent!";
		}
		return "¡Excelente!";
	}
	public String getMensajePalabraFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "The word was";
		}
		return "La palabra era";
	}
	public String getMensajeTiempoFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Time";
		}
		return "Tiempo";
	}
	public String getMensajeJugadorFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "Player";
		}
		return "Jugador";
	}
	public String getMensajeGanasteFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "¡YOU WIN!";
		}
		return "¡GANASTE!";
	}
	public String getMensajePerdisteFinalIdioma() {
		if(this.idiomaActual.startsWith("English")){
			return "GAME OVER";
		}
		return "PERDISTE";
	}
	public String[] getColumnasRanking() {
	    if (this.idiomaActual.startsWith("English")) {
	        return new String[] {"Player", "Score", "Time"};
	    }
	    return new String[] {"Jugador", "Puntos", "Tiempo"};
	}

    public Icon getLogoDependiendoIdioma() {
		if(this.getIdiomaActual().startsWith("English")) {
			return (getLogoIdiomaActual = new ImageIcon(InterfazInicio.class.getResource("/recursos/Logo.png")));
		}
		else{
			return (getLogoIdiomaActual =new ImageIcon(InterfazInicio.class.getResource("/recursos/LogoEspaniol.png")));
		}
    }
    //============================SETTERS===========================//
    
    public void setTiempoRespuesta(Long tiempo) {
		usuario.setTiempoRespuesta(tiempo);
	}

	public void setNombreUsuario(String text) {
		usuario.setNombre(text);
	}
	
	public void setIdiomaActual(String idiomaActual) {
		this.idiomaActual=idiomaActual;
	}
		
	public void setDificultadActual(String dificultadActual) {
		this.dificultadActual=dificultadActual;
		
	}
	//===============================METODOS=============================//	
    public void iniciarJuego() {
    	ConfiguracionInicial configuracion = new ConfiguracionInicial();
		Wungsdle wungsdle = new Wungsdle();
		LogicaPalabra logicaP = wungsdle.comenzarLogicaPalabra();
		configuracion.crearConfiguracionInicial(wungsdle,logicaP);
    }
    public void iniciarJuego(String idiomaActualReinicio, String dificultadReinicio) {
    	ConfiguracionInicial configuracion = new ConfiguracionInicial();
		Wungsdle wungsdle = new Wungsdle(idiomaActualReinicio,dificultadReinicio);
		LogicaPalabra logicaP = wungsdle.comenzarLogicaPalabra();
		configuracion.crearConfiguracionInicial(wungsdle,logicaP, idiomaActualReinicio,dificultadReinicio);
    }
    
	public void crearNombreUsuario(String nombre) {
		if(nombre == null || nombre.length()==0 || nombre=="") {
			throw new IllegalArgumentException("Nombre vacio");
		}
		usuario.crearNombreUsuario(nombre);
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