package core;

import gui.GuiBuilder;
import junit.framework.TestCase;

import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCordsBuilder extends TestCase {
    private CoordsBuilder cordsBuilder;

    @Override
    protected void setUp() throws Exception {
        this.cordsBuilder = new CoordsBuilder(10, 10);
    }

    public void testBuildCordsFromPointShouldReturnArrayOfPointsWithCorrectLength() {
        ArrayList<Point> boatCords = cordsBuilder.buildFromPoint(new Point(5, 5), "left", 1);
        ArrayList<Point> destCords = cordsBuilder.buildFromPoint(new Point(6, 5), "left", 2);
        ArrayList<Point> cruisCords = cordsBuilder.buildFromPoint(new Point(7, 5), "left", 3);
        ArrayList<Point> battleCords = cordsBuilder.buildFromPoint(new Point(8, 5), "left", 4);

        Assert.assertEquals("Cords for boat has correct length", boatCords.size(), 1);
        Assert.assertEquals("Cords for destroyer has correct length", destCords.size(), 2);
        Assert.assertEquals("Cords for cruiser has correct length", cruisCords.size(), 3);
        Assert.assertEquals("Cords for battleship has correct length", battleCords.size(), 4);
    }

    public void testBuildCordsFromPointShouldReturnCordsWithSamePointIfCordsLenghtEqualOne() {
        Point pointFrom = new Point(5, 5);

        ArrayList<Point> cords = cordsBuilder.buildFromPoint(pointFrom, "left", 1);

        Assert.assertTrue("Point in cords is same like start point", pointFrom == cords.get(0));
    }

    // mb should later move these comments to method names
    // check cords when ship should be placed from the top right corner to left
    public void testBuildCordsFromPointShouldReturnCordsWithSameRowIfDirectionLeft() {
        Point pointFrom = new Point(0, 9);

        ArrayList<Point> cords = cordsBuilder.buildFromPoint(pointFrom, "left", 3);

        Assert.assertEquals("Cords has correct length", cords.size(), 3);
        Assert.assertTrue("Cords has correct row", cords.get(0).getRow() == 0 && cords.get(1).getRow() == 0 && cords.get(2).getRow() == 0);
        Assert.assertTrue("Cords has correct cell", cords.get(0).getCell() == 9 && cords.get(1).getCell() == 8 && cords.get(2).getCell() == 7);
    }

    // check cords when ship should be placed from the top left corner to right
    public void testBuildCordsFromPointShouldReturnCordsWithSameRowIfDirectionRight() {
        Point pointFrom = new Point(0, 0);

        ArrayList<Point> cords = cordsBuilder.buildFromPoint(pointFrom, "right", 3);

        Assert.assertEquals("Cords has correct length", cords.size(), 3);
        Assert.assertTrue("Cords has correct row", cords.get(0).getRow() == 0 && cords.get(1).getRow() == 0 && cords.get(2).getRow() == 0);
        Assert.assertTrue("Cords has correct cell", cords.get(0).getCell() == 0 && cords.get(1).getCell() == 1 && cords.get(2).getCell() == 2);
    }

    // check cords when ship should placed from the bottom left corner corner to top
    public void testBuildCordsFromPointShouldReturnCordsWithSameRowIfDirectionTop() {

        Point pointFrom = new Point(9, 0);

        ArrayList<Point> cords = cordsBuilder.buildFromPoint(pointFrom, "top", 3);

        Assert.assertEquals("Cords has correct length", cords.size(), 3);
        Assert.assertTrue("Cords has correct row", cords.get(0).getRow() == 9 && cords.get(1).getRow() == 8 && cords.get(2).getRow() == 7);
        Assert.assertTrue("Cords has correct cell", cords.get(0).getCell() == 0 && cords.get(1).getCell() == 0 && cords.get(2).getCell() == 0);
    }

    // check cords when ship should be placed from the top left corner to bottom
    public void testBuildCordsFromPointShouldReturnCordsWithSameRowIfDirectionBottom() {
        Point pointFrom = new Point(0, 0);

        ArrayList<Point> cords = cordsBuilder.buildFromPoint(pointFrom, "bottom", 3);

        Assert.assertEquals("Cords has correct length", cords.size(), 3);
        Assert.assertTrue("Cords has correct row", cords.get(0).getRow() == 0 && cords.get(1).getRow() == 1 && cords.get(2).getRow() == 2);
        Assert.assertTrue("Cords has correct cell", cords.get(0).getCell() == 0 && cords.get(1).getCell() == 0 && cords.get(2).getCell() == 0);
    }

    // check cords when ship should be placed from the top left corner to top(outside of field)
    // If the coordinates are outside the field of play
    public void testBuildCordsFromPointShouldReturnEmptyArrayListIfCordsOutsideFieldOFPlay() {
        Point leftTopCornerPoint = new Point(0, 0);
        ArrayList<Point> cordsFromTopLeftCornerToTop = cordsBuilder.buildFromPoint(leftTopCornerPoint, "top", 3);
        Assert.assertEquals("List of cords should be empty if cords from top left corner to top", cordsFromTopLeftCornerToTop.size(), 0);

        ArrayList<Point> cordsFromTopLeftCornerToLeft = cordsBuilder.buildFromPoint(leftTopCornerPoint, "left", 3);
        Assert.assertEquals("List of cords should be empty if cords from top left corner to left", cordsFromTopLeftCornerToLeft.size(), 0);

        Point rightTopCornerPoint = new Point(0, 9);
        ArrayList<Point> cordsFromTopRightCornerToTop = cordsBuilder.buildFromPoint(rightTopCornerPoint, "top", 3);
        Assert.assertEquals("List of cords should be empty if cords from top right corner to top", cordsFromTopRightCornerToTop.size(), 0);

        ArrayList<Point> cordsFromTopRightCornerToRight = cordsBuilder.buildFromPoint(rightTopCornerPoint, "right", 3);
        Assert.assertEquals("List of cords should be empty if cords from top tight corner to right", cordsFromTopRightCornerToRight.size(), 0);

        Point leftBottomCornerPoint = new Point(9, 0);
        ArrayList<Point> cordsFromBottomLeftCornerToBottom = cordsBuilder.buildFromPoint(leftBottomCornerPoint, "bottom", 3);
        Assert.assertEquals("List of cords should be empty if cords from bottom left corner to bottom", cordsFromBottomLeftCornerToBottom.size(), 0);

        ArrayList<Point> cordsFromBottomLeftCornerToLeft = cordsBuilder.buildFromPoint(leftBottomCornerPoint, "left", 3);
        Assert.assertEquals("List of cords should be empty if cords from top left corner to left", cordsFromBottomLeftCornerToLeft.size(), 0);

        Point rightBottomCornerPoint = new Point(9, 9);
        ArrayList<Point> cordsFromBottomRightCornerToBottom = cordsBuilder.buildFromPoint(rightBottomCornerPoint, "bottom", 3);
        Assert.assertEquals("List of cords should be empty if cords from bottom left corner to bottom", cordsFromBottomRightCornerToBottom.size(), 0);

        ArrayList<Point> cordsFromBottomRightCornerToRight = cordsBuilder.buildFromPoint(rightBottomCornerPoint, "right", 3);
        Assert.assertEquals("List of cords should be empty if cords from top left corner to left", cordsFromBottomRightCornerToRight.size(), 0);
    }

    public void testBuildCordsFromPointShouldReturnEmptyArrayListIfCordsCrossExistsPoints() {
        cordsBuilder.buildFromPoint(new Point(5, 5), "right", 3);
        ArrayList<Point> crossedPoints = cordsBuilder.buildFromPoint(new Point(6, 5), "top", 3);
        Assert.assertEquals("List of cords should be empty if points crossed another cords", crossedPoints.size(), 0);
    }
}
