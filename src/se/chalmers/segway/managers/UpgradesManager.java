package se.chalmers.segway.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.LinkedList;

import se.chalmers.segway.game.Upgrade;

/**
 * A class to keep track of what upgrades exist and whether they have been acquired by the player or not.
 * The class is a singleton in order to guarantee that all the upgrades are synchronized and that the
 * class is reachable in all parts of the app without having classes passing it along.
 */
public class UpgradesManager {
	/*
	 * Class variables
	 */ 
	static final UpgradesManager INSTANCE = new UpgradesManager();
	private LinkedList<String> Upgrades = new LinkedList<String>();
    private HashMap<String,Boolean> UpgradeEnabled = new HashMap<String,Boolean>();
	private HashMap<String,Integer> UpgradeCost = new HashMap<String,Integer>();

	/*
	 * Class methods
	 */
	
	
	/**
	 * Enables an upgrade and notifies the affected class.
	 * @param upgrade the upgrade to enable
	 */
	private void enableUpgrade(Upgrade upgrade ) {
	}
	
	/**
	 * Disables an upgrade and notifies the affected class.
	 * @param upgrade the upgrade to disable
	 */
	private void disableUpgrade(Upgrade upgrade) {
		upgrade.disable();
		//TODO: Notify affected class
		/**
		 * An enum to represent upgrades and provide useful information about them
		 * @author meddan
		 *
		 */
	}
	
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
     * Upgrades are stored in the format "Name Cost Enabled(true/false)"
	 */
	private void loadUpgrades() {
		//TODO: File reader
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("../../../../../assets/upgrades/currentupgrades.txt"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
               try {
                   String line = reader.readLine();

                   while (line!=null){
                       String[] split = line.split(" ");
                       if(Upgrades.contains(split[0])){
                    	   Upgrades.add(split[0]);
                       }
                       UpgradeCost.put(split[0],Integer.parseInt(split[1]));
                       UpgradeEnabled.put(split[0], Boolean.valueOf(split[2]));
                   }
               } catch(Exception e){
            	   System.out.println("Error: Loading of upgrades failed.");
               }
               try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Writes which upgrades have been bought to a file.
	 */
	private void saveUpgrades() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter("../../../../../assets/upgrades/currentupgrades.txt"));
		} catch (IOException e) {
			System.out.println("Writing went wrong");
			e.printStackTrace();
		}
		for(String upgradeName: Upgrades){
			try {
				writer.write(upgradeName + " " + UpgradeCost.get(upgradeName) + " " + UpgradeEnabled.get(upgradeName));
			} catch (IOException e) {
				System.out.println("Writing went wrong");				
				e.printStackTrace();
			}
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static UpgradesManager getInstance() {
		return INSTANCE;
	}
	
}
