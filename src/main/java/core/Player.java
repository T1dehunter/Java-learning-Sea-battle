package core;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Ship> ships;
    private ArrayList<Move> moves = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        int score = 0;

        for (Ship s: ships) {
            score += s.getLength();
        }

        return score;
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public boolean isHit(Point opponentMove) {
        Ship ship = findShipByCords(opponentMove);
        return ship != null;
    }

    public void addHit(Point opponentMove) {
        Ship ship = findShipByCords(opponentMove);

        if (ship == null) {
            return;
        }

        ship.addHit(opponentMove);
    }

    public boolean isMoveWasMade(Point newMove) {
        for (Move m : moves) {
            if (m.getRow() == newMove.getRow() && m.getCell() == newMove.getCell()) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

    // temp code for test some idea
    public class Move extends Point {
        private String status;

        public Move(int row, int cell, String status) {
            super(row, cell);

            this.status = status;
        }

        public String getStatus() {
            return status;
        }
    }

    // temp code for test some idea
    public Move getLastMove() {
        return this.moves.get(this.moves.size() - 1);
    }

    public void addMove(Point cords, String status) {
        moves.add(new Move(cords.getRow(), cords.getCell(), status));
    }


    private Ship findShipByCords(Point cords) {
        for (Ship s: ships) {
            ArrayList<Point> shipCords = s.getCoordinates();
            for (Point p : shipCords) {
                if (p.getRow() == cords.getRow() && p.getCell() == cords.getCell()) {
                    return s;
                }
            }
        }
        
        return null;
    }
}
