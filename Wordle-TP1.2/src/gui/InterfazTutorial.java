package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import entidades.Palabra;
import entidades.Usuario;

import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class InterfazTutorial
{

	private JFrame frame;
	private Usuario usuario;
	private Palabra palabraSecreta;

	/**
	 * Create the application.
	 */
	public InterfazTutorial(String nombre, String palabraSecreta)
	{
		this.usuario = new Usuario(nombre);
		this.palabraSecreta = new Palabra(palabraSecreta);
		
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
	private void initialize()
	{
		frame = new JFrame();
		frame.setSize(1000, 722);
		frame.getContentPane().setLayout(null);
		
		if (usuario != null) 
		{
			JLabel nombreUsuario = new JLabel("Nombre: " + this.usuario.retornarNombre());
			nombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			nombreUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			nombreUsuario.setBounds(30, 51, 200, 48);
			frame.getContentPane().add(nombreUsuario);
		}
		

		JLabel etiquetaLogoTutorial = new JLabel("");
		etiquetaLogoTutorial.setIcon(new ImageIcon(InterfazJuego.class.getResource("/recursos/Logo.png")));
		etiquetaLogoTutorial.setBounds(206, 23, 600, 150);
		frame.getContentPane().add(etiquetaLogoTutorial);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane tutorialScroll = new JScrollPane();
		tutorialScroll.setBounds(141, 201, 724, 437);
		frame.getContentPane().add(tutorialScroll);
		
		JLabel etiquetaTutorial = new JLabel("");
		etiquetaTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/Tutorial.png")));
		tutorialScroll.setViewportView(etiquetaTutorial);
	
		
		JButton btnSiguienteTutorial = new JButton("Siguiente ->");
		btnSiguienteTutorial.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		btnSiguienteTutorial.setBounds(735, 676, 130, 36);
		frame.getContentPane().add(btnSiguienteTutorial);
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        btnSiguienteTutorial.addActionListener(e -> 
        {
            try 
            {
                InterfazJuego juego = new InterfazJuego(this.usuario, this.palabraSecreta);
                juego.getFrame().setVisible(true); // o tutorial.setVisible(true) según cómo la tengas hecha
                
                frame.dispose(); // opcional: cierra la ventana actual
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        });
		
	}
	
	public JFrame getFrame()
	{
	    return frame;
	}
	

}
