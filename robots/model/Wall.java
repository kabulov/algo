package ru.ncedu.java.dmsi.robots.model;


public class Wall implements WorldObject {

	Point xy; 
	private int id;	//assigned by world
	
	public Wall(Point p, int id) {
		xy = new Point(p);
	}
	
	public Wall(int x, int y, int id) {
		xy = new Point(x, y);
	}
	
	@Override
	public Point getPoint() {
		return xy;
	}
	
	public int getId() {
		return id;
	}

}
