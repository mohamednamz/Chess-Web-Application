package Controller;

import Chess.InitialiseGame;
import Chess.Player;
import Chess.PlayerInterface;
import Chess.Server;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class LeaveGameController implements Route {

    PlayerInterface playerInterface;

    PageRenderer pageRenderer;

    Server server;

    public LeaveGameController(PlayerInterface playerInterface, PageRenderer pageRenderer, Server server) {
        this.server = server;
        this.pageRenderer = pageRenderer;
        this.playerInterface = playerInterface;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        String playerName = request.cookie("name");

        Player player = playerInterface.getPlayer(playerName);

        InitialiseGame game = server.getGame(player);

        if (game == null) {
            return pageRenderer.renderGameOver(player);
        }

        player = server.leaveGame(player);

        return pageRenderer.renderPlayerLeftBoard(player);

    }
}
