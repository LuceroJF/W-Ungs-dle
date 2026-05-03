package principal;

import javax.swing.*;
import entidades.ConfiguracionInicial;
import entidades.LogicaPalabra;
import entidades.Wungsdle;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		Wungsdle wungsdle = new Wungsdle();
		wungsdle.iniciarJuego();
	}
}
