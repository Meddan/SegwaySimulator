package se.chalmers.segway.game;

import java.io.Serializable;

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
