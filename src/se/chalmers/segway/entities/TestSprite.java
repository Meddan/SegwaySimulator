package se.chalmers.segway.entities;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Sprite for prototype of the game. 
 * @author Trivoc
 *
 */
public class TestSprite extends Sprite {
  
    //Skicka in VBOMAnager från en metod i MainActivity(?) (mEngine.getVertexBuffer...)
   public TestSprite(float xPos, float yPos, ITextureRegion textureRegion, 
	   VertexBufferObjectManager vBOManager){
       super(xPos, yPos, textureRegion, vBOManager);
   }
   
   
}
