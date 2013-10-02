package se.chalmers.segway.game;

public class PlayerData {
	private String name;
	private int cash;
	
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
}
