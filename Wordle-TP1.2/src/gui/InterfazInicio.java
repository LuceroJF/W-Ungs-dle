package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Canvas;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Rectangle;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class InterfazInicio
{

	private JFrame frame;
	private JTextField txtNombre;


	/**
	 * Create the application.
	 */
	public InterfazInicio()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		setFrame(new JFrame());
		getFrame().setSize(800, 600);
		getFrame().getContentPane().setFont(new Font("Luckiest Guy", Font.PLAIN, 11));
		getFrame().getContentPane().setLayout(null);
		
		JLabel logo = new JLabel("New label");
		logo.setIcon(new ImageIcon(InterfazInicio.class.getResource("/recursos/Logo.png")));
		logo.setBounds(190, 41, 598, 175);
		getFrame().getContentPane().add(logo);
		
		JLabel etiquetaNombre = new JLabel("Nombre / Name:");
		etiquetaNombre.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		etiquetaNombre.setBounds(367, 267, 130, 29);
		getFrame().getContentPane().add(etiquetaNombre);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		txtNombre.setBounds(530, 267, 150, 29);
		getFrame().getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel etiquetaIdioma = new JLabel("Idioma / Language:");
		etiquetaIdioma.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		etiquetaIdioma.setBounds(361, 318, 136, 29);
		getFrame().getContentPane().add(etiquetaIdioma);
		
		JComboBox idioma = new JComboBox();
		idioma.setModel(new DefaultComboBoxModel(new String[] {"Espa\u00F1ol - ES", "English - EN"}));
		idioma.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		idioma.setBounds(530, 318, 150, 29);
		getFrame().getContentPane().add(idioma);
		
		JLabel etiquetaDificultad = new JLabel("Dificultad / Difficulty:");
		etiquetaDificultad.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		etiquetaDificultad.setBounds(339, 372, 158, 29);
		getFrame().getContentPane().add(etiquetaDificultad);
		
		JComboBox dificultad = new JComboBox();
		dificultad.setModel(new DefaultComboBoxModel(new String[] {"Facil / Easy", "Dificil / Hard"}));
		dificultad.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		dificultad.setBounds(530, 372, 150, 29);
		getFrame().getContentPane().add(dificultad);
		
		JButton btnSiguiente = new JButton("Siguiente ->");
		btnSiguiente.setFont(new Font("Luckiest Guy", Font.BOLD, 14));
		btnSiguiente.setBounds(621, 511, 130, 36);
		btnSiguiente.setEnabled(false);

		getFrame().getContentPane().add(btnSiguiente);
		

		// Valida si el usuario ingreso un nombre, para activar el boton Siguiente //
		
        txtNombre.getDocument().addDocumentListener(new DocumentListener()
        {

            private void validar() {

                btnSiguiente.setEnabled(!txtNombre.getText().trim().isEmpty());
            }

            @Override
            public void insertUpdate(DocumentEvent e) 
            {
                validar();
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                validar();
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                validar();
            }
        });

        btnSiguiente.addActionListener(e -> 
        {
            try 
            {
            	// Capturo el nombre del usuario.
            	String nombre = txtNombre.getText();
                
                // Caputuro el idioma elegido por el usuario.
                String idiomaElegido = (String)idioma.getSelectedItem();
                
                // Capturo la dificultad seleccionada por el usuario.
                String dificultadElegida = (String)dificultad.getSelectedItem();
                
                // Tomo una palabra secreta, dependiendo de la dificultad y el idioma seleccionado.
                String rutaTxt = rutaTxtSegunSeleccion(idiomaElegido, dificultadElegida);
                String palabraSecreta = palabraAleatoriaDesdeRecurso(rutaTxt);
                
                System.out.println("nombre:" + nombre + ", idioma: " + idiomaElegido + ", dificultad elegida: " + dificultadElegida + " y palabra secreta: " + palabraSecreta);
                
                                                
                InterfazTutorial tutorial = new InterfazTutorial(nombre, palabraSecreta); 
                tutorial.getFrame().setVisible(true);

                getFrame().dispose();
            } 
            catch (Exception ex) 
            {
                ex.printStackTrace();
            }
        });
		
		
		
		getFrame().setBounds(100, 100, 1000, 800);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	private static String rutaTxtSegunSeleccion(String idiomaElegido, String dificultadElegida)
    {
        boolean esEspanol = (idiomaElegido != null) && idiomaElegido.startsWith("Español");
        boolean esFacil   = (dificultadElegida != null) && dificultadElegida.startsWith("Facil");

        String lang = esEspanol ? "ES" : "EN";
        String dif  = esFacil   ? "listaPalabrasFaciles" : "listaPalabraSDificiles";

        
        return "/recursos/" + dif + "_" + lang + ".txt";
    }

    private static String palabraAleatoriaDesdeRecurso(String rutaRecurso)
    {
        List<String> palabras = new ArrayList<>();

        
        try (InputStream is = InterfazInicio.class.getResourceAsStream(rutaRecurso))
        {
            if (is == null) {
                throw new IllegalArgumentException("No se encontró el recurso: " + rutaRecurso);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)))
            {
                String line;
                while ((line = br.readLine()) != null)
                {
                    line = line.trim();
                    if (!line.isEmpty())
                    {
                        
                        palabras.add(line);
                    }
                }
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Error leyendo el recurso: " + rutaRecurso, ex);
        }

        if (palabras.isEmpty()) {
            throw new IllegalStateException("El archivo está vacío: " + rutaRecurso);
        }

        int idx = ThreadLocalRandom.current().nextInt(palabras.size());
        return palabras.get(idx);
    }
   
   // Funciones auxiliares
	public JFrame getFrame() 
	{
		return frame;
	}

	public void setFrame(JFrame frame)
	{
		this.frame = frame;
	}
}
