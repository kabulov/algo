package ru.ncedu.java.dmsi.robots.model;

public class ActiveWorldObject implements WorldObject {

	protected int id;	//assigned by world, is inherited by Robot and Bullet
	protected Point xy;
	
	public ActiveWorldObject(int id, Point p) {
		this.id = id;
		xy = p;
	}	

	@Override
	public Point getPoint() {
		return xy;
	}
	
	public int getId() {
		return id;
	}

	public void setPoint(Point point) {	//is set by world
		xy = point;
	}
}
