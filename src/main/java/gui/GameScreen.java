package gui;

import core.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class GameScreen {
    private GameField playerField;
    private GameField opponentField;

    private JLabel messageLabel;

    public GameScreen(int fieldSize, PlayerDTO playerData, PlayerActionHandler handler, PlayerAction action) {
        this.playerField = new GameField(fieldSize, playerData.getOwnCells());
        this.opponentField = new GameField(fieldSize, handler, action);
    }

    public GameField getPlayerField() {
        return playerField;
    }

    public GameField getOpponentField() {
        return opponentField;
    }

    public void build(ArrayList<String> data) {
        JFrame mainFrame;
        JPanel controlPanel;

        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(5, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        messageLabel = new JLabel("some text",JLabel.CENTER);
        controlPanel = new JPanel();

        mainFrame.add(playerField.getElement());
        mainFrame.add(new JLabel("",JLabel.CENTER));
        mainFrame.add(opponentField.getElement());
        mainFrame.add(messageLabel);
        mainFrame.add(controlPanel);

        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setHorizontalTextPosition(SwingConstants.LEFT);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Submit Button clicked.");
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageLabel.setText("Cancel Button clicked.");
            }
        });

        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }

    public void setMessage(ArrayList<String> messages) {
        if (messages != null) {
            String joined = String.join(", ", messages);
            messageLabel.setText(joined);
        }
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
