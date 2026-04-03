package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;

import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import entidades.Palabra;
import entidades.Usuario;
import entidades.Wordle;

import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Component;


public class InterfazJuego {

	private JFrame frame;
	private Usuario usuario;
	private Wordle juego;
	private String palabraUsuario = "";
	


	public InterfazJuego(Usuario usuario, Palabra palabra)
	{
		this.usuario = usuario;
		this.juego = new Wordle(this.usuario, palabra);
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
		
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame();

		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBounds(0, 0, 791, 553);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new CardLayout(0, 0));
		


		

		
		/////////////////////////////////////////////////////////////// JUEGO//////////////////////////////////////////////////////////////

		JPanel Wordle = new JPanel();
		Wordle.setVisible(false);
		panel.add(Wordle, "Juego");
		Wordle.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(197, 123, 482, 393);
		panel_1.setBackground(new Color(177, 222, 208));
		Wordle.add(panel_1);
		
		final int[] filaActual = {0}; 
		final int[] colActual = {0};

		JLabel[][] casillas = new JLabel[6][5];

		panel_1.setLayout(new GridLayout(6, 5, 5, 5));
		

		for (int fila = 0; fila < 6; fila++) 
		{
			for (int col = 0; col < 5; col++) 
			{
				JLabel label = new JLabel("", SwingConstants.CENTER);
				label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				label.setOpaque(true);
				label.setBackground(Color.WHITE);
				casillas[fila][col] = label;
				panel_1.add(label);
			}
		}


		JLabel etiquetaPuntosUsuario = new JLabel("PUNTAJE: " + this.juego.consultarPuntosUsuario());
		etiquetaPuntosUsuario.setBounds(10, 11, 169, 21);
		etiquetaPuntosUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		Wordle.add(etiquetaPuntosUsuario);

		JLabel etiquetaIntentoUsuario = new JLabel("INTENTOS: " + this.juego.consultarIntentoUsuario());
		etiquetaIntentoUsuario.setBounds(10, 30, 169, 21);
		etiquetaIntentoUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		Wordle.add(etiquetaIntentoUsuario);

		JLabel etiquetaNombreJuego = new JLabel("Wordle");
		etiquetaNombreJuego.setBounds(138, 9, 599, 96);
		etiquetaNombreJuego.setIcon(new ImageIcon(InterfazJuego.class.getResource("/recursos/Logo.png")));
		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		Wordle.add(etiquetaNombreJuego);



		for (char c = 'A'; c <= 'Z'; c++)
		{
		    String key = String.valueOf(c);
		    panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		           .put(KeyStroke.getKeyStroke(key), key);
		    panel_1.getActionMap().put(key, new AbstractAction()
		    {
		        @Override
		        public void actionPerformed(ActionEvent e) 
		        {
		        	if (filaActual[0] < 6 && colActual[0] < 5)
		            {
		                palabraUsuario += key;
		                casillas[filaActual[0]][colActual[0]].setText(key);
		                colActual[0]++;
		            }
		        }
		    });
		}
		
		panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
        .put(KeyStroke.getKeyStroke("ENTER"), "enter");
		panel_1.getActionMap().put("enter", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			
				if (palabraUsuario.length() == 5) 
				{
					boolean acerto = juego.comparaPalabraUsuario(palabraUsuario.toLowerCase());

					
					if(acerto)
					{
						usuario.aciertaPalabraCompleta();
						etiquetaPuntosUsuario.setText("PUNTAJE: " + usuario.retornarPuntos());						etiquetaIntentoUsuario.setText("INTENTOS: " + usuario.mostrarIntento());
						etiquetaPuntosUsuario.revalidate();						etiquetaPuntosUsuario.repaint();						etiquetaIntentoUsuario.revalidate();						etiquetaIntentoUsuario.repaint();

						System.out.println("nombre usuario " + usuario.retornarNombre() + " y los puntos del usuario son: " + usuario.retornarPuntos() + " y acerto: "  + juego.comparaPalabraUsuario(palabraUsuario.toLowerCase()));
					}
					
					else {
						
						if (usuario.mostrarIntento() > 0) 
						{
							usuario.descontarIntento();

							
							etiquetaIntentoUsuario.setText("INTENTOS: " + usuario.mostrarIntento());
							etiquetaIntentoUsuario.revalidate();
							etiquetaIntentoUsuario.repaint();

							
							filaActual[0]++;
							colActual[0] = 0;
							palabraUsuario = "";
						}
					}

					
				}
			}
		});
		

		panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
		.put(KeyStroke.getKeyStroke("BACK_SPACE"), "borrar");

		panel_1.getActionMap().put("borrar", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				
				if (colActual[0] > 0) 
				{
					colActual[0]--;

					
					casillas[filaActual[0]][colActual[0]].setText("");

					
					if (palabraUsuario.length() > 0) {
						palabraUsuario = palabraUsuario.substring(0, palabraUsuario.length() - 1);
					}
				}
			}
		});

		
		etiquetaPuntosUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		etiquetaPuntosUsuario.revalidate();
		etiquetaPuntosUsuario.repaint();
		etiquetaPuntosUsuario.setText("PUNTAJE: " + this.juego.consultarPuntosUsuario());
		Wordle.add(etiquetaPuntosUsuario);
		

		etiquetaIntentoUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		etiquetaIntentoUsuario.setText("INTENTOS: " + usuario.mostrarIntento());
		Wordle.add(etiquetaIntentoUsuario);

		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		Wordle.add(etiquetaNombreJuego);
		
	

	}



	public Window getFrame()
	{
		// TODO Auto-generated method stub
		return frame;
	}
}
