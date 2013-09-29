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
		private final boolean isEnabled;
		private Upgrades(int cost, boolean isEnabled) {
			this.cost = cost; 
			this.isEnabled = isEnabled;
		}
		public int getCost(){
			return this.cost;
		}
		public boolean isEnabled(){
			return this.isEnabled;
		}
		
}
