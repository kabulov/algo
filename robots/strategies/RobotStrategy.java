package ru.ncedu.java.dmsi.robots.strategies;
import ru.ncedu.java.dmsi.robots.model.*;
import ru.ncedu.java.dmsi.robots.ActionContext;

public interface RobotStrategy {

	public Action getAction(ActionContext context, Point currentPosition);		
	
}
