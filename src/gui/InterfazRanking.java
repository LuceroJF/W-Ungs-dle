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
    private Wungsdle wungsdle;
    private String mensaje = "";

    //===========================CONSTRUCTOR================================//
    public InterfazRanking(InterfazInicio menu, Wungsdle juego) {
        this.menuPrincipal = menu;
        this.wungsdle = juego;
        crearInterfazRanking();
    }

    private void crearInterfazRanking() {

        this.setSize(1366,768);
        getContentPane().setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //LABEL TITULO
        JLabel titulo = new JLabel("RANKING", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(484, 71, 300, 40);
        getContentPane().add(titulo);

        //COLUMNAS
        String[] columnas = wungsdle.getColumnasRanking();
        java.util.List<String> ranking = Ranking.getRankingOrdenado();
        String[][] datos = new String[ranking.size()][3];
        for (int i = 0; i < ranking.size(); i++) {
            String[] partes = ranking.get(i).split(",");
            datos[i][0] = partes[0];
            datos[i][1] = partes[1];
            datos[i][2] = partes[2] + " seg";
        }

        //TABLA
        JTable tabla = new JTable(new DefaultTableModel(datos, columnas) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        });
        tabla.setRowHeight(30);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(434, 151, 400, 300);
        getContentPane().add(scroll);

        //BOTON RESET
        JButton btnReset = new JButton();
        rankingBoton = wungsdle.getTextoBotonRanking();
        btnReset.setText(rankingBoton);
        btnReset.setBounds(484, 471, 300, 40);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ranking.resetearPuntajes();
                mensaje= wungsdle.getTextoMensajeRanking();
                JOptionPane.showMessageDialog(InterfazRanking.this, mensaje);
                InterfazRanking.this.dispose();
                InterfazRanking nueva = new InterfazRanking(menuPrincipal, wungsdle);
                nueva.setVisible(true);
            }
        });

        getContentPane().add(btnReset);

        //BOTON VOLVER
        JButton btnVolver = new JButton(); // "Volver al menú"
        volver = wungsdle.getTextoBotonVolver();
        btnVolver.setText(volver);
        btnVolver.setBounds(484, 531, 300, 40);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                InterfazRanking.this.dispose();
                menuPrincipal.setVisible(true);
                menuPrincipal.setLocationRelativeTo(null);
            }
        });
        getContentPane().add(btnVolver);
    }
}