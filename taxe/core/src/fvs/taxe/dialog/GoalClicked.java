package fvs.taxe.dialog;

import gameLogic.goal.Goal;
import gameLogic.map.Station;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GoalClicked extends ClickListener {

	private Goal goal;

	public GoalClicked(Goal goal) {
		this.goal = goal;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		// if a goal is selected, show the stations as 'selected'
		final Station origin = goal.getOrigin();
		final Station dest = goal.getDestination();
	
		origin.getActor().selected();
		dest.getActor().selected();
	}

}
