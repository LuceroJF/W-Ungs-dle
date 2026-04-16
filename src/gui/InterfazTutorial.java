package gui;

import javax.swing.*;
import entidades.Wungsdle;
import java.awt.*;
import java.awt.event.*;

public class InterfazTutorial extends JFrame {
	
	private InterfazInicio menuPrincipal;
	private JFrame frame;
	private Wungsdle wungsdle;
    private String volver = "";
	
    //==========================CONSTRUCTOR============================//
	public InterfazTutorial(InterfazInicio menu, Wungsdle juego) {
		menuPrincipal = menu;
		this.wungsdle = juego;
		crearInterfazTutorial();
	}

	private void crearInterfazTutorial() {

		JPanel tutorial = new JPanel();
		JLabel etiquetaLogoTutorial = new JLabel();
		JScrollPane tutorialScroll = new JScrollPane();
		JLabel etiquetaTutorial = new JLabel("");
		JButton btnVolver = new JButton("");
		
        volver = wungsdle.getTextoBotonVolver();
		btnVolver.setText(volver);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				RegresarMenuPrincipal(accion);
			}
		});

		etiquetaLogoTutorial.setBounds(378, 11, 600, 167);
		tutorialScroll.setBounds(329, 224, 699, 329);
		tutorialScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		etiquetaTutorial.setAlignmentY(0.2f);
		btnVolver.setBounds(552, 613, 263, 59);
		tutorial.setLayout(null);
		tutorial.add(etiquetaLogoTutorial);
		tutorialScroll.setViewportView(etiquetaTutorial);
		
		if(wungsdle.getIdiomaActual().startsWith("English")) {
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
		this.setBounds(0, 0, 1366,768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	//============================METODOS==============================//
	private void RegresarMenuPrincipal(ActionEvent accion) {
		menuPrincipal.setVisible(true);
		menuPrincipal.setLocationRelativeTo(null);
		this.dispose();
	}

}
