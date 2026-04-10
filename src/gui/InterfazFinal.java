package gui;

import javax.swing.*;
import java.awt.*;
import entidades.Wungsdle;

public class InterfazFinal extends JFrame{
	private Wungsdle wungsdle;
    private JFrame frame;
    private boolean gano;

    public InterfazFinal(String usuario, String palabraSecreta, boolean gano, Wungsdle wordle) {
    	this.wungsdle = wordle;
        this.gano = gano;
        crearInterfazFinal();
    }

    private void crearInterfazFinal() {

        frame = new JFrame();
        frame.setSize(600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //TITULO
        JLabel titulo = new JLabel(gano ? "¡GANASTE!" : "GAME OVER");
        titulo.setFont(new Font("Arial", Font.BOLD, 32));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(150, 20, 300, 50);
        frame.add(titulo);

        //NOMBRE
        JLabel nombre = new JLabel("Jugador: " + wungsdle.devolverNombreUsuario());
        nombre.setBounds(180, 90, 300, 25);
        nombre.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(nombre);

        //Tiempo
        JLabel tiempo = new JLabel("Tiempo: " + wungsdle.retornarTiempoUsuario());
        tiempo.setBounds(180, 120, 300, 25);
        tiempo.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(tiempo);

        //PALABRA CORRECTA
        JLabel palabra = new JLabel("La palabra era: " + wungsdle.getPalabraSecreta().toUpperCase());
        palabra.setBounds(150, 160, 300, 30);
        palabra.setFont(new Font("Arial", Font.BOLD, 18));
        palabra.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(palabra);

        //MENSAJE EXTRA
        JLabel mensaje = new JLabel(gano ? "¡Excelente!" : "Mejor suerte la próxima");
        mensaje.setBounds(150, 200, 300, 30);
        mensaje.setHorizontalAlignment(SwingConstants.CENTER);
        mensaje.setFont(new Font("Arial", Font.ITALIC, 16));
        frame.add(mensaje);

        //BOTON REINICIAR
        JButton btnReiniciar = new JButton("Reiniciar");
        btnReiniciar.setBounds(200, 260, 180, 40);
        frame.add(btnReiniciar);

        btnReiniciar.addActionListener(e -> {
            InterfazInicio inicio = new InterfazInicio(wungsdle);
            inicio.setVisible(true);
            frame.dispose();
        });

        //BOTON SALIR
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(200, 320, 180, 40);
        frame.add(btnSalir);

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        //COLOR DE FONDO SEGUN RESULTADO
        if (gano) {
            frame.getContentPane().setBackground(new Color(200, 255, 200)); // verde claro
        } else {
            frame.getContentPane().setBackground(new Color(255, 200, 200)); // rojo claro
        }
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}