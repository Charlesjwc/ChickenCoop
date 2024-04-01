import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;

public class ChickenRunner2
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(25,25);
        ActorWorld world = new ActorWorld(mygrid);
        
        world.add(new Location(14,14),new Chicken(20, 40));
        world.add(new Location(11,13),new Chicken(20, 40));
        world.add(new Location(16,16),new Chicken(20, 40));
        world.add(new Location(15,12),new Chicken(20, 40));
        world.add(new Location(23,24),new Chicken(20, 40));
        world.add(new Location(20,23),new Chicken(20, 40));
        world.add(new Location(2,23),new Chicken(20, 40));
        world.add(new Location(4,21),new Chicken(20, 40));
        world.add(new Location(5,24),new Chicken(20, 40));
        world.add(new Location(8,24),new Chicken(20, 40));
        world.add(new Location(11,20),new Chicken(20, 40));
        world.add(new Location(9,19),new Chicken(20, 40));
        world.add(new Location(13,19),new Chicken(20, 40));
        world.add(new Location(17,22),new Chicken(20, 40));
        world.add(new Location(6,8),new Chicken(20, 40));
        world.add(new Location(10,12),new Chicken(20, 40));
        world.add(new Location(5,5),new Chicken(20, 40));
        world.add(new Location(5,8),new Chicken(20, 40));
        world.add(new Location(8,20),new Chicken(20, 40));
        world.add(new Location(12,22),new Chicken(20, 40));
        world.add(new Location(23,12),new Chicken(20, 40));
        world.add(new Location(3,2),new Chicken(20, 40));
        world.add(new Location(0,0),new Chicken(20, 40));
        world.add(new Location(2,8),new Chicken(20, 40));
        world.add(new Location(21,18),new Chicken(20, 40));
        world.add(new Location(19,17),new Chicken(20, 40));
        world.add(new Location(8,7),new Chicken(20, 40));
        
        world.add(new Location(6,8),new Fox());
        world.add(new Location(18,23),new Fox());
        world.add(new Location(23,2),new Fox());
        world.add(new Location(24,24),new Fox());
        
        world.add(new Location(3,0),new Rock());
        world.add(new Location(0,3),new Rock());
        world.add(new Location(15,7),new Rock());
        world.add(new Location(11,6),new Rock());
        world.add(new Location(0,7),new Rock());	
        world.add(new Location(19,1),new Rock());
        world.add(new Location(20,17),new Rock());
        		
        world.show();
    }
}
