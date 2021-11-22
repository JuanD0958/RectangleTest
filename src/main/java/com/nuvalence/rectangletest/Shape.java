package com.nuvalence.rectangletest;

import java.util.List;

interface Shape {

    boolean contains(Rectangle rectangle);

    AdjacencyType getAdjacencyType(Rectangle rectangle);

    List<Point> intersects(Rectangle rectangle);
}