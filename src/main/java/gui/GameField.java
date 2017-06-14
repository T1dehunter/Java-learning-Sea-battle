package gui;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

import core.Cell;
import core.Point;


class GameField extends JFrame {
    private PlayerActionHandler handler;
    private PlayerAction action;
    private ArrayList<Cell> cords;
    private MyTableModel model;

    private final JTable table;

    public class MyTableModel extends AbstractTableModel {
        private int size;

        public MyTableModel(int fieldSize) {
            super();

            this.size = fieldSize;
        }
        @Override
        public int getRowCount() {
            return size;
        }
        @Override
        public int getColumnCount() {
            return size;
        }
        @Override
        public Object getValueAt(int r, int c) {
            return "";
        }
    }

    GameField(int size, PlayerActionHandler handler, PlayerAction action) {
        super("Game field");

        this.cords = new ArrayList<>();
        this.handler = handler;
        this.action = action;

        this.model = new MyTableModel(size);

        table = new JTable(this.model);

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

    GameField(int size, ArrayList<Cell> cells) {
        super("Game field");

        this.cords = cells;

        this.model = new MyTableModel(size);

        table = new JTable(this.model);

        init();
    }

    JTable getElement() {
        return table;
    }

    private Cell getCell(int cellRow, int cellColumn) {
        for (Cell c : cords) {
            if (c.getRow() == cellRow && c.getCell() == cellColumn) {
                return c;
            }
        }
        return null;
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int cell) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, cell);

                Cell playerCell = getCell(row, cell);

                if (playerCell != null) {
                    if (playerCell.getColor().equals("pink"))
                        c.setBackground(Color.PINK);
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
        });
    }

    public void display(Cell c) {
        Cell existsCell = getCell(c.getRow(), c.getCell());

        if (existsCell != null) {
            cords.remove(existsCell);
        }

        cords.add(c);

        model.fireTableDataChanged();
    }
}
