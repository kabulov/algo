package ru.ncedu.java.dmsi.robots.model;

import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.strategies.*;

public class Robot extends ActiveWorldObject implements RobotStrategy {

	private RobotStrategy strategy;
	
	public Robot(RobotStrategy initialStrategy, int id, Point point) {
		super(id, point);
		strategy = initialStrategy;		
	}

	@Override
	public Action getAction(ActionContext context, Point currentPosition) {//currentPosition is used to define new position
		return strategy.getAction(context, currentPosition);
	}
}
