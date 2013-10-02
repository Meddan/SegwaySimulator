package se.chalmers.segway.scenes;

import java.util.Timer;

import org.andengine.engine.handler.timer.TimerHandler;
import se.chalmers.segway.scenes.SceneManager.SceneType;
/**
 * Extends BaseScene to create a more fitting abstract class for the gameScenes.
 * @author meddan
 *
 */
public abstract class GameBaseScene extends BaseScene {
	private long stopWatchTime;
	@Override
	public abstract void createScene();

	@Override
	public abstract void onBackKeyPressed();

	@Override
	public abstract SceneType getSceneType();

	@Override
	public abstract void disposeScene();
	
	/**
	 * Starts the timer.
	 */
	private void startTimer(){
		stopWatchTime = System.currentTimeMillis();
	}
	/**
	 * Stops the timer and returns the amount of time it was running in milliseconds
	 * @return time in millseconds
	 */
	private long stopTimerAndReturnTime(){
		return System.currentTimeMillis()-stopWatchTime;
	}
	

}
