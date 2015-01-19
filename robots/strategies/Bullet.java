package ru.ncedu.java.dmsi.robots.strategies;

import ru.ncedu.java.dmsi.robots.model.ActiveWorldObject;
import ru.ncedu.java.dmsi.robots.model.Point;

public class Bullet extends ActiveWorldObject{
	
	private Point motionVector;
	
	public Bullet(int id, Point p, Point motionVector) {
		super(id, p);
		this.motionVector = motionVector;	//direction
	}
	
	public Point getMotionVector() {
		return motionVector;
	}
			
}
