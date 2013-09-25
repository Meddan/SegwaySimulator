package se.chalmers.segway.game;
/**
 * Model for an basic upgrade.
 * @author Wiixtor!
 *
 */
public class Upgrade {
	private String name;
	private int cost;
	private boolean enabled;
	
	public Upgrade(String name, int cost, boolean enabled) {
		this.name = name;
		this.cost = cost;
		this.enabled = enabled;
	}
	
	public void enable(Upgrade upgrade) {
		upgrade.enabled = true;
	}
	
	public void disable(Upgrade upgrade) {
		upgrade.enabled = false;
	}
}
