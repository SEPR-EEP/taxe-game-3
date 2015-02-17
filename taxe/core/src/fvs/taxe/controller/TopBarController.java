package fvs.taxe.controller;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.run;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import fvs.taxe.TaxeGame;
import gameLogic.GameState;
import gameLogic.GameStateListener;
import gameLogic.obstacle.Obstacle;
import gameLogic.obstacle.ObstacleListener;
import gameLogic.obstacle.ObstacleType;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class TopBarController {
	public final static int CONTROLS_HEIGHT = 40;

	private Context context;
	TextButton endTurnButton;
	private Label flashMessage;
	private Label obstacleLabel;
	private TopBarActor topBarBackground;

	public TopBarController(final Context context) {
		this.context = context;

		context.getGameLogic().subscribeObstacleChanged(new ObstacleListener(){

			@Override
			public void started(Obstacle obstacle) {
				ObstacleType type = obstacle.getType();						     
				Color color = null;
				switch(type){
				case BLIZZARD:
					color = Color.WHITE;
					break;
				case FLOOD:
					color = Color.valueOf("1079c1");
					break;
				case VOLCANO:
					color = Color.valueOf("ec182c");
					break;
				case EARTHQUAKE:
					color = Color.valueOf("7a370a");
					break;
				}				
				displayObstacleMessage(obstacle.getType().toString() + " in " + obstacle.getStation().getName(), color);
			}

			@Override
			public void ended(Obstacle obstacle) {
			}		        	
		});
	}

	public void drawBackground() {
		topBarBackground = new TopBarActor();
		context.getStage().addActor(topBarBackground);
	}
	
	public void drawLabels() {
		drawFlashLabel();
		drawObstacleLabel();
	}
	public void drawFlashLabel() {
		flashMessage = new Label("", context.getSkin());
		flashMessage.setPosition(450, TaxeGame.HEIGHT - 24);
		flashMessage.setAlignment(0);
		context.getStage().addActor(flashMessage);
	}

	public void drawObstacleLabel() {
		obstacleLabel = new Label("", context.getSkin());
		obstacleLabel.setColor(Color.BLACK);
		obstacleLabel.setPosition(10,TaxeGame.HEIGHT - 34);
		context.getStage().addActor(obstacleLabel);
	}

	public void displayFlashMessage(String message, Color color) {
		displayFlashMessage(message, color, 2f);
	}

	public void displayFlashMessage(String message, Color color, float time) {
		flashMessage.setText(message);
		flashMessage.setColor(color);
		flashMessage.addAction(sequence(delay(time), fadeOut(0.25f)));
	}

	public void displayFlashMessage(String message, Color backgroundColor, Color textColor, float time) {
		topBarBackground.setObstacleColor(backgroundColor);
		topBarBackground.setControlsColor(backgroundColor);
		flashMessage.clearActions();
		flashMessage.setText(message);
		flashMessage.setColor(textColor);
		flashMessage.addAction(sequence(delay(time), fadeOut(0.25f), run(new Runnable() {
			public void run() {
				topBarBackground.setControlsColor(Color.LIGHT_GRAY);
				if (obstacleLabel.getActions().size == 0){
					topBarBackground.setObstacleColor(Color.LIGHT_GRAY);
				}
			}
		})));
	}

	public void displayObstacleMessage(String message, Color color) {
		// display a message to the obstacle topBar label, with topBarBackground color color and given message
		// wraps automatically to correct size
		obstacleLabel.clearActions();
		obstacleLabel.setText(message);
		obstacleLabel.setColor(Color.BLACK);
		obstacleLabel.pack();
		topBarBackground.setObstacleColor(color);
		topBarBackground.setObstacleWidth(obstacleLabel.getWidth()+20);
		obstacleLabel.addAction(sequence(delay(2f),fadeOut(0.25f), run(new Runnable() {
			public void run() {
				// run action to reset obstacle label after it has finished displaying information
				obstacleLabel.setText("");
				topBarBackground.setObstacleColor(Color.LIGHT_GRAY);
			}
		})));
	}

	public void drawEndTurnButton() {
		endTurnButton = new TextButton("End Turn", context.getSkin());
		endTurnButton.setPosition(TaxeGame.WIDTH - 100.0f, TaxeGame.HEIGHT - 33.0f);
		endTurnButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				context.getGameLogic().getPlayerManager().turnOver();
			}
		});

		context.getGameLogic().subscribeStateChanged(new GameStateListener() {
			@Override
			public void changed(GameState state) {
				if(state == GameState.NORMAL) {
					endTurnButton.setVisible(true);
				} else {
					endTurnButton.setVisible(false);
				}
			}
		});

		context.getStage().addActor(endTurnButton);
	}
}