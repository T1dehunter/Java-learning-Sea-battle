package core;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class CoordsBuilder {
    private Random randGen = new Random();
    private String[] directions = {"left", "right", "top", "bottom"};

    private ArrayList<Point> builtPoints = new ArrayList<>();
    private int fieldSize;

    public CoordsBuilder(int fieldSize) {
        this.fieldSize = fieldSize;
    }

    public ArrayList<Point> buildRandomCoords(Integer cordsLength) {
        int maxNumberOfAttemptsForCalculateCoords = 1000;
        ArrayList<Point> randomCoords = new ArrayList<>();

        // mb should later add custom exception
        for (int i = 0; i < maxNumberOfAttemptsForCalculateCoords; i++) {
            Point randomPointFrom = getRandomPoint();
            String randomDirection = getRandomDirection();

            randomCoords = buildFromPoint(randomPointFrom, randomDirection, cordsLength);

            if (randomCoords.size() == cordsLength) {
                break;
            }
        }

        return randomCoords;
    }

    public ArrayList<Point> buildFromPoint(Point startPoint, String direction, int length) {
        ArrayList<Point> res = new ArrayList<>();

        res.add(startPoint);

        if (length > 1) {
            for (int i = 1; i < length; i++) {
                res.add(getPointByDirection(res.get(res.size() - 1), direction));
            }
        }

        if (!isCordsCorrect(res)) {
            return new ArrayList<>();
        }

        builtPoints.addAll(res);

        return res;
    }

    private Point getRandomPoint() {
        int randRow = getRandomNumber();
        int randCell = getRandomNumber();

        return new Point(randRow, randCell);
    }

    private int getRandomNumber() {
        return randGen.nextInt(fieldSize);
    }

    private String getRandomDirection() {
        return directions[randGen.nextInt(directions.length)];
    }

    private Point getPointByDirection(Point pointFrom, String direction) {
        if (direction.equals("left")) {
            return new Point(pointFrom.getRow(), pointFrom.getCell() - 1);
        } else if (direction.equals("right")) {
            return new Point(pointFrom.getRow(), pointFrom.getCell() + 1);
        } else if (direction.equals("top")) {
            return new Point(pointFrom.getRow() - 1, pointFrom.getCell());
        } else {
            return new Point(pointFrom.getRow() + 1, pointFrom.getCell());
        }
    }

    private boolean isCordsCorrect(ArrayList<Point> cords) {
        for (Point point: cords) {
            if (isPointOutsideOfField(point) || isPointInBuiltPoints(point) || isPointTooCloseToOtherPoints(point)) {
                return false;
            }
        }

        return true;
    }

    private boolean isPointOutsideOfField(Point point) {
        return point.getRow() < 0 || point.getRow() > (fieldSize - 1) || point.getCell() < 0 || point.getCell() > (fieldSize -1);
    }

    private boolean isPointInField(Point point) {
        return !isPointOutsideOfField(point);
    }

    private boolean isPointInBuiltPoints(Point newPoint) {
        for (Point existsPoint: builtPoints) {
            if (existsPoint.getRow() == newPoint.getRow() && existsPoint.getCell() == newPoint.getCell()) {
                return true;
            }
        }

        return false;
    }

    private boolean isPointTooCloseToOtherPoints(Point point) {
        ArrayList<Point> aroundPoints = getAroundPointsFromPoint(point);

        ArrayList<Point> res = aroundPoints.stream()
                .filter(this::isPointInField)
                .filter(this::isPointInBuiltPoints)
                .collect(Collectors.toCollection(ArrayList::new));

        return res.size() > 0;
    }

    private ArrayList<Point> getAroundPointsFromPoint(Point point) {
        ArrayList<Point> aroundPoints = new ArrayList<Point>();

        Point pointFromTop = new Point(point.getRow() - 1, point.getCell());
        Point pointFromBottom = new Point(point.getRow() + 1, point.getCell());
        Point pointFromLeft = new Point(point.getRow(), point.getCell() - 1);
        Point pointFromRight = new Point(point.getRow(), point.getCell() + 1);

        aroundPoints.add(pointFromTop);
        aroundPoints.add(pointFromBottom);
        aroundPoints.add(pointFromLeft);
        aroundPoints.add(pointFromRight);

        return aroundPoints;
    }
}
