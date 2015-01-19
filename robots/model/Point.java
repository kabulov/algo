package ru.ncedu.java.dmsi.robots.model;

public class Point {

	private final int x, y;
	
	public Point(Point from) {
		this.x = from.x;
		this.y = from.y;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
