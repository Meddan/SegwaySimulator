package se.chalmers.segway.scenes;

import java.util.LinkedList;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.scenes.SceneManager.SceneType;

public class ShopScene extends BaseScene implements
		IOnMenuItemClickListener {
	
	LinkedList<Upgrades> upgrades = new LinkedList<Upgrades>();

	@Override
	public void createScene() {
		createBackground();
		showUpgrades();
		createTheRest();
		System.out.println("Skapa shop");
	}

	private void createBackground() {
		setBackground(new Background(Color.CYAN));
	}
	
	private void createTheRest() {
		
		System.out.println("ttt");
//		Text head = generateText("test", 1, 1);
//		attachChild(head);
	}
	
	private void showUpgrades() {
		Text test = generateText("Upgrades", -260, 400);

		for(int i = 0; i < 10; i++) {
			attachChild(generateText("Upgrade", -260, 320-i*80));
		}

		/*
		int n = 0;
		for(Upgrades t : upgrades) {
			attachChild(generateText(t.getName(), -260, 320-n*80));
			n++;
		}
		*/
		
		attachChild(test);
	}
	
	private void showInfo() {
		
	}

	@Override
	public void onBackKeyPressed() {
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		System.out.println(pMenuItem.getID());
		return true;
	}
	
	private Text generateText(String text, int xOffset, int yOffset) {
		return new Text(camera.getCenterX()+xOffset, camera.getCenterY()+yOffset / 2,
				resourcesManager.tipFont, text, vbom);
	}
}
