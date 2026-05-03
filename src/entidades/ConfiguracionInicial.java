package entidades;

import gui.InterfazInicio;

public class ConfiguracionInicial {
	
	private Wungsdle wungsdle;
	private LogicaPalabra logica;
	
	//======================CONSTRUCTOR=========================//	
	public void crearConfiguracionInicial(Wungsdle juego, LogicaPalabra logicaP) {
		this.logica = logicaP;
		this.wungsdle = juego;
		logica.crearPalabra("Español - ES","Facil - Easy");
		System.out.println(logica.getPalabraSecreta());
		InterfazInicio window = new InterfazInicio(wungsdle,logica);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}
	public void crearConfiguracionInicial(Wungsdle juego, LogicaPalabra logicaP, String idiomaActualReinicio, String dificultadReinicio) {
		this.logica = logicaP;
		this.wungsdle = juego;
		logica.crearPalabra(idiomaActualReinicio, dificultadReinicio);
		InterfazInicio window = new InterfazInicio(wungsdle,logica);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}
	//=====================SETTER Y GETTERS======================//
	public Wungsdle getWungsdle() {
		return wungsdle;
	}
	
	public void setWungsdle(Wungsdle wungsdle) {
		this.wungsdle = wungsdle;
	}
}
