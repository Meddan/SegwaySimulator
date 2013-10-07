
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
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "upgrades");
		if(file.exists()){
			System.out.println("FOUND UPGRADE FILE BITCH");
			try {
				//TODO: Needs testing
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				obj = ois.readObject();
				ois.close();
				if (obj != null && obj instanceof Upgrades){
					for(Upgrades u : Upgrades.values()){
						for(Upgrades v : Upgrades.values()){
							if (u.getName().equals(v.getName())){
								v.setActive(u.isActivated());
							}
						}
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		} else {
			System.out.println("NO UPGRADE FILE BITCH");
		}
		
	}
	
	/**
	 * Writes which upgrades have been bought to a file.
	 */
	public static void saveUpgrades() {
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		System.out.println("BITCH IS DIR " +  path.isDirectory());
		path.mkdir();
		System.out.println("BITCH IS DIR " +  path.isDirectory());
		FileOutputStream fos;
		File file = new File(path, "upgrades");
		try {
			System.out.println("TRYING TO WRITE UPGRADE BITCH");
			fos = new FileOutputStream(file);
			
			System.out.println("FOS CREATED BITCH");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			System.out.println("OOS CREATED BITCH");
			oos.writeObject(Upgrades.class);
			System.out.println("WROTE UPGRADE DATA BITCH");
			oos.close();
			System.out.println("CLOSED BITCH");
			System.out.println("Exactly after it is " + file.exists() + " BITCH");
		} catch (Exception e) {
			System.out.println("EXCEPTION BITCH");
			e.printStackTrace();
			
		}
	}
	/**
	 * Saves the players data.
	 * @param data the playerdata to be saved
	 */
	public static void savePlayerData(PlayerData data){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		System.out.println("BITCH IS DIR " +  path.isDirectory());
		path.mkdir();
		System.out.println("BITCH IS DIR " +  path.isDirectory());
		FileOutputStream fos;
		File file = new File(path, "player");
		try {
			System.out.println("TRYING TO WRITE PLAYER BITCH");
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
			System.out.println("FOUND PLAYER FILE BITCH");
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
			System.out.println("NO PLAYER FILE BITCH");
			return null;
		}
	}
		
}
