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

	private boolean forward = true;
	private boolean backward = false;
	private float lastPos = 0;
	private Body body;
	private int footContacts = 0;
	final long[] PLAYER_ANIMATE = new long[] { 50, 50, 50 };

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

				// System.out.println("Position: " + body.getPosition().x);
				//System.out.println("Position - lastPosition "
				//		+ (body.getPosition().x - lastPos));
				if (body.getPosition().x - lastPos < 0.1) {
					if (backward) {
						stopAnimation();
						animate(PLAYER_ANIMATE, 0, 2, true);
						forward = true;
					}
				} //else if (body.getPosition().x - lastPos < 0.1) {
//					if (forward) {
//						stopAnimation();
//						animate(PLAYER_ANIMATE, 0, 2, true);
//						backward = true;
//					}
//				}
				lastPos = body.getPosition().x;
			}
		});
	}

	public void increaseFootContacts() {
		footContacts++;
	}

	public void decreaseFootContacts() {
		footContacts--;
	}

	public void setSpeed(Vector2 v) {
		body.setLinearVelocity(2*(v.x), body.getLinearVelocity().y);
	}

	public void jump() {
		if (footContacts < 1) {
			return;
		}
		body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 6));
	}

	public abstract void onDie();

}
