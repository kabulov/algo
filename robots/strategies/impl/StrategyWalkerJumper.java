package ru.ncedu.java.dmsi.robots.strategies.impl;

import java.util.Random;

import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.strategies.*;
import ru.ncedu.java.dmsi.robots.model.*;

public class StrategyWalkerJumper implements RobotStrategy {

	//jumper
	
	private Random rand;
	
	public StrategyWalkerJumper() {
		rand = new Random();
	}
	
	@Override
	public Action getAction(ActionContext context, Point currentPosition) {
		int dist = rand.nextInt(10); //max step is 9
		Point p = new Point(currentPosition.getX() + new Random().nextInt(dist), currentPosition.getY() + new Random().nextInt(dist));
		
		if (context.isPointFree(p)) 
			return new MoveAction(p);
		
		return new MoveAction(currentPosition);
	}
}
