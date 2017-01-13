package core;

import gui.GuiBuilder;
import junit.framework.TestCase;

import org.junit.Assert;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestCoordsBuilder extends TestCase {
    private CoordsBuilder coordsBuilder;

    @Override
    protected void setUp() throws Exception {
        this.coordsBuilder = new CoordsBuilder(10, 10);
    }

    public void testBuildCoordsFromPointShouldReturnArrayOfPointsWithCorrectLength() {
        ArrayList<Point> boatCoords = coordsBuilder.buildFromPoint(new Point(3, 5), "left", 1);
        ArrayList<Point> destCoords = coordsBuilder.buildFromPoint(new Point(5, 5), "left", 2);
        ArrayList<Point> cruisCoords = coordsBuilder.buildFromPoint(new Point(7, 5), "left", 3);
        ArrayList<Point> battleCoords = coordsBuilder.buildFromPoint(new Point(9, 5), "left", 4);

        Assert.assertEquals("Coords for boat has correct length", boatCoords.size(), 1);
        Assert.assertEquals("Coords for destroyer has correct length", destCoords.size(), 2);
        Assert.assertEquals("Coords for cruiser has correct length", cruisCoords.size(), 3);
        Assert.assertEquals("Coords for battleship has correct length", battleCoords.size(), 4);
    }

    public void testBuildCoordsFromPointShouldReturnCoordsWithSamePointIfCoordsLenghtEqualOne() {
        Point pointFrom = new Point(5, 5);

        ArrayList<Point> coords = coordsBuilder.buildFromPoint(pointFrom, "left", 1);

        Assert.assertTrue("Point in coords is same like start point", pointFrom == coords.get(0));
    }

    // mb should later move these comments to method names
    // check coords when ship should be placed from the top right corner to left
    public void testBuildCoordsFromPointShouldReturnCoordsWithSameRowIfDirectionLeft() {
        Point pointFrom = new Point(0, 9);

        ArrayList<Point> coords = coordsBuilder.buildFromPoint(pointFrom, "left", 3);

        Assert.assertEquals("Coords has correct length", coords.size(), 3);
        Assert.assertTrue("Coords has correct row", coords.get(0).getRow() == 0 && coords.get(1).getRow() == 0 && coords.get(2).getRow() == 0);
        Assert.assertTrue("Coords has correct cell", coords.get(0).getCell() == 9 && coords.get(1).getCell() == 8 && coords.get(2).getCell() == 7);
    }

    // check coords when ship should be placed from the top left corner to right
    public void testBuildCoordsFromPointShouldReturnCoordsWithSameRowIfDirectionRight() {
        Point pointFrom = new Point(0, 0);

        ArrayList<Point> coords = coordsBuilder.buildFromPoint(pointFrom, "right", 3);

        Assert.assertEquals("Coords has correct length", coords.size(), 3);
        Assert.assertTrue("Coords has correct row", coords.get(0).getRow() == 0 && coords.get(1).getRow() == 0 && coords.get(2).getRow() == 0);
        Assert.assertTrue("Coords has correct cell", coords.get(0).getCell() == 0 && coords.get(1).getCell() == 1 && coords.get(2).getCell() == 2);
    }

    // check coords when ship should placed from the bottom left corner corner to top
    public void testBuildCoordsFromPointShouldReturnCoordsWithSameRowIfDirectionTop() {

        Point pointFrom = new Point(9, 0);

        ArrayList<Point> coords = coordsBuilder.buildFromPoint(pointFrom, "top", 3);

        Assert.assertEquals("Coords has correct length", coords.size(), 3);
        Assert.assertTrue("Coords has correct row", coords.get(0).getRow() == 9 && coords.get(1).getRow() == 8 && coords.get(2).getRow() == 7);
        Assert.assertTrue("Coords has correct cell", coords.get(0).getCell() == 0 && coords.get(1).getCell() == 0 && coords.get(2).getCell() == 0);
    }

    // check coords when ship should be placed from the top left corner to bottom
    public void testBuildCoordsFromPointShouldReturnCoordsWithSameRowIfDirectionBottom() {
        Point pointFrom = new Point(0, 0);

        ArrayList<Point> coords = coordsBuilder.buildFromPoint(pointFrom, "bottom", 3);

        Assert.assertEquals("Coords has correct length", coords.size(), 3);
        Assert.assertTrue("Coords has correct row", coords.get(0).getRow() == 0 && coords.get(1).getRow() == 1 && coords.get(2).getRow() == 2);
        Assert.assertTrue("Coords has correct cell", coords.get(0).getCell() == 0 && coords.get(1).getCell() == 0 && coords.get(2).getCell() == 0);
    }

    // check coords when ship should be placed from the top left corner to top(outside of field)
    // If the coordinates are outside the field of play
    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsOutsideFieldOFPlay() {
        Point leftTopCornerPoint = new Point(0, 0);
        ArrayList<Point> coordsFromTopLeftCornerToTop = coordsBuilder.buildFromPoint(leftTopCornerPoint, "top", 3);
        Assert.assertEquals("List of coords should be empty if coords from top left corner to top", coordsFromTopLeftCornerToTop.size(), 0);

        ArrayList<Point> coordsFromTopLeftCornerToLeft = coordsBuilder.buildFromPoint(leftTopCornerPoint, "left", 3);
        Assert.assertEquals("List of coords should be empty if coords from top left corner to left", coordsFromTopLeftCornerToLeft.size(), 0);

        Point rightTopCornerPoint = new Point(0, 9);
        ArrayList<Point> coordsFromTopRightCornerToTop = coordsBuilder.buildFromPoint(rightTopCornerPoint, "top", 3);
        Assert.assertEquals("List of coords should be empty if coords from top right corner to top", coordsFromTopRightCornerToTop.size(), 0);

        ArrayList<Point> coordsFromTopRightCornerToRight = coordsBuilder.buildFromPoint(rightTopCornerPoint, "right", 3);
        Assert.assertEquals("List of coords should be empty if coords from top tight corner to right", coordsFromTopRightCornerToRight.size(), 0);

        Point leftBottomCornerPoint = new Point(9, 0);
        ArrayList<Point> coordsFromBottomLeftCornerToBottom = coordsBuilder.buildFromPoint(leftBottomCornerPoint, "bottom", 3);
        Assert.assertEquals("List of coords should be empty if coords from bottom left corner to bottom", coordsFromBottomLeftCornerToBottom.size(), 0);

        ArrayList<Point> coordsFromBottomLeftCornerToLeft = coordsBuilder.buildFromPoint(leftBottomCornerPoint, "left", 3);
        Assert.assertEquals("List of coords should be empty if coords from top left corner to left", coordsFromBottomLeftCornerToLeft.size(), 0);

        Point rightBottomCornerPoint = new Point(9, 9);
        ArrayList<Point> coordsFromBottomRightCornerToBottom = coordsBuilder.buildFromPoint(rightBottomCornerPoint, "bottom", 3);
        Assert.assertEquals("List of coords should be empty if coords from bottom left corner to bottom", coordsFromBottomRightCornerToBottom.size(), 0);

        ArrayList<Point> coordsFromBottomRightCornerToRight = coordsBuilder.buildFromPoint(rightBottomCornerPoint, "right", 3);
        Assert.assertEquals("List of coords should be empty if coords from top left corner to left", coordsFromBottomRightCornerToRight.size(), 0);
    }

    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsCrossExistsPoints() {
        coordsBuilder.buildFromPoint(new Point(5, 5), "right", 3);
        ArrayList<Point> crossedPoints = coordsBuilder.buildFromPoint(new Point(6, 5), "top", 3);
        Assert.assertEquals("List of coords should be empty if points crossed another coords", crossedPoints.size(), 0);
    }

    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsTooCloseToOtherPointsOnTop() {
        // another points placed to close from top
        coordsBuilder.buildFromPoint(new Point(0, 0), "right", 3);
        ArrayList<Point> res = coordsBuilder.buildFromPoint(new Point(1, 0), "right", 3);
        Assert.assertEquals("List of coords should be empty if points placed too close other to points from top", res.size(), 0);
    }

    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsTooCloseToOtherPointsOnBottom() {
        // another points placed to close from bottom
        coordsBuilder.buildFromPoint(new Point(1, 0), "right", 3);
        ArrayList<Point> res = coordsBuilder.buildFromPoint(new Point(0, 0), "right", 3);
        Assert.assertEquals("List of coords should be empty if points placed too close other to points from bottom", res.size(), 0);
    }

    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsTooCloseToOtherPointsOnLeft() {
        // another points placed to close from left
        coordsBuilder.buildFromPoint(new Point(0, 0), "right", 3);
        ArrayList<Point> res = coordsBuilder.buildFromPoint(new Point(0, 3), "right", 3);
        Assert.assertEquals("List of coords should be empty if points placed too close other to points from left", res.size(), 0);
    }

    public void testBuildCoordsFromPointShouldReturnEmptyArrayListIfCoordsTooCloseToOtherPointsOnRight() {
        // another points placed to close from left
        coordsBuilder.buildFromPoint(new Point(0, 3), "right", 3);
        ArrayList<Point> res = coordsBuilder.buildFromPoint(new Point(0, 0), "right", 3);
        Assert.assertEquals("List of coords should be empty if points placed too close other to points from right", res.size(), 0);
    }
}
