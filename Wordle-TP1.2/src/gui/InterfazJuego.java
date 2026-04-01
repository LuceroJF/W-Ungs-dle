package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

import entidades.Palabra;
import entidades.Usuario;
import entidades.Wordle;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazJuego {

	private JFrame frame;
	private Usuario usuario;
	private Wordle juego;
	private JTextField textField;
	private String palabraUsuario;
	private int posicionLetras;

	/**
	 * Create the application.
	 */
	public InterfazJuego(Usuario usuario, Palabra palabra) {
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1227, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		if (usuario != null) 
		{
			JLabel nombreUsuario = new JLabel("Nombre: " + this.usuario.retornarNombre());
			nombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			nombreUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			nombreUsuario.setBounds(30, 51, 200, 48);
			frame.getContentPane().add(nombreUsuario);
			
			JLabel puntosUsuario = new JLabel("Puntaje: " + this.usuario.retornarPuntos());
			puntosUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			puntosUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			puntosUsuario.setBounds(30, 81, 200, 48);
			frame.getContentPane().add(puntosUsuario);
			
			JLabel intentoUsuario = new JLabel("Intentos: " + this.usuario.mostrarIntento());
			intentoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			intentoUsuario.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			intentoUsuario.setBounds(30, 111, 200, 48);
			frame.getContentPane().add(intentoUsuario);
		}
		
		// inicializo la palabra usuario como vacio.
		this.palabraUsuario = "";
		
		// controla en que 
		this.posicionLetras = 0;
		
		JLabel etiquetaLogoJuego = new JLabel("");
		etiquetaLogoJuego.setIcon(new ImageIcon(InterfazJuego.class.getResource("/recursos/Logo.png")));
		etiquetaLogoJuego.setBounds(275, 79, 600, 139);
		frame.getContentPane().add(etiquetaLogoJuego);
		/*
		JLabel[][] etiquetas = new JLabel[6][5];
		for(int fila = 0; fila < etiquetas.length; fila ++ )
		{
			for(int col = 0; col < etiquetas[0].length; col++)
			{
				etiquetas[fila][col] = new JLabel("");
				etiquetas[fila][col].setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				etiquetas[fila][col].setForeground(new Color(0, 0, 0));
				etiquetas[fila][col].setBackground(new Color(0, 0, 0));
				etiquetas[fila][col].setFont(new Font("Comic Sans MS", Font.BOLD, 14));
				etiquetas[fila][col].setHorizontalAlignment(SwingConstants.CENTER);
				frame.getContentPane().add(etiquetas[fila][col]);
			}
		}*/
		
		JLabel etiquetaLetra0 = new JLabel("");
		etiquetaLetra0.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra0.setForeground(new Color(0, 0, 0));
		etiquetaLetra0.setBackground(new Color(0, 0, 0));
		etiquetaLetra0.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra0.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra0.setBounds(400, 259, 56, 56);
		frame.getContentPane().add(etiquetaLetra0);
		
		JLabel etiquetaLetra1 = new JLabel("");
		etiquetaLetra1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra1.setForeground(new Color(0, 0, 0));
		etiquetaLetra1.setBackground(new Color(0, 0, 0));
		etiquetaLetra1.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra1.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra1.setBounds(470, 259, 56, 56);
		frame.getContentPane().add(etiquetaLetra1);
		
		JLabel etiquetaLetra2 = new JLabel("");
		etiquetaLetra2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra2.setForeground(new Color(0, 0, 0));
		etiquetaLetra2.setBackground(new Color(0, 0, 0));
		etiquetaLetra2.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra2.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra2.setBounds(540, 259, 56, 56);
		frame.getContentPane().add(etiquetaLetra2);
		
		JLabel etiquetaLetra3 = new JLabel("");
		etiquetaLetra3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra3.setForeground(new Color(0, 0, 0));
		etiquetaLetra3.setBackground(new Color(0, 0, 0));
		etiquetaLetra3.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra3.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra3.setBounds(610, 259, 56, 56);
		frame.getContentPane().add(etiquetaLetra3);
		
		JLabel etiquetaLetra4 = new JLabel("");
		etiquetaLetra4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra4.setForeground(new Color(0, 0, 0));
		etiquetaLetra4.setBackground(new Color(0, 0, 0));
		etiquetaLetra4.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra4.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra4.setBounds(680, 259, 56, 56);
		frame.getContentPane().add(etiquetaLetra4);
		
		JLabel etiquetaLetra5= new JLabel("");
		etiquetaLetra5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra5.setForeground(new Color(0, 0, 0));
		etiquetaLetra5.setBackground(new Color(0, 0, 0));
		etiquetaLetra5.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra5.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra5.setBounds(400, 329, 56, 56);
		frame.getContentPane().add(etiquetaLetra5);
		
		JLabel etiquetaLetra6 = new JLabel("");
		etiquetaLetra6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra6.setForeground(new Color(0, 0, 0));
		etiquetaLetra6.setBackground(new Color(0, 0, 0));
		etiquetaLetra6.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra6.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra6.setBounds(470, 329, 56, 56);
		frame.getContentPane().add(etiquetaLetra6);
		
		JLabel etiquetaLetra7 = new JLabel("");
		etiquetaLetra7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra7.setForeground(new Color(0, 0, 0));
		etiquetaLetra7.setBackground(new Color(0, 0, 0));
		etiquetaLetra7.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra7.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra7.setBounds(540, 329, 56, 56);
		frame.getContentPane().add(etiquetaLetra7);
		
		JLabel etiquetaLetra8 = new JLabel("");
		etiquetaLetra8.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra8.setForeground(new Color(0, 0, 0));
		etiquetaLetra8.setBackground(new Color(0, 0, 0));
		etiquetaLetra8.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra8.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra8.setBounds(610, 329, 56, 56);
		frame.getContentPane().add(etiquetaLetra8);
		
		JLabel etiquetaLetra9 = new JLabel("");
		etiquetaLetra9.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra9.setForeground(new Color(0, 0, 0));
		etiquetaLetra9.setBackground(new Color(0, 0, 0));
		etiquetaLetra9.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra9.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra9.setBounds(680, 329, 56, 56);
		frame.getContentPane().add(etiquetaLetra9);
		
		
		JLabel etiquetaLetra10= new JLabel("");
		etiquetaLetra10.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra10.setForeground(new Color(0, 0, 0));
		etiquetaLetra10.setBackground(new Color(0, 0, 0));
		etiquetaLetra10.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra10.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra10.setBounds(400, 399, 56, 56);
		frame.getContentPane().add(etiquetaLetra10);
		
		JLabel etiquetaLetra11 = new JLabel("");
		etiquetaLetra11.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra11.setForeground(new Color(0, 0, 0));
		etiquetaLetra11.setBackground(new Color(0, 0, 0));
		etiquetaLetra11.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra11.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra11.setBounds(470, 399, 56, 56);
		frame.getContentPane().add(etiquetaLetra11);
		
		JLabel etiquetaLetra12 = new JLabel("");
		etiquetaLetra12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra12.setForeground(new Color(0, 0, 0));
		etiquetaLetra12.setBackground(new Color(0, 0, 0));
		etiquetaLetra12.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra12.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra12.setBounds(540, 399, 56, 56);
		frame.getContentPane().add(etiquetaLetra12);
		
		JLabel etiquetaLetra13 = new JLabel("");
		etiquetaLetra13.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra13.setForeground(new Color(0, 0, 0));
		etiquetaLetra13.setBackground(new Color(0, 0, 0));
		etiquetaLetra13.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra13.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra13.setBounds(610, 399, 56, 56);
		frame.getContentPane().add(etiquetaLetra13);
		
		JLabel etiquetaLetra14 = new JLabel("");
		etiquetaLetra14.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra14.setForeground(new Color(0, 0, 0));
		etiquetaLetra14.setBackground(new Color(0, 0, 0));
		etiquetaLetra14.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra14.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra14.setBounds(680, 399, 56, 56);
		frame.getContentPane().add(etiquetaLetra14);
		
		JLabel etiquetaLetra15= new JLabel("");
		etiquetaLetra15.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra15.setForeground(new Color(0, 0, 0));
		etiquetaLetra15.setBackground(new Color(0, 0, 0));
		etiquetaLetra15.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra15.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra15.setBounds(400, 469, 56, 56);
		frame.getContentPane().add(etiquetaLetra15);
		
		JLabel etiquetaLetra16 = new JLabel("");
		etiquetaLetra16.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra16.setForeground(new Color(0, 0, 0));
		etiquetaLetra16.setBackground(new Color(0, 0, 0));
		etiquetaLetra16.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra16.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra16.setBounds(470, 469, 56, 56);
		frame.getContentPane().add(etiquetaLetra16);
		
		JLabel etiquetaLetra17 = new JLabel("");
		etiquetaLetra17.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra17.setForeground(new Color(0, 0, 0));
		etiquetaLetra17.setBackground(new Color(0, 0, 0));
		etiquetaLetra17.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra17.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra17.setBounds(540, 469, 56, 56);
		frame.getContentPane().add(etiquetaLetra17);
		
		JLabel etiquetaLetra18 = new JLabel("");
		etiquetaLetra18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra18.setForeground(new Color(0, 0, 0));
		etiquetaLetra18.setBackground(new Color(0, 0, 0));
		etiquetaLetra18.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra18.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra18.setBounds(610, 469, 56, 56);
		frame.getContentPane().add(etiquetaLetra18);
		
		JLabel etiquetaLetra19 = new JLabel("");
		etiquetaLetra19.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra19.setForeground(new Color(0, 0, 0));
		etiquetaLetra19.setBackground(new Color(0, 0, 0));
		etiquetaLetra19.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra19.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra19.setBounds(680, 469, 56, 56);
		frame.getContentPane().add(etiquetaLetra19);
		
		JLabel etiquetaLetra20= new JLabel("");
		etiquetaLetra20.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra20.setForeground(new Color(0, 0, 0));
		etiquetaLetra20.setBackground(new Color(0, 0, 0));
		etiquetaLetra20.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra20.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra20.setBounds(400, 539, 56, 56);
		frame.getContentPane().add(etiquetaLetra20);
		
		JLabel etiquetaLetra21 = new JLabel("");
		etiquetaLetra21.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra21.setForeground(new Color(0, 0, 0));
		etiquetaLetra21.setBackground(new Color(0, 0, 0));
		etiquetaLetra21.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra21.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra21.setBounds(470, 539, 56, 56);
		frame.getContentPane().add(etiquetaLetra21);
		
		JLabel etiquetaLetra22 = new JLabel("");
		etiquetaLetra22.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra22.setForeground(new Color(0, 0, 0));
		etiquetaLetra22.setBackground(new Color(0, 0, 0));
		etiquetaLetra22.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra22.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra22.setBounds(540, 539, 56, 56);
		frame.getContentPane().add(etiquetaLetra22);
		
		JLabel etiquetaLetra23 = new JLabel("");
		etiquetaLetra23.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra23.setForeground(new Color(0, 0, 0));
		etiquetaLetra23.setBackground(new Color(0, 0, 0));
		etiquetaLetra23.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra23.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra23.setBounds(610, 539, 56, 56);
		frame.getContentPane().add(etiquetaLetra23);
		
		JLabel etiquetaLetra24 = new JLabel("");
		etiquetaLetra24.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra24.setForeground(new Color(0, 0, 0));
		etiquetaLetra24.setBackground(new Color(0, 0, 0));
		etiquetaLetra24.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra24.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra24.setBounds(680, 539, 56, 56);
		frame.getContentPane().add(etiquetaLetra24);
		
		JLabel etiquetaLetra25= new JLabel("");
		etiquetaLetra25.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra25.setForeground(new Color(0, 0, 0));
		etiquetaLetra25.setBackground(new Color(0, 0, 0));
		etiquetaLetra25.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra25.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra25.setBounds(400, 609, 56, 56);
		frame.getContentPane().add(etiquetaLetra25);
		
		JLabel etiquetaLetra26 = new JLabel("");
		etiquetaLetra26.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra26.setForeground(new Color(0, 0, 0));
		etiquetaLetra26.setBackground(new Color(0, 0, 0));
		etiquetaLetra26.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra26.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra26.setBounds(470, 609, 56, 56);
		frame.getContentPane().add(etiquetaLetra26);
		
		JLabel etiquetaLetra27 = new JLabel("");
		etiquetaLetra27.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra27.setForeground(new Color(0, 0, 0));
		etiquetaLetra27.setBackground(new Color(0, 0, 0));
		etiquetaLetra27.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra27.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra27.setBounds(540, 609, 56, 56);
		frame.getContentPane().add(etiquetaLetra27);
		
		JLabel etiquetaLetra28 = new JLabel("");
		etiquetaLetra28.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra28.setForeground(new Color(0, 0, 0));
		etiquetaLetra28.setBackground(new Color(0, 0, 0));
		etiquetaLetra28.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra28.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra28.setBounds(610, 609, 56, 56);
		frame.getContentPane().add(etiquetaLetra28);
		
		JLabel etiquetaLetra29 = new JLabel("");
		etiquetaLetra29.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		etiquetaLetra29.setForeground(new Color(0, 0, 0));
		etiquetaLetra29.setBackground(new Color(0, 0, 0));
		etiquetaLetra29.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		etiquetaLetra29.setHorizontalAlignment(SwingConstants.CENTER);
		etiquetaLetra29.setBounds(680, 609, 56, 56);
		frame.getContentPane().add(etiquetaLetra29);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if(palabraUsuario.length() < 5)
				{
					palabraUsuario = palabraUsuario + e.getKeyChar();
				}
			}	

		});
		
		//Evento de escritura por el usuario.
		textField.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER && palabraUsuario.length() == 5)
				{
					if(juego.comparaPalabraUsuario(palabraUsuario))
					{
						//usuario.terminarJuego();
					}
					else
					{
						//fila ++;
						//col = 0;
						//usuario.descontarIntento();
					}
					
					
				}
			}
		});
				
		
		
		textField.setForeground(new Color(255, 255, 255));
		textField.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		textField.setBounds(400, 694, 336, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	}
	
	public JFrame getFrame() 
	{
	    return frame;
	}

}
