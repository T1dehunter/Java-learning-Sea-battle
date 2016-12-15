package gui;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.TableModelListener;

import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
//import javax.swing.table.ListDialog;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import javax.swing.table.Mouse



//https://docs.oracle.com/javase/7/docs/api/javax/swing/JTable.html
//https://docs.oracle.com/javase/7/docs/api/javax/swing/table/TableModel.html

//http://skipy-ru.livejournal.com/1577.html

class Field {
    private GameScreen handler;

    void addListener(GameScreen handler) {
        this.handler = handler;
    }

    public void display(String s) {
        System.out.print("Field displays -> " + s);
    }

    public void testEvent() {
        System.out.print("\nUser made some event");
        handler.handleFieldCellClick(new Point(9, 9));
    }
}






public class BattleField extends JFrame {

    class Point {
        private int row;
        private int cell;

        public Point(int row, int cell) {
            this.row = row;
            this.cell = cell;
        }

        public int getRow() {
            return row;
        }

        public int getCell() {
            return cell;
        }
    }


    private final JTable table;

    public BattleField() {
            super("Game field");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            table = new JTable(10, 10);

            ArrayList<Point> cords = new ArrayList<Point>();

            cords.add(new Point(0, 1));
            cords.add(new Point(1, 1));
            cords.add(new Point(2, 1));

            cords.add(new Point(0, 5));
            cords.add(new Point(0, 6));
            cords.add(new Point(0, 7));

            class CellRenderer extends DefaultTableCellRenderer {
                private ArrayList<Point> cords;

                private CellRenderer(ArrayList<Point> cords) {
                    this.cords = cords;
                }

                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int cell) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, cell);

                    if (isCellINotEmpty(row, cell)) {
                        c.setBackground(Color.ORANGE);
                    } else {
                        c.setBackground(Color.GRAY);
                    }

                    return c;
                }

                private Boolean isCellINotEmpty(int cellRow, int cellColumn) {
                    for (Point cord : cords) {
                        if (cord.getRow() == cellRow && cord.getCell() == cellColumn) {
                            return true;
                        }
                    }

                    return false;
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

        public JTable getElement() {
           return table;
        }
}
