
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.awt.Color;

public class Chicken extends Critter
{
	//	Field Variables
	private int age;
	private int count;
	
	private static final double DARKENING_FACTOR = 0.05;
	
	//	Constructor
	public Chicken() {
		setColor(Color.WHITE);
		age = 0;
		count = 0;
	}
	
	//	Constructor
	public Chicken(int x, int y) {
		setColor(Color.WHITE);
		age = 0;
		count = 0;
	}
	
    /**
     * Override getActors to do nothing
     */
    public ArrayList<Actor> getActors()
    {
        return new ArrayList<Actor>();
    }
	
	//	50/50 between moving and turning
	public Location selectMoveLocation(ArrayList<Location> locs)
    {
		Location loc = getLocation().getAdjacentLocation(getDirection() 
													+ Location.LEFT);
        int r = (int) (Math.random() * 2);
        if (r == 0 && getGrid().isValid(loc) && getGrid().get(loc) == null) 
			return loc;
        return getLocation();
    }
    
    /**
     * 50/50 between moving and turning, turns if can't move
     */
    public void makeMove(Location loc)
    {
		age ++;
		count ++;
		int threshold = 1;
		if (age > 200) threshold = 2;
		if (age > 280) {
			threshold = 4;
			Color c = getColor();
			int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

			setColor(new Color(red, green, blue));
		}
		if (age == 300) {
			die();
		}
		if (age >= 300)
			count = 0;

		
		if (count == threshold) {
			//	Turn
			if (loc.equals(getLocation())) {
				int turns = (int)(Math.random() * 7);
				for (int i = 0; i < turns; i++) {
					setDirection(getDirection() + Location.HALF_RIGHT);
				}
			}
			// Move
			else {
				Location pastLoc = getLocation();
				moveTo(loc);
				if (age <= 200) {
					
					int r = (int)(Math.random() * 20);
					if (r == 13) {
						Actor egg = new Egg();
						egg.putSelfInGrid(getGrid(), pastLoc);
					}
				}
			}
			count = 0;
		}
    }
    
    public void die() {
		Location l = getLocation();
		Actor stone = new Tombstone();
		stone.putSelfInGrid(getGrid(), l);
	}
}
