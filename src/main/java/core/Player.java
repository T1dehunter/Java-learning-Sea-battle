package core;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Player {
    private String name;
    private ArrayList<Ship> ships;
    private ArrayList<Move> moves = new ArrayList<>();
    private ShipBuilder builder;
    private Ship lastAffectedShip;

    public Player(String name, ShipBuilder builder) {
        this.name = name;
        this.builder = builder;
    }

    public String getName() {
        return this.name;
    }

    public Integer getScore() {
        return ships.stream().mapToInt(Ship::getLength).sum();
    }

    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    public void placeShips() {
        this.ships = builder.buildListShips();
    }

    public boolean isHit(Point opponentMove) {
        Ship ship = findShipByCords(opponentMove);
        return ship != null;
    }

    public boolean isLose() {
        return getScore() == 0;
    }

    public boolean isShipWasDestroyed() {
        return lastAffectedShip != null && lastAffectedShip.getLength() == 0;
    }

    public void addHit(Point opponentMove) {
        Ship ship = findShipByCords(opponentMove);

        if (ship == null) {
            return;
        }

        ship.addHit(opponentMove);

        lastAffectedShip = ship;
    }

    public boolean isMoveWasMade(Point newMove) {
        return moves.stream().anyMatch(m -> m.getRow() == newMove.getRow() && m.getCell() == newMove.getCell());
    }

    public ArrayList<Ship> getShips() {
        return this.ships;
    }

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

    public Move getLastMove() {
        return this.moves.get(this.moves.size() - 1);
    }

    public void addMove(Point cords, String status) {
        moves.add(new Move(cords.getRow(), cords.getCell(), status));
    }

    public ArrayList<Move> getMoves() {
        return moves;
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
