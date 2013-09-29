package se.chalmers.segway.entities;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import se.chalmers.segway.resources.ResourcesManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Sprite for prototype of the game. 
 * @author Trivoc
 *
 */
public class TestSprite extends AnimatedSprite {
   
    private Camera characterCam;
    private PhysicsWorld physWorld;
    private Body body;
    
    private boolean canRun;
    
  
    //Skicka in VBOMAnager fr��n en metod i MainActivity(?) (mEngine.getVertexBuffer...)
   public TestSprite(float xPos, float yPos, VertexBufferObjectManager vboManager,
	   Camera camera, PhysicsWorld physWorld){
       super(xPos, yPos, ResourcesManager.getInstance().playerRegion, vboManager);
       
       this.characterCam = camera;
       this.canRun = false;
       this.physWorld = physWorld;
       
       createPhysics(camera, physWorld);
       characterCam.setChaseEntity(this);
       
   }
   
   
   private void createPhysics(final Camera camera, PhysicsWorld physicsWorld)
   {        
       body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));

       body.setUserData("player");
       body.setFixedRotation(true);
       
       physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false)
       {
           public void onUpdate(float pSecondsElapsed)
           {
               super.onUpdate(pSecondsElapsed);
               camera.onUpdate(0.1f);
               
               if (getY() <= 0)
               {                    
                   onDie();
               }
               
               if (canRun)
               {    
                   body.setLinearVelocity(new Vector2(5, body.getLinearVelocity().y)); 
               }
           }
       });
   }
   
   public void onDie(){
       
   }
   
   public void setRunning()
   {
       canRun = true;       
       final long[] PLAYER_ANIMATE = new long[] { 100, 100, 100 };
       animate(PLAYER_ANIMATE, 0, 2, true);
   }
   
   public void jump(){
       body.setLinearVelocity(new Vector2(body.getLinearVelocity().x, 150));
   }
   
}
