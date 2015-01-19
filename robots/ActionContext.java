package ru.ncedu.java.dmsi.robots;

import ru.ncedu.java.dmsi.robots.model.Point;
import ru.ncedu.java.dmsi.robots.world.World;

public class ActionContext {

	private World world;
	
	public ActionContext(World world) {
		this.world = world;
	}
	
	public boolean isPointFree(Point p) {
		return world.isPointFree(p);
	}
	
}
