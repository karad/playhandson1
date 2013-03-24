package models;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import events.*;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Akka;
import play.libs.Json;
import play.mvc.WebSocket;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import static play.libs.F.*;
import static java.util.concurrent.TimeUnit.*;
import static akka.pattern.Patterns.ask;

public class WebSocketActor extends UntypedActor {

    // アクターのインスタンス
    private final static ActorRef ref = Akka.system().actorOf(new Props(WebSocketActor.class));
    // 送信先メンバーの一覧
    Map<String, WebSocket.Out<JsonNode>> members = new HashMap<String, WebSocket.Out<JsonNode>>();

    /**
     * 送信先に追加されるメソッド
     * @param username ユーザー名
     * @param in WebSocketの受信
     * @param out WebSocketの送信
     * @throws Exception
     */
    public static void join(final String username, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception {

        // 初回アクセス時にJOINイベントを発生
        Boolean result = (Boolean) Await.result(ask(ref, new Message(username, "", "", WebSocketEvent.JOIN, out), 1000), Duration.create(1, SECONDS));

        if(result) {
            // WebSocketを通じて入ってきたJSONがあれば、MESSAGEイベントを発生
            in.onMessage(new Callback<JsonNode>() {
                public void invoke(JsonNode event) {
                    ref.tell(new Message(username, event.get("x").asText(), event.get("y").asText(), WebSocketEvent.MESSAGE, null));
                }
            });
            // WebSocketがクローズしたときに、QUITイベントの発生
            in.onClose(new Callback0() {
                public void invoke() {
                    ref.tell(new Message(username, "", "", WebSocketEvent.QUIT, null));
                }
            });
        } else {
            // エラーの送出
            ObjectNode error = Json.newObject();
            error.put("error", result);
            out.write(error);
        }
    }

    /**
     * イベント発生時に実行するメソッド
     * @param message イベントオブジェクト
     * @throws Exception
     */
    @Override
    public void onReceive(Object message) throws Exception {

        // イベントかどうか判定
        Option<Message> event = EventUtil.getEvent(message);
        if(event.isDefined()){
            Message m = event.get();
            switch (m.getEventType()) {
                // 送信先メンバーに追加
                case JOIN:
                    members.put(m.getUsername(), m.getChannel());
                    getSender().tell(true);
                    break;
                // 全員にメッセージを送信
                case MESSAGE:
                    WebSocketMessenger.notifyAll(m.getUsername(), m.getX(), m.getY(), members);
                    break;
                // 送信先メンバーから除外
                case QUIT:
                    members.remove(m.getUsername());
                    break;
                default:
                    unhandled(message);
                    break;
            }
        }

    }

}
