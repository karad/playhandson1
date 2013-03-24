package events;

/**
 * WebSocket用各種イベントタイプ
 * JOIN      :　送信先一覧に追加
 * MESSAGE   :　メッセージを送信先一覧に送信
 * QUIT      :　送信先一覧から削除
 */
public enum WebSocketEvent {

    JOIN    { String init() { return "JOIN";    } },
    MESSAGE { String init() { return "MESSAGE"; } },
    QUIT    { String init() { return "QUIT";    } };

    abstract String init();

}
