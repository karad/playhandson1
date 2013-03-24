package events;

import org.codehaus.jackson.JsonNode;
import play.mvc.WebSocket;

import java.io.Serializable;

public class Message implements Event, Serializable {

    // ユーザ名
    final String username;
    // ドラッグのX座標
    final String x;
    // ドラッグのY座標
    final String y;
    // イベントの種類
    final WebSocketEvent event;
    // ユーザが持っているチャンネル
    final WebSocket.Out<JsonNode> channel;

    public Message(String username, String x, String y, WebSocketEvent e, WebSocket.Out<JsonNode> o) {
        this.username = username;
        this.x = x;
        this.y = y;
        this.event = e;
        this.channel = o;
    }

    @Override
    public WebSocketEvent getEventType() {
        return event;
    }

    public String getUsername() {
        return username;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public WebSocket.Out<JsonNode> getChannel() {
        return channel;
    }
}