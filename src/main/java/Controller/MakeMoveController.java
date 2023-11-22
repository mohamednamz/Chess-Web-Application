package Controller;

import Chess.*;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class MakeMoveController implements Route {

    PlayerInterface playerInterface;

    PageRenderer pageRenderer;
    Server server;

    public MakeMoveController(PlayerInterface playerInterface, PageRenderer pageRenderer, Server server) {
        this.pageRenderer = pageRenderer;
        this.server = server;
        this.playerInterface = playerInterface;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        int endX = 0;
        int endY = 0;
        int counter = 0;

        String playerName = request.cookie("name");

        Player player = playerInterface.getPlayer(playerName);

        InitialiseGame game = server.getGame(player);

        if (server.getGame(player).getPlayerOne() == null || server.getGame(player).getPlayerTwo() == null) {
            pageRenderer.renderBoard(counter, game, player);
        }

        if (game.getStartX() == 10 && game.getStartY() == 10) {
            game.setStartX(Integer.parseInt(request.queryParams("x")));
            game.setStartY(Integer.parseInt(request.queryParams("y")));
            counter++;
            return pageRenderer.renderBoard(counter, game, player);
        } else {
            endX = Integer.parseInt(request.queryParams("x"));
            endY = Integer.parseInt(request.queryParams("y"));
        }

        if (game.MakeMove(player, game.getStartX(), game.getStartY(), endX, endY)) {
            return pageRenderer.renderNewBoard(game);
        }

        game.setStartX(10);
        game.setStartY(10);

        counter = counter + 2;

        return pageRenderer.renderBoard(counter, game, player);
    }
}
