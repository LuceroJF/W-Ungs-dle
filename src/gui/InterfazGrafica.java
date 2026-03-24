package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Adjustable;
import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Juego;
import model.Jugador;

import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class InterfazGrafica {

	private JFrame frame;
	private JTextField txfNombre;
	private JScrollPane scrollPane;
	private Juego juego;
	private Jugador jugador;
	private JTextField textField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazGrafica window = new InterfazGrafica();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public InterfazGrafica() {
		initialize();
	}


	private void initialize() {
		frame = new JFrame();

		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		panel.setBounds(0, 0, 791, 553);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new CardLayout(0, 0));

		JPanel MenuPrincipal = new JPanel();
		MenuPrincipal.setBackground(new Color(255, 255, 255));
		panel.add(MenuPrincipal, "MenuPrincipal");
		MenuPrincipal.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\Eclipse\\Programacion 3\\src\\recursos\\imagenes\\wordle_og_1200x630.png"));
		lblNewLabel.setBounds(115, 11, 572, 147);
		MenuPrincipal.add(lblNewLabel);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(199, 169, 409, 354);
		MenuPrincipal.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Dificultad");
		lblNewLabel_1.setFont(new Font("Clear Sans Medium", Font.BOLD, 15));
		lblNewLabel_1.setBounds(58, 94, 113, 21);
		panel_3.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Lenguaje");
		lblNewLabel_1_1.setFont(new Font("Clear Sans Medium", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(58, 145, 113, 21);
		panel_3.add(lblNewLabel_1_1);

		JComboBox comboBoxDificultad = new JComboBox();
		comboBoxDificultad.setModel(
				new DefaultComboBoxModel(new String[] { "Facil - Easy", "Medio - Medium", "Dificil - Hard" }));
		comboBoxDificultad.setBounds(163, 95, 175, 22);
		panel_3.add(comboBoxDificultad);

		JComboBox comboBoxLenguaje = new JComboBox();
		comboBoxLenguaje.setModel(new DefaultComboBoxModel(new String[] { "Español - ES", "Ingles - EN" }));
		comboBoxLenguaje.setBounds(163, 146, 175, 22);
		panel_3.add(comboBoxLenguaje);

		txfNombre = new JTextField();
		JButton btnSiguiente = new JButton("Siguiente");

		txfNombre.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				verificar();
			}

			public void removeUpdate(DocumentEvent e) {
				verificar();
			}

			public void insertUpdate(DocumentEvent e) {
				verificar();
			}

			// Creamos una pequeña función interna para validar
			public void verificar() {
				// .trim().isEmpty() verifica si está vacío o solo tiene espacios
				if (txfNombre.getText().trim().isEmpty()) {
					btnSiguiente.setEnabled(false); // Se apaga
				} else {
					btnSiguiente.setEnabled(true); // Se prende
				}
			}
		});

		txfNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txfNombre.setFont(new Font("Luckiest Guy", Font.BOLD, 15));
		txfNombre.setBounds(58, 41, 280, 31);
		panel_3.add(txfNombre);
		txfNombre.setColumns(10);

		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Obtenemos el layout del panel padre (el que se llama 'panel')
				CardLayout cl = (CardLayout) (panel.getLayout());

				// 2. Le decimos que muestre el panel que se llama "Tutorial"
				cl.show(panel, "Tutorial");
				// Crea el jugador con el nombre introducido
				jugador = new Jugador(txfNombre.getText());
				juego = new Juego(comboBoxDificultad.getSelectedItem().toString(),
						comboBoxLenguaje.getSelectedItem().toString());
				System.out.println(jugador.mostrarNombreJugador());
				System.out.println(comboBoxDificultad.getSelectedItem().toString());
				System.out.println(comboBoxLenguaje.getSelectedItem().toString());
				System.out.println(juego.getPalabraSecreta());
				// Forzamos al scroll a subir ni bien mostramos la pantalla
				SwingUtilities.invokeLater(() -> {
					scrollPane.getVerticalScrollBar().setValue(0);
				});
			}
		});

		btnSiguiente.setBackground(new Color(119, 196, 172));
		btnSiguiente.setFont(new Font("Luckiest Guy", Font.BOLD, 15));
		btnSiguiente.setBounds(94, 233, 216, 39);
		panel_3.add(btnSiguiente);

		if (txfNombre.getText().isEmpty()) {
			btnSiguiente.setEnabled(false);
		}

		if (!txfNombre.getText().isEmpty()) {
			btnSiguiente.setEnabled(true);
		}

		/////////////////////////////////////////////////////////////////// TUTORIAL//////////////////////////////////////////////////////////////////////////////

		JPanel Tutorial = new JPanel();
		Tutorial.setName("Tutorial");
		Tutorial.setToolTipText("");
		panel.add(Tutorial, "Tutorial");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 33, 716, 400);
		JButton btnSiguienteTutorial = new JButton("Siguiente");
		btnSiguienteTutorial.setBounds(292, 469, 216, 39);
		Tutorial.setLayout(null);

		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setEnabled(false);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Tutorial.add(scrollPane);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\Eclipse\\Programacion 3\\src\\recursos\\imagenes\\1aef2b2f-6fb1-42f3-b004-716abbd618dd.png"));
		scrollPane.setViewportView(lblNewLabel_4);

		btnSiguienteTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Obtenemos el layout del panel padre (el que se llama 'panel')
				CardLayout cl = (CardLayout) (panel.getLayout());

				// 2. Le decimos que muestre el panel que se llama "Juego"
				cl.show(panel, "Juego");
			}
		});

		btnSiguienteTutorial.setFont(new Font("Luckiest Guy", Font.BOLD, 15));
		btnSiguienteTutorial.setBackground(new Color(119, 196, 172));
		Tutorial.add(btnSiguienteTutorial);

		/////////////////////////////////////////////////////////////// JUEGO//////////////////////////////////////////////////////////////

		JPanel Juego = new JPanel();
		Juego.setVisible(false);
		panel.add(Juego, "Juego");
		Juego.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(177, 222, 208));
		panel_1.setBounds(187, 63, 482, 393);
		Juego.add(panel_1);
		// panel_1.setLayout(null);
		
		final int[] filaActual = {0}; 
		final int[] colActual = {0};

		JLabel[][] casillas = new JLabel[6][5];

		panel_1.setLayout(new GridLayout(6, 5, 5, 5));

		for (int col = 0; col < 6; col++) {
			for (int fila = 0; fila < 5; fila++) {
				JLabel label = new JLabel("", SwingConstants.CENTER);

				label.setBounds(63, 31, 46, 53);

				label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				label.setOpaque(true);
				label.setBackground(Color.WHITE);
				casillas[col][fila] = label;

				panel_1.add(label);
			}
			
			
		}
		for (char c = 'A'; c <= 'Z'; c++) {
            String key = String.valueOf(c);

            panel_1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);

            panel_1.getActionMap().put(key, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(key);
                    if (colActual[0] < 5) {
                        casillas[filaActual[0]][colActual[0]].setText(key);
                        colActual[0]++;
                    }
                    
                }
            });
        }

		JLabel lblNewLabel_2 = new JLabel("PUNTAJE: ");
		lblNewLabel_2.setFont(new Font("Luckiest Guy", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 169, 21);
		Juego.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("W-ungs-dle");
		lblNewLabel_3.setFont(new Font("Luckiest Guy", Font.BOLD, 45));
		lblNewLabel_3.setBounds(304, 11, 432, 54);
		Juego.add(lblNewLabel_3);
		Juego.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panel_1, lblNewLabel_2, lblNewLabel_3}));
		

	}
}
