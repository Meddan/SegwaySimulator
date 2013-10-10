package se.chalmers.segway.entities;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.resources.ResourcesManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public abstract class Player extends AnimatedSprite {

	private Body body;
	private boolean hasContact = false;
	final long[] PLAYER_SLOW_ANIMATE = new long[] { 100, 100, 100 };
	final long[] PLAYER_FAST_ANIMATE = new long[] { 50, 50, 50 };
	final int PIXELS_TO_METERS = 32;
	private Vector2 speed;

	public Player(float pX, float pY, VertexBufferObjectManager vbo,
			Camera camera, PhysicsWorld physicsWorld) {
		super(pX, pY, ResourcesManager.getInstance().player_region, vbo);
		createPhysics(camera, physicsWorld);
		camera.setChaseEntity(this);
		this.setRotationCenter(getRotationCenterX(), getRotationCenterY()-0.3f);
	}

	private void createPhysics(final Camera camera, PhysicsWorld physicsWorld) {
		body = PhysicsFactory.createBoxBody(physicsWorld, this,
				BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));

		body.setUserData("player");
		body.setFixedRotation(true);

		physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body,
				true, false) {
			@Override
			public void onUpdate(float pSecondsElapsed) {
				super.onUpdate(pSecondsElapsed);
				camera.onUpdate(0.1f);

				if (getY() <= 0) {
					onDie();
				}

				movePlayer(pSecondsElapsed);

				if (Math.abs(body.getLinearVelocity().x) < 0.5) {
					animate(PLAYER_FAST_ANIMATE, 0, 2, true);
				}
			}
		});
	}

	/**
	 * Reads the latest data from the accelerometer and moves Player accordingly. 
	 * @param pSecondsElapsed the delta since last update, to compensate.
	 */
	public void movePlayer(float pSecondsElapsed) {
		if (speed != null) {
			body.applyForce(speed.mul(50 * pSecondsElapsed), body.getPosition());
			if (Math.abs(body.getLinearVelocity().x) >= 15) {
				body.setLinearVelocity(
						Math.signum(body.getLinearVelocity().x) * 15,
						body.getLinearVelocity().y);
			}
		}
	}

	/**
	 * Moves to appropriate place, described in pixels.
	 * @param x position x in pixels.
	 * @param y position x in pixels.
	 */
	public void setRealPosition(int x, int y) {
		body.setTransform(x / PIXELS_TO_METERS, y / PIXELS_TO_METERS,
				body.getAngle());
	}

	public void setContact(boolean b) {
		hasContact = b;
	}

	public void setSpeed(Vector2 v) {
		speed = v;
	}

	/**
	 * Makes the Player jump if it has contact with the ground.
	 */
	public void jump() {
		if (hasContact == false) {
			return;
		}
		hasContact = false;
		if(Upgrades.AntigravityWheels.isActivated()){
			body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 14));
		} else {
			body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 7));
		}

	}
	
	public void stop(){
		
		body.setType(BodyType.StaticBody);
		
	}

	public abstract void onDie();

}
