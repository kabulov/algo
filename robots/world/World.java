package ru.ncedu.java.dmsi.robots.world;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import ru.ncedu.java.dmsi.robots.ActionContext;
import ru.ncedu.java.dmsi.robots.model.*;
import ru.ncedu.java.dmsi.robots.strategies.*;

//this world has borders, and any position outside the world is considered to be not free!

public class World {

	private int id;	//razdaem id vsem novim worldobjectam
	private int width, height;

	private static final int maxw = 500;
	private static final int maxh = 500;
	
	//everybody has id
	
	HashMap<Integer, WorldObject> all; //id -> worldobject
	boolean[][] used; //is used position
	int[][] idMap; //id of worldobject in this position

	private HashSet<Integer> st; //use to store and then delete worldobjects with id`s in st	
	
	public World(int w, int h) throws Exception {
		
		if (w < 0 || w > maxw) throw new Exception();
		if (h < 0 || h > maxh) throw new Exception();
		
		id = 0;
		width = w;
		height = h;
		
		used = new boolean[w][h]; //w - x, h -y
		for (int i = 0; i < w; ++i)
			for (int j = 0; j < h; ++j)
				used[i][j] = false;
		
		idMap = new int[w][h];
		for (int i = 0; i < w; ++i)
			for (int j = 0; j < h; ++j)
				idMap[i][j] = 0;
		
		all = new HashMap<>();
		st = new HashSet<>();
		
	}
	
	private boolean isOutside(Point p) {
		int x = p.getX();
		int y = p.getY();
		return x < 0 || x >= width || y < 0 || y >= height;
	}
	
	public boolean isPointFree(Point p) {
		if (isOutside(p)) return false;
		return !used[p.getX()][p.getY()];
	}
	
	public int getNewId() {
		return ++id;
	}
	
	public void addWall(int x, int y) {//never used by now
		if (used[x][y]) return;
		used[x][y] = true;
		int id = getNewId();
		Wall wall = new Wall(new Point(x, y), id);
		all.put(id, wall);
		idMap[x][y] = id;
	}

	public void addRobot(Robot robot) {
		Point point = robot.getPoint();
		used[point.getX()][point.getY()] = true;
		all.put(robot.getId(), robot);
		idMap[point.getX()][point.getY()] = robot.getId();
	}
	
	public void addBullet(Bullet bullet) {
		Point p = bullet.getPoint();
		used[p.getX()][p.getY()] = true;
		idMap[p.getX()][p.getY()] = bullet.getId();
		all.put(bullet.getId(), bullet);
	}
	
	public void moveRobot(Robot robot) {
		//dvigaemsa v novuyu poziciyu so vsemi proverkami, ili puskaem pulyu so vsemi proverkami
		
		//try perenesti context kak pole i init v nachale tolko 
		Action action = robot.getAction(new ActionContext(this), robot.getPoint());
		if (action instanceof MoveAction) { //bad...
			//pod pulyu ne popadet. s drugim robotom v odnoy kletke ne okajetsa. na stenku i za kartu ne viydet
			Point dest = ((MoveAction)action).getDest();
			if (isOutside(dest)) {
				st.add(robot.getId());
				return;
			}
			if (used[dest.getX()][dest.getY()]) return;
			used[dest.getX()][dest.getY()] = true;
			idMap[dest.getX()][dest.getY()] = robot.getId();
			int x, y;
			x = robot.getPoint().getX();
			y = robot.getPoint().getY();
			used[x][y] = false;
			idMap[x][y] = 0;
			robot.setPoint(dest);			
		} else {
			MakeShotAction shot = (MakeShotAction)action;
			Point origin = shot.getOrigin();
			if (isOutside(origin)) 
				return;
			
			if (!used[origin.getX()][origin.getY()]) {
				addBullet(new Bullet(getNewId(), origin, shot.getMotionVector()));
				return;
			}
			
			if (all.get(idMap[origin.getX()][origin.getY()]) instanceof Wall) //bad
				return;
			
			//otherwise robot or another bullet
			st.add(idMap[origin.getX()][origin.getY()]); //kill what is in origin
		}
	}
	
	public void moveBullet(Bullet bullet) {
		//esli uletel naruju ubivaem, esli stena ubivaem, esli pustaya kletka dvigaem, inache bullet ili robot, v lubom sluchae ubivaemsa s tem chto v kletke 
		
		Point current = bullet.getPoint();
		used[current.getX()][current.getY()] = false;
		Point direction = bullet.getMotionVector();
		Point next = new Point(current.getX() + direction.getX(), current.getY() + direction.getY());
		
		if (isOutside(next)) {
			st.add(bullet.getId());
			return;
		}
		
		bullet.setPoint(next);
		
		if (used[next.getX()][next.getY()]) {
			//che to tut ne chisto : vzames equal instanceof, xotya mojno cherez getclass getname no dolgo
			st.add(bullet.getId());
			if (!(all.get(idMap[next.getX()][next.getY()]) instanceof Wall)) {
				st.add(idMap[next.getX()][next.getY()]);
			} 
			return;
		} else {
			used[next.getX()][next.getY()] = true;
		}		
	}
	
	public void moveAll() { //one round
		//if an object was killed in this round its not processed and is in st
		st.clear();
		
		for (Iterator<Integer> it = all.keySet().iterator(); it.hasNext();){
			int id = it.next();
			if (st.contains(id)) continue;
			WorldObject wo = all.get(id);
			if (wo instanceof Bullet) {//che to tut nechisto
				moveBullet((Bullet)wo); //tut toje
			} else {
				moveRobot((Robot)wo);
			}
		}
		
		for (Iterator<Integer> it = st.iterator(); it.hasNext(); ) {
			int id = it.next();
			WorldObject awo = all.get(id);
			int x = awo.getPoint().getX(), y = awo.getPoint().getY();
			used[x][y] = false;
			all.remove(id);
		}
		
		st.clear();
	}
	
}
