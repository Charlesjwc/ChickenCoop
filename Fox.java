import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

import java.awt.Color;

public class Fox extends Critter {
	
	// 	# of move calls to nap
	private final int NAP_TURNS = 10;
	//	# of hunger for death
	private final int MAX_HUNGER = 20;
	/*	Hunger starts at - max napTurns at the start of napping. The fox dies
	 * 	if hunger = 20 */
	private int hunger;
	/*	if napTurns = 0, the fox is not napping */
	private int napTurns;
	
	public Fox() {
		setColor(null);
		hunger = 0;
		napTurns = 0;
	}
	
	public ArrayList<Actor> getActors() {
		//	Get adjacent
		ArrayList<Actor> adjacent = super.getActors();
		//	Only have chickens outputted
		ArrayList<Actor> chickens = new ArrayList<Actor>(8);
		for (int i = 0; i < adjacent.size(); i++) {
			if (adjacent.get(i) instanceof Chicken) {
				chickens.add(adjacent.get(i));
			}
		}
		return chickens;
	}
	
	public void processActors(ArrayList<Actor> actors) {
		if (napTurns == 0) {
			int n = actors.size();
			if (n == 0)
				return;
			//	Choose a random victim
			int r = (int) (Math.random() * n);
			Actor victim = actors.get(r);
			Location l = victim.getLocation();
			//	Kill victim
			victim.removeSelfFromGrid();
			//	Replace with tombstone
			Tombstone stone = new Tombstone();
			stone.putSelfInGrid(getGrid(), l);
			hunger = NAP_TURNS * -1;
			napTurns = NAP_TURNS;
		}
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs) {
		//	Get distance to all chickens on grid
		//	Get all chickens on grid
		ArrayList<Actor> chickens = getAllChickens();
		//	Find smallest distance to a chicken
		double smallestDist = -1;
		int smallestIndex = -1;
		
		//	Get distance of every chicken
		Location locFox = getLocation();
		for (int i = 0; i < chickens.size(); i++) {
			Location locChick = chickens.get(i).getLocation();
			double rowDiff = locChick.getRow() - locFox.getRow();
			double colDiff = locChick.getCol() - locFox.getCol();
			double dist = Math.sqrt((rowDiff * rowDiff) + (colDiff * colDiff));
			//	Check if dist is smalles
			if (dist < smallestDist || smallestDist == -1) {
				smallestDist = dist;
				smallestIndex = i;
			}
		}
		
		if (smallestIndex == -1)
			return super.selectMoveLocation(locs);
		
		//	Dont move if chicken adj, accounting for diagonals.
		if (smallestDist < 1.5)
			return getLocation();
		
		//	Get direction towards closest
		Actor closest = chickens.get(smallestIndex);
		int dir = locFox.getDirectionToward(closest.getLocation());
		
		/* 	If square in front is occupied, add 45, if occupied then
		 * 	subtract 90, then add 135. then subtract 180. If all of those
		 * 	are occupied, move randomly */
		int change = 45;
		while (Math.abs(change) <= 180) {
			Location l = locFox.getAdjacentLocation(dir);
			if (getGrid().isValid(l) && getGrid().get(l) == null)
				return l;
			else {
				if (change > 0)
					change += 45;
				if (change < 0)
					change -= 45;
				change = change * -1;
			}
			dir += change;
		}
		return super.selectMoveLocation(locs);
	}
	
	private ArrayList<Actor> getAllChickens() {
		//	Get all actors
		ArrayList<Location> actorLocs = getGrid().getOccupiedLocations();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for (Location l: actorLocs) {
			actors.add(getGrid().get(l));
		}
		//	Store only chickens
		ArrayList<Actor> chickens = new ArrayList<Actor>();
		for (int i = 0; i < actors.size(); i++) {
			if (actors.get(i) instanceof Chicken) {
				chickens.add(actors	.get(i));
			}
		}
		return chickens;
	}
	
	public void makeMove(Location loc) {
		hunger ++;
		if (hunger > MAX_HUNGER) {
			Location l = getLocation();
			Actor stone = new Tombstone();
			stone.putSelfInGrid(getGrid(), l);
			return;
		}
		if (napTurns > 0) {
			napTurns --;
		} else {
			super.makeMove(loc);
		}
	}
}
