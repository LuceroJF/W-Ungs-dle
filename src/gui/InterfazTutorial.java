package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import entidades.Wungsdle;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazTutorial extends JFrame {
	private InterfazInicio menuPrincipal;
	private JFrame frame;
	private Wungsdle wordle;

	public InterfazTutorial(InterfazInicio menu, Wungsdle wordle) {
		menuPrincipal = menu;
		this.wordle = wordle;
		crearInterfazTutorial();
	}

	private void crearInterfazTutorial() {
		////////////////////////////////////////////////////// BOTONES/////////////////////////////////////////////////////////

		JPanel tutorial = new JPanel();
		JLabel etiquetaLogoTutorial = new JLabel();
		JScrollPane tutorialScroll = new JScrollPane();
		JLabel etiquetaTutorial = new JLabel("");
		JButton btnVolver = new JButton("");

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				RegresarMenuPrincipal(accion);
			}
		});

		etiquetaLogoTutorial.setBounds(513, 11, 600, 167);
		tutorialScroll.setBounds(450, 197, 699, 720);
		tutorialScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		etiquetaTutorial.setAlignmentY(0.2f);
		btnVolver.setBounds(686, 944, 263, 52);
		btnVolver.setText("Volver");
		tutorial.setLayout(null);
		tutorial.add(etiquetaLogoTutorial);
		etiquetaLogoTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/Logo.png")));
		tutorial.add(tutorialScroll);

		tutorialScroll.setViewportView(etiquetaTutorial);
		etiquetaTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/Tutorial.png")));
		tutorial.add(btnVolver);
		btnVolver.setFont(new Font("Luckiest Guy", Font.BOLD, 14));

		tutorial.setBounds(450, 100, 800, 800);

		this.getContentPane().add(tutorial, BorderLayout.CENTER);
		this.setBounds(0, 0, 1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void RegresarMenuPrincipal(ActionEvent accion) {
		menuPrincipal.setVisible(true);
		menuPrincipal.setLocationRelativeTo(null);
		this.dispose();
	}

}
