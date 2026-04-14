package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entidades.Wungsdle;

public class InterfazConfig extends JFrame {

	private Wungsdle wordle;
	private InterfazInicio menuPrincipal;
	private String guardar="";
	private String volver="";
	private String idioma="";
	private String dificultad="";
	private String mensaje="";
	
	//=======================CONSTRUCTOR==========================//
	public InterfazConfig(InterfazInicio menu, Wungsdle wordle) {
		this.menuPrincipal = menu;
		this.wordle = wordle;
		crearInterfazConfiguracion();
	}

	private void crearInterfazConfiguracion() {

		JPanel panelConfiguracion = new JPanel();
		JButton btnVolver = new JButton("");
		JLabel btnIdioma = new JLabel("");
		JLabel lblNewLabel_1 = new JLabel("");
		JComboBox comboBox_Idioma = new JComboBox();
		comboBox_Idioma.setFont(new Font("Luckiest Guy", Font.PLAIN, 15));
		JComboBox comboBox_Dificultad = new JComboBox();
		comboBox_Dificultad.setFont(new Font("Luckiest Guy", Font.PLAIN, 15));

		panelConfiguracion.setBounds(400, 300, 846, 576);
		getContentPane().add(panelConfiguracion);
		panelConfiguracion.setLayout(null);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				volverMenuPrincipal(accion);
			}
		});

        volver = wordle.getTextoBotonVolver();
        btnVolver.setText(volver);
		btnVolver.setBounds(722, 416, 289, 36);
		panelConfiguracion.add(btnVolver);

        idioma= wordle.getTextoIdioma();
        btnIdioma.setText(idioma);
		btnIdioma.setBounds(722, 120, 289, 22);
		panelConfiguracion.add(btnIdioma);

		dificultad=wordle.getTextoDificultad();
		lblNewLabel_1.setText(dificultad);
		lblNewLabel_1.setBounds(722, 196, 289, 25);
		panelConfiguracion.add(lblNewLabel_1);

		comboBox_Idioma.setModel(new DefaultComboBoxModel(new String[] { "Español - ES", "English - EN" }));
		comboBox_Idioma.setToolTipText("");
		comboBox_Idioma.setBounds(722, 153, 289, 45);
		panelConfiguracion.add(comboBox_Idioma);

		comboBox_Dificultad.setModel(
				new DefaultComboBoxModel(new String[] { "Facil - Easy", "Normal - Medium", "Dificil - Hard" }));
		comboBox_Dificultad.setBounds(722, 232, 289, 45);
		panelConfiguracion.add(comboBox_Dificultad);

		this.getContentPane().add(panelConfiguracion, BorderLayout.CENTER);

		JButton btnGuardar = new JButton("");
		guardar=wordle.getTextoBotonGuardar();
		btnGuardar.setText(guardar);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordle.crearPalabra(comboBox_Idioma.getSelectedItem().toString(),
						comboBox_Dificultad.getSelectedItem().toString());
                wordle.setIdiomaActual(comboBox_Idioma.getSelectedItem().toString());
                mensaje = wordle.getTextoMensajeConfig();
                wordle.alertValidacion(mensaje);
				System.out.println(wordle.getPalabraSecreta());
			}
		});

		btnGuardar.setBounds(722, 364, 289, 36);
		panelConfiguracion.add(btnGuardar);
		this.setBounds(0, 0, 1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//=============================METODOS==========================//
	private void volverMenuPrincipal(ActionEvent accion) {
		menuPrincipal.actualizarTextos("nuevo");
		this.dispose();
	}

}
