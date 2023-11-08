package Controller;

import Chess.Board;
import html.PageRenderer;
import spark.Request;
import spark.Response;
import spark.Route;

public class print implements Route {

    PageRenderer pageRenderer;

    public print(PageRenderer pageRenderer) {
        this.pageRenderer = pageRenderer;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        Board board = new Board();

        return pageRenderer.renderNewBoard();
    }
}
