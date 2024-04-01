import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

public class TombstoneRunner
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(20,20);
        ActorWorld world = new ActorWorld(mygrid);
		
        world.add(new Location(0,0),new Tombstone());
        world.add(new Location(10,1),new Tombstone());
        world.add(new Location(12,15),new Tombstone());
        
        world.show();
    }
}
