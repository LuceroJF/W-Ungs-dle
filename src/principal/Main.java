package principal;

import java.awt.*;
import java.awt.EventQueue;
import javax.swing.JFrame;

import entidades.Wungsdle;
import gui.InterfazInicio;

public class Main extends JFrame
{
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Wungsdle wungsdle = new Wungsdle();
					wungsdle.crearPalabra("Español - ES","Facil - Easy");
					wungsdle.setIdiomaActual("Español - ES");
					System.out.println(wungsdle.getPalabraSecreta());
					InterfazInicio window = new InterfazInicio(wungsdle);
					window.setVisible(true);
					window.setLocationRelativeTo(null);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}
}
