package se.chalmers.segway.scenes;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import se.chalmers.segway.scenes.SceneManager.SceneType;

public class LevelSelectionScene extends BaseScene implements
		IOnMenuItemClickListener {

	private MenuScene selectionChildScene;
	private final int BACK = 0;
	private final int LEVEL_1 = 1;

	@Override
	public void createScene() {
		createBackground();
		createSelectionChildScene();
	}

	private void createBackground() {
		attachChild(new Sprite(400, 240,
				resourcesManager.selection_background_region, vbom) {
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) {
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		});
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

		for (int i = 1; i < 5; i++) {
			IMenuItem button = new ScaleMenuItemDecorator(new SpriteMenuItem(i,
					resourcesManager.level_button, vbom), 1.2f, 1);
			//TODO: WHY IT NO WORK button.setPosition(i*50 + 80, 200);
			selectionChildScene.addMenuItem(button);
		}

		

		selectionChildScene.buildAnimations();
		selectionChildScene.setBackgroundEnabled(false);

		

		selectionChildScene.setOnMenuItemClickListener(this);

		setChildScene(selectionChildScene);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		SceneManager.getInstance().loadGameScene(engine, pMenuItem.getID());
		return true;
	}
}
