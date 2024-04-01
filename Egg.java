import info.gridworld.actor.Actor;
import java.awt.Color;

public class Egg extends Actor{
	
	private int lifetime;
	
	public Egg() {
		setColor(Color.WHITE);
		lifetime = 50;
	}
	
	public void act() {
		if (lifetime <= 0) {
			Actor c = new Chicken();
			c.putSelfInGrid(getGrid(), getLocation());
		}
		if (lifetime == 5)
			setColor(Color.RED);
		lifetime --;
	}
}
