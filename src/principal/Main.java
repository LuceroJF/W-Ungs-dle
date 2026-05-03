package principal;

import javax.swing.*;

import entidades.AudioControladora;
import entidades.ConfiguracionInicial;
import entidades.GestionSonido;
import entidades.LogicaPalabra;
import entidades.Wungsdle;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		GestionSonido.inicializar();
		Wungsdle wungsdle = new Wungsdle();
		wungsdle.iniciarJuego();
	}
}
