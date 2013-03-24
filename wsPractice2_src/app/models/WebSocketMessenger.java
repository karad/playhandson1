package models;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;
import play.mvc.WebSocket;

import java.util.Map;

public class WebSocketMessenger {

    /**
     * 送信先全員に送信
     * @param username 送信主
     * @param x x座標
     * @param y y座標
     * @param members 送信先一覧
     */
    public static void notifyAll(String username, String x, String y, Map<String, WebSocket.Out<JsonNode>> members) {
        
        // #7
        
    }
}
