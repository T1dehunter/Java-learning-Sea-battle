package gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.TableModelListener;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class GameScreen {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JTable table;
    private BattleField battleField;
    private Handler handler;

    public GameScreen(BattleField battleField) {
        this.battleField = battleField;
    }

    public void build() {
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(600,600);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        statusLabel = new JLabel("",JLabel.CENTER);
        controlPanel = new JPanel();

        table = battleField.getElement();

        mainFrame.add(table);

        mainFrame.add(statusLabel);
//        mainFrame.add(new JScrollPane(table));
//        mainFrame.add(table);

        mainFrame.add(controlPanel);

        JButton javaButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);

        javaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Submit Button clicked.");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });

        controlPanel.add(javaButton);
        controlPanel.add(cancelButton);


//        setPreferredSize(new Dimension(400, 400));
//        setLocationRelativeTo(null);
//        getContentPane().add(new JScrollPane(table));

        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
