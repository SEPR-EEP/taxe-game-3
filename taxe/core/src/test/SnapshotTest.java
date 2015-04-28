package test;

import gameLogic.Game;
import gameLogic.GameState;
import gameLogic.Player;
import gameLogic.PlayerManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SnapshotTest extends LibGdxTest {
    private PlayerManager pm;
    private Game game;
    private Player p1;

    @Before
    public void setUpGame() throws Exception {
        game = Game.getInstance();
        game.getPlayerManager();
        pm = game.getPlayerManager();
        p1 = pm.getCurrentPlayer();
    }

    @Test
    public void testPlayerChanged() throws Exception {

        game.setState(GameState.NORMAL);
        assertTrue("Game State is initally set to NORMAL", game.getState() == GameState.NORMAL);

        game.setState(GameState.WAITING);
        assertTrue("Game State is now set to WAITING", game.getState() == GameState.WAITING);

        assertTrue("There should be 2 snapshots of the Game already.", game.getSnapshotsNumber() + 1 == 2);

        game.replaySnapshot(0);
        assertTrue("Game state is now again NORMAL", game.getState() == GameState.NORMAL);

    }

}