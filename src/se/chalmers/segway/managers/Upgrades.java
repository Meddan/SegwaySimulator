package se.chalmers.segway.managers;

/**
 * An enum that keeps track of the upgrades, their costs and whether they are enabled or not.
 * @author meddan
 *
 */
public enum Upgrades {
		AntigravityWheels (100,false),
		SuperHelmet(300, false);
		
		private final int cost;
		private boolean isActive;
		private Upgrades(int cost, boolean isEnabled) {
			this.cost = cost; 
			this.isActive = isEnabled;
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
		
}
