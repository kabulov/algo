package ru.ncedu.java.dmsi.robots.strategies;

import ru.ncedu.java.dmsi.robots.model.Point;

public class MoveAction implements Action {
	
	private Point to;
	
	public MoveAction(int x, int y) {
		to = new Point(x, y);
	}
	
	public MoveAction(Point p) {
		to = new Point(p);
	}
	
	public Point getDest() {//moves to to
		return to;
	}

}
