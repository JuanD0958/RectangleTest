package com.nuvalence.rectangletest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RectangleTest {

    @Test
    void testRectangleContains() {
        Rectangle rectangle1 = new Rectangle(0,0,10,10);
        Rectangle rectangle2 = new Rectangle(1,1,5,5);
        assertTrue(rectangle1.contains(rectangle2));
    }

    @Test
    void testRectangleNotContains() {
        Rectangle rectangle1 = new Rectangle(0,0,5,5);
        Rectangle rectangle2 = new Rectangle(1,1,5,5);
        assertFalse(rectangle1.contains(rectangle2));
    }

    @Test
    void testEqualRectangles() {
        Rectangle rectangle1 = new Rectangle(0,0,10,10);
        Rectangle rectangle2 = new Rectangle(0,0,10,10);
        assertTrue(rectangle1.equals(rectangle2));
    }

    @Test
    void testRectangles2Intersections() {
        Rectangle rectangle1 = new Rectangle(1,1,8,3);
        Rectangle rectangle2 = new Rectangle(8,0,4,5);

        List<Point> intersections = rectangle1.intersects(rectangle2);
        assertEquals(2,intersections.size());

        Point expectedPoint1 = new Point(8,1);
        Point expectedPoint2 = new Point(8,4);

        assertTrue(intersections.contains(expectedPoint1));
        assertTrue(intersections.contains(expectedPoint2));
    }

    @Test
    void testSwitchRectangles2Intersections() {
        Rectangle rectangle1 = new Rectangle(1,1,8,3);
        Rectangle rectangle2 = new Rectangle(8,0,4,5);

        List<Point> intersections = rectangle2.intersects(rectangle1);
        assertEquals(2,intersections.size());

        Point expectedPoint1 = new Point(8,1);
        Point expectedPoint2 = new Point(8,4);

        assertTrue(intersections.contains(expectedPoint1));
        assertTrue(intersections.contains(expectedPoint2));
    }

    @Test
    void testRectangles4Intersections() {
        Rectangle rectangle1 = new Rectangle(1,1,8,3);
        Rectangle rectangle2 = new Rectangle(8,0,1,5);

        List<Point> intersections = rectangle1.intersects(rectangle2);
        assertEquals(4,intersections.size());

        Point expectedPoint1 = new Point(8,1);
        Point expectedPoint2 = new Point(8,4);
        Point expectedPoint3 = new Point(9,1);
        Point expectedPoint4 = new Point(9,4);

        assertTrue(intersections.contains(expectedPoint1));
        assertTrue(intersections.contains(expectedPoint2));
        assertTrue(intersections.contains(expectedPoint3));
        assertTrue(intersections.contains(expectedPoint4));
    }

    @Test
    void testRectanglesAnyIntersections() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(8,1,2,2);

        List<Point> intersections = rectangle1.intersects(rectangle2);
        assertEquals(0,intersections.size());
    }

    @Test
    void testRectanglesAdjacencyProper() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(3,1,2,2);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.PROPER, adjacencyType);
    }

    @Test
    void testRectanglesAdjacencyPartial() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(3,1,2,1);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.PARTIAL, adjacencyType);
    }

    @Test
    void testRectanglesAdjacencySub() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(3,1,2,4);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.SUB_LINE, adjacencyType);
    }

    @Test
    void testRectanglesNotAdjacency() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(4,1,2,2);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.NO_ADJACENT, adjacencyType);
    }

    @Test
    void testRectanglesVerticalAdjacency() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(1,3,2,2);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.PROPER, adjacencyType);

    }

    @Test
    void testRectanglesNotVerticalAdjacency() {
        Rectangle rectangle1 = new Rectangle(1,1,2,2);
        Rectangle rectangle2 = new Rectangle(1,4,2,2);

        AdjacencyType adjacencyType = rectangle2.isAdjacent(rectangle1);
        assertEquals(AdjacencyType.NO_ADJACENT, adjacencyType);
    }
}
