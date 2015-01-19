package ru.ncedu.java.dmsi.robots.strategies;

import ru.ncedu.java.dmsi.robots.model.Point;

public class MakeShotAction implements Action {

	private Point origin;
	private Point motionVector;
	
	public MakeShotAction(Point p, Point motionVector) {
		this.origin = p;
		this.motionVector = motionVector;
	}
	
	public Point getOrigin() {//first location/site
		return origin;
	}
	
	public Point getMotionVector() {//direction
		return motionVector;
	}

}
