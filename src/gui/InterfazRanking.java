package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import entidades.Wungsdle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazRanking extends JFrame {

	private InterfazInicio menuPrincipal;
	private JFrame frame;
	private JTable table;
	private Wungsdle wordle;

	public InterfazRanking(InterfazInicio menu, Wungsdle wordle) {
		menuPrincipal = menu;
		this.wordle = wordle;
		crearInterfazRanking();
	}

	private void crearInterfazRanking() {

		JPanel panelRanking = new JPanel();

		getContentPane().add(panelRanking);
		panelRanking.setLayout(null);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Jugador", "Puntaje" }));
		table.setBounds(459, 61, 790, 522);
		panelRanking.add(table);

		frame = new JFrame();
		frame.setBounds(100, 100, 1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelRanking.setBounds(450, 100, 800, 800);

		this.getContentPane().add(panelRanking, BorderLayout.CENTER);

		JButton buttonReiniciarRanking = new JButton("");
		buttonReiniciarRanking.setBounds(678, 796, 360, 44);
		panelRanking.add(buttonReiniciarRanking);

		JButton buttonVolverMenu = new JButton("");
		buttonVolverMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent accion) {
				volverMenuPrincipal(accion);
			}
		});
		buttonVolverMenu.setBounds(678, 873, 360, 44);
		panelRanking.add(buttonVolverMenu);
		this.setBounds(0, 0, 1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void volverMenuPrincipal(ActionEvent accion) {
		menuPrincipal.setVisible(true);
		menuPrincipal.setLocationRelativeTo(null);
		this.dispose();
	}
}
