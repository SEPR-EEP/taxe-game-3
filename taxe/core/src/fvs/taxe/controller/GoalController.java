package fvs.taxe.controller;

import fvs.taxe.TaxeGame;
import fvs.taxe.dialog.GoalClicked;
import gameLogic.Player;
import gameLogic.PlayerChangedListener;
import gameLogic.PlayerManager;
import gameLogic.goal.Goal;
import gameLogic.goal.GoalListener;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class GoalController {
	private Context context;
	private Group goalButtons = new Group();

	public GoalController(Context context) {
		this.context = context;
	}

	public void showCurrentPlayerGoals() {
		goalButtons.remove();
		goalButtons.clear();

		drawHeaderText();

		float top = (float) TaxeGame.HEIGHT;
		float x = 10.0f;
		float y = top - 25.0f - TopBarController.CONTROLS_HEIGHT;

		PlayerManager pm = context.getGameLogic().getPlayerManager();
		Player currentPlayer = pm.getCurrentPlayer();

		for (Goal goal : currentPlayer.getGoals()) {
			if(goal.getComplete()) {
				continue;
			}
			y-=30;
			TextButton button  = new TextButton(goal.toString(), context.getSkin());
			button.setPosition(x,y);
			button.addListener(new GoalClicked(goal));
			goalButtons.addActor(button);
		}

		context.getStage().addActor(goalButtons);
	}

	public void drawHeaderText() {
		TaxeGame game = context.getTaxeGame();
		float top = (float) TaxeGame.HEIGHT;
		float x = 10.0f;
		float y = top - 10.0f - TopBarController.CONTROLS_HEIGHT;

		game.batch.begin();
		game.fontSmall.setColor(Color.BLACK);
		game.fontSmall.draw(game.batch, playerGoalHeader(), x, y);
		game.batch.end();
	}

	private String playerGoalHeader() {
		return "Player " + context.getGameLogic().getPlayerManager().getCurrentPlayer().getPlayerNumber() + " Goals:";
	}
}
