package se.chalmers.segway.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import se.chalmers.segway.managers.ResourcesManager;

public class DeathScene extends Sprite
{
    
    public DeathScene(VertexBufferObjectManager pSpriteVertexBufferObject)
    {
        super(0, 0, 650, 400, ResourcesManager.getInstance().death_window_region, pSpriteVertexBufferObject);
    }
    
    /**
     * Change star`s tile index, depends on stars count.
     * @param starsCount
     */
    public void display(Scene scene, Camera camera)
    {
        
        // Hide HUD
        camera.getHUD().setVisible(false);
        
        // Disable camera chase entity
        camera.setChaseEntity(null);
        
        // Attach our level complete panel in the middle of camera
        setPosition(camera.getCenterX(), camera.getCenterY());
        scene.attachChild(this);
    }
}
