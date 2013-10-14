package se.chalmers.segway.entities;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import se.chalmers.segway.game.Upgrades;
import se.chalmers.segway.resources.ResourcesManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Boulder extends Sprite {

	private Body body;
	private Player player;
	private int speed;
	private TimerHandler speedTimer = new TimerHandler(0.1f,
			new ITimerCallback() {
				public void onTimePassed(final TimerHandler pTimerHandler) {
					pTimerHandler.reset();

					if (speed <= 0) {
						ResourcesManager.getInstance().engine.unregisterUpdateHandler(speedTimer);
					} else {
						speed += -1;
					}
				}
			});

	public Boulder(float pX, float pY, PhysicsWorld physicsWorld,
			FixtureDef FIXTURE_DEF, Player player) {

		super(pX, pY, ResourcesManager.getInstance().boulder_region,
				ResourcesManager.getInstance().vbom);

		body = PhysicsFactory.createCircleBody(physicsWorld, this,
				BodyType.StaticBody, FIXTURE_DEF);
		body.setUserData("boulder");
		this.player = player;
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body,
				true, false));
		speed = 35;

	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		if (player.collidesWith(this) && body.getLinearVelocity().x > 5) {
			if (!Upgrades.SuperHelmet.isActivated()) {
			player.stop();
			player.onDie();
			} else {
				Upgrades.SuperHelmet.setActive(false);
			}
		} 

		if (Math.abs(this.getX() - player.getX()) < 30
				&& body.getType() == BodyType.StaticBody) {
			body.setType(BodyType.DynamicBody);
			ResourcesManager.getInstance().engine.registerUpdateHandler(speedTimer);
		}

		if (body.getType() == BodyType.DynamicBody) {
				body.applyForce(
						new Vector2(speed, 0), body.getPosition());
		}
	}
	
	public void stop() {
		this.body.setType(BodyType.StaticBody);
	}

}
