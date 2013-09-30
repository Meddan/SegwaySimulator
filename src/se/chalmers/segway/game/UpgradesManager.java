
package se.chalmers.segway.game;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.LinkedList;

import android.content.Context;
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
	public static void loadUpgrades() {
		File file = new File("upgrades");
		try {
			//TODO: Needs testing
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object obj = ois.readObject();
			if (obj != null && obj instanceof Upgrades){
				for (Upgrades upg : Upgrades.values()){
					if(upg.getName().equals(((Upgrades) obj).getName())){
						upg.setActive(((Upgrades) obj).isActivated());
					}
				}
			}
		} catch (Exception e){
			
		}
		
	}
	
	/**
	 * Writes which upgrades have been bought to a file.
	 */
	public static void saveUpgrades() {
		File file = new File("upgrades");
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for(Upgrades upg : Upgrades.values()){
				oos.writeObject(upg);
			}
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
