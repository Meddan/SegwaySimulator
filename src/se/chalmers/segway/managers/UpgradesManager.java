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
	 * Class methods
	 */
	
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
     * Upgrades are stored in the format "Name Enabled(true/false)"
	 */
	private void loadUpgrades() {
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("../../../../../assets/upgrades/currentupgrades.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
               try {
                   String line = reader.readLine();

                   while (line!=null){
                	   //Splits the line so that the first part of split contains the name and the second part contains whether the upgrade was enabled or not
                       String[] split = line.split(" ");
                       for(Upgrades upg : Upgrades.values()){
                    	   if(upg.getName().equals(split[0])){
                    		   upg.setActive(Boolean.parseBoolean(split[1]));
                    	   }
                       }
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
		for(Upgrades upg : Upgrades.values()){
			try {
				writer.write(upg.getName() + " " + upg.isActivated());
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
}
