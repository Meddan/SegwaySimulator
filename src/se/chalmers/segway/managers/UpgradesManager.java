package se.chalmers.segway.managers;

import se.chalmers.segway.game.Upgrade;

/**
 * A class to keep track of what upgrades exist and wether they have been aquired by the player or not.
 * The class is a singleton in order to guarantee that all the upgrades are synched.
 */
public class UpgradesManager {
	/*
	 * Class variables
	 */ 
	static final UpgradesManager INSTANCE = new UpgradesManager();

	
	/*
	 * Upgrades &
	 * initialization
	 */
	//Example upgrade.
	private Upgrade goldCookies = new Upgrade(666);

	/*
	 * Class methods
	 */
	
	/**
	 * Enables an upgrade and notifies the affected class.
	 * @param upgrade the upgrade to enable
	 */
	private void enableUpgrade(Upgrade upgrade) {
		upgrade.enable();
		//TODO: Notify affected class
	}
	
	/**
	 * Disables an upgrade and notifies the affected class.
	 * @param upgrade the upgrade to disable
	 */
	private void disableUpgrade(Upgrade upgrade) {
		upgrade.disable();
		//TODO: Notify affected class
	}
	
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
     * Upgrades are stored in the format "Name Cost Enabled(true/false)"
	 */
	private void loadUpgrades() {
		//TODO: File reader
        BufferedReader reader = new BufferReader(new FileReader("../../../../../assets/upgrades/currentupgrades.txt"))
               try {
                   StringBuilder builder = new StringBuilder();
                   String line = br.readLine();

                   while (line!=null){
                       String[] split = line.split(" ");

                   }
               }

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
