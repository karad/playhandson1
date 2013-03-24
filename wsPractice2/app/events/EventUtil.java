package events;

import static play.libs.F.*;

/**
 * メッセージのオブジェクト判定ユーティリティ
 */
public class EventUtil {

    public static Option<Message> getEvent(Object object){
        if(object instanceof Message){
            return Some((Message) object);
        }
        return new None<Message>();
    }

}
