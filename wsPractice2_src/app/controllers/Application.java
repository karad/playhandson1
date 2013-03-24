package controllers;

import models.WebSocketActor;
import org.codehaus.jackson.JsonNode;
import play.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }
    /**
     * ドラッグアプリのWebページ
     * @param username ユーザー名
     * @return Result
     */
    public static Result draggable(String username) {
        // #1
    }

    /**
     * WebSocketのアクセス先
     * @return WebSocketのJSONノード
     */
    public static WebSocket<JsonNode> ws() {
        // #2
    }
}
