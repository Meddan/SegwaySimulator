package se.chalmers.segway.entities;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;

import se.chalmers.segway.managers.ResourcesManager;

/**
 * Sprite for prototype of the game. 
 * @author Trivoc
 *
 */
public class TestSprite extends AnimatedSprite {
   
    
    private Camera characterCam;
    private PhysicsWorld physWorld;
    private Body body;
    
    private boolean canJump;
    
  
    //Skicka in VBOMAnager från en metod i MainActivity(?) (mEngine.getVertexBuffer...)
   public TestSprite(float xPos, float yPos, VertexBufferObjectManager vboManager,
	   Camera camera, PhysicsWorld physWorld){
       super(xPos, yPos, ResourcesManager.getInstance().playerRegion, vboManager);
       
       this.canJump = false;
       this.physWorld = physWorld;
       
   }
   
   
   
}
