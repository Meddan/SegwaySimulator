package se.chalmers.segway.game;
/**
 * Model for an basic upgrade.
 * @author Wiixtor!
 *
 */
public class Upgrade {
	private int cost;
	private boolean enabled;
	
	/**
	 * 
	 * @param cost The amount of cookies it will cost to purchase this upgrade.
	 */
	public Upgrade(int cost) {
		this.cost = cost;
		this.enabled = false;
	}
	
	public void enable() {
		this.enabled = true;
	}
	
	public void disable() {
		this.enabled = false;
	}
}
