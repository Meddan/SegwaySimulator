package se.chalmers.segway.entities;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import se.chalmers.segway.managers.ResourcesManager;

public abstract class Player extends AnimatedSprite {

	private Body body;
	private boolean hasContact = false;
	final long[] PLAYER_SLOW_ANIMATE = new long[] { 100, 100, 100 };
	final long[] PLAYER_FAST_ANIMATE = new long[] { 50, 50, 50 };

	public Player(float pX, float pY, VertexBufferObjectManager vbo,
			Camera camera, PhysicsWorld physicsWorld) {
		super(pX, pY, ResourcesManager.getInstance().player_region, vbo);
		createPhysics(camera, physicsWorld);
		camera.setChaseEntity(this);
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

				if (Math.abs(body.getLinearVelocity().x) < 0.5) {
					animate(PLAYER_FAST_ANIMATE, 0, 2, true);
				} 
			}
		});
	}

	public void setContact(boolean b) {
		System.out.println("setting contact to" + b);
		hasContact = b;
	}

	public void setSpeed(Vector2 v) {
		body.applyForce(v, body.getPosition());

		if (Math.abs(body.getLinearVelocity().x) >= 15) {
			body.setLinearVelocity(
					Math.signum(body.getLinearVelocity().x) * 15,
					body.getLinearVelocity().y);
		}
	}

	public void jump() {
		if (hasContact == false) {
			return;
		}
		hasContact = false;
		body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 6));

	}

	public abstract void onDie();

}
