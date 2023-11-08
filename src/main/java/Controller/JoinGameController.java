package Controller;

import Chess.InitialiseGame;
import Chess.Player;
import Chess.PlayerInterface;
import Chess.Server;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class JoinGameController implements Route {

    PlayerInterface playerInterface = new PlayerInterface();
    PageRenderer pageRenderer = new PageRenderer();

    Server server = new Server();

    public JoinGameController(PlayerInterface playerInterface, PageRenderer pageRenderer, Server server) {
        this.pageRenderer = pageRenderer;
        this.playerInterface = playerInterface;
        this.server = server;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String playerName = request.cookie("name");

        Player player = playerInterface.getPlayer(playerName);

        InitialiseGame game = server.joinGame(player);

        if (game == null) {
            return "Waiting for opponent";
        }

        return pageRenderer.renderNewBoard();
    }
}
