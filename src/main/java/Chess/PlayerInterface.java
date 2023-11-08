package Chess;

import java.util.ArrayList;
import java.util.List;

public class PlayerInterface {

    public List<Player> playerList = new ArrayList<>();

    public void addPlayer(String playerName) {
        Player player = new Player();
        player.name = playerName;
        playerList.add(player);
        player.userId = playerList.size();
    }

    public Player getPlayer(String playerName) {
        for (Player player: playerList) {
            if(player.name.equals(playerName)) {
                return player;
            }
        }
        return null;
    }




}
