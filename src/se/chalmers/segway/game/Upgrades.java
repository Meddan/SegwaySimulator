package se.chalmers.segway.game;

import java.io.Serializable;

/**
 * An enum that keeps track of the upgrades, their costs and whether they are enabled or not.
 * @author meddan
 *
 */
public enum Upgrades implements Serializable {
		AntigravityWheels (1000,false,"AntigravityWheels", "Jump higher!"),
		SuperHelmet(3000, false,"SuperHelmet","Protect dem braincells"),
		RocketBoost(5000,false,"RocketBoost","Go really really fast");
		
		private final int cost;
		private boolean isActive;
		private final String name;
		private final String info;
		private Upgrades(int cost, boolean isEnabled, String name, String info) {
			this.cost = cost; 
			this.isActive = isEnabled;
			this.name = name;
			this.info = info;
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
		public String getInfo(){
			return this.info;
		}
}
