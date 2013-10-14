package se.chalmers.segway.entities;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

import se.chalmers.segway.resources.ResourcesManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class FallSpike extends Sprite {

	private Body body;
	private Player player;

	public FallSpike(float pX, float pY, PhysicsWorld physicsWorld,
			FixtureDef FIXTURE_DEF, Player player) {

		super(pX, pY, ResourcesManager.getInstance().fallSpike_region,
				ResourcesManager.getInstance().vbom);

		body = PhysicsFactory.createBoxBody(physicsWorld, this,
				BodyType.StaticBody, FIXTURE_DEF);
		body.setUserData("fallSpike");
		this.player = player;
		physicsWorld.registerPhysicsConnector(new PhysicsConnector(
				this, body, true, false));
		
	}

	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		super.onManagedUpdate(pSecondsElapsed);

		if (player.collidesWith(this)) {
			player.stop();
			player.onDie();
		}

		if (Math.abs(this.getX() - player.getX()) < 30
				&& body.getType() == BodyType.StaticBody) {
			body.setType(BodyType.DynamicBody);
		}
		
	}

}
