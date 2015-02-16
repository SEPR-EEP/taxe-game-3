package fvs.taxe.controller;

import fvs.taxe.actor.ObstacleActor;
import fvs.taxe.actor.ParticleEffectActor;
import gameLogic.obstacle.Obstacle;
import gameLogic.obstacle.ObstacleListener;
import gameLogic.obstacle.ObstacleType;
import gameLogic.obstacle.Rumble;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;

import Util.Tuple;

public class ObstacleController {

	private Context context;
	private HashMap<String, ParticleEffectActor> effects;
	private Rumble rumble;
	
	public ObstacleController(final Context context) {
		// take care of rendering of stations (only rendered on map creation, visibility changed when active)
		this.context = context;
		effects = new HashMap<String, ParticleEffectActor>();
		createParticleEffects();
		rumble = new Rumble();
		context.getGameLogic().subscribeObstacleChanged(new ObstacleListener() {
			
			@Override
			public void started(Obstacle obstacle) {
				obstacle.start();
				obstacle.getStation().setObstacle(obstacle); 
				// set the obstacle so its visible
				obstacle.getActor().setVisible(true);
				
				// shake the screen if the obstacle is an earthquake
				if (obstacle.getType() == ObstacleType.EARTHQUAKE) {
					rumble.rumble(context, 1f, 2f);
				}
				if (obstacle.getType() == ObstacleType.BLIZZARD) {
					effects.get("Blizzard").setPosition(obstacle.getPosition().getX(), obstacle.getPosition().getY());
					effects.get("Blizzard").start(); 
				} else if (obstacle.getType() == ObstacleType.FLOOD) {
					effects.get("Flood").setPosition(obstacle.getPosition().getX()-10, obstacle.getPosition().getY() + 50);
					effects.get("Flood").start(); 
				} else if (obstacle.getType() == ObstacleType.VOLCANO) {
					effects.get("Volcano").setPosition(obstacle.getPosition().getX(), obstacle.getPosition().getY()-10);
					effects.get("Volcano").start(); 
				}
			}
			
			@Override
			public void ended(Obstacle obstacle) {
				obstacle.getActor().setVisible(false);
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

	public Rumble getRumble() {
		return rumble;
	}
}
