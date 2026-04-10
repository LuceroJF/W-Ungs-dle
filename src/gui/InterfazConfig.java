package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import entidades.Wungsdle;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class InterfazConfig extends JFrame {

	private JFrame frame;
	private Wungsdle wordle;

	private InterfazInicio menuPrincipal;

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
		comboBox_Idioma.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JComboBox comboBox_Dificultad = new JComboBox();
		comboBox_Dificultad.setFont(new Font("Tahoma", Font.PLAIN, 20));

		panelConfiguracion.setBounds(400, 300, 846, 576);
		getContentPane().add(panelConfiguracion);
		panelConfiguracion.setLayout(null);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				volverMenuPrincipal(accion);
			}

		});

		btnVolver.setBounds(722, 416, 289, 36);
		panelConfiguracion.add(btnVolver);

		btnIdioma.setBounds(722, 120, 289, 22);
		panelConfiguracion.add(btnIdioma);

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
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordle.crearPalabra(comboBox_Idioma.getSelectedItem().toString(),
						comboBox_Dificultad.getSelectedItem().toString());

				System.out.println(wordle.getPalabraSecreta());
			}
		});

		btnGuardar.setBounds(722, 364, 289, 36);
		panelConfiguracion.add(btnGuardar);
		this.setBounds(0, 0, 1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void volverMenuPrincipal(ActionEvent accion) {
		menuPrincipal.setVisible(true);
		menuPrincipal.setLocationRelativeTo(null);
		this.dispose();
	}

}
