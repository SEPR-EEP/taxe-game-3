package fvs.taxe.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ParticleEffectActor extends Actor {

	ParticleEffect particleEffect;

	public ParticleEffectActor(ParticleEffect particleEffect) {
		super();
		this.particleEffect = particleEffect;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		particleEffect.draw(batch, Gdx.graphics.getDeltaTime());
		
	}
	
	public void start() {
		particleEffect.start();
	}
	
	@Override
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		particleEffect.setPosition(x, y);
	}

}
