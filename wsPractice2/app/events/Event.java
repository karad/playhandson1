package events;

public interface Event {
    // イベントタイプ取得
    public WebSocketEvent getEventType();
}