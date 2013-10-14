package se.chalmers.segway.game;

import java.io.Serializable;
/**
 * A class that keeps track of the players settings so that they can be saved between sessions.
 * @author meddan
 *
 */
public class Settings implements Serializable{
	private boolean soundIsOn = true;
	public Settings(boolean soundIsOn){
		this.soundIsOn = soundIsOn;
	}
	public void setSound(Boolean b){
		soundIsOn = b;
	}
	public boolean isSoundOn(){
		return soundIsOn;
	}
}
