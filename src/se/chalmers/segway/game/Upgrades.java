package se.chalmers.segway.game;

import java.io.Serializable;

/**
 * An enum that keeps track of the upgrades, their costs and whether they are enabled or not.
 * @author meddan
 *
 */
public enum Upgrades implements Serializable {
		AntigravityWheels (100,false,"AntigravityWheels"),
		SuperHelmet(300, false,"SuperHelmet"),
		RocketBoost(500,false,"RocketBoost");
		
		private final int cost;
		private boolean isActive;
		private final String name;
		private Upgrades(int cost, boolean isEnabled, String name) {
			this.cost = cost; 
			this.isActive = isEnabled;
			this.name = name;
		}
		/**
		 * Returns the cost of the upgrade
		 * @return the cost
		 */
		public int getCost(){
			return this.cost;
		}
		/**
		 * Returns whether the upgrade is active or not
		 * @return whether it is active or not
		 */
		public boolean isActivated(){
			return this.isActive;
		}
		/**
		 * Sets the active status of the upgrade
		 * @param b
		 */
		public void setActive(boolean b){
			this.isActive = b;
		}
		/**
		 * Returns the name of the upgrade as a string
		 * @return the name
		 */
		public String getName(){
			return this.name;
		}
		
}
