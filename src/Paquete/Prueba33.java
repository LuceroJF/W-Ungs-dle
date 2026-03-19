package Paquete;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;

public class Prueba33 {

	//
//	private JFrame frame;
//	private JTextField value1;
//	private JTextField value2;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Prueba33 window = new Prueba33();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the application.
//	 */
//	public Prueba33() {
//		initialize();
//	}
//
//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		frame = new JFrame();
//		frame.setBounds(100, 100, 450, 300);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//		
//		JLabel sadsa = new JLabel("Value");
//		sadsa.setBounds(10, 11, 69, 24);
//		frame.getContentPane().add(sadsa);
//		
//		JLabel sad = new JLabel("Value2");
//		sad.setBounds(10, 58, 46, 14);
//		frame.getContentPane().add(sad);
//		
//		JLabel lblNewLabel_2 = new JLabel("Res");
//		lblNewLabel_2.setBounds(10, 117, 46, 14);
//		frame.getContentPane().add(lblNewLabel_2);
//		
//		value1 = new JTextField();
//		value1.setBounds(50, 13, 86, 20);
//		frame.getContentPane().add(value1);
//		value1.setColumns(10);
//		
//		value2 = new JTextField();
//		value2.setBounds(50, 55, 86, 20);
//		frame.getContentPane().add(value2);
//		value2.setColumns(10);
//		
//		JTextArea res = new JTextArea();
//		res.setBounds(50, 112, 86, 22);
//		frame.getContentPane().add(res);
//
// 
//        JButton buttonCalc = new JButton("Calc");
//        buttonCalc.setBounds(219, 114, 84, 20);
//        frame.getContentPane().add(buttonCalc);
//		
//        buttonCalc.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent arg0) {
//
//                String s1 = value1.getText();
//                String s2 = value2.getText();
//
//                double a = Double.parseDouble(s1);
//                double b = Double.parseDouble(s2);
//
//                res.setText(Double.toString(a+b));
//                
//            }
//
//
//        });
//
//
//
//
//
//
//    
//	}


	    public static void main(String[] args) {
	        JFrame frame = new JFrame("Ejemplo");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 300);

	        // Main
	        JPanel contenedor = new JPanel(new CardLayout());

	        // P1 
	        JPanel panel1 = new JPanel();
	        JButton btnSiguiente = new JButton("Siguiente");

	        panel1.add(new JLabel("Pantalla 1"));
	        panel1.add(btnSiguiente);

	        // P2
	        JPanel panel2 = new JPanel();
	        panel2.add(new JLabel("Pantalla 2"));

	        // Pantallas
	        contenedor.add(panel1, "pantalla1");
	        contenedor.add(panel2, "pantalla2");

	        // Codigo boton
	        btnSiguiente.addActionListener(e -> {
	            CardLayout cl = (CardLayout) contenedor.getLayout();
	            cl.show(contenedor, "pantalla2");
	        });

	        frame.getContentPane().add(contenedor);
	        frame.setVisible(true);
	    }
	}
	

