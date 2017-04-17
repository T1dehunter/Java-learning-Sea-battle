package gui;

import core.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GameScreen {
    private GameField playerField;
    private GameField opponentField;

    private JLabel statusLabel;

    public GameScreen(int gameFieldWidth, int gameFieldHeight, PlayerDTO playerData, PlayerActionHandler handler, PlayerAction action) {
        this.playerField = new GameField(gameFieldWidth, gameFieldHeight, playerData.getOwnCells());
        this.opponentField = new GameField(gameFieldWidth, gameFieldHeight, handler, action);
    }

    public void build(String data) {
        JFrame mainFrame;
        JPanel controlPanel;

        playerField.display(data);
        opponentField.display(data);

        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(5, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        statusLabel = new JLabel("some text",JLabel.CENTER);
        controlPanel = new JPanel();

        mainFrame.add(playerField.getElement());
        mainFrame.add(new JLabel("",JLabel.CENTER));
        mainFrame.add(opponentField.getElement());
        mainFrame.add(statusLabel);
        mainFrame.add(controlPanel);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Submit Button clicked.");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusLabel.setText("Cancel Button clicked.");
            }
        });

        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    public void update(PlayerDTO playerData) {
        /*
        *  get player cells, update field with player cells
        *  get enemy cells, update field with enemy cells
        *
        *
        * */
    }
}
