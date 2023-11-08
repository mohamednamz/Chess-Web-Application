package Controller;

import Chess.PlayerInterface;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController implements Route {

    PlayerInterface playerInterface;
    MainPageController mainPageController;


    public LoginController(PlayerInterface playerInterface, MainPageController mainPageController) {
        this.playerInterface = playerInterface;
        this.mainPageController = mainPageController;
    }


    @Override
    public Object handle(Request request, Response response) throws Exception {

        response.cookie("name", request.queryParams("name"));
        return mainPageController.handle(request,response);
    }
}
