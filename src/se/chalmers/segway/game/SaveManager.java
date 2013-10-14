
package se.chalmers.segway.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import se.chalmers.segway.resources.ResourcesManager;

/**
 * A class to save and load upgrades in between sessions.
 */
public class SaveManager {
	/**
	 * Reads from a file which upgrades have been bought in previous
	 * sessions and enables them.
	 */
	public static void loadUpgrades() {
		//Creates a path to the folder where the upgrades are saved.
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "upgrades");
		if(file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
				//Iterates over the hashmap of upgrades and matches them by name with the upgrades in the class Upgrades.
				if (obj != null && obj instanceof HashMap<?, ?>){
					for(Object i : ((HashMap<?,?>)obj).keySet()){
						for(Upgrades u : Upgrades.values()){
							if(u.getName().equals((String)i)){
								u.setActive((Boolean) ((HashMap<?,?>)obj).get(i));
							}
						}
					}
				}
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Writes which upgrades have been bought to a file.
	 */
	public static void saveUpgrades() {
		//Saves the upgrades in a hashmap. Identifies via name and saves whether or not they are enabled.
		HashMap<String,Boolean> saveMap = new HashMap<String, Boolean>();
		for(Upgrades upg : Upgrades.values()){
			saveMap.put(upg.getName(), upg.isActivated());
		}
		//The path where the file will be saved
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		path.mkdir();
		FileOutputStream fos;
		File file = new File(path, "upgrades");
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(saveMap);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Saves the players data.
	 * @param data the playerdata to be saved
	 */
	public static void savePlayerData(PlayerData data){
		//The path to the file
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		path.mkdir();
		FileOutputStream fos;
		File file = new File(path, "player");
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Returns the players saved data
	 * @return PlayerData from file, null if the file doesn't exist (the player has never saved). 
	 */
	public static PlayerData loadPlayerData(){
		//The path to the file
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "player");
		if(file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
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
			return null;
		}
	}
	/**
	 * Saves settings to a file
	 * @param settings the settings object to be saved
	 */
	public static void saveSettings(Settings settings){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		path.mkdir();
		FileOutputStream fos;
		File file = new File(path, "settings");
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(settings);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Loads settings from a file
	 * @return the settings. Null if no file was found.
	 */
	public static Settings loadSettings(){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "settings");
		if(file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				obj = ois.readObject();
				ois.close();
				if (obj != null && obj instanceof Settings){
					return (Settings)obj;
				} else {
					return null;
				}
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}
		
}