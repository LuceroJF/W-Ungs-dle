package model;

import gui.InterfazGrafica;

public class Jugador {
	String nombre;
	Integer puntaje;
	
	InterfazGrafica interfaz = new InterfazGrafica();
	
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
	}
	
	public String mostrarNombreJugador() {
		return nombre;
	}
}
