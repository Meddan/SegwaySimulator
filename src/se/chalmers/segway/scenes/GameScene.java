package se.chalmers.segway.scenes;

import se.chalmers.segway.main.SceneManager.SceneType;

public class GameScene extends BaseScene {
	@Override
	public void createScene() {
		setBackground(new Background(Color.BLUE));
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
}
