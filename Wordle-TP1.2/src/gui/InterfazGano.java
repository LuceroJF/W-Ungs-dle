package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


import entidades.Usuario;


public class InterfazGano {

	private JFrame frame;
	private Usuario usuario;
	
	public InterfazGano(Usuario usuario)
	{
		this.usuario = usuario;
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
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(1000, 800);
		frame.setBounds(0, 0, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel nombreUsuario = new JLabel("NOMBRE: " + this.usuario.retornarNombre());
		nombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		nombreUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		nombreUsuario.setBounds(10, 11, 169, 21);
		frame.getContentPane().add(nombreUsuario);
		
		JLabel etiquetaPuntosUsuario = new JLabel("PUNTAJE: " + this.usuario.retornarPuntos());
		etiquetaPuntosUsuario.setBounds(10, 30, 169, 21);
		etiquetaPuntosUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		frame.getContentPane().add(etiquetaPuntosUsuario);

		JLabel etiquetaIntentoUsuario = new JLabel("INTENTOS: " + this.usuario.mostrarIntento());
		etiquetaIntentoUsuario.setBounds(10, 49, 169, 21);
		etiquetaIntentoUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		frame.getContentPane().add(etiquetaIntentoUsuario);

		JLabel etiquetaNombreJuego = new JLabel("Wordle");
		etiquetaNombreJuego.setBounds(186, 11, 599, 157);
		etiquetaNombreJuego.setIcon(new ImageIcon(InterfazJuego.class.getResource("/recursos/Logo.png")));
		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		frame.getContentPane().add(etiquetaNombreJuego);
		
		JLabel etiquetaImagenGano = new JLabel("");
		etiquetaImagenGano.setIcon(new ImageIcon(InterfazGano.class.getResource("/recursos/ganaste.png")));
		etiquetaImagenGano.setBounds(133, 188, 691, 311);
		frame.getContentPane().add(etiquetaImagenGano);
	}

	public Window getFrame() 
	{
		return frame;
	}
}
