package com.nuvalence.rectangletest;

import java.util.List;

interface Shape {

    boolean contains(Rectangle rectangle);

    AdjacencyType isAdjacent(Rectangle rectangle);

    List<Point> intersects(Rectangle rectangle);
}