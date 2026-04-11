package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import entidades.Ranking;

public class InterfazRanking {

    private JFrame frame;
    private InterfazInicio menuPrincipal;

    public InterfazRanking(InterfazInicio menu) {
        this.menuPrincipal = menu;
        initialize();
    }

    private void initialize() {

        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ================== TITULO ==================
        JLabel titulo = new JLabel("RANKING", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 28));
        titulo.setBounds(250, 20, 300, 40);
        frame.add(titulo);

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
        frame.add(scroll);

        // ================== BOTON RESET ==================
        JButton btnReset = new JButton("Reiniciar Ranking");
        btnReset.setBounds(250, 420, 300, 40);

        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Ranking.resetearPuntajes();
                JOptionPane.showMessageDialog(frame, "Ranking reiniciado");

                frame.dispose();
                InterfazRanking nueva = new InterfazRanking(menuPrincipal);
                nueva.getFrame().setVisible(true);
            }
        });

        frame.add(btnReset);

        // ================== BOTON VOLVER ==================
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setBounds(250, 480, 300, 40);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                menuPrincipal.setVisible(true);
                menuPrincipal.setLocationRelativeTo(null);
            }
        });

        frame.add(btnVolver);
    }

    public JFrame getFrame() {
        return frame;
    }
}