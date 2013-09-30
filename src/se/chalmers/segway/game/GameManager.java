package se.chalmers.segway.game;


/**
 * Class for keeping track of values currently used in the game. WIP. 
 * @author Trivoc
 *
 */
public class GameManager {

    
    private GameManager instance;
    
    private int currentSpeed;
    private int coinCount;
    
    private GameManager () {
	
    }
    
    public void setSpeed(int speed){
	currentSpeed = speed;
    }
    
    public void incrementCoinCount(){
	coinCount++;
    }
    
    public int getCointCount(){
	return coinCount;
    }
    
    public int getCurrentSpeed(){
	return currentSpeed;
    }
    
    
    /**
     * Call this whenever resetting the game. 
     */
    public void resetGame(){
	currentSpeed = 0;
	coinCount = 0;
    }
    
    
    public GameManager getInstance(){
	if (instance == null){
	    return new GameManager();
	}
	return instance;
    }
    
}
