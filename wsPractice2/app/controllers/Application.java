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
        session("username", username);
        return ok(draggable.render("ドラッグアプリ", username));
    }

    /**
     * WebSocketのアクセス先
     * @return WebSocketのJSONノード
     */
    public static WebSocket<JsonNode> ws() {
        final String username = session("username");
        return new WebSocket<JsonNode>() {
            public void onReady(final WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out) {
                try {
                    WebSocketActor.join(username, in, out);
                } catch (Exception e) {
                    Logger.error("Can't connect WebSocket");
                    e.printStackTrace();
                }
            }
        };
    }
}
