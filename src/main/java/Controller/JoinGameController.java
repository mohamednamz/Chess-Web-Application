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

        int counter = 0;

        String playerName = request.cookie("name");

        Player player = playerInterface.getPlayer(playerName);

        if (player.isInGame) {
            InitialiseGame game = server.getGame(player);
            return pageRenderer.renderNewBoard(game); //TODO i need to put a isGameOver in the game.

        }


        InitialiseGame game = server.joinGame(player);

        if (game == null) {
            return "Waiting for opponent";
        }

        return pageRenderer.renderNewBoard(game);
    }
}
