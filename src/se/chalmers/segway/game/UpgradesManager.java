
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

/**
 * A class to save and load upgrades in between sessions.
 */
public class UpgradesManager {
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
	 */
	public static void loadUpgrades() {
		File file = new File("upgrades");
		try {
			//TODO: Needs testing
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			ObjectInputStream ois = new ObjectInputStream(bis);
			Object obj = ois.readObject();
			while (obj != null){
				if (obj != null && obj instanceof Upgrades){
					for (Upgrades upg : Upgrades.values()){
						if(upg.getName().equals(((Upgrades) obj).getName())){
							upg.setActive(((Upgrades) obj).isActivated());
						}
					}
				}
				obj = ois.readObject();
			}
			ois.close();
			bis.close();
			fis.close();
		} catch (Exception e){
			e.printStackTrace();
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
