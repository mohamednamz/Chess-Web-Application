package Controller;

import Chess.InitialiseGame;
import Chess.Player;
import Chess.PlayerInterface;
import Chess.Server;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class refreshBoardController implements Route {

    PlayerInterface playerInterface;
    Server server;
    PageRenderer pageRenderer;

    public refreshBoardController(PlayerInterface playerInterface, Server server, PageRenderer pageRenderer) {
        this.playerInterface = playerInterface;
        this.server = server;
        this.pageRenderer = pageRenderer;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        String playerName = request.cookie("name");

        Player player = playerInterface.getPlayer(playerName);

        InitialiseGame game = server.getGame(player);

        return pageRenderer.renderNewBoard(game);

    }
}
