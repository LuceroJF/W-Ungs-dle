package gui;

import javax.swing.*;
import entidades.Wungsdle;
import java.awt.*;
import java.awt.event.*;

public class InterfazTutorial extends JFrame {
	
	private InterfazInicio menuPrincipal;
	private JFrame frame;
	private Wungsdle wordle;
    private String volver = "";
	
	public InterfazTutorial(InterfazInicio menu, Wungsdle wordle) {
		menuPrincipal = menu;
		this.wordle = wordle;
		crearInterfazTutorial();
	}

	private void crearInterfazTutorial() {

		JPanel tutorial = new JPanel();
		JLabel etiquetaLogoTutorial = new JLabel();
		JScrollPane tutorialScroll = new JScrollPane();
		JLabel etiquetaTutorial = new JLabel("");
		JButton btnVolver = new JButton("");
		
        volver = wordle.getTextoBotonVolver();
		btnVolver.setText(volver);
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
		tutorial.setLayout(null);
		tutorial.add(etiquetaLogoTutorial);
		tutorialScroll.setViewportView(etiquetaTutorial);
		
		if(wordle.getIdiomaActual().startsWith("English")) {
			etiquetaLogoTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/Logo.png")));
			etiquetaTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/TutorialIngles.jpg")));
		}
		else {
			
			etiquetaLogoTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/LogoEspaniol.png")));
			etiquetaTutorial.setIcon(new ImageIcon(InterfazTutorial.class.getResource("/recursos/Tutorial.png")));
		}
		tutorial.add(tutorialScroll);
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
