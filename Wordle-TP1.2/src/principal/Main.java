package principal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import gui.InterfazInicio;

public class Main
{
	

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InterfazInicio window = new InterfazInicio();
					window.getFrame().setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});

	}

}
