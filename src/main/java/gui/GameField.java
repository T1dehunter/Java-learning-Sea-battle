package gui;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;

//import javax.swing.table.ListDialog;
import java.awt.*;
import java.util.ArrayList;

import core.Cell;
import core.Point;
//import javax.swing.table.Mouse



//https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
//https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableModel.html

//http://skipy-ru.livejournal.com/1577.html


public class GameField extends JFrame {
    private GameScreen handler;
    private final JTable table;
    private ArrayList<Cell> cords;

    public GameField(ArrayList<Cell> cells) {
        super("Game field");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cords = cells;

        table = new JTable(10, 10);

        class CellRenderer extends DefaultTableCellRenderer {
            private ArrayList<Cell> cords;

            private CellRenderer(ArrayList<Cell> cords) {
                this.cords = cords;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int cell) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, cell);

                Cell playerCell = getCell(row, cell);

//                if (isCellINotEmpty(row, cell)) {
                if (playerCell != null) {
                    if (playerCell.getColor().equals("orange"))
                        c.setBackground(Color.ORANGE);
                    if (playerCell.getColor().equals("green"))
                        c.setBackground(Color.GREEN);
                    if (playerCell.getColor().equals("blue"))
                        c.setBackground(Color.BLUE);
                    if (playerCell.getColor().equals("yellow"))
                        c.setBackground(Color.YELLOW);
                    if (playerCell.getColor().equals("red"))
                        c.setBackground(Color.RED);
                } else {
                    c.setBackground(Color.GRAY);
                }

                return c;
            }

            private Boolean isCellINotEmpty(int cellRow, int cellColumn) {
                for (Cell cord : cords) {
                    if (cord.getRow() == cellRow && cord.getCell() == cellColumn) {
                        return true;
                    }
                }

                return false;
            }

            private Cell getCell(int cellRow, int cellColumn) {
                for (Cell c : cords) {
                    if (c.getRow() == cellRow && c.getCell() == cellColumn) {
                        return c;
                    }
                }

                return null;
            }
        }

        table.setDefaultRenderer(Object.class, new CellRenderer(cords));

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                System.out.print("ROW -> " + row + " *** " + "CELL -> " + col);
            }
        });
    }

    public void display(String s) {
        System.out.print("Field displays -> " + s);
    }

    public JTable getElement() {
        return table;
    }

    void addListener(GameScreen handler) {
        this.handler = handler;
    }

    public void testEvent() {
        System.out.print("\nUser made some event");
        handler.handleFieldCellClick(new Point(9, 9));
    }
}
