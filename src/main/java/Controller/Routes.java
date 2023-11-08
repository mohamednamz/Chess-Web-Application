package Controller;

import java.util.List;

public class Routes {

    String route;
    String routeName;

    List<Routes> listOfRoutes;

    public Routes(String route, String routeName) {
        this.route = route;
        this.routeName = routeName;
    }

    public String getRoute() {
        return route;
    }

    public String getRouteName() {
        return routeName;
    }

    public List<Routes> getListOfRoutes() {
        return listOfRoutes;
    }




}
