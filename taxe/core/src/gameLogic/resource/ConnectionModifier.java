package gameLogic.resource;

import gameLogic.Player;

/**
 * Created by richard on 05/04/15.
 */
public class ConnectionModifier extends Resource {

    public ConnectionModifier(String name, Player player){
        this.name = name;
        this.setPlayer(player);
    }

    @Override
    public void dispose() {
    }
}
