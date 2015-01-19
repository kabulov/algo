package ru.ncedu.java.dmsi.robots.strategies.impl;

import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.strategies.*;
import ru.ncedu.java.dmsi.robots.model.*;

public class StrategyShooterAround implements RobotStrategy {

	//shoots all directions(4 directions)
	
	private int state;
	
	public StrategyShooterAround() {
		state = 0;
	}
	
	@Override
	public Action getAction(ActionContext context, Point currentPosition) {
		
		int[][] direction = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
		};
		
		++state;
		state %= 4;
		
		return new MakeShotAction(new Point(currentPosition.getX() + direction[state][0], currentPosition.getY() + direction[state][1]), 
													  										new Point(direction[state][0], direction[state][1]));
	}
	

}
