package gui;

import javax.swing.*;
import java.awt.*;
import entidades.ConfiguracionInicial;
import entidades.LogicaPalabra;
import entidades.Wungsdle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazFinal extends JFrame {

	private ConfiguracionInicial configuracionInicial;
	private InterfazWungsdle interfazJuego;
	private Wungsdle wungsdle;
	private boolean gano;
	private LogicaPalabra logica;
	String botonSalirIdioma = "";
	String botonReiniciarIdioma = "";
	String mensajeFinalIdioma = "";
	String mensajeFinal2Idioma ="";
	String mensajePalabraFinalIdioma="";
	String mensajeTiempoFinalIdioma="";
	String mensajeJugadorFinalIdioma= "";
	String mensajeGanasteFinalIdioma="";
	String mensajePerdisteFinalIdioma= "";

	// =========================CONSTRUCTOR========================//
	public InterfazFinal(InterfazWungsdle juego, String usuario, String palabraSecreta, boolean gano,
			Wungsdle wungsdleJuego, LogicaPalabra logicaP) {
		logica = logicaP;
		interfazJuego = juego;
		this.wungsdle = wungsdleJuego;
		this.gano = gano;
		crearInterfazFinal();
	}

	private void crearInterfazFinal() {

		new JFrame();
		setSize(600, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// TITULO
		mensajePerdisteFinalIdioma = wungsdle.getMensajePerdisteFinalIdioma();
		mensajeGanasteFinalIdioma = wungsdle.getMensajeGanasteFinalIdioma();
		JLabel titulo = new JLabel(gano ? mensajeGanasteFinalIdioma : mensajePerdisteFinalIdioma);
		titulo.setFont(new Font("Luckiest Guy", Font.PLAIN, 30));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBounds(150, 20, 300, 50);
		getContentPane().add(titulo);

		// NOMBRE
		mensajeJugadorFinalIdioma = wungsdle.getMensajeJugadorFinalIdioma();
		JLabel nombre = new JLabel(mensajeJugadorFinalIdioma+ " " + wungsdle.getNombreUsuario());
		nombre.setBounds(180, 90, 300, 25);
		nombre.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		getContentPane().add(nombre);

		// Tiempo
		mensajeTiempoFinalIdioma = wungsdle.getMensajeTiempoFinalIdioma();
		JLabel tiempo = new JLabel(mensajeTiempoFinalIdioma+ " " + wungsdle.getTimeMilis(wungsdle.getTiempoUsuario()));
		tiempo.setBounds(180, 120, 300, 25);
		tiempo.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		getContentPane().add(tiempo);

		// PALABRA CORRECTA
		mensajePalabraFinalIdioma = wungsdle.getMensajePalabraFinalIdioma();
		JLabel palabra = new JLabel(mensajePalabraFinalIdioma+ " " + logica.getPalabraSecreta().toUpperCase());
		palabra.setBounds(150, 160, 300, 30);
		palabra.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		palabra.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(palabra);

		// MENSAJE EXTRA
		mensajeFinalIdioma = wungsdle.getMensajeFinalIdioma();
		mensajeFinal2Idioma = wungsdle.getMensajeFinal2Idioma();
		JLabel mensaje = new JLabel(gano ? mensajeFinal2Idioma : mensajeFinalIdioma);
		mensaje.setBounds(150, 200, 300, 30);
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		getContentPane().add(mensaje);

		// BOTON REINICIAR
		botonReiniciarIdioma = wungsdle.getBotonReiniciarIdioma();
		JButton btnReiniciar = new JButton(botonReiniciarIdioma);
		btnReiniciar.setBounds(200, 260, 180, 40);
		getContentPane().add(btnReiniciar);
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wungsdle wungsdle = new Wungsdle();
				wungsdle.iniciarJuego();
				interfazJuego.dispose();
				InterfazFinal.this.dispose();
			}
		});

		// BOTON SALIR
		botonSalirIdioma = wungsdle.getBotonSalirIdioma();
		JButton btnSalir = new JButton(botonSalirIdioma);
		btnSalir.setBounds(200, 320, 180, 40);
		getContentPane().add(btnSalir);

		btnSalir.addActionListener(e -> {
			System.exit(0);
		});

		// COLOR DE FONDO SEGUN RESULTADO
		if (gano) {
			this.getContentPane().setBackground(new Color(200, 255, 200)); // verde claro
		} else {
			this.getContentPane().setBackground(new Color(255, 200, 200)); // rojo claro
		}
		this.setVisible(true);
	}
}