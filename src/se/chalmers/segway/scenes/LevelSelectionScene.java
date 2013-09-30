package se.chalmers.segway.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.util.adt.color.Color;

import android.R.color;

import se.chalmers.segway.scenes.SceneManager.SceneType;

public class LevelSelectionScene extends BaseScene implements IOnMenuItemClickListener {
	
	private MenuScene selectionChildScene;
	private final int BACK = 0;
	private final int LEVEL_1 = 1;
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		return false;
	}

	@Override
	public void createScene() {
		createBackground();
	}
	
	private void createBackground() {
		this.setBackground(new Background(Color.RED));
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_SELECTION;
	}

	@Override
	public void disposeScene() {
		
	}
	
	private void createSelectionChildScene() {
		selectionChildScene = new MenuScene(camera);
		selectionChildScene.setPosition(0, 0);

		final IMenuItem lvl1MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(LEVEL_1, resourcesManager.play_region,
						vbom), 1.2f, 1);
		lvl1MenuItem.setPosition(lvl1MenuItem.getX(), lvl1MenuItem.getY()-60);
	}
}
