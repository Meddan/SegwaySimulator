package se.chalmers.segway.game;

import java.io.Serializable;

/**
 * A class to sava data about the player
 * @author meddan
 *
 */
public class PlayerData implements Serializable{
	private String name;
	private int cash;
	private int highestLevelFinished;
	public PlayerData(String name){
		this.name = name;
		this.highestLevelFinished = 6;
	}
	
	public int getMoney(){
		return cash;
	}
	public void setMoney(int amount){
		this.cash = amount;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setHighestLevelCleared(int level){
		this.highestLevelFinished=level;
	}
	public int getHighestLevelCleared(){
		return this.highestLevelFinished;
	}
}