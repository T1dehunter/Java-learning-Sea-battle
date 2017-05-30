package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.ArrayList;

import core.Cell;
import core.Point;


class GameField extends JFrame {
    private PlayerActionHandler handler;
    private PlayerAction action;
    private ArrayList<Cell> cords;

    private final JTable table;

    GameField(int width, int height, PlayerActionHandler handler, PlayerAction action) {
        super("Game field");

        this.cords = new ArrayList<>();
        this.handler = handler;
        this.action = action;

        table = new JTable(width, height);

        init();

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                int col = table.columnAtPoint(e.getPoint());

                GameField.this.action
                        .setAction("cell selected")
                        .setPoint(new Point(row, col));

                GameField.this.handler.handle(GameField.this.action);
            }
        });
    }

    GameField(int width, int height, ArrayList<Cell> cells) {
        super("Game field");

        this.cords = cells;

        table = new JTable(width, height);

        init();
    }

    JTable getElement() {
        return table;
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        class CellRenderer extends DefaultTableCellRenderer {
            private ArrayList<Cell> cords;

            private CellRenderer(ArrayList<Cell> cords) {
                this.cords = cords;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int cell) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, cell);

                Cell playerCell = getCell(row, cell);

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
    }

    public void display(String s) {
        System.out.print("Field displays -> " + s);

        class CellRenderer extends DefaultTableCellRenderer {
            private ArrayList<Cell> cords;

            private CellRenderer(ArrayList<Cell> cords) {
                this.cords = cords;
            }

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int cell) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, cell);

                Cell playerCell = getCell(row, cell);

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

            private Cell getCell(int cellRow, int cellColumn) {
                for (Cell c : cords) {
                    if (c.getRow() == cellRow && c.getCell() == cellColumn) {
                        return c;
                    }
                }

                return null;
            }
        }

        ArrayList<Cell> test = new ArrayList<>();

        cords.add(new Cell(9, 9, "blue"));

        table.setDefaultRenderer(Object.class, new CellRenderer(cords));
    }
}
