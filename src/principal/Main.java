package principal;

import javax.swing.*;
import entidades.ConfiguracionInicial;
import entidades.Wungsdle;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		ConfiguracionInicial configuracion = new ConfiguracionInicial();
		Wungsdle wordle = new Wungsdle();
		configuracion.crearConfiguracionInicial(wordle);
	}
}
