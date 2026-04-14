package entidades;

import gui.InterfazInicio;

public class ConfiguracionInicial {
	
	private Wungsdle wungsdle;
	
	//======================CONSTRUCTOR=========================//	
	public void crearConfiguracionInicial(Wungsdle juego) {
		
		this.wungsdle = juego;
		wungsdle.crearPalabra("Español - ES","Facil - Easy");
		System.out.println(wungsdle.getPalabraSecreta());
		InterfazInicio window = new InterfazInicio(wungsdle);
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
