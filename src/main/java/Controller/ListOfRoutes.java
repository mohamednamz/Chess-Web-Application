package Controller;

import java.util.ArrayList;
import java.util.List;

public class ListOfRoutes {

    List<Routes> routes;

    public List<Routes> listOfRoutes() {

        routes = new ArrayList<>();

        routes.add(new Routes("/Login/JoinGame", "join game"));
        routes.add(new Routes("/Lobby", "Lobby"));


        return routes;
    }

    public String getRoute(int i) {
        return routes.get(i).getRoute();
    }

    public String getRouteName(int i) {
        return routes.get(i).getRouteName();
    }


}
