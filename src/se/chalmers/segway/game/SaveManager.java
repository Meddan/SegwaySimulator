
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
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "upgrades");
		if(file.exists()){
			try {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object obj = ois.readObject();
				ois.close();
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
		HashMap<String,Boolean> saveMap = new HashMap<String, Boolean>();
		for(Upgrades upg : Upgrades.values()){
			saveMap.put(upg.getName(), upg.isActivated());
		}
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
	 * @return PlayerData from file, null if something went wrong, the file doesn't exist
	 */
	public static PlayerData loadPlayerData(){
		File path=new File(ResourcesManager.getInstance().activity.getFilesDir(),"saves");
		File file = new File(path, "player");
		if(file.exists()){
			try {
				//TODO: Needs testing
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