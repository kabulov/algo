package ru.ncedu.java.dmsi.robots.strategies.impl;

import java.util.Random;

import ru.ncedu.java.dmsi.robots.model.*;
import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.strategies.*;

public class StrategyShooterRandom implements RobotStrategy{

	//shoots in random direction 
	
	@Override
	public Action getAction(ActionContext context, Point currentPosition) {
			
		int[][] direction = {
			{0, 1},
			{1, 0},
			{0, -1},
			{-1, 0}
		};
		
		int i = new Random().nextInt(4);
		Point p = new Point(currentPosition.getX() + direction[i][0], currentPosition.getY() + direction[i][1]);
		
		if (context.isPointFree(p)) {
			return new MoveAction(p);
		} else {
			return new MakeShotAction(p, new Point(direction[i][0], direction[i][1]));
		}
		
	}

}
