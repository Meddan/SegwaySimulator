package se.chalmers.segway.scenes;

import java.util.LinkedList;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.AutoWrap;
import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.color.Color;

import se.chalmers.segway.game.PlayerData;
import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.scenes.SceneManager.SceneType;

public class ShopScene extends BaseScene implements IOnMenuItemClickListener {

	private Font headerFont;
	private Font listFont;
	private Upgrades selected;
	private MenuScene upgradeChildScene;
	private LinkedList<Upgrades> upgrades;
	private PlayerData player;
	private HUD hud;
	private Sprite cookieCounter;
	private Text cookieAmount;

	private Text name;
	private Text description;
	private Text price;

	@Override
	public void createScene() {
		loadFonts();
		upgrades = new LinkedList<Upgrades>();
		upgradeChildScene = new MenuScene(camera);

		setTexts();

		createBackground();
		showUpgrades();
		showInfo();
		createHUD();
		System.out.println("Skapa shop");
	}

	private void setTexts() {
		name = generateText("", 580, 380, listFont, true);
		description = generateText("", 580, 260, listFont, true);
		price = generateText("", 580, 210, listFont, true);

		upgradeChildScene.attachChild(name);
		upgradeChildScene.attachChild(description);
		upgradeChildScene.attachChild(price);
	}

	public void setPlayerData(PlayerData player) {
		this.player = player;
	}

	private void loadFonts() {
		headerFont = resourcesManager.headingFont;
		listFont = resourcesManager.listFont;
	}

	private void createBackground() {
		setBackground(new Background(Color.WHITE));
	}

	private void createHUD() {
		hud = new HUD();
		camera.setHUD(hud);
	}

	public void updateHUD() {
		camera.setHUD(hud);
		hud.detachChildren();
		cookieCounter = new Sprite(455, 50,
				resourcesManager.cookieCounter_region, vbom);
		cookieAmount = new Text(620, 20, resourcesManager.loadingFont, ":"
				+ player.getMoney(), vbom);
		cookieAmount.setPosition(495 + (14 * Integer
				.toString(player.getMoney()).length()), 47);
		hud.attachChild(cookieAmount);
		hud.attachChild(cookieCounter);
	}

	private void showUpgrades() {
		upgradeChildScene.attachChild(generateText("Upgrades", 200, 420,
				headerFont));

		int n = 0;
		upgrades = new LinkedList<Upgrades>();
		for (Upgrades t : Upgrades.values()) {
			if (!t.isActivated()) {
				System.out.println(t.getName());
				upgrades.add(t);
				IMenuItem upgrade = new ScaleMenuItemDecorator(
						new SpriteMenuItem(n + 10,
								resourcesManager.upgrade_region, vbom), 1.05f,
						1);
				upgrade.attachChild(generateText(t.getName(),
						(int) upgrade.getWidth() / 2,
						(int) upgrade.getHeight() / 2,
						resourcesManager.listFont));
				upgrade.setPosition(210, 320 - n * 65);

				upgradeChildScene.addMenuItem(upgrade);
				n++;
			}
		}
		upgradeChildScene.setBackgroundEnabled(false);

		upgradeChildScene.setOnMenuItemClickListener(this);

		setChildScene(upgradeChildScene);
	}

	private void showInfo() {
		upgradeChildScene
				.attachChild(generateText("Shop", 600, 420, headerFont));
		int x = 580;

		System.out.println("11");
		if (selected == null) {
			System.out.println("12");
			name.setText("Choose an upgrade");
			System.out.println(13);
		} else {
			System.out.println(14);
			IMenuItem buyButton;

			name.setText(selected.getName());
			description.setText(selected.getInfo());
			price.setText("Price: " + selected.getCost());

			System.out.println(15);
			buyButton = new ScaleMenuItemDecorator(new SpriteMenuItem(5,
					resourcesManager.upgrade_region, vbom), 0.6f, 0.7f);
			buyButton.attachChild(generateText("Buy",
					(int) buyButton.getWidth() / 2,
					(int) buyButton.getHeight() / 2, listFont));
			buyButton.setPosition(x, 120);
			upgradeChildScene.addMenuItem(buyButton);
		}
		upgradeChildScene.detachSelf();
	}

	private void buy() {
		if (player.getMoney() >= selected.getCost()) {
			if (selected.getName() == "Rehab") {
				Upgrades.Shrooms.setActive(false);
			} else if (selected.getName() == "Shrooms") {
				Upgrades.Rehab.setActive(false);
			}
			selected.setActive(true);
			player.setMoney(player.getMoney() - selected.getCost());

			System.out.println("Update the upgrade list");
			upgradeChildScene.detachChildren();
			upgradeChildScene.clearTouchAreas();
			selected = null;
			showUpgrades();
			setTexts();
		} else {
			System.out.println("Not enough monies");
		}
	}

	@Override
	public void onBackKeyPressed() {
		disposeScene();
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType() {
		return SceneType.SCENE_GAME;
	}

	@Override
	public void disposeScene() {
		cookieCounter.setVisible(false);
		cookieAmount.setVisible(false);
	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {

		if (pMenuItem.getID() == 5) {
			buy();
		} else if (pMenuItem.getID() >= 10) {
			System.out.println("ClickedID: " + pMenuItem.getID());
			selected = upgrades.get(pMenuItem.getID() - 10);
			System.out.println("Selected: " + selected);
		}

		System.out.println(pMenuItem.getID());
		showInfo();
		return true;
	}

	private Text generateText(String text, int xOffset, int yOffset, Font font) {
		Text t = new Text(xOffset, yOffset, font, text, 400, vbom);
		return t;
	}

	private Text generateText(String text, int xOffset, int yOffset, Font font,
			boolean wrap) {
		Text t = generateText(text, xOffset, yOffset, font);
		if (wrap)
			t.setAutoWrap(AutoWrap.WORDS);
		t.setAutoWrapWidth(250);
		return t;
	}
}
