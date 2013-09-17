package se.chalmers.segway.scenes;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.adt.color.Color;

import com.badlogic.gdx.math.Vector2;

import se.chalmers.segway.main.SceneManager.SceneType;

public class GameScene extends BaseScene {
	
	/**
	 * Variables
	 */
	private HUD gameHUD;
	private Text scoreText;
	private int score;
	private PhysicsWorld physicsWorld;
	
	
	/**
	 * Methods
	 */
	@Override
	public void createScene() {
	    createBackground();
	    createHUD();
	    createPhysics();
	}

	@Override
	public void onBackKeyPressed() {

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {

	}
	
	private void createBackground() {
		setBackground(new Background(Color.BLUE));		
	}
	
	private void createHUD() {
		gameHUD = new HUD();
		
	    // CREATE SCORE TEXT
	    scoreText = new Text(20, 420, resourcesManager.loadingFont, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), vbom);
	    scoreText.setAnchorCenter(0, 0);    
	    scoreText.setText("Score: 0");
	    gameHUD.attachChild(scoreText);
	    
	    camera.setHUD(gameHUD);
	}
	
	private void addToScore(int i) {
		score += i;
		scoreText.setText("Score: " + score);
	}
	
	private void createPhysics() {
	    physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false); 
	    registerUpdateHandler(physicsWorld);
	}
	
}
