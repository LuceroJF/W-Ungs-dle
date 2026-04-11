package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import entidades.Wungsdle;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class InterfazWungsdle extends JFrame {

	private JFrame frame;
	private Wungsdle wungsdle;
	private String palabraUsuario = "";
	private boolean juegoTerminado = false;
	private JTextField txfieldNombreUsuario;
	private boolean liberarTeclado = false;
	private String nombre = "";
	private String comenzar = "";
	private String mensajeErrorNombre;

	public InterfazWungsdle(Wungsdle wungsdle) {
		this.wungsdle = wungsdle;
		crearInterfazWungsdle();
	}

	public void crearInterfazWungsdle() {
		JPanel panelJuego = new JPanel();
		JLabel etiquetaNombreJuego = new JLabel();
		etiquetaNombreJuego.setBounds(47, 64, 599, 96);
		JLabel intentos = new JLabel("INTENTOS: " + wungsdle.consultarIntentoUsuario());
		intentos.setBounds(312, 203, 200, 20);
		JPanel panelVerde = new JPanel(new GridLayout(6, 5, 5, 5));
		panelVerde.setBounds(110, 234, 482, 393);
		JLabel[][] casillas = new JLabel[6][5];
		comenzar = wungsdle.getTextoComenzarJuego();
		nombre = wungsdle.getTextoIngresarNombre();
		panelJuego.setVisible(true);
		intentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		long tiempoInicio = System.currentTimeMillis();

		JPanel panelNombre = new JPanel();
		panelNombre.setPreferredSize(new Dimension(300, 300));
		panelNombre.setBounds(329, 344, 889, 484);
		this.getContentPane().add(panelNombre, BorderLayout.NORTH);
		panelNombre.setLayout(null);

		txfieldNombreUsuario = new JTextField();
		txfieldNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txfieldNombreUsuario.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		txfieldNombreUsuario.setBounds(189, 174, 564, 50);
		panelNombre.add(txfieldNombreUsuario);
		txfieldNombreUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel(nombre);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		lblNewLabel.setBounds(334, 131, 290, 32);
		panelNombre.add(lblNewLabel);

		JButton btnComenzarJuego = new JButton(comenzar);
		// Hasta que no se presione el boton de comenzar, el juego no escucha la tecla
		// "Enter"
		
		btnComenzarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			  if((txfieldNombreUsuario.getText() != null) && (txfieldNombreUsuario.getText() !="") && (!txfieldNombreUsuario.getText().isEmpty() && (!txfieldNombreUsuario.getText().isBlank()))) {
			    wungsdle.asignarNombreUsuario(txfieldNombreUsuario.getText());
				liberarTeclado(e);
				panelNombre.setVisible(false);
				System.out.println(wungsdle.devolverNombreUsuario());
				panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "enter");
			}
				else {
					mensajeErrorNombre = wungsdle.getTextoMensajeErrorNombre();
					wungsdle.alertError(mensajeErrorNombre);
					throw new IllegalArgumentException(mensajeErrorNombre);
					
				}
			}
		});
		
		
		btnComenzarJuego.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnComenzarJuego.setBounds(306, 283, 344, 40);
		panelNombre.add(btnComenzarJuego);

		panelJuego.setBounds(451, 183, 679, 667);
		this.getContentPane().add(panelJuego);
		panelJuego.setLayout(null);

		panelVerde.setBackground(new Color(177, 222, 208));
		panelJuego.add(panelVerde);

		etiquetaNombreJuego.setIcon(new ImageIcon(InterfazWungsdle.class.getResource("/recursos/Logo.png")));
		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		panelJuego.add(etiquetaNombreJuego);
		panelJuego.add(intentos);

		panelJuego.setLayout(null);

		final int[] filaActual = { 0 };
		final int[] colActual = { 0 };

		for (int fila = 0; fila < 6; fila++) {
			for (int col = 0; col < 5; col++) {
				JLabel label = new JLabel("", SwingConstants.CENTER);
				label.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
				label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				label.setOpaque(true);
				label.setBackground(Color.WHITE);
				casillas[fila][col] = label;
				panelVerde.add(label);
			}
		}

		etiquetaNombreJuego.setIcon(new ImageIcon(InterfazWungsdle.class.getResource("/recursos/Logo.png")));
		etiquetaNombreJuego.setFont(new Font("Luckiest Guy", Font.BOLD, 25));
		panelJuego.add(etiquetaNombreJuego);
		this.getContentPane().setLayout(null);
		this.getContentPane()
				.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { panelNombre, txfieldNombreUsuario,
						panelJuego, panelVerde, etiquetaNombreJuego, intentos, lblNewLabel, btnComenzarJuego }));

		this.setBounds(0, 0, 1920, 1080);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ================== TECLADO ==================

		for (char c = 'A'; c <= 'Z'; c++) {

			String key = String.valueOf(c);

			panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);

			panelVerde.getActionMap().put(key, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (juegoTerminado)
						return;

					if (filaActual[0] < 6 && colActual[0] < 5 && liberarTeclado == true) {
						palabraUsuario += key;
						casillas[filaActual[0]][colActual[0]].setText(key);
						colActual[0]++;
					}
				}
			});
		}

		// ================== ENTER ==================

		panelVerde.getActionMap().put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				//// Ahora además de verificar si lo que ingresa el usuario tiene 5 caracteres,
				//// verifica si dicha palabra existe entre las
				/// casi 2 mil palabras de los TXT (Codigo se encuentra al final de Wungsdle,
				//// idealmente hay que modificarlo, funciona pero es copia)
				if (palabraUsuario.length() == 5 && wungsdle.verificarSiExiste(palabraUsuario,
						wungsdle.getIdiomaActual(), wungsdle.getDificultadActual())) {

					String[] resultado = wungsdle.evaluarColorLetra(palabraUsuario.toLowerCase());
					wungsdle.sumarPuntosPorResultado(resultado);
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
						// TIEMPO
						long tiempoFinal = System.currentTimeMillis();
						long latenciaMs = tiempoFinal - tiempoInicio;

						long minutos = (latenciaMs / 1000) / 60;
						long segundos = (latenciaMs / 1000) % 60;

						wungsdle.asignarTiempoRespuesta(latenciaMs);
						//punto si gana
                        wungsdle.sumarVictoria();
                        //guardar
                        wungsdle.guardarResultado();
						juegoTerminado = true;

						panelVerde.setEnabled(false);
						panelVerde.setFocusable(false);
						panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
						panelVerde.getActionMap().clear();

						palabraUsuario = "";

						SwingUtilities.invokeLater(() -> {
							InterfazFinal fin = new InterfazFinal(InterfazWungsdle.this,
									wungsdle.devolverNombreUsuario(), wungsdle.getPalabraSecreta(), true, wungsdle);
							fin.setVisible(true);

						});
					}

					// ================== PIERDE ==================
					if (wungsdle.consultarIntentoUsuario() > 1) {
						wungsdle.descontarIntento();
						intentos.setText("INTENTOS: " + wungsdle.consultarIntentoUsuario());

						filaActual[0]++;
						colActual[0] = 0;
						palabraUsuario = "";

					} else {

						long tiempoFinal = System.currentTimeMillis();
						Long latencia = tiempoInicio - tiempoFinal;
						System.out.println(latencia);
						wungsdle.asignarTiempoRespuesta(latencia);
						juegoTerminado = true;
                        wungsdle.guardarResultado();
						panelVerde.setEnabled(false);
						panelVerde.setFocusable(false);
						panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
						panelVerde.getActionMap().clear();

						SwingUtilities.invokeLater(() -> {
							InterfazFinal fin = new InterfazFinal(InterfazWungsdle.this,
									wungsdle.devolverNombreUsuario(), wungsdle.getPalabraSecreta(), false, wungsdle);
							fin.setVisible(true);

						});
					}
				} else {
					wungsdle.alertError("Ingrese una palabra correcta");
				}
			}
		});

		// ================== BACKSPACE ==================
		panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "borrar");

		panelVerde.getActionMap().put("borrar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (juegoTerminado)
					return;

				if (colActual[0] > 0) {
					colActual[0]--;
					casillas[filaActual[0]][colActual[0]].setText("");

					if (!palabraUsuario.isEmpty()) {
						palabraUsuario = palabraUsuario.substring(0, palabraUsuario.length() - 1);
					}
				}
			}
		});
	}


	private void liberarTeclado(ActionEvent e) {
		liberarTeclado = true;
	}
}
