package gui;

import javax.swing.*;

import entidades.LogicaPalabra;
import entidades.Usuario;
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
	private LogicaPalabra logica;
	String intentosIdioma = "";
	String puntajeIdioma = "";
	String tiempoIdioma = "";
	private Icon logoActual;
	private Timer contador;
	private long tiempoInicio;

	// ====================CONSTRUCTOR=========================//
	public InterfazWungsdle(Wungsdle juego, LogicaPalabra logicaP) {
		this.wungsdle = juego;
		this.logica = logicaP;
		logoActual = wungsdle.getLogoDependiendoIdioma();
		crearInterfazWungsdle();
	}

	public void crearInterfazWungsdle() {
		JPanel panelJuego = new JPanel();
		panelJuego.setVisible(true);
		// LOGO JUEGO
		JLabel lblNombreJuego = new JLabel();
		lblNombreJuego.setIcon(logoActual);
		lblNombreJuego.setBounds(47, 64, 599, 96);
		// LABEL INTENTOS
		intentosIdioma = wungsdle.getIntentosIdioma();
		JLabel intentos = new JLabel(intentosIdioma + " " + logica.consultarIntentoUsuario());
		intentos.setBounds(478, 203, 114, 30);
		intentos.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));

		JPanel panelGrillaJuego = new JPanel(new GridLayout(6, 5, 5, 5));
		panelGrillaJuego.setBounds(110, 234, 482, 393);

		JLabel[][] celdas = new JLabel[6][5];

		comenzar = wungsdle.getTextoComenzarJuego();
		nombre = wungsdle.getTextoIngresarNombre();

		// PANEL NOMBRE
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
		// BOTON COMENZAR
		JButton btnComenzarJuego = new JButton();
		btnComenzarJuego.setText(comenzar);
		btnComenzarJuego.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnComenzarJuego.setBounds(306, 283, 344, 40);

		txfieldNombreUsuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnComenzarJuego.doClick();
                }
            }
        });
		puntajeIdioma = wungsdle.getPuntosIdioma();
		JLabel puntaje = new JLabel(puntajeIdioma + " " + logica.consultarPuntaje());
		puntaje.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		puntaje.setBounds(319, 203, 125, 30);
		panelJuego.add(puntaje);

		// LABEL TIEMPO
		tiempoIdioma = wungsdle.getTiempoIdioma();
		JLabel tiempo = new JLabel();
		tiempo.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		tiempo.setText("<dynamic> 0");
		tiempo.setBounds(106, 203, 172, 30);
		panelJuego.add(tiempo);

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
					// Inicializador de reloj

					tiempo.setText(tiempoIdioma + ": 00:00");

					InterfazWungsdle.this.tiempoInicio = System.currentTimeMillis();

					contador = new Timer(1000, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							long diferencia = System.currentTimeMillis() - tiempoInicio;
							tiempo.setText(tiempoIdioma + ": " + wungsdle.getTimeMilis(diferencia));
						}
					});

					contador.start();

					panelGrillaJuego.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"),
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
		panelGrillaJuego.setBackground(new Color(177, 222, 208));
		panelJuego.add(panelGrillaJuego);
		panelJuego.add(lblNombreJuego);
		panelJuego.add(intentos);
		panelJuego.add(puntaje);
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
				celdas[fila][col] = label;
				panelGrillaJuego.add(label);
			}
		}
		this.getContentPane().setLayout(null);
		getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { panelNombre, txfieldNombreUsuario, panelJuego,
						panelGrillaJuego, lblNombreJuego, intentos, lblNewLabel, btnComenzarJuego, puntaje, tiempo }));
		this.setBounds(0, 0, 1366, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ================== TECLADO ==================

		for (char c = 'A'; c <= 'Z'; c++) {

			String key = String.valueOf(c);

			panelGrillaJuego.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(key), key);

			panelGrillaJuego.getActionMap().put(key, new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (juegoTerminado) {
						contador.stop();
						return;
					}
					if (filaActual[0] < 6 && colActual[0] < 5 && liberarTeclado == true) {
						palabraUsuario += key;
						celdas[filaActual[0]][colActual[0]].setText(key);
						colActual[0]++;
					}
				}
			});
		}

		// ================== ENTER ==================

		panelGrillaJuego.getActionMap().put("enter", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] resultado = logica.getColorLetra(palabraUsuario.toLowerCase());
				for (int i = 0; i < 5; i++) {
					if (resultado[i].equals("VERDE")) {
						celdas[filaActual[0]][i].setBackground(Color.GREEN);
						logica.acertoUsuario(true);
					} else if (resultado[i].equals("AMARILLO")) {
						celdas[filaActual[0]][i].setBackground(Color.YELLOW);
						logica.acertoUsuario(false);
					} else {
						celdas[filaActual[0]][i].setBackground(Color.LIGHT_GRAY);
						logica.acertoUsuario(false);
					}
				}
				juegoTerminado |= logica.estadoActualPartida(palabraUsuario, tiempoInicio, InterfazWungsdle.this);
				if (logica.getIntentoInvalido() == true) {
					// Marca la palabra invalida en rojo y luego la setea.
					for (int i = 0; i < 5; i++) {
						celdas[filaActual[0]][i].setBackground(Color.RED);
					}
					Timer timer = new Timer(1000, new ActionListener() {
						public void actionPerformed(ActionEvent evento) {
							for (int i = 0; i < 5; i++) {
								celdas[filaActual[0]][i].setBackground(Color.WHITE);
								celdas[filaActual[0]][i].setText("");
							}
							palabraUsuario = "";
							colActual[0] = 0;
						}
					});
					timer.setRepeats(false);
					timer.start();
					return;
				}
				intentos.setText(intentosIdioma + " " + logica.consultarIntentoUsuario());
				puntaje.setText(puntajeIdioma + " " + logica.consultarPuntaje());
				filaActual[0]++;
				colActual[0] = 0;
				palabraUsuario = "";
			}
		});

		// ================== BACKSPACE ==================
		panelGrillaJuego.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"),
				"borrar");

		panelGrillaJuego.getActionMap().put("borrar", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (juegoTerminado)
					return;
				if (colActual[0] > 0) {
					colActual[0]--;
					celdas[filaActual[0]][colActual[0]].setText("");
					for (int i = 0; i < 5; i++) {
						celdas[filaActual[0]][i].setBackground(Color.white);
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
