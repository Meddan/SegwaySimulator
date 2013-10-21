package se.chalmers.segway.scenes;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.util.adt.color.Color;

import se.chalmers.segway.entities.BackgroundEntity;
import se.chalmers.segway.game.PlayerData;
import se.chalmers.segway.game.SaveManager;
import se.chalmers.segway.game.Settings;
import se.chalmers.segway.scenes.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements
		IOnMenuItemClickListener {
	// If music is on default
	private boolean sound = false;
	IMenuItem shopMenuItem;
	IMenuItem soundonMenuItem;
	IMenuItem soundoffMenuItem;
	IMenuItem playMenuItem;
	private PlayerData playerData;
	private Settings settings;

	@Override
	public void createScene() {
		createMenuChildScene();
		createBackground();
		initMusic();
	}

	public void setPlayerData(PlayerData data) {
		this.playerData = data;
	}

	/**
	 * Starts music if none is running and makes it loop forever.
	 */
	private void initMusic() {
		this.resourcesManager.musicManager.setMasterVolume(0);
		this.resourcesManager.music.setLooping(true);
		this.resourcesManager.music3.setLooping(true);
		this.resourcesManager.music2.setLooping(true);
	}

	@Override
	public void onBackKeyPressed() {
		System.out.println("CLEAN EXIT BITCH");
		System.out.println("BITCH CASH WRITTEN IS: " + playerData.getMoney());
		if(playerData == null){
			System.out.println("PLAYERDATA IS NULL BITCH");
		}
		SaveManager.saveSettings(settings);
		SaveManager.savePlayerData(playerData);
		SaveManager.saveUpgrades();
		System.exit(0);

	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_MENU;
	}

	@Override
	public void disposeScene() {
	}

	/**
	 * Adds a background to the scene.
	 */
	private void createBackground() {
		setBackground(new Background(new Color(0.21f, 0.8f, 0.11f)));
		
		Text title = new Text(0, 0, resourcesManager.titleFont, "Segway Simulator", vbom);
		title.setPosition(camera.getCenterX()-180, camera.getCenterY()+100);
		title.setAutoWrap(AutoWrap.WORDS);
		title.setAutoWrapWidth(250);
		title.setScale(1.2f);

		AnimatedSprite segwayKid = new AnimatedSprite(1, 3, resourcesManager.player_region, vbom);
		segwayKid.setPosition(camera.getCenterX()+130, camera.getCenterY()+46);
		segwayKid.setRotation(30);
		segwayKid.setScale(2.3f);
		segwayKid.animate(40, true);
		
		attachChild(new BackgroundEntity(5.9f, 1200, 380, 1200, -400, resourcesManager.menu_background_region, vbom));
		attachChild(new BackgroundEntity(5.9f, 400, 380, 1200, -400, resourcesManager.menu_background_region, vbom));
		attachChild(segwayKid);
		attachChild(title);
	}

	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_SHOP = 2;

	private void createMenuChildScene() {
		menuChildScene = new MenuScene(camera);
		menuChildScene.setPosition(0, 0);

		playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY,
				resourcesManager.play_region, vbom), 0.8f, 0.7f);
		soundonMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
				MENU_OPTIONS, resourcesManager.soundon_region, vbom), 0.8f, 0.7f);
		soundoffMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(
				MENU_OPTIONS, resourcesManager.soundoff_region, vbom), 0.8f, 0.7f);
		shopMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_SHOP,
				resourcesManager.shop_region, vbom), 0.8f, 0.7f);

		menuChildScene.addMenuItem(playMenuItem);
		menuChildScene.addMenuItem(shopMenuItem);
		menuChildScene.addMenuItem(soundoffMenuItem);
		menuChildScene.addMenuItem(soundonMenuItem);

		menuChildScene.buildAnimations();
		menuChildScene.setBackgroundEnabled(false);

		playMenuItem.setPosition(camera.getCenterX()-280, camera.getCenterY()-100);
		shopMenuItem.setPosition(camera.getCenterX(), camera.getCenterY()-100);
		soundoffMenuItem.setPosition(camera.getCenterX()+280, camera.getCenterY()-100);
		soundonMenuItem.setPosition(camera.getCenterX()+280, camera.getCenterY()-100);
		
		soundoffMenuItem.setVisible(!sound);
		soundonMenuItem.setVisible(sound);

		menuChildScene.setOnMenuItemClickListener(this);

		setChildScene(menuChildScene);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		switch (pMenuItem.getID()) {
		case MENU_PLAY:
			disposeScene();
			SceneManager.getInstance().loadSelectionScene(engine);
			return true;
		case MENU_OPTIONS:
			soundoffMenuItem.setVisible(sound);
			soundonMenuItem.setVisible(!sound);
			sound = !sound;
			if (sound) {
				resourcesManager.musicManager.setMasterVolume(1);
				resourcesManager.music.resume();
			} else {
				resourcesManager.musicManager.setMasterVolume(0);
				resourcesManager.music.pause();
			}
			return true;
		case MENU_SHOP:
			disposeScene();
			SceneManager.getInstance().loadShopScene(engine);
			return true;
		default:
			return false;
		}
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
		
	}
}
