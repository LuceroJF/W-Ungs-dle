package principal;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

import entidades.ConfiguracionInicial;
import entidades.Wungsdle;
import gui.InterfazInicio;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		ConfiguracionInicial configuracion = new ConfiguracionInicial();
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				
				try
				{
					Wungsdle wordle = new Wungsdle();
					configuracion.crearConfiguracionInicial(wordle);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}
}
