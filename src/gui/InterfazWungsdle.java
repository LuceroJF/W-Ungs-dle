package gui;

import javax.swing.*;
import entidades.Wungsdle;
import java.awt.event.*;
import java.awt.*;
import org.eclipse.wb.swing.FocusTraversalOnArray;

public class InterfazWungsdle extends JFrame {

	private Wungsdle wungsdle;
	private String palabraUsuario = "";
	private boolean juegoTerminado = false;
	private JTextField txfieldNombreUsuario;
	private boolean liberarTeclado = false;
	private String nombre = "";
	private String comenzar = "";
	private String mensajeErrorNombre;

	// ====================CONSTRUCTOR=========================//
	public InterfazWungsdle(Wungsdle juego) {
		this.wungsdle = juego;
		crearInterfazWungsdle();
	}

	public void crearInterfazWungsdle() {
		JPanel panelJuego = new JPanel();
		panelJuego.setVisible(true);

		JLabel etiquetaNombreJuego = new JLabel();
		etiquetaNombreJuego.setBounds(47, 64, 599, 96);

		JLabel intentos = new JLabel("INTENTOS: " + wungsdle.consultarIntentoUsuario());
		intentos.setBounds(312, 203, 200, 20);
		intentos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JPanel panelVerde = new JPanel(new GridLayout(6, 5, 5, 5));
		panelVerde.setBounds(110, 234, 482, 393);

		JLabel[][] casillas = new JLabel[6][5];

		comenzar = wungsdle.getTextoComenzarJuego();
		nombre = wungsdle.getTextoIngresarNombre();

		long tiempoInicio = System.currentTimeMillis();

		JPanel panelNombre = new JPanel();
		panelNombre.setPreferredSize(new Dimension(300, 300));
		panelNombre.setBounds(213, 172, 889, 484);
		this.getContentPane().add(panelNombre, BorderLayout.NORTH);
		panelNombre.setLayout(null);

		txfieldNombreUsuario = new JTextField();
		txfieldNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txfieldNombreUsuario.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		txfieldNombreUsuario.setBounds(189, 174, 564, 50);

		panelNombre.add(txfieldNombreUsuario);
		txfieldNombreUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setText(nombre);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		lblNewLabel.setBounds(334, 131, 290, 32);
		panelNombre.add(lblNewLabel);

		JButton btnComenzarJuego = new JButton();
		btnComenzarJuego.setText(comenzar);
		btnComenzarJuego.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnComenzarJuego.setBounds(306, 283, 344, 40);

		// Hasta que no se presione el boton de comenzar, el juego no escucha la tecla
		// "Enter"
		btnComenzarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((txfieldNombreUsuario.getText() != null) && (txfieldNombreUsuario.getText() != "")
						&& (!txfieldNombreUsuario.getText().isEmpty() && (!txfieldNombreUsuario.getText().isBlank()
								&& txfieldNombreUsuario.getText().length() < 10))) {
					wungsdle.crearNombreUsuario(txfieldNombreUsuario.getText());
					liberarTeclado(e);
					panelNombre.setVisible(false);
					panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),
							"enter");
				} else {
					mensajeErrorNombre = wungsdle.getTextoMensajeErrorNombre();
					wungsdle.alertError(mensajeErrorNombre);
					txfieldNombreUsuario.setText("");
					throw new IllegalArgumentException(mensajeErrorNombre);
				}
			}
		});

		panelNombre.add(btnComenzarJuego);

		panelJuego.setBounds(335, 11, 679, 667);
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

		this.setBounds(0, 0, 1366, 768);
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

				/*
				 * Ahora además de verificar si lo que ingresa el usuario tiene 5 caracteres,
				 * verifica si dicha palabra existe entre las casi 2 mil palabras de los TXT
				 * (Codigo se encuentra al final de Wungsdle, idealmente hay que modificarlo,
				 * funciona pero es copia)
				 */
				
				/*
				if (palabraUsuario.length() == 5 && wungsdle.verificarSiExiste(palabraUsuario,
						wungsdle.getIdiomaActual(), wungsdle.getDificultadActual())) {
				*///<-Modificacion
				if(wungsdle.esPalabraValida(palabraUsuario)) {
					
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
						long latenciaMs = tiempoFinal-tiempoInicio;

						long minutos = (latenciaMs / 1000) / 60;
						long segundos = (latenciaMs / 1000) % 60;

						wungsdle.setTiempoRespuesta(latenciaMs);
						// punto si gana
						wungsdle.sumarPuntosGanador();
						// guardar
						wungsdle.guardarResultado();
						juegoTerminado = true;

						panelVerde.setEnabled(false);
						panelVerde.setFocusable(false);
						panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
						panelVerde.getActionMap().clear();

						palabraUsuario = "";

						SwingUtilities.invokeLater(() -> {
							InterfazFinal fin = new InterfazFinal(InterfazWungsdle.this, wungsdle.getNombreUsuario(),
									wungsdle.getPalabraSecreta(), true, wungsdle);
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
						Long latencia = tiempoFinal - tiempoInicio;
						wungsdle.setTiempoRespuesta(latencia);
						juegoTerminado = true;
						wungsdle.guardarResultado();
						panelVerde.setEnabled(false);
						panelVerde.setFocusable(false);
						panelVerde.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).clear();
						panelVerde.getActionMap().clear();

						SwingUtilities.invokeLater(() -> {
							InterfazFinal fin = new InterfazFinal(InterfazWungsdle.this, wungsdle.getNombreUsuario(),
									wungsdle.getPalabraSecreta(), false, wungsdle);
							fin.setVisible(true);

						});
					}
				} else {
					//Marca la palabra invalida en rojo y luego la setea.
					for (int i = 0; i < 5; i++) {
						casillas[filaActual[0]][i].setBackground(Color.RED);
					}
					Timer timer = new Timer(1000, new ActionListener() {
					    public void actionPerformed(ActionEvent evento) {
					        for (int i = 0; i < 5; i++) {
					            casillas[filaActual[0]][i].setBackground(Color.WHITE);
					            casillas[filaActual[0]][i].setText("");
					        }
					        palabraUsuario = "";
					        colActual[0] = 0;
					    }
					});
					timer.setRepeats(false);
					timer.start();
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
					for (int i = 0; i < 5; i++) {
						casillas[filaActual[0]][i].setBackground(Color.white);
					}

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
