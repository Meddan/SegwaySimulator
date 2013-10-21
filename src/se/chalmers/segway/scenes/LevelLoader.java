package se.chalmers.segway.scenes;

import java.io.IOException;

import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.ColorModifier;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.SAXUtils;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.xml.sax.Attributes;

import se.chalmers.segway.entities.Boulder;
import se.chalmers.segway.entities.FallSpike;
import se.chalmers.segway.entities.Player;
import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.resources.ResourcesManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * This class can be used to load a level from an XML-file (assuming it is
 * written correctly) into a provided {@link GameScene}.
 * 
 * @author Dover
 * 
 */

public class LevelLoader extends EntityLoader<SimpleLevelEntityLoaderData> {

	/*
	 * Tags for critical information such as position on screen etc
	 */
	private static final String TAG_ENTITY = "entity";
	private static final String TAG_ENTITY_ATTRIBUTE_X = "x";
	private static final String TAG_ENTITY_ATTRIBUTE_Y = "y";
	private static final String TAG_ENTITY_ATTRIBUTE_TYPE = "type";

	/*
	 * Tags necessary for identifying different kinds of entities while loading
	 * the level
	 */
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1 = "platform1";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2 = "platform2";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3 = "platform3";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_STANDINGPLATFORM = "StandingPlatform";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM_CUBE = "Platform_Cube";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_SPRING = "spring";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN = "coin";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLAYER = "player";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_GOLDEN_COOKIE = "golden_cookie";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_GASTANK = "gastank";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_SPIKES = "spikes";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_FALLING_SPIKE = "fallspike";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_BOULDER = "boulder";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_DOWN = "zone_down";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_UP = "zone_up";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_LEFT = "zone_left";
	private static final Object TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_RIGHT = "zone_right";

	private Player player;
	private ResourcesManager resourcesManager;
	private SceneManager sceneManager;
	private PhysicsWorld physicsWorld;
	private VertexBufferObjectManager vbom;
	private GameScene gameScene;

	private FixtureDef FIXTURE_DEF;
	private FixtureDef zoneFixtureDef;

	/**
	 * Creates a new LevelLoader with the provided {@link PhysicsWorld},
	 * {@link Player} and {@link GameScene}.
	 * 
	 * It is important that the PhysicsWorld and Player sent to this class are
	 * the EXACT same as the ones in the provided GameScene, otherwise the code
	 * will not function properly and stuff will break. And broken code kills
	 * kittens.
	 * 
	 * @param pw
	 *            the PhysicsWorld that is used by the provided GameScene
	 * @param p
	 *            the Player that is used by the provided GameScene
	 * @param gs
	 *            the GameScene that is loading a level using this class
	 */
	public LevelLoader(PhysicsWorld pw, Player p, GameScene gs) {
		super(TAG_ENTITY);
		this.init();
		this.gameScene = gs;
		physicsWorld = pw;
		player = p;
	}

	private void init() {
		sceneManager = SceneManager.getInstance();
		resourcesManager = ResourcesManager.getInstance();
		vbom = resourcesManager.vbom;
		createFixtureDefs();
	}

	private void createFixtureDefs() {
		FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);
		zoneFixtureDef = PhysicsFactory.createFixtureDef(0, 0, 0);
		zoneFixtureDef.isSensor = true;
	}

	/**
	 * Loads all entities into the level.
	 */
	public IEntity onLoadEntity(final String pEntityName,
			final IEntity pParent, final Attributes pAttributes,
			final SimpleLevelEntityLoaderData pSimpleLevelEntityLoaderData)
			throws IOException {
		final int x = SAXUtils.getIntAttributeOrThrow(pAttributes,
				TAG_ENTITY_ATTRIBUTE_X);
		final int y = SAXUtils.getIntAttributeOrThrow(pAttributes,
				TAG_ENTITY_ATTRIBUTE_Y);
		final String type = SAXUtils.getAttributeOrThrow(pAttributes,
				TAG_ENTITY_ATTRIBUTE_TYPE);

		final Sprite levelObject;

		// Cases for loading different objects
		if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM1)) { // Loads
																		// platform1
			levelObject = loadPlatform(x, y, "platform1",
					resourcesManager.platform1_region);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM2)) { // Loads
																				// platform2
			levelObject = loadPlatform(x, y, "platform2",
					resourcesManager.platform2_region);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM3)) { // Loads
																				// platform3
			levelObject = loadPlatform(x, y, "platform3",
					resourcesManager.platform3_region);
		} else if (type
				.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_STANDINGPLATFORM)) { // Loads
																				// a
																				// standing
																				// platform
			levelObject = loadPlatform(x, y, "StandingPlatform",
					resourcesManager.StandingPlatform_region);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLATFORM_CUBE)) { // Loads
																					// a
																					// cubed
																					// platfor
			levelObject = loadPlatform(x, y, "Platform_Cube",
					resourcesManager.platform_cube_region);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_SPIKES)) { // Loads
																			// lethal
																			// spikes
			levelObject = new Sprite(x, y, resourcesManager.spikes_region, vbom) { // Creates
																					// the
																					// collisionlistener
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) {
					super.onManagedUpdate(pSecondsElapsed);

					if (player.collidesWith(this) && player.getY() - 48 >= y) {
						player.stop();
						player.onDie();
					}
				}
			};
			final Body body = PhysicsFactory.createBoxBody(physicsWorld,
					levelObject, BodyType.StaticBody, FIXTURE_DEF);
			body.setUserData("spikes");
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(
					levelObject, body, true, false));

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_BOULDER)) { // Loads
																			// deadly
																			// boulder

			levelObject = new Boulder(x, y, physicsWorld, FIXTURE_DEF, player);

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_FALLING_SPIKE)) { // Loads
																					// falling
																					// spikes

			levelObject = new FallSpike(x, y, physicsWorld, FIXTURE_DEF, player);

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_SPRING)) { // Loads
																			// a
																			// spring
			levelObject = new AnimatedSprite(x, y,
					resourcesManager.spring_region, vbom) {
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) { // Creates
																		// the
																		// collisionlistener
					super.onManagedUpdate(pSecondsElapsed);

					if (player.collidesWith(this) && player.getY() - 40 > y) {
						player.applyStaticForce(new Vector2(0, 15));
						animate(new long[] { 100, 100, 100, 100, 100, 100, 100,
								100 }, 0, 7, false);
					}
				}
			};
			final Body body = PhysicsFactory.createBoxBody(physicsWorld,
					levelObject, BodyType.StaticBody, FIXTURE_DEF);
			body.setUserData("spikes");
			physicsWorld.registerPhysicsConnector(new PhysicsConnector(
					levelObject, body, true, false));

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_COIN)) { // Loads
																		// a
																		// coin
			ITextureRegion cookie = resourcesManager.cookies_region
					.getTextureRegion((int) (Math.random() * 8));
			levelObject = new Sprite(x, y, cookie, vbom) {
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) { // Creates
																		// the
																		// collisionlistener
					super.onManagedUpdate(pSecondsElapsed);

					if (player.collidesWith(this)) {
						resourcesManager.crunch.play();
						gameScene.addToScore(10);
						this.setVisible(false);
						this.setIgnoreUpdate(true);
					}
				}
			};
			levelObject.registerEntityModifier(new LoopEntityModifier(
					new ScaleModifier(1, 0.4f, 0.6f)));
			// Loading player type objects
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_PLAYER)) { // Loads
																			// the
																			// player
			player.setRealPosition(x, y);

			levelObject = player;
			// Doesn't quite load the player. While he is inserted into the
			// scene,
			// the object has already been created and the only thing that
			// changes
			// is the position of it.
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_GOLDEN_COOKIE)) { // Loads
																					// the
																					// golden
																					// cookie
			levelObject = new Sprite(x, y, resourcesManager.golden_cookie, vbom) { // Creates
																					// the
																					// collisionlistener
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) {
					super.onManagedUpdate(pSecondsElapsed);

					if (player.collidesWith(this)) {
						GameScene gs = (GameScene) sceneManager
								.getCurrentScene();
						gs.showLevelComplete();
						player.stop();
					}
				}
			};

		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_GASTANK)) { // Loads
																			// a
																			// gastank
			levelObject = new Sprite(x, y, resourcesManager.gastank, vbom) { // Creates
																				// the
																				// collisionlistener
				@Override
				protected void onManagedUpdate(float pSecondsElapsed) {
					super.onManagedUpdate(pSecondsElapsed);

					if (player.collidesWith(this)) {
						gameScene.addToBoost(10);
						this.setVisible(false);
						this.setIgnoreUpdate(true);
					}
				}
			};
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_DOWN)) { // Loads
																				// a
																				// zone
																				// down
			levelObject = loadZone(x, y, "zone_down",
					resourcesManager.zone_down);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_UP)) { // Loads
																			// a
																			// zone
																			// up
			levelObject = loadZone(x, y, "zone_up", resourcesManager.zone_up);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_LEFT)) { // Loads
																				// a
																				// zone
																				// left
			levelObject = loadZone(x, y, "zone_left",
					resourcesManager.zone_left);
		} else if (type.equals(TAG_ENTITY_ATTRIBUTE_TYPE_VALUE_ZONE_RIGHT)) { // Loads
																				// a
																				// zone
																				// right
			levelObject = loadZone(x, y, "zone_right",
					resourcesManager.zone_right);
		} else {
			throw new IllegalArgumentException();
		}

		levelObject.setCullingEnabled(true);

		return levelObject;
	}

	/**
	 * Generalized method for loading zones.
	 * 
	 * @param x
	 *            x-position of the zone
	 * @param y
	 *            y-posiiton of the zone
	 * @param zone
	 *            the string which will be used to identify the zone
	 * @param zoneRegion
	 *            the texture that will be used to show the zone
	 * 
	 * @return a zone created with the specified parameters
	 */
	private Sprite loadZone(int x, int y, String zone, ITextureRegion zoneRegion) {
		Sprite zoneSprite = new Sprite(x, y, zoneRegion, vbom);
		final Body body = PhysicsFactory.createBoxBody(physicsWorld,
				zoneSprite, BodyType.StaticBody, zoneFixtureDef);
		body.setUserData(zone);
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(zoneSprite,
				body, true, false));

		return zoneSprite;
	}

	/**
	 * Generalized method for loading platforms.
	 * 
	 * @param x
	 *            x-position for the platform
	 * @param y
	 *            y-position for the platform
	 * @param platform
	 *            the string which will be used to identify the platform
	 * @param platformRegion
	 *            the texture that will be used to show the platform
	 * @return
	 */
	private Sprite loadPlatform(final int x, final int y, String platform,
			ITextureRegion platformRegion) {
		Sprite platformSprite = new Sprite(x, y, platformRegion, vbom) {
			@Override
			protected void onManagedUpdate(float pSecondsElapsed) {
				super.onManagedUpdate(pSecondsElapsed);
				if (Upgrades.Shrooms.isActivated()) {
					registerEntityModifier(new LoopEntityModifier(
							new ColorModifier(1, (float) Math.random(),
									(float) Math.random(),
									(float) Math.random(),
									(float) Math.random(),
									(float) Math.random(),
									(float) Math.random())));
				}
			}
		};
		final Body body = PhysicsFactory.createBoxBody(physicsWorld,
				platformSprite, BodyType.StaticBody, FIXTURE_DEF);
		body.setUserData(platform);
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(
				platformSprite, body, true, false));
		return platformSprite;
	}

}
