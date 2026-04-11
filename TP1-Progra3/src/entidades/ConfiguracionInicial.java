package entidades;

import gui.InterfazInicio;

public class ConfiguracionInicial {
	
	private Wungsdle wungsdle;
		
	public void crearConfiguracionInicial(Wungsdle wordle) {
		
		wungsdle = wordle;
		wungsdle.crearPalabra("Español - ES","Facil - Easy");
		System.out.println(wungsdle.getPalabraSecreta());
		InterfazInicio window = new InterfazInicio(wungsdle);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		
	}
	
	
	public Wungsdle getWungsdle() {
		return wungsdle;
	}
	
	public void setWungsdle(Wungsdle wungsdle) {
		this.wungsdle = wungsdle;
	}
	
}
