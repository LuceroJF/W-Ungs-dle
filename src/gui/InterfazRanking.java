package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import entidades.Ranking;
import entidades.Wungsdle;

public class InterfazRanking extends JFrame {

    private InterfazInicio menuPrincipal;
    private String volver ="";
    private String rankingBoton ="";
    private Wungsdle wordle;
    private String mensaje = "";

    public InterfazRanking(InterfazInicio menu, Wungsdle wordle) {
        this.menuPrincipal = menu;
        this.wordle = wordle;
        crearInterfazRanking();
    }

    private void crearInterfazRanking() {

        this.setSize(800, 600);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================== TITULO ==================
        JLabel titulo = new JLabel("RANKING", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(250, 20, 300, 40);
        this.add(titulo);

        // ================== COLUMNAS ==================
        String[] columnas = {"Jugador", "Puntos", "Tiempo"};

        java.util.List<String> ranking = Ranking.obtenerRankingOrdenado();

        String[][] datos = new String[ranking.size()][3];

        for (int i = 0; i < ranking.size(); i++) {
            String[] partes = ranking.get(i).split(",");
            datos[i][0] = partes[0];
            datos[i][1] = partes[1];
            datos[i][2] = partes[2] + " seg";
        }

        // ================== TABLA ==================
        JTable tabla = new JTable(new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(200, 100, 400, 300);
        this.add(scroll);

        // ================== BOTON RESET ==================
        JButton btnReset = new JButton();
        rankingBoton = wordle.getTextoBotonRanking();
        btnReset.setText(rankingBoton);
        btnReset.setBounds(250, 420, 300, 40);

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ranking.resetearPuntajes();
                mensaje= wordle.getTextoMensajeRanking();
                JOptionPane.showMessageDialog(InterfazRanking.this, mensaje);
                InterfazRanking.this.dispose();
                InterfazRanking nueva = new InterfazRanking(menuPrincipal, wordle);
                nueva.setVisible(true);
            }
        });

        this.add(btnReset);

        // ================== BOTON VOLVER ==================
        JButton btnVolver = new JButton(); // "Volver al menú"
        volver = wordle.getTextoBotonVolver();
        btnVolver.setText(volver);
        btnVolver.setBounds(250, 480, 300, 40);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfazRanking.this.dispose();
                menuPrincipal.setVisible(true);
                menuPrincipal.setLocationRelativeTo(null);
            }
        });

        this.add(btnVolver);
    }

}