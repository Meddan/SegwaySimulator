
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

import se.chalmers.segway.resources.ResourcesManager;
import android.content.Context;

/**
 * A class to save and load upgrades in between sessions.
 */
public class SaveManager {
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
	 */
	public static void loadUpgrades() {
		File file = new File("upgrades");
		if(file.exists()){
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
	/**
	 * Saves the players data.
	 * @param data the playerdata to be saved
	 */
	public static void savePlayerData(PlayerData data){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		path.mkdir();
		FileOutputStream fos;
		File file = new File(path, "player");
		try {
			System.out.println("TRYING TO WRITE BITCH");
			fos = new FileOutputStream(file);
			
			System.out.println("FOS CREATED BITCH");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			System.out.println("OOS CREATED BITCH");
			oos.writeObject(data);
			System.out.println("WROTE DATA BITCH");
			oos.close();
			System.out.println("CLOSED BITCH");
			System.out.println("Exactly after it is " + file.exists() + " BITCH");
		} catch (Exception e) {
			System.out.println("EXCEPTION BITCH");
			e.printStackTrace();
		}
		//System.out.println(new File("player").exists() + " BITCH");
	}
	/**
	 * Returns the players saved data
	 * @return PlayerData from file, null if something went wrong, the file doesn't exist
	 */
	public static PlayerData loadPlayerData(){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "player");
		if(file.exists()){
			System.out.println("FOUND FILE BITCH");
			try {
				//TODO: Needs testing
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				obj = ois.readObject();
				ois.close();
				if (obj != null && obj instanceof PlayerData){
					return (PlayerData) obj;
				} else {
					return null;
				}
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}
		} else {
			System.out.println("NO FILE BITCH");
			return null;
		}
	}
		
}
