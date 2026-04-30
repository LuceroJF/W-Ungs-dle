package gui;

import javax.swing.*;
import java.awt.*;
import entidades.ConfiguracionInicial;
import entidades.LogicaPalabra;
import entidades.Wungsdle;

public class InterfazFinal extends JFrame{
	
	private ConfiguracionInicial configuracionInicial;
	private InterfazWungsdle interfazJuego;
	private Wungsdle wungsdle;
    private boolean gano;
    private LogicaPalabra logica;

    //=========================CONSTRUCTOR========================//
    public InterfazFinal(InterfazWungsdle juego, String usuario, String palabraSecreta, boolean gano, Wungsdle wungsdleJuego, LogicaPalabra logicaP) {
    	logica = logicaP;
    	interfazJuego = juego;
    	this.wungsdle = wungsdleJuego;
        this.gano = gano;
        crearInterfazFinal();
    }

    private void crearInterfazFinal() {

        new JFrame();
        setSize(600, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //TITULO
        JLabel titulo = new JLabel(gano ? "¡GANASTE!" : "GAME OVER");
        titulo.setFont(new Font("Luckiest Guy", Font.PLAIN, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(150, 20, 300, 50);
        this.add(titulo);

        //NOMBRE
        JLabel nombre = new JLabel("Jugador: " + wungsdle.getNombreUsuario());
        nombre.setBounds(180, 90, 300, 25);
        nombre.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
        this.add(nombre);

        //Tiempo
        JLabel tiempo = new JLabel("Tiempo: " + wungsdle.getTimeMilis(wungsdle.getTiempoUsuario()));
        tiempo.setBounds(180, 120, 300, 25);
        tiempo.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
        this.add(tiempo);

        //PALABRA CORRECTA
        JLabel palabra = new JLabel("La palabra era: " + logica.getPalabraSecreta().toUpperCase());
        palabra.setBounds(150, 160, 300, 30);
        palabra.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
        palabra.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(palabra);

        //MENSAJE EXTRA
        JLabel mensaje = new JLabel(gano ? "¡Excelente!" : "Mejor suerte la próxima");
        mensaje.setBounds(150, 200, 300, 30);
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        mensaje.setFont(new Font("Luckiest Guy", Font.PLAIN, 20));
        this.add(mensaje);

        //BOTON REINICIAR
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(200, 260, 180, 40);
        this.add(btnReiniciar);

        btnReiniciar.addActionListener(e -> {
        	configuracionInicial = new ConfiguracionInicial();
        	Wungsdle wungsdle1 = new Wungsdle();
        	LogicaPalabra logicaP1= wungsdle1.comenzarLogicaPalabra();
            configuracionInicial.crearConfiguracionInicial(wungsdle1, logicaP1);
            interfazJuego.dispose();
            this.dispose();
        });

        //BOTON SALIR
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 320, 180, 40);
        this.add(btnSalir);

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        //COLOR DE FONDO SEGUN RESULTADO
        if (gano) {
            this.getContentPane().setBackground(new Color(200, 255, 200)); // verde claro
        } else {
            this.getContentPane().setBackground(new Color(255, 200, 200)); // rojo claro
        }
        this.setVisible(true);
    }
}