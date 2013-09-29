package se.chalmers.segway.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.app.Activity;

import se.chalmers.segway.managers.ResourcesManager;
import se.chalmers.segway.managers.SceneManager.SceneType;

public abstract class BaseScene extends Scene {
	
	
	// ---------------------------------------------
	// VARIABLES
	// ---------------------------------------------
	protected Engine engine;
	protected Activity activity;
	protected ResourcesManager resourcesManager;
	protected VertexBufferObjectManager vbom;
	protected BoundCamera camera;

	// ---------------------------------------------
	// CONSTRUCTOR
	// ---------------------------------------------
	public BaseScene() {
		this.resourcesManager = ResourcesManager.getInstance();
		this.engine = resourcesManager.engine;
		this.activity = resourcesManager.activity;
		this.vbom = resourcesManager.vbom;
		this.camera = resourcesManager.camera;
		createScene();
	}

	
	// ---------------------------------------------
	// ABSTRACTION
	// ---------------------------------------------
	/**
	 * The creation of the actual scene, used to initialize stuff.
	 */
	public abstract void createScene();

	/**
	 * Whatever happens when you press the hardware back button.
	 */
	public abstract void onBackKeyPressed();

	/**
	 * Returns the type of the scene used. (ENUM)
	 * @return
	 */
	public abstract SceneType getSceneType();

	/**
	 * Disposes the scene.
	 */
	public abstract void disposeScene();
}
