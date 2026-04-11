package gui;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import entidades.ConfiguracionInicial;
import entidades.Wungsdle;
import java.awt.Component;

public class InterfazInicio extends JFrame {

	private JFrame frame;
	private Wungsdle wordle;
	private String iniciarJuego = "";
	private String configuracion = "";

	public InterfazInicio(Wungsdle wordle) {
		this.wordle = wordle;
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
		///////////////////////////////////////////////////////// BOTONES///////////////////////////////////////////////////

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

		setSize(1920, 1080);
		getContentPane().setFont(new Font("Luckiest Guy", Font.PLAIN, 11));
		getContentPane().setLayout(null);
		panel.setLayout(null);

		logo.setBounds(89, 5, 600, 315);
		panel.add(logo);
		if(wordle.getIdiomaActual().startsWith("English")) {
			logo.setIcon(new ImageIcon(InterfazInicio.class.getResource("/recursos/Logo.png")));
		}
		else {
			logo.setIcon(new ImageIcon(InterfazInicio.class.getResource("/recursos/LogoEspaniol.png")));
		}

		contenedorMenu.setBounds(99, 331, 592, 320);
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

		panel.setBounds(450, 100, 1920, 1080);
		panel.setLayout(null);

		this.getContentPane().add(panel);
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(
				new Component[] { contenedorMenu, logo, btnInicio, btnTutorial, btnConfig, btnRanking }));

		this.setBounds(0, 0, 1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void visualizarInterfazJuego(ActionEvent accion) {
		InterfazWungsdle juego = new InterfazWungsdle(wordle);
		juego.setVisible(true);
		juego.setLocationRelativeTo(null);
		this.dispose();
	}

	private void visualizarInterfazTutorial(ActionEvent accion) {
		InterfazTutorial tutorial = new InterfazTutorial(this, this.wordle);
		tutorial.setVisible(true);
		tutorial.setLocationRelativeTo(null);
		this.dispose();
	}
	
	private void visualizarInterfazConfiguracion(ActionEvent accion) {
		InterfazConfig config = new InterfazConfig(this, this.wordle);
		config.setVisible(true);
		config.setLocationRelativeTo(null);
		this.dispose();
	}

	private void visualizarInterfazRanking(ActionEvent accion) {
	    InterfazRanking ranking = new InterfazRanking(this, wordle);
	    ranking.getFrame().setVisible(true);
	    ranking.getFrame().setLocationRelativeTo(null);
	    this.dispose();
	}

	public void actualizarTextos() {
		iniciarJuego = (wordle.getTextoBotonInicio());
		configuracion = (wordle.getTextoConfiguracion());

	}

	/// Este está duplicado para diferenciarlo del que empieza con el main (el de
	/// arriba) y el que es actualizado por la configuracion (este de abajo)
	public void actualizarTextos(String nuevo) {
		iniciarJuego = (wordle.getTextoBotonInicio());
		InterfazInicio nuevaInterfazIdiomaActual = new InterfazInicio(wordle);
		nuevaInterfazIdiomaActual.setVisible(true);
		nuevaInterfazIdiomaActual.setLocationRelativeTo(null);
		this.dispose();

	}

}
