package fvs.taxe.actor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import gameLogic.map.IPositionable;

public class StationActor extends Image {
    private static final int STATION_SIZE_INCREASE = 10;	// the size the station increases (x + y) when the station selected()
	private static final float SELECTION_DURATION = 3f;		// the amount of time the selection stays active
	private static int width = 20;
    private static int height = 20;

    public StationActor(IPositionable location) {
        super(new Texture(Gdx.files.internal("station_dot.png")));
        
        setSize(width, height);
        setPosition(location.getX() - width / 2, location.getY() - height / 2);
    }

	public void selected() {
		setPosition(getX()-STATION_SIZE_INCREASE/2,getY()-STATION_SIZE_INCREASE/2);
		setSize(width+STATION_SIZE_INCREASE, height +STATION_SIZE_INCREASE);
		
		Timer.schedule(new Task(){
		    @Override
		    public void run() {
		    	setPosition(getX()+STATION_SIZE_INCREASE/2,getY()+STATION_SIZE_INCREASE/2);
		    	setSize(width, height);
		    }
		}, SELECTION_DURATION);
	}
    
    
}
