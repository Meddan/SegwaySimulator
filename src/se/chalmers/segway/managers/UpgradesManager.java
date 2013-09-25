package se.chalmers.segway.managers;

public class UpgradesManager {
	/*
	 * Class variables
	 */ 
	static final UpgradesManager INSTANCE = new UpgradesManager();
	
	/*
	 * Upgrades
	 */
	private boolean goldCookies;
	
	/*
	 * Class methods
	 */
	
	/**
	 * Enables an upgrade and notifies the affected class.
	 * @param upgrade
	 */
	private void enableUpgrade(boolean upgrade) {
		upgrade = true;
		//TODO: Notify affected class
	}
	
	/**
	 * Disables an upgrade and notifies the affected class.
	 * @param upgrade
	 */
	private void disableUpgrade(boolean upgrade) {
		upgrade = false;
		//TODO: Notify affected class
	}
	
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
	 */
	private void loadUpgrades() {
		//TODO: File reader
	}
	
	/**
	 * Writes which upgrades have been bought to a file.
	 */
	private void saveUpgrades() {
		//TODO: File writer
	}
	
	public static UpgradesManager getInstance() {
		return INSTANCE;
	}
	
}

