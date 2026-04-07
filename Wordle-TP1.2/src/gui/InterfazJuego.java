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
import java.awt.Rectangle;


public class InterfazJuego {

	private JFrame frame;
	private Usuario usuario;
	private Wordle juego;
	private String palabraUsuario = "";
	private boolean juegoTerminado = false;
	


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
		frame.setSize(800, 600);
		frame.setBounds(0, 0, 1000, 800);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 791, 553);
		panel.setLayout(new CardLayout(0, 0));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		
		
		


		

		
		/////////////////////////////////////////////////////////////// JUEGO//////////////////////////////////////////////////////////////

		JPanel Wordle = new JPanel();
		Wordle.setBounds(new Rectangle(0, 0, 800, 800));
		Wordle.setVisible(false);
		panel.add(Wordle, "Juego");
		Wordle.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(253, 188, 482, 365);
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
		
		JLabel nombreUsuario = new JLabel("NOMBRE: " + this.usuario.retornarNombre());
		nombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		nombreUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		nombreUsuario.setBounds(10, 11, 169, 21);
		Wordle.add(nombreUsuario);

		JLabel etiquetaPuntosUsuario = new JLabel("PUNTAJE: " + this.juego.consultarPuntosUsuario());
		etiquetaPuntosUsuario.setBounds(10, 31, 169, 21);
		etiquetaPuntosUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		Wordle.add(etiquetaPuntosUsuario);

		JLabel etiquetaIntentoUsuario = new JLabel("INTENTOS: " + this.juego.consultarIntentoUsuario());
		etiquetaIntentoUsuario.setBounds(10, 51, 169, 21);
		etiquetaIntentoUsuario.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		Wordle.add(etiquetaIntentoUsuario);

		JLabel etiquetaNombreJuego = new JLabel("Wordle");
		etiquetaNombreJuego.setBounds(197, 23, 599, 139);
		etiquetaNombreJuego.setIcon(new ImageIcon(InterfazJuego.class.getResource("/recursos/Logo.png")));
		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		Wordle.add(etiquetaNombreJuego);



        for (char c = 'A'; c <= 'Z'; c++) {
            String key = String.valueOf(c);

            panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(KeyStroke.getKeyStroke(key), key);

            panel_1.getActionMap().put(key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (juegoTerminado) return;

                    if (filaActual[0] < 6 && colActual[0] < 5) {
                        palabraUsuario += key;
                        casillas[filaActual[0]][colActual[0]].setText(key);
                        colActual[0]++;
                    }
                }
            });
        }

        // ================== ENTER ==================
        panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("ENTER"), "enter");

        panel_1.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (palabraUsuario.length() == 5) {

                    String[] resultado = juego.evaluarIntento(palabraUsuario.toLowerCase());

                    boolean acerto = true;

                    for (int i = 0; i < 5; i++) {

                        if (resultado[i].equals("VERDE")) {
                            casillas[filaActual[0]][i].setBackground(Color.GREEN);
                        } else if (resultado[i].equals("AMARILLO")) {
                            casillas[filaActual[0]][i].setBackground(Color.YELLOW);
                            acerto = false;
                        } else {
                            casillas[filaActual[0]][i].setBackground(Color.LIGHT_GRAY);
                            acerto = false;
                        }
                    }

                    // ================== GANA ==================
                    if (acerto) {

                    	juegoTerminado = true;

               
                    	panel_1.setEnabled(false);
                    	panel_1.setFocusable(false);
                    	panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
                    	panel_1.getActionMap().clear();

                    	//
                    	palabraUsuario = "";

                    	SwingUtilities.invokeLater(() -> {
                    	    InterfazGano fin = new InterfazGano(usuario);
                    	    fin.getFrame().setVisible(true);
                    	    frame.dispose();
                    	});
                    }
                    

                    // ================== PIERDE ==================
                    if (usuario.mostrarIntento() > 0) {

                        usuario.descontarIntento();
                        etiquetaIntentoUsuario.setText("INTENTOS: " + usuario.mostrarIntento());

                        filaActual[0]++;
                        colActual[0] = 0;
                        palabraUsuario = "";

                    } else {

                    	juegoTerminado = true;

                    	panel_1.setEnabled(false);
                    	panel_1.setFocusable(false);
                    	panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
                    	panel_1.getActionMap().clear();

                    	SwingUtilities.invokeLater(() -> {
                    	    InterfazPerdio fin = new InterfazPerdio(usuario);
                    	    fin.getFrame().setVisible(true);
                    	    frame.dispose();
                    	});
                    }
                }
            }
        });

        // ================== BACKSPACE ==================
        panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke("BACK_SPACE"), "borrar");

        panel_1.getActionMap().put("borrar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (juegoTerminado) return;

                if (colActual[0] > 0) {
                    colActual[0]--;
                    casillas[filaActual[0]][colActual[0]].setText("");

                    if (!palabraUsuario.isEmpty()) {
                        palabraUsuario = palabraUsuario.substring(0, palabraUsuario.length() - 1);
                    }
                }
            }
        });

        frame.setVisible(true);
    }
    
   
    public JFrame getFrame() {
        return frame;
    }
}