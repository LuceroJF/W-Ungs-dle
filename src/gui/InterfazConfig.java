package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import entidades.Wungsdle;

public class InterfazConfig extends JFrame {

	private Wungsdle wungsdle;
	private InterfazInicio menuPrincipal;
	private String guardar="";
	private String volver="";
	private String idioma="";
	private String dificultad="";
	private String mensaje="";
	
	//=======================CONSTRUCTOR==========================//
	public InterfazConfig(InterfazInicio menu, Wungsdle juego) {
		this.menuPrincipal = menu;
		this.wungsdle = juego;
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

		panelConfiguracion.setBounds(400, 300, 1366,768);
		getContentPane().add(panelConfiguracion);
		panelConfiguracion.setLayout(null);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				volverMenuPrincipal(accion);
			}
		});

        volver = wungsdle.getTextoBotonVolver();
        btnVolver.setText(volver);
		btnVolver.setBounds(514, 421, 289, 36);
		panelConfiguracion.add(btnVolver);

        idioma= wungsdle.getTextoIdioma();
        btnIdioma.setText(idioma);
		btnIdioma.setBounds(514, 125, 289, 22);
		panelConfiguracion.add(btnIdioma);

		dificultad=wungsdle.getTextoDificultad();
		lblNewLabel_1.setText(dificultad);
		lblNewLabel_1.setBounds(514, 201, 289, 25);
		panelConfiguracion.add(lblNewLabel_1);

		comboBox_Idioma.setModel(new DefaultComboBoxModel(new String[] { "Español - ES", "English - EN" }));
		comboBox_Idioma.setToolTipText("");
		comboBox_Idioma.setBounds(514, 158, 289, 45);
		panelConfiguracion.add(comboBox_Idioma);

		comboBox_Dificultad.setModel(
				new DefaultComboBoxModel(new String[] { "Facil - Easy", "Normal - Medium", "Dificil - Hard" }));
		comboBox_Dificultad.setBounds(514, 237, 289, 45);
		panelConfiguracion.add(comboBox_Dificultad);

		this.getContentPane().add(panelConfiguracion, BorderLayout.CENTER);

		JButton btnGuardar = new JButton("");
		guardar=wungsdle.getTextoBotonGuardar();
		btnGuardar.setText(guardar);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_Idioma.getSelectedItem().toString() == "Español - ES") {
					wungsdle.alertValidacion("Configuración guardada exitosamente");
				}
				else {
					wungsdle.alertValidacion("Configuration saved successfully");				}
				
				wungsdle.crearPalabra(comboBox_Idioma.getSelectedItem().toString(),
				comboBox_Dificultad.getSelectedItem().toString());
                wungsdle.setIdiomaActual(comboBox_Idioma.getSelectedItem().toString());
                mensaje = wungsdle.getTextoMensajeConfig();
			}
		});

		btnGuardar.setBounds(514, 369, 289, 36);
		panelConfiguracion.add(btnGuardar);
		this.setBounds(0, 0, 1366,768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//=============================METODOS==========================//
	private void volverMenuPrincipal(ActionEvent accion) {
		menuPrincipal.actualizarTextos("nuevo");
		this.dispose();
	}

}
