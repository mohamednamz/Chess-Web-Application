package Controller;

import Chess.Player;
import Chess.PlayerInterface;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class MainPageController implements Route {

    PlayerInterface playerInterface;

    PageRenderer pageRenderer;

    ListOfRoutes listOfRoutes;

    public MainPageController(PlayerInterface playerInterface,
                              PageRenderer pageRenderer,
                              ListOfRoutes listOfRoutes) {
        this.listOfRoutes = listOfRoutes;
        this.pageRenderer = pageRenderer;
        this.playerInterface = playerInterface;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {
        request.cookie("namz");
        return pageRenderer.render(listOfRoutes.listOfRoutes());
    }
}
