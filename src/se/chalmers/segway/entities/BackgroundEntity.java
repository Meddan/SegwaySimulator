package se.chalmers.segway.entities;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class BackgroundEntity extends Sprite {

	int firstX;
	int lastX;
	int startX;
	int startY;
	float time = 0;
	float timeModifier;

	public BackgroundEntity(int startX, int startY, int firstX, int lastX,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(startX, startY, pTextureRegion, pVertexBufferObjectManager);

		this.startX = startX;
		this.startY = startY;
		this.firstX = firstX;
		this.lastX = lastX;
		this.time = Math.abs(firstX-lastX)-startX;
		timeModifier = 0;
	}
	
	public BackgroundEntity(float timeModifier, int startX, int startY, int firstX, int lastX,
			ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(startX, startY, pTextureRegion, pVertexBufferObjectManager);

		this.startX = startX;
		this.startY = startY;
		this.firstX = firstX;
		this.lastX = lastX;
		this.time = Math.abs(firstX-lastX)-startX;
		this.timeModifier = timeModifier;
		
	}

	// @Override
	// public void onManagedDraw(GLState pGLState, Camera pCamera) {
	// super.preDraw(pGLState, pCamera);
	// System.out.println("ManagedDraw");
	// }
	//
	// @Override
	// protected void onManagedUpdate(float pSecondsElapsed) {
	// System.out.println("ManagedUpdate");
	// }

	@Override
	protected void preDraw(GLState pGLState, Camera pCamera) {
		super.preDraw(pGLState, pCamera);
		pGLState.enableDither();
		super.setPosition(firstX-time%Math.abs(firstX-lastX), startY);
		time += 1.1 + timeModifier;
	}
}
