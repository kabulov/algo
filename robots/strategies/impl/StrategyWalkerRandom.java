package ru.ncedu.java.dmsi.robots.strategies.impl;

import java.util.ArrayList;
import java.util.Collections;

import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.strategies.*;
import ru.ncedu.java.dmsi.robots.model.*;

public class StrategyWalkerRandom implements RobotStrategy {

	//implements drunk walking by moving randomly to one of 4 possible not not engaged positions
	
	@Override
	public Action getAction(ActionContext context, Point currentPosition) {
		//never shoots, moves only
		
		int[][] direction = { //nujno vinesti kuda to chtob vsem strategy bilo vidno
				{0, 1},
				{1, 0},
				{0, -1},
				{-1, 0}
		};
		
		ArrayList<Integer> order = new ArrayList<>();
		for (int i = 0; i < 4; ++i) order.add(i);
		Collections.shuffle(order);
		
		for (int i = 0; i < 4; ++i) {
			Point p = new Point(currentPosition.getX() + direction[order.get(i)][0], currentPosition.getY() + direction[order.get(i)][1]);
			if (context.isPointFree(p)) 
				return new MoveAction(p);
		}
		
		return new MoveAction(currentPosition);
	}

}
