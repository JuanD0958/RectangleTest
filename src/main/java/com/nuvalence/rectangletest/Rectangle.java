package com.nuvalence.rectangletest;


import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

enum AdjacencyType {
    SUB_LINE,
    PROPER,
    PARTIAL,
    NO_ADJACENT
}


public class Rectangle implements Shape{

    private int x;
    private int y;
    private int width;
    private int height;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean contains(Rectangle rectangle) {
        int originalRectangleWidth = this.width;
        int originalRectangleHeight = this.height;

        int originalRectanglePositionX = this.x;
        int originalRectanglePositionY = this.y;

        if ((originalRectangleWidth | originalRectangleHeight | rectangle.x | rectangle.y) < 0) {
            return false;
        }

        if (rectangle.x < originalRectanglePositionX || rectangle.y < originalRectanglePositionY) {
            return false;
        }

        int originalRectangleSize = originalRectanglePositionX + originalRectangleWidth;
        int rectangleSize = rectangle.x + rectangle.width;

        if ((rectangleSize <= rectangle.x) && (originalRectangleSize >= originalRectanglePositionX || rectangleSize > originalRectangleWidth)){
            return false;
        }else{
            if (originalRectangleWidth >= originalRectanglePositionX && rectangleSize > originalRectangleWidth){
                return false;
            }
        }

        int originalRectangleHeightSize = originalRectangleHeight + originalRectanglePositionY;
        int rectangleHeightSize = rectangle.y + rectangle.height;

        if ((rectangleHeightSize <= rectangle.y) && (originalRectangleHeightSize >= rectangle.y || rectangleHeightSize > originalRectangleHeightSize)){
            return false;
        } else {
            if (originalRectangleHeight >= originalRectanglePositionY && rectangleHeightSize > originalRectangleHeight){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Point> intersects(Rectangle rectangle) {
        List<Point> intersections = new ArrayList<>();

        int rectangle1PosX = this.x;
        int rectangle1PosY = this.y;
        int rectangle2PosX = rectangle.x;
        int rectangle2PosY = rectangle.y;

        int rectangle1TotalWidth = rectangle1PosX + this.width;
        int rectangle1TotalHeight = rectangle1PosY + this.height;

        int rectangle2TotalWidth = rectangle2PosX + rectangle.width;
        int rectangle2TotalHeight = rectangle2PosY + rectangle.height;


        if (rectangle1PosX < rectangle2PosX) {
            rectangle1PosX = rectangle2PosX;
        }
        if (rectangle1PosY < rectangle2PosY){
            rectangle1PosY = rectangle2PosY;
        }
        if (rectangle1TotalWidth > rectangle2TotalWidth){
            rectangle1TotalWidth = rectangle2TotalWidth;
        }
        if (rectangle1TotalHeight > rectangle2TotalHeight){
            rectangle1TotalHeight = rectangle2TotalHeight;
        }

        rectangle1TotalWidth -= rectangle1PosX;
        rectangle1TotalHeight -= rectangle1PosY;

        if (rectangle1TotalWidth < 0) {
            return intersections;
        }

        if ((rectangle1PosX == this.x && rectangle1PosY == rectangle.y) || (rectangle1PosX == rectangle.x && rectangle1PosY == this.y)) {
           Point pos1 = new Point(rectangle1PosX,rectangle1PosY);
           intersections.add(pos1);
        }

        if (((rectangle1PosX + rectangle1TotalWidth) == (this.x + this.width) && rectangle1PosY == rectangle.y) || ((rectangle1PosX + rectangle1TotalWidth) == (rectangle.x + rectangle.width) && rectangle1PosY == this.y)) {
            Point pos2 = new Point((rectangle1PosX + rectangle1TotalWidth),rectangle1PosY);
            intersections.add(pos2);
        }


        if ((rectangle1PosX == this.x && (rectangle1PosY + rectangle1TotalHeight) == (rectangle.y + rectangle.height)) || (rectangle1PosX == rectangle.x && (rectangle1PosY + rectangle1TotalHeight) == (this.y + this.height))) {
            Point pos3 = new Point(rectangle1PosX,(rectangle1PosY + rectangle1TotalHeight));
            intersections.add(pos3);
        }

        if ((  (rectangle1PosX + rectangle1TotalWidth) == (this.x + this.width) && (rectangle1PosY + rectangle1TotalHeight) == (rectangle.y + rectangle.height)) || ((rectangle1PosX + rectangle1TotalWidth) == (rectangle.x + rectangle.width) && (rectangle1PosY + rectangle1TotalHeight) == (this.y + this.height))) {
            Point pos4 = new Point((rectangle1PosX + rectangle1TotalWidth) ,(rectangle1PosY + rectangle1TotalHeight));
            intersections.add(pos4);
        }

        return intersections;
    }

    @Override
    public AdjacencyType isAdjacent(Rectangle rectangle){
        if ((this.x == rectangle.getX() + rectangle.width) || (this.x + this.width == rectangle.getX())) {
            if ((this.height - rectangle.height) == 0) {
                return AdjacencyType.PROPER;
            }
            if (this.height - rectangle.height > 0) {
                return AdjacencyType.SUB_LINE;
            }else{
                return AdjacencyType.PARTIAL;
            }
        }
        if ((this.y == rectangle.getY() + rectangle.height) || (this.y + this.height == rectangle.getY())) {
            if ( this.width - rectangle.width == 0) {
                return AdjacencyType.PROPER;
            }
            if ( this.width  - rectangle.width > 0) {
                return AdjacencyType.SUB_LINE;
            }else{
                return AdjacencyType.PARTIAL;
            }
        }
        return AdjacencyType.NO_ADJACENT;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle rectangle = (Rectangle)obj;
            return ((x == rectangle.x) && (y == rectangle.y) && (width == rectangle.width) && (height == rectangle.height));
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }
}
