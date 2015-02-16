package fvs.taxe.controller;

import fvs.taxe.actor.ObstacleActor;
import fvs.taxe.actor.ParticleEffectActor;
import gameLogic.obstacle.Obstacle;
import gameLogic.obstacle.ObstacleListener;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import Util.Tuple;

public class ObstacleController {

	private Context context;
	private HashMap<String, ParticleEffectActor> effects;
	
	public ObstacleController(Context context) {
		// take care of rendering of stations (only rendered on map creation, visibility changed when active)
		this.context = context;
		effects = new HashMap<String, ParticleEffectActor>();
		createParticleEffects();
		context.getGameLogic().subscribeObstacleChanged(new ObstacleListener() {
			
			@Override
			public void started(Obstacle obstacle) {
				obstacle.start();
				obstacle.getStation().setObstacle(obstacle); 
			}
			
			@Override
			public void ended(Obstacle obstacle) {
				obstacle.getStation().clearObstacle();
				obstacle.end();
			}
		});
	}

	public void drawObstacles(){
		// needs to only be called once, on map creation
		// adds all obstacles to the stage but makes them invisible
		ArrayList<Tuple<Obstacle, Float>> obstaclePairs = context.getGameLogic().getObstacleManager().getObstacles();
		for (Tuple<Obstacle, Float> obstaclePair: obstaclePairs) {
			renderObstacle(obstaclePair.getFirst(), false);
		}
	}

	private ObstacleActor renderObstacle(Obstacle obstacle, boolean visible) {
		// render the obstacle's actor with the visibility given
		ObstacleActor obstacleActor = new ObstacleActor(obstacle);
		obstacleActor.setVisible(visible);
		obstacle.setActor(obstacleActor);
		context.getStage().addActor(obstacleActor);
		return obstacleActor;
	}
	
	private void createParticleEffects() {
		ParticleEffect snowEffect = new ParticleEffect();
		snowEffect.load(Gdx.files.internal("effects/snow.p"), Gdx.files.internal("effects"));
		ParticleEffectActor snowActor = new ParticleEffectActor(snowEffect);
		effects.put("Blizzard", snowActor);
		
		ParticleEffect floodEffect = new ParticleEffect();
		floodEffect.load(Gdx.files.internal("effects/flood.p"), Gdx.files.internal("effects"));
		ParticleEffectActor floodActor = new ParticleEffectActor(floodEffect);
		effects.put("Flood", floodActor);
		
		ParticleEffect volcanoEffect = new ParticleEffect();
		volcanoEffect.load(Gdx.files.internal("effects/volcano.p"), Gdx.files.internal("effects"));
		ParticleEffectActor volcanoActor = new ParticleEffectActor(volcanoEffect);
		effects.put("Volcano", volcanoActor);
	}
	
	public void drawObstacleEffects() {
		for (ParticleEffectActor actor : effects.values()) {
			context.getStage().addActor(actor);
			
		}
	}

	public HashMap<String, ParticleEffectActor> getEffectActors() {
		return effects;
	}
	
}
