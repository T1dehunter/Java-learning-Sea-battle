package core;


import java.util.ArrayList;

public class CoordsBuilder {
    private ArrayList buildedPoints;
    private int fieldWith;
    private int fieldHeight;

    public CoordsBuilder(int fieldWith, int fieldHeight) {
        this.fieldWith = fieldWith;
        this.fieldHeight = fieldHeight;
    }

    public ArrayList<Point> buildRandom(int length) {
        /*
        get random x (by width)
        get random y (by height)
        cords = foreach this.buildFromPoint(x, t, length)
         */
    }

    public Point buildFromPoint(Point randomStartPoint, int length) {
        /*
        random check direction horiz or vertical
        get points by length
        change each next point cords using direction(ex for vertical on each cords increase y)
         */
    }
}
