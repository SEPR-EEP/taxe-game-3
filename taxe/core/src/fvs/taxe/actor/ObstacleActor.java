package fvs.taxe.actor;

import gameLogic.map.IPositionable;
import gameLogic.obstacle.Obstacle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ObstacleActor extends Image {

	private static int width = 50;
    private static int height = 50;

	public ObstacleActor(Obstacle obstacle) {
		super(getTexture(obstacle));
		obstacle.setActor(this);
		setSize(width, height);
		IPositionable position = obstacle.getPosition();
		setPosition(position.getX() - (width/2), position.getY() - (height/2));
	}

	private static Texture getTexture(Obstacle obstacle) {
		switch(obstacle.getType()){
		case VOLCANO:
			return new Texture(Gdx.files.internal("obstacles/volcano.png"));
		case BLIZZARD:
			return new Texture(Gdx.files.internal("obstacles/blizzard.png"));
		case FLOOD:
			return new Texture(Gdx.files.internal("obstacles/flood.png"));
		case EARTHQUAKE:
			return new Texture(Gdx.files.internal("obstacles/earthquake.png"));
		default:
			return null;
		}
	}
}
