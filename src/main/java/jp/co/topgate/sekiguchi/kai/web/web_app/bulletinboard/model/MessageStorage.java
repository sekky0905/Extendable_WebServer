package jp.co.topgate.sekiguchi.kai.web.web_app.bulletinboard.model;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Messageインスタンスを格納するためのクラス
 * Created by sekiguchikai on 2016/11/28.
 */
public class MessageStorage {
    private static int currentId = 0;

    /**
     * Messageを格納するためのリスト
     */
    private static List<Message> messageList = new ArrayList<>();

    /**
     * Messageを受け取りmessageListに格納するためのメソッド
     *
     * @param message messageListに格納するモデル
     */
    public static void setMessageList(Message message) {
        message.setId(currentId);
        MessageStorage.messageList.add(message);
        currentId++;
    }

    /**
     * インデックスで指定されたmessageListに格納されたモデルを返すメソッド
     *
     * @param index リストのインデックス
     * @return インデックスで指定されたモデル
     */
    public static Message getMessageList(int index) {
        return MessageStorage.messageList.get(index);
    }


    /**
     * インデックスで指定されたモデルを削除するメソッド
     *
     * @param id 削除対象のメッセージのID
     */
    public static void removeMessage(int id) {
        messageList = messageList.stream().filter((Message message) -> message.getId() != id).collect(Collectors.toList());
    }

    public static Stream<Message> getAllMessage() {
        return messageList.stream();
    }

    /**
     * 指定されたユーザーネームのモデルのみ格納するListを生成
     *
     * @param name ユーザーネーム
     */
    public static Stream<Message> searchMessage(String name) {
        return messageList.stream().filter((Message message) -> message.getName().equals(name));
    }

    /**
     * messageListに保持している全てのインスタンスを削除するメソッド
     */
    static void removeAllMessage() {
        currentId = 1;
        MessageStorage.messageList.clear();
    }
}


