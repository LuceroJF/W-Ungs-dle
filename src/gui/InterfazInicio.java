package gui;

import javax.swing.JFrame;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.*;
import javax.swing.*;
import java.awt.Button;
import java.awt.event.*;

import entidades.LogicaPalabra;
import entidades.Wungsdle;

public class InterfazInicio extends JFrame {

	private Wungsdle wungsdle;
	private String iniciarJuego = "";
	private String configuracion = "";
	private Icon logoActual;
	private LogicaPalabra logica;

	//========================CONSTRUCTOR=========================//
	public InterfazInicio(Wungsdle juego, LogicaPalabra logicaP) {
		this.logica=logicaP;
		this.wungsdle = juego;
		logoActual= wungsdle.retornarLogoIdioma();
		crearInterfazInicio();
	}

	private void crearInterfazInicio() {

		JPanel panel = new JPanel();
		JLabel logo = new JLabel();
		JPanel contenedorMenu = new JPanel();
		Button btnInicio = new Button();
		Button btnTutorial = new Button();
		Button btnConfig = new Button();
		Button btnRanking = new Button();
		actualizarTextos();

		
		btnInicio.setLabel(iniciarJuego);
		btnInicio.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				visualizarInterfazJuego(accion);
			}
		});
		btnTutorial.setLabel("Tutorial");
		btnTutorial.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				visualizarInterfazTutorial(accion);
			}

		});
		btnConfig.setLabel(configuracion);
		btnConfig.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				visualizarInterfazConfiguracion(accion);
			}

		});
		btnRanking.setLabel("Ranking");
		btnRanking.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				visualizarInterfazRanking(accion);
			}

		});

		setSize(1366,768);
		getContentPane().setFont(new Font("Luckiest Guy", Font.PLAIN, 11));
		getContentPane().setLayout(null);
		panel.setLayout(null);

		logo.setBounds(160, 135, 600, 315);
		panel.add(logo);
		logo.setIcon(logoActual);

		contenedorMenu.setBounds(168, 437, 592, 320);
		panel.add(contenedorMenu);
		contenedorMenu.setLayout(null);

		btnInicio.setBounds(157, 68, 293, 41);
		contenedorMenu.add(btnInicio);

		btnTutorial.setBounds(157, 119, 293, 41);
		contenedorMenu.add(btnTutorial);

		btnConfig.setBounds(157, 176, 293, 41);
		contenedorMenu.add(btnConfig);

		btnRanking.setBounds(157, 232, 293, 41);
		contenedorMenu.add(btnRanking);
		contenedorMenu.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { btnInicio, btnTutorial, btnConfig, btnRanking }));

		panel.setBounds(222, -178, 1366,768);
		panel.setLayout(null);

		this.getContentPane().add(panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { contenedorMenu, logo, btnInicio, btnTutorial, btnConfig, btnRanking }));

		this.setBounds(0, 0, 1366,768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	//==============================METODOS==========================//
	private void visualizarInterfazJuego(ActionEvent accion) {
		InterfazWungsdle juego = new InterfazWungsdle(wungsdle, logica);
		juego.setVisible(true);
		juego.setLocationRelativeTo(null);
		this.dispose();
	}

	private void visualizarInterfazTutorial(ActionEvent accion) {
		InterfazTutorial tutorial = new InterfazTutorial(this, this.wungsdle);
		tutorial.setVisible(true);
		tutorial.setLocationRelativeTo(null);
		this.dispose();
	}
	
	private void visualizarInterfazConfiguracion(ActionEvent accion) {
		InterfazConfig config = new InterfazConfig(this, this.wungsdle);
		config.setVisible(true);
		config.setLocationRelativeTo(null);
		this.dispose();
	}

	private void visualizarInterfazRanking(ActionEvent accion) {
	    InterfazRanking ranking = new InterfazRanking(this, wungsdle);
	    ranking.setVisible(true);
	    ranking.setLocationRelativeTo(null);
	    this.dispose();
	}

	public void actualizarTextos() {
		iniciarJuego = (wungsdle.getTextoBotonInicio());
		configuracion = (wungsdle.getTextoConfiguracion());
	}

	/// Este está duplicado para diferenciarlo del que empieza con el main (el de
	/// arriba) y el que es actualizado por la configuracion (este de abajo)
	public void actualizarTextos(String nuevo) {
		iniciarJuego = (wungsdle.getTextoBotonInicio());
		InterfazInicio nuevaInterfazIdiomaActual = new InterfazInicio(wungsdle,logica);
		nuevaInterfazIdiomaActual.setVisible(true);
		nuevaInterfazIdiomaActual.setLocationRelativeTo(null);
		this.dispose();
	}

}
