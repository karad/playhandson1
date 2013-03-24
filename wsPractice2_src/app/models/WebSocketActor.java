package models;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import events.*;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
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
    // #3

    // 送信先メンバーの一覧
    // #4

    /**
     * 送信先に追加されるメソッド
     * @param username ユーザー名
     * @param in WebSocketの受信
     * @param out WebSocketの送信
     * @throws Exception
     */
    public static void join(final String username, WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception {

        // #5

    }

    /**
     * イベント発生時に実行するメソッド
     * @param message イベントオブジェクト
     * @throws Exception
     */
    @Override
    public void onReceive(Object message) throws Exception {

        // #6

    }



}
