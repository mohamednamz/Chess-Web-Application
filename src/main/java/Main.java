import Chess.*;
import Controller.*;
import html.PageRenderer;

import static spark.Spark.*;

public class Main {

    public static void setUpServer() {
        //staticFiles.externalLocation("C:\\Users\\User\\Documents\\Coding Umar\\Teaching-main\\Chess-Web-Application\\src\\Resources");
        staticFiles.location("/public"); // Static files
        port(80);

        exception(Exception.class, (exception, request, response) -> {
            String message = "<div>" + exception.toString() + "</div>";

            StackTraceElement[] trace = exception.getStackTrace();

            for (StackTraceElement stackTrace : trace) {
                message += "<div>" + stackTrace.toString() + "</div>";
            }
            response.body(message);
        });


    }


    public static void main(String[] args) {
        setUpServer();

        Server server = new Server();

        Player player1 = new Player();
        player1.name = "namz";
        player1.userId = 1;

        Player player2 = new Player();
        player2.name = "umar";
        player2.userId = 2;

        PlayerInterface playerInterface = new PlayerInterface();

        playerInterface.addPlayer("namz");
        playerInterface.addPlayer("umar");

        PageRenderer pageRenderer = new PageRenderer();
        LobbyController lobbyController = new LobbyController(playerInterface, pageRenderer);
        MainPageController mainPageController = new MainPageController(playerInterface, pageRenderer, new ListOfRoutes());
        MakeMoveController makeMoveController = new MakeMoveController(playerInterface,pageRenderer,server);

        get("/Lobby", lobbyController);

        get("/print", new print(pageRenderer));
        get("/Login", new LoginController(playerInterface, mainPageController));
        get("/Login/JoinGame", new JoinGameController(playerInterface, pageRenderer, server));
        get("/makeMove", makeMoveController);
        get("/render", new refreshBoardController(playerInterface,server,pageRenderer));
        get("/Login/leaveGame", new LeaveGameController(playerInterface,pageRenderer,server));
    }
}
