package se.chalmers.segway.scenes;

import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import se.chalmers.segway.scenes.SceneManager.SceneType;

public class LevelSelectionScene extends BaseScene implements IOnMenuItemClickListener {
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		return false;
	}

	@Override
	public void createScene() {
		
	}

	@Override
	public void onBackKeyPressed() {
		
	}

	@Override
	public SceneType getSceneType() {
		return null;
	}

	@Override
	public void disposeScene() {
		
	}

}
