package com.nuvalence.rectangletest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class RectangletestApplication {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("We are going to add the rectangles properties using this format (position x, position y, width, height)");
		System.out.printf("Rectangle 1:\n ");

		System.out.println("Position X:");
		int posX = in.nextInt();
		System.out.println("Position Y:");
		int posY = in.nextInt();

		System.out.println("Width:");
		int width = in.nextInt();

		System.out.println("Height:");
		int height = in.nextInt();

		Rectangle rectangle = new Rectangle(posX, posY, width, height);

		System.out.printf("Rectangle 2:\n ");

		System.out.println("Position X:");
		posX = in.nextInt();
		System.out.println("Position Y:");
		posY = in.nextInt();

		System.out.println("Width:");
		width = in.nextInt();

		System.out.println("Height:");
		height = in.nextInt();

		Rectangle rectangle2 = new Rectangle(posX, posY, width, height);

		System.out.printf("Rectangle 1 contains Rectangle 2: %b \n",rectangle.contains(rectangle2));

		System.out.printf("Is Rectangle 1 adjacent to Rectangle 2: %b\n",rectangle.isAdjacent(rectangle2));

		List<Point> intersections = rectangle.intersects(rectangle2);
		System.out.printf("Intersections between both rectangles %d\n", intersections.size());
		for(Point point: intersections) {
			System.out.printf("Point(%d, %d)\n", point.getX(), point.getY());
		}
	}
}
