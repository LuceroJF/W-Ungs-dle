package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Jugador;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InterfazGrafica {

	private JFrame frame;
	private JTextField txfNombre;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the application.
	 */
	public InterfazGrafica() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 791, 553);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new CardLayout(0, 0));

		JPanel MenuPrincipal = new JPanel();
		MenuPrincipal.setBackground(new Color(255, 255, 255));
		panel.add(MenuPrincipal, "MenuPrincipal");
		MenuPrincipal.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("../wordle_og_1200x630.png"));
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
				Jugador jugador = new Jugador(txfNombre.getText());

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
		Tutorial.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 27, 716, 400);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		Tutorial.add(scrollPane);

		JTextArea txtrdeQuTrata = new JTextArea();
		txtrdeQuTrata.setText(
				"¿De qué trata Wordle?\nEl objetivo es adivinar una palabra oculta de 5 letras en un máximo de 6 intentos. Cada vez que envías una palabra, el juego te da pistas usando colores.\r\nEsta es la parte más importante. Después de cada intento, el color de los cuadritos cambiará:\r\n\r\n🟩 Verde\t¡Acierto total!\tLa letra está en la palabra y en la posición correcta.\r\n🟨 Amarillo,\tCasi...\tLa letra sí está en la palabra, pero en una posición diferente.\r\n⬛ Gris\tError\tLa letra no forma parte de la palabra oculta.\r\n\r\nReglas paso a paso\n:\r\nEscribe una palabra válida: Debes ingresar una palabra que exista (de 5 letras). \r\nNo puedes poner \"AAAAA\".\r\n\n\nPresiona Enter: Al enviar la palabra, los cuadritos se darán vuelta para mostrarte los colores.\r\n\r\n\n\nUsa las pistas: \n\n\r\nSi una letra salió gris, no la vuelvas a usar en el siguiente intento.\r\n\n\nSi salió amarilla, cámbiala de lugar.\n\n\r\nSi salió verde, déjala exactamente donde está.\r\n\r\n\n\nGanar o Perder: \n\nGanas si consigues que las 5 letras se pongan verdes antes del 6to intento.\n\nPierdes si agotas los 6 intentos sin adivinarla.\n\n\r\n\r\n🚀 Ejemplo de una partida:  \nImagina que la palabra oculta es \"TIGRE\":\n\n1er Intento: Escribes PERRO\n\nP (Gris), E (Amarillo), R (Verde), R (Gris), O (Gris).\n\nPista: Ya sabes que la R va en la tercera posición y que la E está en la palabra pero no ahí.\n\n2do Intento: Escribes TIGRE\n\nT (Verde), I (Verde), G (Verde), R (Verde), E (Verde).\n\n¡VICTORIA! 🏆\r\n");
		txtrdeQuTrata.setLineWrap(true);
		txtrdeQuTrata.setWrapStyleWord(true);
		scrollPane.setViewportView(txtrdeQuTrata);

		JButton btnNewButton_1 = new JButton("Siguiente");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Obtenemos el layout del panel padre (el que se llama 'panel')
				CardLayout cl = (CardLayout) (panel.getLayout());

				// 2. Le decimos que muestre el panel que se llama "Tutorial"
				cl.show(panel, "Juego");
			}
		});
		btnNewButton_1.setFont(new Font("Luckiest Guy", Font.BOLD, 15));
		btnNewButton_1.setBackground(new Color(119, 196, 172));
		btnNewButton_1.setBounds(292, 469, 216, 39);
		Tutorial.add(btnNewButton_1);

		/////////////////////////////////////////////////////////////// JUEGO//////////////////////////////////////////////////////////////

		JPanel Juego = new JPanel();
		panel.add(Juego, "Juego");
		Juego.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(177, 222, 208));
		panel_1.setBounds(187, 63, 434, 411);
		Juego.add(panel_1);
		panel_1.setLayout(null);

		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				formattedTextField.setText(e.getKeyText(0));
			}
		});
		formattedTextField.setEditable(false);
		formattedTextField.setBounds(63, 28, 53, 48);
		panel_1.add(formattedTextField);

		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setEditable(false);
		formattedTextField_1.setBounds(126, 28, 53, 48);
		panel_1.add(formattedTextField_1);

		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		formattedTextField_2.setEditable(false);
		formattedTextField_2.setBounds(189, 28, 53, 48);
		panel_1.add(formattedTextField_2);

		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		formattedTextField_3.setEditable(false);
		formattedTextField_3.setBounds(252, 28, 53, 48);
		panel_1.add(formattedTextField_3);

		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		formattedTextField_4.setEditable(false);
		formattedTextField_4.setBounds(315, 28, 53, 48);
		panel_1.add(formattedTextField_4);

		JFormattedTextField formattedTextField_5 = new JFormattedTextField();
		formattedTextField_5.setEditable(false);
		formattedTextField_5.setBounds(63, 82, 53, 48);
		panel_1.add(formattedTextField_5);

		JFormattedTextField formattedTextField_1_1 = new JFormattedTextField();
		formattedTextField_1_1.setEditable(false);
		formattedTextField_1_1.setBounds(126, 82, 53, 48);
		panel_1.add(formattedTextField_1_1);

		JFormattedTextField formattedTextField_2_1 = new JFormattedTextField();
		formattedTextField_2_1.setEditable(false);
		formattedTextField_2_1.setBounds(189, 82, 53, 48);
		panel_1.add(formattedTextField_2_1);

		JFormattedTextField formattedTextField_3_1 = new JFormattedTextField();
		formattedTextField_3_1.setEditable(false);
		formattedTextField_3_1.setBounds(252, 82, 53, 48);
		panel_1.add(formattedTextField_3_1);

		JFormattedTextField formattedTextField_4_1 = new JFormattedTextField();
		formattedTextField_4_1.setEditable(false);
		formattedTextField_4_1.setBounds(315, 82, 53, 48);
		panel_1.add(formattedTextField_4_1);

		JFormattedTextField formattedTextField_6 = new JFormattedTextField();
		formattedTextField_6.setEditable(false);
		formattedTextField_6.setBounds(63, 138, 53, 48);
		panel_1.add(formattedTextField_6);

		JFormattedTextField formattedTextField_1_2 = new JFormattedTextField();
		formattedTextField_1_2.setEditable(false);
		formattedTextField_1_2.setBounds(126, 138, 53, 48);
		panel_1.add(formattedTextField_1_2);

		JFormattedTextField formattedTextField_2_2 = new JFormattedTextField();
		formattedTextField_2_2.setEditable(false);
		formattedTextField_2_2.setBounds(189, 138, 53, 48);
		panel_1.add(formattedTextField_2_2);

		JFormattedTextField formattedTextField_3_2 = new JFormattedTextField();
		formattedTextField_3_2.setEditable(false);
		formattedTextField_3_2.setBounds(252, 138, 53, 48);
		panel_1.add(formattedTextField_3_2);

		JFormattedTextField formattedTextField_4_2 = new JFormattedTextField();
		formattedTextField_4_2.setEditable(false);
		formattedTextField_4_2.setBounds(315, 138, 53, 48);
		panel_1.add(formattedTextField_4_2);

		JFormattedTextField formattedTextField_7 = new JFormattedTextField();
		formattedTextField_7.setEditable(false);
		formattedTextField_7.setBounds(63, 194, 53, 48);
		panel_1.add(formattedTextField_7);

		JFormattedTextField formattedTextField_1_3 = new JFormattedTextField();
		formattedTextField_1_3.setEditable(false);
		formattedTextField_1_3.setBounds(126, 194, 53, 48);
		panel_1.add(formattedTextField_1_3);

		JFormattedTextField formattedTextField_2_3 = new JFormattedTextField();
		formattedTextField_2_3.setEditable(false);
		formattedTextField_2_3.setBounds(189, 194, 53, 48);
		panel_1.add(formattedTextField_2_3);

		JFormattedTextField formattedTextField_3_3 = new JFormattedTextField();
		formattedTextField_3_3.setEditable(false);
		formattedTextField_3_3.setBounds(252, 194, 53, 48);
		panel_1.add(formattedTextField_3_3);

		JFormattedTextField formattedTextField_4_3 = new JFormattedTextField();
		formattedTextField_4_3.setEditable(false);
		formattedTextField_4_3.setBounds(315, 194, 53, 48);
		panel_1.add(formattedTextField_4_3);

		JFormattedTextField formattedTextField_8 = new JFormattedTextField();
		formattedTextField_8.setEditable(false);
		formattedTextField_8.setBounds(63, 251, 53, 48);
		panel_1.add(formattedTextField_8);

		JFormattedTextField formattedTextField_1_4 = new JFormattedTextField();
		formattedTextField_1_4.setEditable(false);
		formattedTextField_1_4.setBounds(126, 251, 53, 48);
		panel_1.add(formattedTextField_1_4);

		JFormattedTextField formattedTextField_2_4 = new JFormattedTextField();
		formattedTextField_2_4.setEditable(false);
		formattedTextField_2_4.setBounds(189, 251, 53, 48);
		panel_1.add(formattedTextField_2_4);

		JFormattedTextField formattedTextField_3_4 = new JFormattedTextField();
		formattedTextField_3_4.setEditable(false);
		formattedTextField_3_4.setBounds(252, 251, 53, 48);
		panel_1.add(formattedTextField_3_4);

		JFormattedTextField formattedTextField_4_4 = new JFormattedTextField();
		formattedTextField_4_4.setEditable(false);
		formattedTextField_4_4.setBounds(315, 251, 53, 48);
		panel_1.add(formattedTextField_4_4);

		JLabel lblNewLabel_2 = new JLabel("PUNTAJE: ");
		lblNewLabel_2.setFont(new Font("Luckiest Guy", Font.BOLD, 20));
		lblNewLabel_2.setBounds(10, 11, 169, 21);
		Juego.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("W-ungs-dle");
		lblNewLabel_3.setFont(new Font("Luckiest Guy", Font.BOLD, 45));
		lblNewLabel_3.setBounds(304, 11, 432, 54);
		Juego.add(lblNewLabel_3);

	}

	public String devolverNombrePersonaje() {
		System.out.println(txfNombre);
		return txfNombre.getText();
	}
}
